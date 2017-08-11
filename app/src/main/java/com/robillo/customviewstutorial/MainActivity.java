package com.robillo.customviewstutorial;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    MyCustomView myCustomView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        myCustomView = (MyCustomView) findViewById(R.id.mcv);
    }

    public void onClick(View view) {
        switch (view.getId()){
            case R.id.one:{
                myCustomView.customPaddingUp(30);
                break;
            }
            case R.id.two:{
                myCustomView.swapColor();
                break;
            }
            case R.id.three:{
                myCustomView.customPaddingDown(30);
            }
        }
    }
}
