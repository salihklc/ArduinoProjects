package com.example.salih.kasim262014ders;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.SeekBar;
import android.widget.TextView;


public class MainActivity extends Activity {
    int red=0,green=0,blue=0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final SeekBar mySeekBar     =   (SeekBar)   findViewById(R.id.seekBar);
        final TextView myView       =   (TextView)  findViewById(R.id.textView);
        final SeekBar mySeekBar2    =   (SeekBar)   findViewById(R.id.seekBar2);
        final SeekBar mySeekBar3    =   (SeekBar)   findViewById(R.id.seekBar3);


        mySeekBar2.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                myView.setBackgroundColor(android.graphics.Color.rgb(progress,green,blue));
                red=progress;
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        mySeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                myView.setText(Integer.toString(progress));
                myView.setBackgroundColor(android.graphics.Color.rgb(red,progress,blue));
                green=progress;
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        mySeekBar3.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                myView.setBackgroundColor(android.graphics.Color.rgb(red,green,progress));
                blue=progress;
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }



}
