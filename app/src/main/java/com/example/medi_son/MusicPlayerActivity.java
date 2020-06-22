package com.example.medi_son;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.Timer;
import java.util.TimerTask;

public class MusicPlayerActivity extends AppCompatActivity {


    private String song ="";
    private MediaPlayer mp = new MediaPlayer();
    private TextView tv;
    private  Timer timer;
    CountDownTimer CountDownTimer;
    private ImageButton play_btn ;




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
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_music_player);



        play_btn = findViewById(R.id.play_btn);
        play_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mp.stop();

                tv = findViewById(R.id.timer_text);


                Intent intent = getIntent();
                String timer_selected= "";
                String name_music ="";

                if (intent != null){
                    timer_selected = intent.getStringExtra("timer_selected");
                    name_music = intent.getStringExtra("name_music");
                }

                int time = 0;

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
                    default:
                        time= 300;
                        break;
                }



                if(mp != null) {
                    playMp3(name_music);
                }else{
                    System.out.println("mp object null error");
                }



                if(CountDownTimer!=null){
                    CountDownTimer.cancel();
                }

                reverseTimer(time, tv);


            }
        });

    }

    private void playMp3(String nameOfFile){
          mp = MediaPlayer.create(this, getResources().getIdentifier(nameOfFile, "raw", getPackageName()));
          mp.start();
    }


    public void reverseTimer(int Seconds,final TextView tv){

        CountDownTimer = new CountDownTimer(Seconds* 1000+1000, 1000) {

            public void onTick(long millisUntilFinished) {
                int seconds = (int) (millisUntilFinished / 1000);
                int minutes = seconds / 60;
                seconds = seconds % 60;
                tv.setText("TIME : " + String.format("%02d", minutes)
                        + ":" + String.format("%02d", seconds));
            }

            public void onFinish() {
                tv.setText("Completed");
            }
        }.start();
    }


    //////////////////////////////NOT USED but useful///////////////////////////////////////////////////////////




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
