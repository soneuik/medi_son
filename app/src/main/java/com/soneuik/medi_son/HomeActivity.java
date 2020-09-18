package com.soneuik.medi_son;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.LoadAdError;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

public class HomeActivity extends AppCompatActivity  implements View.OnClickListener{

/*    ViewPager viewPager;
    LinearLayout sliderDotspanel;
    private int dotscount;
    private ImageView[] dots;*/


    //Initialzing Imagebuttons
    ImageButton btn_bonfire1, btn_bonfire2;
    ImageButton btn_wave1,btn_wave2,btn_wave3;
    ImageButton btn_rain1, btn_rain2,btn_rain3,btn_rain4;
    ImageButton btn_thunder1, btn_thunder2,btn_thunder3 ;
    ImageButton btn_img ;
    //Firebase
    private StorageReference storageRef = FirebaseStorage.getInstance().getReference();
    private StorageReference dateRef;
    //images names
    String[] img_arr =   {"bonfire1", "bonfire2",
                        "wave1","wave2","wave3",
                        "rain1","rain2","rain3","rain4",
                        "thunder1","thunder2","thunder3"};

    private AdView mAdView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        MobileAds.initialize(this, new OnInitializationCompleteListener() {
            @Override
            public void onInitializationComplete(InitializationStatus initializationStatus) {
            }
        });


        mAdView = findViewById(R.id.ad_banner);
        adMob_banner();
       /* AdLoader adLoader = new AdLoader.Builder(HomeActivity.this, "ca-app-pub-6769539245756212/4501572000")
                .forUnifiedNativeAd(new UnifiedNativeAd.OnUnifiedNativeAdLoadedListener() {
                    @Override
                    public void onUnifiedNativeAdLoaded(UnifiedNativeAd unifiedNativeAd) {
                        // Show the ad.
                        TemplateView template = findViewById(R.id.tpAdmob);
                        template.setNativeAd(unifiedNativeAd);
                    }
                })
                .withAdListener(new AdListener() {
                    @Override
                    public void onAdFailedToLoad(int errorCode) {
                        // Handle the failure by logging, altering the UI, and so on.
                    }
                })
                .withNativeAdOptions(new NativeAdOptions.Builder()
                        // Methods in the NativeAdOptions.Builder class can be
                        // used here to specify individual options settings.
                        .build())
                .build();

        adLoader.loadAds(new AdRequest.Builder().build(), 3);*/





        if(!isOnline()){

        }


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


        //image loader
        for(int i=0; i<img_arr.length; i++){
            String btn_name = "btn_" + img_arr[i];

            System.out.println("btn_name: "+btn_name);
            int id = 0;

            switch(btn_name) {
                case "btn_bonfire1":
                    btn_img =btn_bonfire1;
                    break;
                case "btn_bonfire2":
                    btn_img =btn_bonfire2;
                    break;
                case "btn_wave1":
                    btn_img =btn_wave1;
                    break;
                case "btn_wave2":
                    btn_img =btn_wave2;
                    break;
                case "btn_wave3":
                    btn_img =btn_wave3;
                    break;
                case "btn_thunder1":
                    btn_img =btn_thunder1;
                    break;
                case "btn_thunder2":
                    btn_img =btn_thunder2;
                    break;
                case "btn_thunder3":
                    btn_img =btn_thunder3;
                    break;
                case "btn_rain1":
                    btn_img =btn_rain1;
                    break;
                case "btn_rain2":
                    btn_img =btn_rain2;
                    break;
                case "btn_rain3":
                    btn_img =btn_rain3;
                    break;
                case "btn_rain4":
                    btn_img =btn_rain4;
                    break;

            }
            //image_loader(img_arr[i], btn_img);
        }


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


/*

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
*/

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



    private void image_loader(final String image_name, final ImageButton img_btn_name){

        storageRef.child("images/"+image_name+".png").getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
            @Override
            public void onSuccess(Uri uri) {


                Glide.with(HomeActivity.this)
                        .load(uri)
                        .apply(new RequestOptions()
                                .override(300, 300)
                        )
                        .into(img_btn_name);

            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception exception) {
                        storageRef.child("images/"+image_name+".jpeg").getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                            @Override
                            public void onSuccess(Uri uri) {


                                Glide.with(HomeActivity.this)
                                        .load(uri)
                                        .apply(new RequestOptions()
                                                .override(300, 300)
                                        )
                                        .into(img_btn_name);

                            }
                        }).addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception exception) {
                                storageRef.child("images/"+image_name+".jpg").getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                                    @Override
                                    public void onSuccess(Uri uri) {


                                        Glide.with(HomeActivity.this)
                                                .load(uri)
                                                .apply(new RequestOptions()
                                                        .override(300, 300)
                                                )
                                                .into(img_btn_name);

                                    }
                                }).addOnFailureListener(new OnFailureListener() {
                                    @Override
                                    public void onFailure(@NonNull Exception exception) {
                                        // Handle any errors
                                    }
                                });
                            }
                        });
            }
        });




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

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(Intent.ACTION_MAIN);
        intent.addCategory(Intent.CATEGORY_HOME);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }










}