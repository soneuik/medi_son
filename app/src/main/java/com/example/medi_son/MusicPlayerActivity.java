package com.example.medi_son;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;

public class MusicPlayerActivity extends AppCompatActivity {


    private String song ="";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_music_player);


        Intent intent = getIntent();


        if (intent != null){
            String timer_selected = intent.getStringExtra("timer_selected");
            String name_music = intent.getStringExtra("name_music");
        }



        Uri myUri = Uri.parse("R.raw.amazozn_animal");
        playSoundForXSeconds(myUri, 100);
    }



    private void playSoundForXSeconds(final Uri soundUri, int seconds) {
        if(soundUri!=null) {
            final MediaPlayer mp = new MediaPlayer();
            try {
                mp.setDataSource(MusicPlayerActivity.this, soundUri);
                mp.prepare();
                mp.start();
            }catch(Exception e) {
                e.printStackTrace();
            }

            Handler mHandler = new Handler();
            mHandler.postDelayed(new Runnable() {
                public void run() {
                    try {
                        mp.stop();
                    }catch(Exception e) {
                        e.printStackTrace();
                    }
                }
            }, seconds * 1000);
        }
    }




}
