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


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        row1_1 = (ImageButton)findViewById(R.id.row1_1);


        row1_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                stopPlaying();
                //mp = MediaPlayer.create(MainActivity.this, R.raw.far);
               // mp.start();
            }
        });





    }



    private void stopPlaying() {
        if (mp != null) {
            mp.stop();
            mp.release();
            mp = null;
        }
    }


}
