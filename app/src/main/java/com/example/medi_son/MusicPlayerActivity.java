package com.example.medi_son;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.widget.TextView;

import java.util.Timer;
import java.util.TimerTask;

public class MusicPlayerActivity extends AppCompatActivity {


    private String song ="";
    private MediaPlayer mp = new MediaPlayer();
    private TextView tv;
    private  Timer timer;


    @Override
    public void onBackPressed ()
    {
        if (mp != null)
            mp.stop();
        super.onBackPressed();
    }

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



    @Override
    protected void onStart() {
        super.onStart();

        tv = findViewById(R.id.timer_text);

        Intent intent = getIntent();

        if (intent != null){
            String timer_selected = intent.getStringExtra("timer_selected");
            String name_music = intent.getStringExtra("name_music");
        }


        //Uri myUri = Uri.parse("R.raw.amazozn_animal");



        CountDownTimer cntr_aCounter = new CountDownTimer(3000, 1000) {
            public void onTick(long millisUntilFinished) {
                mp = MediaPlayer.create(MusicPlayerActivity.this, R.raw.forest_bird);
                mp.start();
            }

            public void onFinish() {
                //code fire after finish
                mp.stop();
            }
        };


        if (mp.isPlaying()) {
            tv.setText("Playing : music.mp3....");
            mp.pause();
        } else {
            tv.setText("pause : music.mp3....");
            mp.start();
            tv.post(mUpdateTime);
        }


    }

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


    private void playerSound() {
        stopPlaying();
        mp = MediaPlayer.create(MusicPlayerActivity.this, R.raw.forest_bird);
        //mp.start();
    }



    private void stopPlaying() {
        if (mp != null) {
            mp.stop();
            mp.reset();
            mp.release();
            mp = null;
        }
    }







    private Runnable mUpdateTime = new Runnable() {
        public void run() {
            int currentDuration;
            if (mp.isPlaying()) {
                currentDuration = mp.getCurrentPosition();
                updatePlayer(currentDuration);
                tv.postDelayed(this, 1000);
            }else {
                tv.removeCallbacks(this);
            }
        }
    };


    private void updatePlayer(int currentDuration){
        tv.setText("" + milliSecondsToTimer((long) currentDuration));
    }

    /**
     * Function to convert milliseconds time to Timer Format
     * Hours:Minutes:Seconds
     * */
    public  String milliSecondsToTimer(long milliseconds) {
        String finalTimerString = "";
        String secondsString = "";

        // Convert total duration into time
        int hours = (int) (milliseconds / (1000 * 60 * 60));
        int minutes = (int) (milliseconds % (1000 * 60 * 60)) / (1000 * 60);
        int seconds = (int) ((milliseconds % (1000 * 60 * 60)) % (1000 * 60) / 1000);
        // Add hours if there
        if (hours > 0) {
            finalTimerString = hours + ":";
        }

        // Prepending 0 to seconds if it is one digit
        if (seconds < 10) {
            secondsString = "0" + seconds;
        } else {
            secondsString = "" + seconds;
        }

        finalTimerString = finalTimerString + minutes + ":" + secondsString;

        // return timer string
        return finalTimerString;
    }







}
