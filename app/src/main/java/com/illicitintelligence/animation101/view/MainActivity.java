package com.illicitintelligence.animation101.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;

import com.illicitintelligence.animation101.R;

public class MainActivity extends AppCompatActivity {


    private Button pauseGameButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

//        droidSpace = new DroidSpace(this);

        setContentView(R.layout.activity_main/*droidSpace*/);
        pauseGameButton = findViewById(R.id.pause_button);

//        pauseGameButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent pauseIntent = new Intent("pause_game_event");
//                sendBroadcast(pauseIntent);
//            }
//        });

        pauseGameButton.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {

                Intent pauseIntent = new Intent("pause_game_event");

                switch (event.getAction())
                {

                    case MotionEvent.ACTION_DOWN:
                        sendBroadcast(pauseIntent);
                        break;
                    case MotionEvent.ACTION_UP:
                        sendBroadcast(pauseIntent);
                        break;
                }
                return true;
            }
        });
    }
}
