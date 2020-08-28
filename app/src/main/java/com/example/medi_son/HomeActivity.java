package com.example.medi_son;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class HomeActivity extends AppCompatActivity  implements View.OnClickListener{

    ViewPager viewPager;
    LinearLayout sliderDotspanel;
    private int dotscount;
    private ImageView[] dots;




    ImageButton btn_bonfire1, btn_bonfire2;
    ImageButton btn_wave1,btn_wave2,btn_wave3;
    ImageButton btn_rain1, btn_rain2,btn_rain3,btn_rain4;
    ImageButton btn_thunder1, btn_thunder2,btn_thunder3 ;
    private MediaPlayer mp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);


        //btn_bonfire
        btn_bonfire1 = (ImageButton) findViewById(R.id.btn_bonfire1);
        btn_bonfire1.setOnClickListener(this);
        btn_bonfire2 = (ImageButton) findViewById(R.id.btn_bonfire2);
        btn_bonfire2.setOnClickListener(this);

        //btn_wave
        btn_wave1 = (ImageButton) findViewById(R.id.btn_wave1);
        btn_wave1.setOnClickListener(this);
        btn_wave2 = (ImageButton) findViewById(R.id.btn_wave2);
        btn_wave2.setOnClickListener(this);        //btn_bonfire
        btn_wave3 = (ImageButton) findViewById(R.id.btn_wave3);
        btn_wave3.setOnClickListener(this);

        //btn_rain
        btn_rain1 = (ImageButton) findViewById(R.id.btn_rain1);
        btn_rain1.setOnClickListener(this);
        btn_rain2 = (ImageButton) findViewById(R.id.btn_rain2);
        btn_rain2.setOnClickListener(this);        //btn_bonfire
        btn_rain3 = (ImageButton) findViewById(R.id.btn_rain3);
        btn_rain3.setOnClickListener(this);
        btn_rain4 = (ImageButton) findViewById(R.id.btn_rain4);
        btn_rain4.setOnClickListener(this);

        //btn_thunder
        btn_thunder1 = (ImageButton) findViewById(R.id.btn_thunder1);
        btn_thunder1.setOnClickListener(this);
        btn_thunder2 = (ImageButton) findViewById(R.id.btn_thunder2);
        btn_thunder2.setOnClickListener(this);        //btn_bonfire
        btn_thunder3 = (ImageButton) findViewById(R.id.btn_thunder3);
        btn_thunder3.setOnClickListener(this);



        BottomNavigationView bottomNavigationView = findViewById(R.id.nav_view);

        bottomNavigationView.setSelectedItemId(R.id.nav_home);

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.nav_home:
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
        });





        ///////////////////////displaying dots indicator-START /////////////////////////////////////////////////////////////////////////////////////////////
    //https://www.sanktips.com/2017/10/01/how-to-add-dots-indicator-to-image-slider-with-viewpager-in-android-studio/
        viewPager = (ViewPager) findViewById(R.id.viewPager);

        sliderDotspanel = (LinearLayout) findViewById(R.id.SliderDots);

        ViewPagerAdapter viewPagerAdapter = new ViewPagerAdapter(this);

        viewPager.setAdapter(viewPagerAdapter);

        dotscount = viewPagerAdapter.getCount();
        dots = new ImageView[dotscount];

        for(int i = 0; i < dotscount; i++){

            dots[i] = new ImageView(this);
            dots[i].setImageDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.non_active_dot));

            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);

            params.setMargins(8, 0, 8, 0);

            sliderDotspanel.addView(dots[i], params);

        }

        dots[0].setImageDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.active_dot));

        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {

                for(int i = 0; i< dotscount; i++){
                    dots[i].setImageDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.non_active_dot));
                }

                dots[position].setImageDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.active_dot));

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        ///////////////////////displaying dots indicator-END /////////////////////////////////////////////////////////////////////////////////////////////

    }

    @Override
    public void onClick(View v) {
        String song = "";
        PlayerModalActivity pmm;
        switch (v.getId()) {

            //Bonfire
            case R.id.btn_bonfire1:
                song = "bonfire1";
                pmm = new PlayerModalActivity(HomeActivity.this, song);
                pmm.show();
                break;

            case R.id.btn_bonfire2:
                song = "bonfire2";
                pmm = new PlayerModalActivity(HomeActivity.this, song);
                pmm.show();
                break;

            //WAVE
            case R.id.btn_wave1:
                song = "wave1";
                pmm = new PlayerModalActivity(HomeActivity.this, song);
                pmm.show();
                break;

            case R.id.btn_wave2:
                song = "wave2";
                pmm = new PlayerModalActivity(HomeActivity.this, song);
                pmm.show();
                break;
            case R.id.btn_wave3:
                song = "wave3";
                pmm = new PlayerModalActivity(HomeActivity.this, song);
                pmm.show();
                break;

            //Rain
            case R.id.btn_rain1:
                song = "rain1";
                pmm = new PlayerModalActivity(HomeActivity.this, song);
                pmm.show();
                break;

            case R.id.btn_rain2:
                song = "rain2";
                pmm = new PlayerModalActivity(HomeActivity.this, song);
                pmm.show();
                break;
            case R.id.btn_rain3:
                song = "rain3";
                pmm = new PlayerModalActivity(HomeActivity.this, song);
                pmm.show();
                break;
            case R.id.btn_rain4:
                song = "rain4";
                pmm = new PlayerModalActivity(HomeActivity.this, song);
                pmm.show();
                break;

            //Thunder
            case R.id.btn_thunder1:
                song = "thunder1";
                pmm = new PlayerModalActivity(HomeActivity.this, song);
                pmm.show();
                break;

            case R.id.btn_thunder2:
                song = "thunder2";
                pmm = new PlayerModalActivity(HomeActivity.this, song);
                pmm.show();
                break;
            case R.id.btn_thunder3:
                song = "thunder3";
                pmm = new PlayerModalActivity(HomeActivity.this, song);
                pmm.show();
                break;



            default:
                break;
        }

    }
}