package com.example.medi_son;

import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class MainActivity extends AppCompatActivity {

    private MediaPlayer mp;
    ImageButton row1_1, row1_2, row1_3, row1_4, row1_5;
    ImageButton row2_1, row2_2, row2_3, row2_4, row2_5;


    //Classic Img_btn
    ImageButton c1_btn, c2_btn,c3_btn,c4_btn,c5_btn,c6_btn,c7_btn,c8_btn;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        //Nature sound buttons
            //row1
            row1_1 = (ImageButton)findViewById(R.id.row1_1);
            row1_2 = (ImageButton)findViewById(R.id.row1_2);
            row1_3 = (ImageButton)findViewById(R.id.row1_3);
            row1_4 = (ImageButton)findViewById(R.id.row1_4);
            row1_5 = (ImageButton)findViewById(R.id.row1_5);
            //row2
            row2_1 = (ImageButton)findViewById(R.id.row2_1);
            row2_2 = (ImageButton)findViewById(R.id.row2_2);
            row2_3 = (ImageButton)findViewById(R.id.row2_3);
            row2_4 = (ImageButton)findViewById(R.id.row2_4);
            row2_5 = (ImageButton)findViewById(R.id.row2_5);



        row1_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                stopPlaying();
                mp = MediaPlayer.create(MainActivity.this, R.raw.c1);
                mp.start();
            }
        });

        row1_2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String song = "amazon_bird";
                PlayerModalActivity pmm =new PlayerModalActivity(MainActivity.this, song);
                pmm.show();

            }
        });


        row2_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                stopPlaying();
                mp = MediaPlayer.create(MainActivity.this, R.raw.amazon_animal);
                mp.start();
            }
        });

        row2_2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                stopPlaying();
                mp = MediaPlayer.create(MainActivity.this, R.raw.amazon_bird);
                mp.start();
            }
        });

        row2_3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                stopPlaying();
                mp = MediaPlayer.create(MainActivity.this, R.raw.forest_bird);
                mp.start();
            }
        });

        row2_4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                stopPlaying();
                mp = MediaPlayer.create(MainActivity.this, R.raw.frog);
                mp.start();
            }
        });

        row2_5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                stopPlaying();
                mp = MediaPlayer.create(MainActivity.this, R.raw.sealion);
                mp.start();
            }
        });




        //classic sound buttons
        c1_btn = (ImageButton)findViewById(R.id.c1_btn);
        c2_btn = (ImageButton)findViewById(R.id.c2_btn);
        c3_btn = (ImageButton)findViewById(R.id.c3_btn);
        c4_btn = (ImageButton)findViewById(R.id.c4_btn);
        c5_btn = (ImageButton)findViewById(R.id.c5_btn);
        c6_btn = (ImageButton)findViewById(R.id.c6_btn);
        c7_btn = (ImageButton)findViewById(R.id.c7_btn);
        c8_btn = (ImageButton)findViewById(R.id.c8_btn);



        c1_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                stopPlaying();
                mp = MediaPlayer.create(MainActivity.this, R.raw.c1);
                mp.start();
            }
        });


        c2_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                stopPlaying();
                mp = MediaPlayer.create(MainActivity.this, R.raw.c2);
                mp.start();
            }
        });

        c3_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                stopPlaying();
                mp = MediaPlayer.create(MainActivity.this, R.raw.c3);
                mp.start();
            }
        });

        c4_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                stopPlaying();
                mp = MediaPlayer.create(MainActivity.this, R.raw.c4);
                mp.start();
            }
        });

        c5_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                stopPlaying();
                mp = MediaPlayer.create(MainActivity.this, R.raw.c5);
                mp.start();
            }
        });

        c6_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                stopPlaying();
                mp = MediaPlayer.create(MainActivity.this, R.raw.c6);
                mp.start();
            }
        });

        c7_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                stopPlaying();
                mp = MediaPlayer.create(MainActivity.this, R.raw.c7);
                mp.start();
            }
        });

        c8_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                stopPlaying();
                mp = MediaPlayer.create(MainActivity.this, R.raw.c8);
                mp.start();
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


}
