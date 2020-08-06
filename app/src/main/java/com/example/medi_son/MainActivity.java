package com.example.medi_son;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private MediaPlayer mp;
    //Nature Sound Img_btn
    ImageButton row1_1, row1_2, row1_3, row1_4, row1_5;
    ImageButton row2_1, row2_2, row2_3, row2_4, row2_5;
    //Classic Img_btn
    ImageButton c1_btn, c2_btn, c3_btn, c4_btn, c5_btn, c6_btn, c7_btn, c8_btn;



    @Override
    protected void onStart() {
        super.onStart();

        stopPlaying();


    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Nature sound buttons
        //row1
        row1_1 = (ImageButton) findViewById(R.id.row1_1);
        row1_1.setOnClickListener(this);
        row1_2 = (ImageButton) findViewById(R.id.row1_2);
        row1_2.setOnClickListener(this);
        row1_3 = (ImageButton) findViewById(R.id.row1_3);
        row1_3.setOnClickListener(this);
        row1_4 = (ImageButton) findViewById(R.id.row1_4);
        row1_4.setOnClickListener(this);
        row1_5 = (ImageButton) findViewById(R.id.row1_5);
        row1_5.setOnClickListener(this);
        //row2
        row2_1 = (ImageButton) findViewById(R.id.row2_1);
        row2_1.setOnClickListener(this);
        row2_2 = (ImageButton) findViewById(R.id.row2_2);
        row2_2.setOnClickListener(this);
        row2_3 = (ImageButton) findViewById(R.id.row2_3);
        row2_3.setOnClickListener(this);
        row2_4 = (ImageButton) findViewById(R.id.row2_4);
        row2_4.setOnClickListener(this);
        row2_5 = (ImageButton) findViewById(R.id.row2_5);
        row2_5.setOnClickListener(this);
        //classic sound buttons
        c1_btn = (ImageButton) findViewById(R.id.c1_btn);
        c1_btn.setOnClickListener(this);
        c2_btn = (ImageButton) findViewById(R.id.c2_btn);
        c2_btn.setOnClickListener(this);
        c3_btn = (ImageButton) findViewById(R.id.c3_btn);
        c3_btn.setOnClickListener(this);
        c4_btn = (ImageButton) findViewById(R.id.c4_btn);
        c4_btn.setOnClickListener(this);
        c5_btn = (ImageButton) findViewById(R.id.c5_btn);
        c5_btn.setOnClickListener(this);
        c6_btn = (ImageButton) findViewById(R.id.c6_btn);
        c6_btn.setOnClickListener(this);
        c7_btn = (ImageButton) findViewById(R.id.c7_btn);
        c7_btn.setOnClickListener(this);
        c8_btn = (ImageButton) findViewById(R.id.c8_btn);
        c8_btn.setOnClickListener(this);



        BottomNavigationView bottomNavigationView = findViewById(R.id.nav_view);

        bottomNavigationView.setSelectedItemId(R.id.nav_Sound);

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.nav_home:
                        startActivity(new Intent(getApplicationContext(), HomeActivity.class));
                        overridePendingTransition(0,0);
                        return true;

                    case R.id.nav_sleep:
                        startActivity(new Intent(getApplicationContext(), SleepActivity.class));
                        overridePendingTransition(0,0);
                        return true;

                    case R.id.nav_Sound:
                        return true;

                }
                return false;
            }
        });


    }


    @Override
    public void onClick(View v) {
        String song = "";
        PlayerModalActivity pmm;
        switch (v.getId()) {

            case R.id.row1_1:
                song = "amazon_bird";
                pmm = new PlayerModalActivity(MainActivity.this, song);
                pmm.show();
                break;

            case R.id.row1_2:
                song = "forest_bird";
                pmm = new PlayerModalActivity(MainActivity.this, song);
                pmm.show();
                break;

            case R.id.row1_3:
                song = "sea_bird";
                pmm = new PlayerModalActivity(MainActivity.this, song);
                pmm.show();
                break;
            case R.id.row1_4:
                song = "river_bird";
                pmm = new PlayerModalActivity(MainActivity.this, song);
                pmm.show();
                break;
            case R.id.row1_5:
                song = "insects";
                pmm = new PlayerModalActivity(MainActivity.this, song);
                pmm.show();
                break;

            case R.id.row2_1:
                song = "salmon";
                pmm = new PlayerModalActivity(MainActivity.this, song);
                pmm.show();
                break;

            case R.id.row2_2:
                song = "sealion";
                pmm = new PlayerModalActivity(MainActivity.this, song);
                pmm.show();
                break;

            case R.id.row2_3:
                song = "frog";
                pmm = new PlayerModalActivity(MainActivity.this, song);
                pmm.show();
                break;
            case R.id.row2_4:
                song = "amazon_bird";
                pmm = new PlayerModalActivity(MainActivity.this, song);
                pmm.show();
                break;
            case R.id.row2_5:
                song = "amazon_bird";
                pmm = new PlayerModalActivity(MainActivity.this, song);
                pmm.show();
                break;
            /////////////////////////classic Sounds
            case R.id.c1_btn:
                song = "c1";
                pmm = new PlayerModalActivity(MainActivity.this, song);
                pmm.show();
                break;

            case R.id.c2_btn:
                song = "c2";
                pmm = new PlayerModalActivity(MainActivity.this, song);
                pmm.show();
                break;
            case R.id.c3_btn:
                song = "c3";
                pmm = new PlayerModalActivity(MainActivity.this, song);
                pmm.show();
                break;
            case R.id.c4_btn:
                song = "c4";
                pmm = new PlayerModalActivity(MainActivity.this, song);
                pmm.show();
                break;

            case R.id.c5_btn:
                song = "c5";
                pmm = new PlayerModalActivity(MainActivity.this, song);
                pmm.show();
                break;

            case R.id.c6_btn:
                song = "c6";
                pmm = new PlayerModalActivity(MainActivity.this, song);
                pmm.show();
                break;

            case R.id.c7_btn:
                song = "c7";
                pmm = new PlayerModalActivity(MainActivity.this, song);
                pmm.show();
                break;
            case R.id.c8_btn:
                song = "c8";
                pmm = new PlayerModalActivity(MainActivity.this, song);
                pmm.show();
                break;

            default:
                break;
        }

    }

    private void stopPlaying() {
        if (mp != null) {
            mp.stop();
            mp.reset();
            mp.release();
            mp = null;
        }
    }


    private BottomNavigationView.OnNavigationItemSelectedListener navigationItemSelectedListener = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
            switch (menuItem.getItemId()) {
                case R.id.nav_home:
                    startActivity(new Intent(getApplicationContext(), SleepActivity.class));
                    overridePendingTransition(0,0);
                    return true;

                case R.id.nav_sleep:
                    startActivity(new Intent(getApplicationContext(), SleepActivity.class));
                    overridePendingTransition(0,0);
                    return true;

                case R.id.nav_Sound:
                    startActivity(new Intent(getApplicationContext(), MainActivity.class));
                    overridePendingTransition(0,0);
                    return true;

            }
            return false;
        }

    };

}
