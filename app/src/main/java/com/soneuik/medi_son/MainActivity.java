package com.soneuik.medi_son;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.LoadAdError;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private MediaPlayer mp;
    //Nature Sound Img_btn
    ImageButton row1_1, row1_2, row1_3, row1_4, row1_5;
    ImageButton row2_1, row2_2, row2_3, row2_4, row2_5;
    //Classic Img_btn
    ImageButton c1_btn, c2_btn, c3_btn, c4_btn, c5_btn, c6_btn, c7_btn, c8_btn;
    private AdView mAdView;
    Button btn_share, btn_email, btn_feedback;


    @Override
    protected void onStart() {
        super.onStart();

        stopPlaying();


    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mAdView = findViewById(R.id.ad_banner);
        adMob_banner();
        btn_share = (Button)findViewById(R.id.btn_share);
        btn_share.setOnClickListener(this);
        btn_email = (Button)findViewById(R.id.btn_email);
        btn_email.setOnClickListener(this);
        btn_feedback = (Button)findViewById(R.id.btn_feedback);
        btn_feedback.setOnClickListener(this);







        //Nature sound buttons

        //classic sound buttons
   /*     c1_btn = (ImageButton) findViewById(R.id.c1_btn);
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
*/


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

            case R.id.btn_share:
                Intent sendIntent = new Intent();
                sendIntent.setAction(Intent.ACTION_SEND);
                sendIntent.putExtra(Intent.EXTRA_TEXT, "https://play.google.com/store/apps/details?id=com.soneuik.medi_son");
                sendIntent.setType("text/plain");
                Intent shareIntent = Intent.createChooser(sendIntent, "Share");
                startActivity(shareIntent);
                break;

            case R.id.btn_email:
                btn_email();
                break;

            case R.id.btn_feedback:
                btn_feedback();
                break;


            /////////////////////////classic Sounds
         /*   case R.id.c1_btn:
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
                break;*/




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

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(Intent.ACTION_MAIN);
        intent.addCategory(Intent.CATEGORY_HOME);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }








    private void btn_email(){
        String mailto = "mailto:soneuik30@gmail.com" +
                "?cc=" +
                "&subject=" + Uri.encode("your subject") +
                "&body=" + Uri.encode("your mail body");
        Intent emailIntent = new Intent(Intent.ACTION_SENDTO);
        emailIntent.setData(Uri.parse(mailto));

        try {
            startActivity(emailIntent);
        } catch (ActivityNotFoundException e) {
            Toast.makeText(MainActivity.this, "Error to open email app", Toast.LENGTH_SHORT).show();
        }

    }

    private void btn_feedback(){

            // TODO Auto-generated method stub
            final String myappPackageName = "com.soneuik.medi_son"; // getPackageName() from Context or Activity object
            try {
                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=" + myappPackageName)));
            } catch (android.content.ActivityNotFoundException anfe) {
                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("http://play.google.com/store/apps/details?id=" + myappPackageName)));
            }
    }



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
