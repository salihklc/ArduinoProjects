package com.example.salih.aralik32014;

import android.app.Activity;
import android.os.Bundle;
import android.text.InputType;
import android.text.method.PasswordTransformationMethod;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.Toast;
import android.widget.ToggleButton;


public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final Switch        mySwitch    =   (Switch)        findViewById(R.id.switch1);
        final ToggleButton  myToggle    =   (ToggleButton)  findViewById(R.id.toggleButton);
        final EditText      myText      =   (EditText)      findViewById(R.id.editText);

        mySwitch.setChecked(true);
        myToggle.setChecked(false);
        myText.setHint("password'unuzu giriniz !");

        myToggle.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(myToggle.isChecked()){
                    myText.setTransformationMethod(new PasswordTransformationMethod());
                }
                else if(!myToggle.isChecked())
                {
                    myText.setTransformationMethod(null);
                }
            }
        });

        mySwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (mySwitch.isChecked()){
                    Toast.makeText(getApplicationContext(),"Switch is Opened",Toast.LENGTH_SHORT).show();
                }
                if (!mySwitch.isChecked()){
                    Toast.makeText(getApplicationContext(),"Switch is Closed",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }


}
