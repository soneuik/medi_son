package com.example.medi_son;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Player_modal  extends Dialog implements
        android.view.View.OnClickListener {

        public Activity c;
        public Dialog d;
        public Button yes, no;



        public Player_modal(Activity a) {
                super(a);
                // TODO Auto-generated constructor stub
                this.c = a;
        }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_player_modal);

            yes = (Button) findViewById(R.id.btn_yes);
            no = (Button) findViewById(R.id.btn_no);
            yes.setOnClickListener(this);
            no.setOnClickListener(this);
    }


        @Override
        public void onClick(View v) {
                switch (v.getId()) {
                        case R.id.btn_yes:
                                c.finish();
                                break;
                        case R.id.btn_no:
                                dismiss();
                                break;
                        default:
                                break;
                }
                dismiss();
        }


}
