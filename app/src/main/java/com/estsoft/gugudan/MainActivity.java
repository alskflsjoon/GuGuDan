package com.estsoft.gugudan;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.button_main_help).setOnClickListener(this);
        findViewById(R.id.button_main_record).setOnClickListener(this);
        findViewById(R.id.button_main_start).setOnClickListener(this);
        findViewById(R.id.button_main_exit).setOnClickListener(this);


    }

    @Override
    public void onClick(View v) {
        int viewId=v.getId();
        switch (viewId){
            case R.id.button_main_help: {
                startActivity(new Intent(MainActivity.this, HelpActivity.class));
                break;
            }
            case R.id.button_main_record: {
                startActivity(new Intent(MainActivity.this, RecorderActivity.class));
                break;
            }
            case R.id.button_main_start: {
                startActivity(new Intent(MainActivity.this,GameActivity.class));
                break;
            }
            case R.id.button_main_exit: {
                finish();
            }
        }
    }
}
