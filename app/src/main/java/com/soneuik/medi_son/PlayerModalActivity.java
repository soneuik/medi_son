package com.soneuik.medi_son;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class PlayerModalActivity extends Dialog implements android.view.View.OnClickListener {

    public Activity current_activity;
    public Dialog d;
    public Button min5, min10, min20;
    public Button min30, min40, min50;
    public Button min60, min120;
    private String name_music ="";

    public PlayerModalActivity(Activity a){
        super(a);

    }

    public PlayerModalActivity(Activity a, String song) {
        super(a);
        // TODO Auto-generated constructor stub
        this.current_activity = a;
        name_music= song;
    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_player_modal);

        min5 = (Button) findViewById(R.id.min5);
        min10 = (Button) findViewById(R.id.min10);
        min20 = (Button) findViewById(R.id.min20);

        min30 = (Button) findViewById(R.id.min30);
        min40 = (Button) findViewById(R.id.min40);
        min50 = (Button) findViewById(R.id.min50);

        min60 = (Button) findViewById(R.id.min60);
        min120 = (Button) findViewById(R.id.min120);



        min5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String timer_selected = "5min";
                Intent timerIntent = new Intent(v.getContext(), MusicPlayerActivity.class);
                timerIntent.putExtra("timer_selected", timer_selected);
                timerIntent.putExtra("name_music", name_music);
                v.getContext().startActivity(timerIntent);

            }
        });

        min10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String timer_selected = "10min";
                Intent timerIntent = new Intent(v.getContext(), MusicPlayerActivity.class);
                timerIntent.putExtra("timer_selected", timer_selected);
                timerIntent.putExtra("name_music", name_music);
                v.getContext().startActivity(timerIntent);
            }
        });


        min20.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String timer_selected = "20min";
                Intent timerIntent = new Intent(v.getContext(), MusicPlayerActivity.class);
                timerIntent.putExtra("timer_selected", timer_selected);
                timerIntent.putExtra("name_music", name_music);
                v.getContext().startActivity(timerIntent);

            }
        });

        min30.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String timer_selected = "30min";
                Intent timerIntent = new Intent(v.getContext(), MusicPlayerActivity.class);
                timerIntent.putExtra("timer_selected", timer_selected);
                timerIntent.putExtra("name_music", name_music);
                v.getContext().startActivity(timerIntent);

            }
        });

        min40.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String timer_selected = "40min";
                Intent timerIntent = new Intent(v.getContext(), MusicPlayerActivity.class);
                timerIntent.putExtra("timer_selected", timer_selected);
                timerIntent.putExtra("name_music", name_music);
                v.getContext().startActivity(timerIntent);
            }
        });


        min50.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String timer_selected = "50min";
                Intent timerIntent = new Intent(v.getContext(), MusicPlayerActivity.class);
                timerIntent.putExtra("timer_selected", timer_selected);
                timerIntent.putExtra("name_music", name_music);
                v.getContext().startActivity(timerIntent);

            }
        });

        min60.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String timer_selected = "60min";
                Intent timerIntent = new Intent(v.getContext(), MusicPlayerActivity.class);
                timerIntent.putExtra("timer_selected", timer_selected);
                timerIntent.putExtra("name_music", name_music);
                v.getContext().startActivity(timerIntent);

            }
        });

        min120.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String timer_selected = "120min";
                Intent timerIntent = new Intent(v.getContext(), MusicPlayerActivity.class);
                timerIntent.putExtra("timer_selected", timer_selected);
                timerIntent.putExtra("name_music", name_music);
                v.getContext().startActivity(timerIntent);
            }
        });




    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.min5:
                current_activity.finish();
                break;
            case R.id.min10:
                current_activity.finish();
                break;
            case R.id.min20:
                dismiss();
                break;
            default:
                break;
        }
        dismiss();
    }


}