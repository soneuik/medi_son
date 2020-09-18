package com.soneuik.medi_son;

import androidx.appcompat.app.AppCompatActivity;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.media.MediaPlayer;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;
import android.webkit.WebView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.LoadAdError;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.soneuik.medi_son.Services.OnClearFromRecentService;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.concurrent.ThreadLocalRandom;

public class MusicPlayerActivity extends AppCompatActivity implements Playable {

    //
    NotificationManager notificationManager;

    List<Track> tracks;
    int position = 0;
    boolean isPlaying = true;
    TextView title;


    //Player
    private MediaPlayer mp = new MediaPlayer();
    private  Timer timer;
    CountDownTimer CountDownTimer;
    private TextView tv;
    private ImageButton play_btn, pause_btn ;
    private String timer_selected= "";
    private String name_music ="";
    private int time = 0;
    private String current_song ="";
    private String current_timer ="";
    //FIREBASE
    private StorageReference storageRef = FirebaseStorage.getInstance().getReference();
    private StorageReference dateRef;

    //GIF
    private String[] selected_arr ;
    private  WebView webView;
    private ImageView gif_img;
    private int gif_position ;
    private  int  length=0;
    private int play_counter =0;

    Intent playbackServiceIntent;

    private AdView mAdView;
    //fire
    private String [] gif_arr_bonfire= {
            "https://media.giphy.com/media/Lf5DUUXwvinHq/giphy.gif",
            "https://media.giphy.com/media/Arxo26T2VGbW8/giphy.gif"
    };
    //rain
    private String [] gif_arr_rain= {
            "https://media.giphy.com/media/vSoXkKBccEQRq/giphy-downsized.gif",
            "https://media.giphy.com/media/vgvWTjGaBqR7W/giphy-downsized.gif",
            "https://media.giphy.com/media/t7Qb8655Z1VfBGr5XB/giphy-downsized.gif",
            "https://media.giphy.com/media/dI3D3BWfDub0Q/giphy-downsized.gif",
            "https://media.giphy.com/media/26DMWExfbZSiV0Btm/giphy-downsized.gif",
            "https://media.giphy.com/media/s9cu1TZU37KY8/giphy-downsized.gif",
            "https://media.giphy.com/media/PN23U6cVRWFFe/giphy-downsized.gif",
            "https://media.giphy.com/media/c0Xv8u8E5HBio/giphy-downsized.gif",
            "https://media.giphy.com/media/JFkqrke8kBZTy/giphy-downsized.gif"
    };
    //wave
    private String [] gif_arr_wave= {
            "https://media.giphy.com/media/ZTAojHK9IHsSQ/giphy-downsized.gif",
            "https://media.giphy.com/media/7CPPm2qysqX7O/giphy-downsized.gif",
            "https://media.giphy.com/media/HGvjR72DXRHWw/giphy-downsized.gif",
            "https://media.giphy.com/media/l2JI8AgTO04zOi62Y/giphy-downsized.gif",
            "https://media.giphy.com/media/128ydcHOsrk5GM/giphy-downsized.gif"
    };
    //thunder
    private String [] gif_arr_thunder= {
            "https://media.giphy.com/media/xaZCqV4weJwHu/giphy-downsized.gif",
            "https://media.giphy.com/media/HhhajSmRfikXC/giphy-downsized.gif",
            "https://media.giphy.com/media/iN6lLmUb8exMI/giphy-downsized.gif"
    };
    //etc
    private String [] gif_arr_etc= {
            "https://media.giphy.com/media/2R1QSI7WEUF4A/giphy-downsized.gif",
            "https://media.giphy.com/media/eZq90yISfPjBC/giphy-downsized.gif",
            "https://media.giphy.com/media/7LsYKNn60KhUs/giphy-downsized.gif",
            "https://media.giphy.com/media/RsjVSZo2y3qKI/giphy-downsized.gif",
            "https://media.giphy.com/media/7NUdxH6uzeI1i/giphy-downsized.gif",
            "https://media.giphy.com/media/zx9NCxVYq6WuA/giphy-downsized.gif",
            "https://media.giphy.com/media/tX99OOO4FA9Pi/giphy-downsized.gif"
    };


