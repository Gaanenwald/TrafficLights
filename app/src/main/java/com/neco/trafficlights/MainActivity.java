package com.neco.trafficlights;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    private LinearLayout b_red, b_yellow, b_green;
    private Button button;
    private boolean flag = false;
    private int counter = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        b_red = findViewById(R.id.bulb_red);
        b_yellow = findViewById(R.id.bulb_yellow);
        b_green = findViewById(R.id.bulb_green);
        button = findViewById(R.id.button1);
    }

    @SuppressLint("SetTextI18n")
    public void onClickStart(View view) {
        if(!flag){
            flag = true;
            button.setText("Stop");

            new Thread(() -> {
                while (flag){
                    counter++;
                    setAllLayoutsIsGrey();
                    setLayoutColor(counter);

                    try {
                        Thread.sleep(3000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }).start();
        }
        else {
            flag = false;
            button.setText("Start");
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        flag = false;
    }

    private void setLayoutColor(int count) {
        switch (count) {
            case 1:
                b_red.setBackgroundColor(getResources().getColor(R.color.red));
                break;
            case 2:
                b_red.setBackgroundColor(getResources().getColor(R.color.red));
                b_yellow.setBackgroundColor(getResources().getColor(R.color.yellow));
                break;
            case 3:
                b_green.setBackgroundColor(getResources().getColor(R.color.green));
                break;
            case 4:
                b_yellow.setBackgroundColor(getResources().getColor(R.color.yellow));
                counter = 0;
                break;
        }
    }

    private void setAllLayoutsIsGrey() {
        int color = getResources().getColor(R.color.gray);

        b_red.setBackgroundColor(color);
        b_yellow.setBackgroundColor(color);
        b_green.setBackgroundColor(color);
    }
}