package com.estsoft.gugudan;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.Timer;
import java.util.TimerTask;

public class GameActivity extends AppCompatActivity implements View.OnClickListener{

    private Button[] answerButtons;
    private Timer timer = new Timer();
    private int totalCount = 0;
    private int correctCount = 0;
    private int answer = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        initGame();
        timer.schedule(new MyTimerTask(),1000,1000);
    }

    @Override
    public void onClick(View v) {
        String textButton = ((Button)v).getText().toString();
        int toInt = Integer.parseInt(textButton);
        if(toInt==answer){
            TextView tv=(TextView)findViewById(R.id.textView_game_correctCount);
            tv.setText(""+(++correctCount));
        }
        newQuestion();
    }

    private void initGame(){
        final int[] answerButtonIds = {
                R.id.button_game_ans1, R.id.button_game_ans2, R.id.button_game_ans3,
                R.id.button_game_ans4, R.id.button_game_ans5, R.id.button_game_ans6,
                R.id.button_game_ans7, R.id.button_game_ans8, R.id.button_game_ans9
        };

        final int answerButtonCount = answerButtonIds.length;

        answerButtons = new Button[answerButtonCount];

        for(int i=0;i<answerButtonCount;i++){
            answerButtons[i]=(Button)findViewById(answerButtonIds[i]);
            answerButtons[i].setOnClickListener(this);
        }

        newQuestion();
    }


    private void newQuestion(){
        final int answerCount=answerButtons.length;
        int[] answers = new int[answerCount];
        int randomOrderAnswer = randomize(0,answerCount);

        for(int i=0; i<answerCount; i++){
            final int operandLeft = randomize(1,9);
            final int operandRight = randomize(1,9);
            final int result = operandLeft * operandRight;

            if(randomOrderAnswer==i){
                TextView tv = (TextView)findViewById(R.id.textView_game_ParamL);
                tv.setText(""+operandLeft);
                tv=(TextView)findViewById(R.id.textView_game_ParamR);
                tv.setText(""+operandRight);
                answer=result;
            }
            boolean isExist = intArrayContains(answers,result);
            if(isExist){
                continue;
            }

            answers[i] = result;
            answerButtons[i].setText(""+result);
        }
        TextView tv=(TextView)findViewById(R.id.textView_game_totalCount);
        tv.setText(""+(totalCount++));
    }

    private boolean intArrayContains(int[]array , int val){
        for(final int i:array){
            if(i==val){
                return true;
            }
        }
        return false;
    }

    private int randomize(int from, int to){
        return (int)(Math.random()*to)+from;
    }

    private class MyTimerTask extends TimerTask {
        private int seconds = 10;

        @Override
        public void run() {
            if(--seconds<0){
                timer.cancel();
                Intent intent = new Intent(GameActivity.this,ResultActivity.class);
                intent.putExtra("totalCount",totalCount);
                intent.putExtra("correctCount",correctCount);
                startActivity(intent);
                finish();
                return;
            }
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    TextView tv = (TextView)findViewById(R.id.textView_game_remainTime);
                    tv.setText(""+seconds);
                }
            });
        }
    }
}
