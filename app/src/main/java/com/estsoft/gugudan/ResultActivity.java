package com.estsoft.gugudan;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class ResultActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
        Intent intent = getIntent();

        int tc = intent.getIntExtra("totalCount",0);
        int cc = intent.getIntExtra("correctCount",0);

        TextView tv = (TextView)findViewById(R.id.textView_result_result);
        tv.setText(cc+" / "+tc);


        findViewById(R.id.button_result_yes).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ResultActivity.this, GameActivity.class));
            }
        });

        findViewById(R.id.button_result_no).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //startActivity(new Intent(ResultActivity.this,MainActivity.class));
                finish();
            }
        });
    }
}
