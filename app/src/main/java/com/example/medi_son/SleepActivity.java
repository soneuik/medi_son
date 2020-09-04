package com.example.medi_son;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;

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

public class SleepActivity extends AppCompatActivity implements View.OnClickListener{


    String[] img_arr =   {"sleep1", "sleep2",
                          "sleep3","sleep4","sleep5"  };

    String[] img_arr2 =   {
            "sleep6","sleep7","sleep8",
            "sleep9","sleep10","sleep11" ,
            "sleep12","sleep13" };

    ImageButton btn_sleep1, btn_sleep2;
    ImageButton btn_sleep3, btn_sleep4,  btn_sleep5;
    ImageButton btn_sleep6, btn_sleep7, btn_sleep8, btn_sleep9;
    ImageButton btn_sleep10, btn_sleep11, btn_sleep12, btn_sleep13;
    LinearLayout lin_sleep3, lin_sleep4,lin_sleep5;

    ImageButton btn_img ;
    //Firebase
    private StorageReference storageRef = FirebaseStorage.getInstance().getReference();
    private StorageReference dateRef;


    private MediaPlayer mp;
    private AdView mAdView;


    @Override
    protected void onStart() {
        super.onStart();
        stopPlaying();
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sleep);


        MobileAds.initialize(this, new OnInitializationCompleteListener() {
            @Override
            public void onInitializationComplete(InitializationStatus initializationStatus) {
            }
        });


        mAdView = findViewById(R.id.ad_banner);
        //classic sound buttons
        btn_sleep1 = (ImageButton) findViewById(R.id.btn_sleep1);
        btn_sleep1.setOnClickListener(this);
        btn_sleep2 = (ImageButton) findViewById(R.id.btn_sleep2);
        btn_sleep2.setOnClickListener(this);
        btn_sleep3 = (ImageButton) findViewById(R.id.btn_sleep3);
        btn_sleep4 = (ImageButton) findViewById(R.id.btn_sleep4);
        btn_sleep5 = (ImageButton) findViewById(R.id.btn_sleep5);
        lin_sleep3 = (LinearLayout) findViewById(R.id.lin_sleep3);
        lin_sleep3.setOnClickListener(this);
        lin_sleep4 = (LinearLayout) findViewById(R.id.lin_sleep4);
        lin_sleep4.setOnClickListener(this);
        lin_sleep5 = (LinearLayout) findViewById(R.id.lin_sleep5);
        lin_sleep5.setOnClickListener(this);
        btn_sleep6 = (ImageButton) findViewById(R.id.btn_sleep6);
        btn_sleep6.setOnClickListener(this);
        btn_sleep7 = (ImageButton) findViewById(R.id.btn_sleep7);
        btn_sleep7.setOnClickListener(this);
        btn_sleep8 = (ImageButton) findViewById(R.id.btn_sleep8);
        btn_sleep8.setOnClickListener(this);
        btn_sleep9 = (ImageButton) findViewById(R.id.btn_sleep9);
        btn_sleep9.setOnClickListener(this);
        btn_sleep10 = (ImageButton) findViewById(R.id.btn_sleep10);
        btn_sleep10.setOnClickListener(this);
        btn_sleep11 = (ImageButton) findViewById(R.id.btn_sleep11);
        btn_sleep11.setOnClickListener(this);
        btn_sleep12 = (ImageButton) findViewById(R.id.btn_sleep12);
        btn_sleep12.setOnClickListener(this);
        btn_sleep13 = (ImageButton) findViewById(R.id.btn_sleep13);
        btn_sleep13.setOnClickListener(this);



        //image loader
        for(int i=0; i<img_arr.length; i++) {
            String btn_name = "btn_" + img_arr[i];
            switch (btn_name) {
                case "btn_sleep1":
                    btn_img = btn_sleep1;
                    break;
                case "btn_sleep2":
                    btn_img = btn_sleep2;
                    break;
                case "btn_sleep3":
                    btn_img = btn_sleep3;
                    break;
                case "btn_sleep4":
                    btn_img = btn_sleep4;
                    break;
                case "btn_sleep5":
                    btn_img = btn_sleep5;
                    break;

            }
            image_loader(img_arr[i], btn_img);

        }


        //image loader
        for(int i=0; i<img_arr2.length; i++) {
            String btn_name = "btn_" + img_arr2[i];
            switch (btn_name) {
                case "btn_sleep6":
                    btn_img = btn_sleep6;
                    break;
                case "btn_sleep7":
                    btn_img = btn_sleep7;
                    break;
                case "btn_sleep8":
                    btn_img = btn_sleep8;
                    break;
                case "btn_sleep9":
                    btn_img = btn_sleep9;
                    break;
                case "btn_sleep10":
                    btn_img = btn_sleep10;
                    break;
                case "btn_sleep11":
                    btn_img = btn_sleep11;
                    break;
                case "btn_sleep12":
                    btn_img = btn_sleep12;
                    break;
                case "btn_sleep13":
                    btn_img = btn_sleep13;
                    break;

            }
            image_loader2(img_arr2[i], btn_img);



            adMob_banner();
        }




