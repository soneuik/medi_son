package com.soneuik.medi_son;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.ActivityNotFoundException;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.LoadAdError;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.soneuik.medi_son.Services.OnClearFromRecentService;

import java.util.ArrayList;
import java.util.List;

public class test  extends AppCompatActivity implements Playable {

    ImageButton play;
    TextView title;

    NotificationManager notificationManager;

    List<Track> tracks;

    int position = 0;
    boolean isPlaying = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_player_notify);


        title = findViewById(R.id.title);

        popluateTracks();

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            createChannel();
            registerReceiver(broadcastReceiver, new IntentFilter("TRACKS_TRACKS"));
            startService(new Intent(getBaseContext(), OnClearFromRecentService.class));
        }

        play = findViewById(R.id.play2);
        play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isPlaying){
                    onTrackPause();
                } else {
                    onTrackPlay();
                }
            }
        });
    }

    private void createChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            NotificationChannel channel = new NotificationChannel(CreateNotification.CHANNEL_ID,
                    "KOD Dev", NotificationManager.IMPORTANCE_LOW);

            notificationManager = getSystemService(NotificationManager.class);
            if (notificationManager != null){
                notificationManager.createNotificationChannel(channel);
            }
        }
    }

    //populate list with tracks
    private void popluateTracks(){
        tracks = new ArrayList<>();

        tracks.add(new Track("Track 1", "Artist 1", R.drawable.c1));
        tracks.add(new Track("Track 2", "Artist 2", R.drawable.c2));
        tracks.add(new Track("Track 3", "Artist 3", R.drawable.c3));
        tracks.add(new Track("Track 4", "Artist 4", R.drawable.c4));
    }

    BroadcastReceiver broadcastReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            String action = intent.getExtras().getString("actionname");

            switch (action){
                case CreateNotification.ACTION_PREVIOUS:
                    onTrackPrevious();
                    break;
                case CreateNotification.ACTION_PLAY:
                    if (isPlaying){
                        onTrackPause();
                    } else {
                        onTrackPlay();
                    }
                    break;
                case CreateNotification.ACTION_NEXT:
                    onTrackNext();
                    break;
            }
        }
    };

    @Override
    public void onTrackPrevious() {

        position--;
        CreateNotification.createNotification(test.this, tracks.get(position),
                R.drawable.ic_baseline_pause_24, position, tracks.size()-1);
        title.setText(tracks.get(position).getTitle());

    }

    @Override
    public void onTrackPlay() {

        CreateNotification.createNotification(test.this, tracks.get(position),
                R.drawable.ic_baseline_pause_24, position, tracks.size()-1);
        play.setImageResource(R.drawable.ic_baseline_pause_24);
        title.setText(tracks.get(position).getTitle());
        isPlaying = true;

    }

    @Override
    public void onTrackPause() {

        CreateNotification.createNotification(test.this, tracks.get(position),
                R.drawable.ic_baseline_play_arrow_24, position, tracks.size()-1);
        play.setImageResource(R.drawable.ic_baseline_play_arrow_24);
        title.setText(tracks.get(position).getTitle());
        isPlaying = false;

    }

    @Override
    public void onTrackNext() {

        position++;
        CreateNotification.createNotification(test.this, tracks.get(position),
                R.drawable.ic_baseline_pause_24, position, tracks.size()-1);
        title.setText(tracks.get(position).getTitle());

    }
    @Override
    public void onTrackTimer() {

        position++;
        CreateNotification.createNotification(test.this, tracks.get(position),
                R.drawable.ic_baseline_pause_24, position, tracks.size()-1);
        title.setText(tracks.get(position).getTitle());

    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            notificationManager.cancelAll();
        }

        unregisterReceiver(broadcastReceiver);
    }
}