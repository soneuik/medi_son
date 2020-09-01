package com.example.medi_son;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.view.View;
import android.webkit.WebView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;

public class MusicPlayerActivity extends AppCompatActivity {



    private MediaPlayer mp = new MediaPlayer();
    private  Timer timer;
    CountDownTimer CountDownTimer;
    private TextView tv;
    private ImageButton play_btn ;
    private ImageView gif_img;

    private String timer_selected= "";
    private String name_music ="";
    private int time = 0;
    private String song ="";
    private  WebView webView;
    private StorageReference storageRef = FirebaseStorage.getInstance().getReference();
    private StorageReference dateRef;

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

        storageRef = FirebaseStorage.getInstance().getReference();



        Intent intent = getIntent();
        if (intent != null){
            timer_selected = intent.getStringExtra("timer_selected");
            name_music = intent.getStringExtra("name_music");
        }


        webView = (WebView) findViewById(R.id.gif_img);
        webView.setVisibility(View.GONE);
        play_btn = findViewById(R.id.play_btn);
        play_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mp.stop();

                tv = findViewById(R.id.timer_text);

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

                //A function to set up the timer
                reverseTimer(time, tv);
                //Hide the play button
                play_btn.setVisibility(View.GONE);



                //GIF 실행
                String filePath = "https://media3.giphy.com/media/Ddab9zJPtaEmI/200.webp?cid=ecf05e47a66faf749d8ed5928cb24190cc9d35d75d3599d1&rid=200.webp";
                webView.setVisibility(View.VISIBLE);
                //webView.loadDataWithBaseURL("file:///android_asset/",data,"text/html","UTF-8",null);
                webView.loadData("<html><head><style type='text/css'>body{margin:auto auto;text-align:center;} img{width:100%25; height:100%;} </style></head><body><img src="+filePath+"/></body></html>" ,"text/html",  "UTF-8");
            }
        });

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










}