        BottomNavigationView bottomNavigationView = findViewById(R.id.nav_view);
        bottomNavigationView.setSelectedItemId(R.id.nav_sleep);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.nav_home:
                        startActivity(new Intent(getApplicationContext(), HomeActivity.class));
                        overridePendingTransition(0,0);
                        return true;

                    case R.id.nav_sleep:
                        return true;

                    case R.id.nav_Sound:
                        startActivity(new Intent(getApplicationContext(), MainActivity.class));
                        overridePendingTransition(0,0);
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

            case R.id.btn_sleep1:
                song = "sleep1";
                pmm = new PlayerModalActivity(SleepActivity.this, song);
                pmm.show();
                break;
            case R.id.btn_sleep2:
                song = "sleep2";
                pmm = new PlayerModalActivity(SleepActivity.this, song);
                pmm.show();
                break;
            case R.id.lin_sleep3:
                song = "sleep3";
                pmm = new PlayerModalActivity(SleepActivity.this, song);
                pmm.show();
                break;
            case R.id.lin_sleep4:
                song = "sleep4";
                pmm = new PlayerModalActivity(SleepActivity.this, song);
                pmm.show();
                break;
            case R.id.lin_sleep5:
                song = "sleep5";
                pmm = new PlayerModalActivity(SleepActivity.this, song);
                pmm.show();
                break;

            case R.id.btn_sleep6:
                song = "bird1";
                pmm = new PlayerModalActivity(SleepActivity.this, song);
                pmm.show();
                break;
            case R.id.btn_sleep7:
                song = "bird2";
                pmm = new PlayerModalActivity(SleepActivity.this, song);
                pmm.show();
                break;

            case R.id.btn_sleep8:
                song = "bird3";
                pmm = new PlayerModalActivity(SleepActivity.this, song);
                pmm.show();
                break;
            case R.id.btn_sleep9:
                song = "bird4";
                pmm = new PlayerModalActivity(SleepActivity.this, song);
                pmm.show();
                break;

            case R.id.btn_sleep10:
                song = "frog";
                pmm = new PlayerModalActivity(SleepActivity.this, song);
                pmm.show();
                break;
            case R.id.btn_sleep11:
                song = "insect";
                pmm = new PlayerModalActivity(SleepActivity.this, song);
                pmm.show();
                break;

            case R.id.btn_sleep12:
                song = "salmon";
                pmm = new PlayerModalActivity(SleepActivity.this, song);
                pmm.show();
                break;
            case R.id.btn_sleep13:
                song = "sealion";
                pmm = new PlayerModalActivity(SleepActivity.this, song);
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




    private void image_loader(final String image_name, final ImageButton img_btn_name){

        storageRef.child("images/sleep/"+image_name+".png").getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
            @Override
            public void onSuccess(Uri uri) {


                Glide.with(SleepActivity.this)
                        .load(uri)
                        .apply(new RequestOptions()
                                .placeholder(R.mipmap.ic_launcher)
                                .override(400, 200)
                        )
                        .into(img_btn_name);

            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception exception) {
                storageRef.child("images/sleep/"+image_name+".jpeg").getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                    @Override
                    public void onSuccess(Uri uri) {

                        System.out.println("img_btn_name: "+img_btn_name);
                        Glide.with(SleepActivity.this)
                                .load(uri)
                                .apply(new RequestOptions()
                                        .placeholder(R.mipmap.ic_launcher)
                                        .override(400, 200)
                                )
                                .into(img_btn_name);

                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception exception) {
                        storageRef.child("images/sleep/"+image_name+".jpg").getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                            @Override
                            public void onSuccess(Uri uri) {


                                Glide.with(SleepActivity.this)
                                        .load(uri)
                                        .apply(new RequestOptions()
                                                .placeholder(R.mipmap.ic_launcher)
                                                .override(400, 200)
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



    private void image_loader2(final String image_name, final ImageButton img_btn_name){

        storageRef.child("images/sleep/"+image_name+".png").getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
            @Override
            public void onSuccess(Uri uri) {


                Glide.with(SleepActivity.this)
                        .load(uri)
                        .apply(new RequestOptions()
                                .placeholder(R.drawable.loading)
                                .override(200, 200)
                        )
                        .into(img_btn_name);

            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception exception) {
                storageRef.child("images/sleep/"+image_name+".jpeg").getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                    @Override
                    public void onSuccess(Uri uri) {

                        System.out.println("img_btn_name: "+img_btn_name);
                        Glide.with(SleepActivity.this)
                                .load(uri)
                                .apply(new RequestOptions()
                                        .override(200, 200)
                                )
                                .into(img_btn_name);

                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception exception) {
                        storageRef.child("images/sleep/"+image_name+".jpg").getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                            @Override
                            public void onSuccess(Uri uri) {


                                Glide.with(SleepActivity.this)
                                        .load(uri)
                                        .apply(new RequestOptions()
                                                .override(200, 200)
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