    @Override
    public void onBackPressed ()
    {
        if (mp != null)
            mp.stop();
        super.onBackPressed();
        notificationManager.cancelAll();;
    }

/*
    @Override
    public void onPause ()
    {
        if (mp != null)
        {
            mp.pause();
            mp.stop();
        }
        super.onPause();
    }

*/

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_music_player);
        //인터넷 체크
        if(!isOnline()){

        }



        //광고 체크
        MobileAds.initialize(this, new OnInitializationCompleteListener() {
            @Override
            public void onInitializationComplete(InitializationStatus initializationStatus) {
            }
        });
        mAdView = findViewById(R.id.ad_banner);
        adMob_banner();


        // Creating Notification Player
        popluateTracks();
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            createChannel();

            registerReceiver(broadcastReceiver, new IntentFilter("TRACKS_TRACKS"));
            startService(new Intent(getBaseContext(), OnClearFromRecentService.class));

        }


        //Play song 체크
        Intent intent = getIntent();
        if (intent != null){
            name_music = intent.getStringExtra("name_music");
            current_song = name_music;
            timer_selected = intent.getStringExtra("timer_selected");
            timer_selector(timer_selected);


            if(gif_selector("bonfire")){
                selected_arr = gif_arr_bonfire;
            }else if(gif_selector("wave")){
                selected_arr = gif_arr_wave;
            }else if(gif_selector("rain")){
                selected_arr = gif_arr_rain;
            }else if(gif_selector("thunder")){
                selected_arr = gif_arr_thunder;
            }else{
                selected_arr = gif_arr_etc;
            }
           gif_position = ThreadLocalRandom.current().nextInt(0, selected_arr.length );
        }

        webView = (WebView) findViewById(R.id.gif_img);
        webView.setVisibility(View.GONE);
        play_btn = findViewById(R.id.play_btn);
        play_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.println("play_btn is clicked");
                System.out.println("isPlaying: "+isPlaying);


                tv = findViewById(R.id.timer_text);

                    System.out.println("stop is clicked");
                    mp.stop();



                StopAndResumeMp3();

                CreateNotification.createNotification(MusicPlayerActivity.this, tracks.get(0), R.drawable.ic_baseline_pause_24,
                        0, tracks.size()-1);

                if(CountDownTimer!=null){
                    CountDownTimer.cancel();
                }
                reverseTimer(time, tv);
                //Hide the play button
                //play_btn.setVisibility(View.GONE);
                //GIF 실행
                String filePath = "";
                filePath = selected_arr[gif_position];
                webView.setVisibility(View.VISIBLE);
                webView.getSettings().setJavaScriptEnabled(true);
                //webView.loadDataWithBaseURL("file:///android_asset/",data,"text/html","UTF-8",null);
                webView.loadData("<html><head><style type='text/css'>body{margin:auto auto;text-align:center;} img{width:100%25; height:100%;} </style></head><body><img src='"+filePath+"'/></body></html>" ,"text/html",  "UTF-8");
            }
        });

        //페이지이동시 자동클릭 버튼
        play_btn.performClick();
    }

    @Override
    public void onDestroy(){
        super.onDestroy();
        mp.pause();
    }


    private void StopAndResumeMp3(){
        play_btn.setVisibility(View.GONE);
        if (!isPlaying){
            mp.pause();
            CountDownTimer.cancel();
            onTrackPause();
            length=mp.getCurrentPosition();
            play_counter++;
            return;
        } else {
            onTrackPlay();
            if(play_counter == 0){
                System.out.print("1st mp3");
                playMp3(name_music);
            }else{
                System.out.print("resume mp3");
                mp.pause();
                mp.start();
                reverseTimer(time, tv);
            }

        }
    }
    private void playMp3 (String nameOfFile){
        // Points to the root reference
        int check = nameOfFile.indexOf("sleep");


        dateRef = storageRef.child("/" + nameOfFile+ ".mp3");
        dateRef.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>()
        {
            @Override
            public void onSuccess(Uri downloadUrl)
            {
                try{
                    System.out.println(downloadUrl.toString());
                    //System.out.println("downloadUrl: "+downloadUrl);
                    mp.setDataSource(downloadUrl.toString());
                    mp.setOnPreparedListener(new MediaPlayer.OnPreparedListener(){
                        @Override
                        public void onPrepared(MediaPlayer mp){
                            mp.setLooping(true);
                            mp.start();
                        }
                    });
                    mp.prepare();
                }catch (IOException e){
                    e.printStackTrace();
                }
            }
        });
    }


    private void stopPlaying() {
        if (mp != null) {
            mp.stop();
            mp.reset();
            mp.release();
            mp = null;
        }
    }


    private void timer_selector(String timer_selected){

        switch(timer_selected) {
            case "5min":
                time= 300;
                break;
            case "10min":
                time= 600;
                break;
            case "20min":
                time= 1200;
                break;

            case "30min":
                time= 1800;
                break;
            case "40min":
                time= 2400;
                break;
            case "50min":
                time= 3000;
                break;
            case "60min":
                time= 3600;
                break;
            case "120min":
                time= 7200;
                break;
            default:
                time= 300;
                break;
        }
    }



    public void reverseTimer(int Seconds,final TextView tv){

        CountDownTimer = new CountDownTimer(Seconds* 1000+1000, 1000) {

            public void onTick(long millisUntilFinished) {
                int seconds = (int) (millisUntilFinished / 1000);
                int minutes = seconds / 60;
                seconds = seconds % 60;
                tv.setText(String.format("%02d", minutes)
                        + ":" + String.format("%02d", seconds));
            }

            public void onFinish() {
                stopPlaying();
                tv.setText("Completed");
            }
        }.start();
    }



    private boolean gif_selector(String song_kind){
        boolean result = name_music.contains(song_kind);
        return result;
    }


    //인터넷 연결 확인
    public boolean isOnline() {
        ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = cm.getActiveNetworkInfo();
        if (netInfo != null && netInfo.isConnectedOrConnecting()) {
            return true;
        } else {
            return false;
        }
    }


    /**
     *
     * Notification Player functions
     *
     *
     */
    private void popluateTracks(){
        tracks = new ArrayList<>();
        tracks.add(new Track("Peaceful Mind", "Feel Relax", R.drawable.s2));
        tracks.add(new Track("Peaceful Mind", "Release your stress", R.drawable.s2));
        tracks.add(new Track("Peaceful Mind", "Feel Realx", R.drawable.s2));


    }

    private void createChannel(){
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            NotificationChannel channel = new NotificationChannel(CreateNotification.CHANNEL_ID,"KOD Dev", NotificationManager.IMPORTANCE_LOW);

            notificationManager = getSystemService(NotificationManager.class);
            if(notificationManager != null){
                notificationManager.createNotificationChannel(channel);
            }
        }
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
                    StopAndResumeMp3();
                    break;
                case CreateNotification.ACTION_NEXT:
                    onTrackNext();
                    break;
                case CreateNotification.ACTION_TIMER:
                    mp.pause();
                    System.out.println("timer clicked");
                    onTrackTimer();
                    break;
            }
        }
    };

    @Override
    public void onTrackPrevious() {
        position--;
        CreateNotification.createNotification(MusicPlayerActivity.this, tracks.get(position),
                R.drawable.ic_baseline_pause_24, position, tracks.size()-1);
        //title.setText(tracks.get(position).getTimer());
    }

    @Override
    public void onTrackPlay() {
        CreateNotification.createNotification(MusicPlayerActivity.this, tracks.get(position),
                R.drawable.ic_baseline_pause_24, position, tracks.size()-1);
        play_btn.setImageResource(R.drawable.ic_baseline_pause_24);
        // title.setText(tracks.get(position).getTimer());
        isPlaying = false;

    }

    @Override
    public void onTrackPause() {
        CreateNotification.createNotification(MusicPlayerActivity.this, tracks.get(position),
                R.drawable.ic_baseline_play_arrow_24, position, tracks.size()-1);
        play_btn.setImageResource(R.drawable.ic_baseline_play_arrow_24);
        // title.setText(tracks.get(position).getTimer());
        isPlaying = true;

    }

    @Override
    public void onTrackNext() {
        position++;
        CreateNotification.createNotification(MusicPlayerActivity.this, tracks.get(position),
                R.drawable.ic_baseline_pause_24, position, tracks.size()-1);
        //title.setText(tracks.get(position).getTimer());
    }

    @Override
    public void onTrackTimer() {
        PlayerModalActivity pmm;
        String song = name_music;
        mp.pause();
       /* pmm = new PlayerModalActivity(MusicPlayerActivity.this, song);
        pmm.show();*/

    }
    public static Intent newLauncherIntent(final Context context) {
        final Intent intent = new Intent(context, PlayerModalActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.setAction(Intent.ACTION_MAIN);
        intent.addCategory(Intent.CATEGORY_LAUNCHER);
        return intent;
    }





    /**
     *
     * Notification Player functions
     *
     *
     */

  /*  @Override
    protected void onStop() {
        super.onStop();
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            notificationManager.cancelAll();;

        }

        //unregisterReceiver(broadcastReceiver);
    }*/














    private void adMob_banner(){


        MobileAds.initialize(this, new OnInitializationCompleteListener() {
            @Override
            public void onInitializationComplete(InitializationStatus initializationStatus) {
            }
        });


        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);


        mAdView.setAdListener(new AdListener() {
            @Override
            public void onAdLoaded() {
                // Code to be executed when an ad finishes loading.
            }

            @Override
            public void onAdFailedToLoad(LoadAdError adError) {
                // Code to be executed when an ad request fails.
                Log.d("ADMOB_ERROR_CODE", "admob error code: " + adError);
            }

            @Override
            public void onAdOpened() {
                // Code to be executed when an ad opens an overlay that
                // covers the screen.
            }

            @Override
            public void onAdClicked() {
                // Code to be executed when the user clicks on an ad.
            }

            @Override
            public void onAdLeftApplication() {
                // Code to be executed when the user has left the app.
            }

            @Override
            public void onAdClosed() {
                // Code to be executed when the user is about to return
                // to the app after tapping on an ad.
            }
        });

    }



}
