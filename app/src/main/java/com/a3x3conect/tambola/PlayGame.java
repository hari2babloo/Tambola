package com.a3x3conect.tambola;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.bcgdv.asia.lib.ticktock.TickTockView;

import java.util.Calendar;

public class PlayGame extends AppCompatActivity {


    TickTockView mCountDown;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.play_game);

        mCountDown = (TickTockView) findViewById(R.id.view_ticktock_countdown);
//        mCountUp = (TickTockView) findViewById(R.id.view_ticktock_countup);


        if (mCountDown != null) {
            mCountDown.setOnTickListener(new TickTockView.OnTickListener() {
                @Override
                public String getText(long timeRemaining) {
                    int seconds = (int) (timeRemaining / 1000) % 60;
                    int minutes = (int) ((timeRemaining / (1000 * 60)) % 60);
                    int hours = (int) ((timeRemaining / (1000 * 60 * 60)) % 24);
                    int days = (int) (timeRemaining / (1000 * 60 * 60 * 24));
                    boolean hasDays = days > 2;
                    return String.format("%1$02d%4$s %2$02d%5$s %3$02d%6$s",
                            hasDays ? days : hours,
                            hasDays ? hours : minutes,
                            hasDays ? minutes : seconds,
                            hasDays ? "d" : "h",
                            hasDays ? "h" : "m",
                            hasDays ? "m" : "s");
                }
            });
        }


    }
    @Override
    protected void onStart() {
        super.onStart();
        Calendar end = Calendar.getInstance();

        end.add(Calendar.DAY_OF_MONTH,25);
        end.add(Calendar.HOUR_OF_DAY,18);
        end.add(Calendar.MINUTE, 26);
        end.add(Calendar.SECOND, 5);

        Calendar start = Calendar.getInstance();
        start.add(Calendar.MINUTE, -1);
        if (mCountDown != null) {
            mCountDown.start(start, end);
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        mCountDown.stop();
        //  mCountUp.stop();
    }


}
