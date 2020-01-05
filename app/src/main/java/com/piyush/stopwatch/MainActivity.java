package com.piyush.stopwatch;

import android.os.Bundle;
import android.os.SystemClock;
import android.view.View;
import android.widget.Chronometer;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private Chronometer mChronoMeter;
    private TextView mStartStopBtn, mTimeTextView;
    private boolean mIsRunning;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mChronoMeter  = findViewById(R.id.chronometer);
        mTimeTextView = findViewById(R.id.seconds);
        mStartStopBtn = findViewById(R.id.start_stop);

        mStartStopBtn.setOnClickListener(this);
    }

    private void startChronoMeter() {
        mChronoMeter.setBase(SystemClock.elapsedRealtime());
        mChronoMeter.start();
        mStartStopBtn.setText(R.string.stop);
        mTimeTextView.setVisibility(View.INVISIBLE);
    }

    private void stopChronoMeter() {
        mChronoMeter.stop();
        long timeWhenStopped = SystemClock.elapsedRealtime() - mChronoMeter.getBase();
        long second = (timeWhenStopped / 1000);
        mTimeTextView.setText(getString(R.string.seconds_format, second));
        mTimeTextView.setVisibility(View.VISIBLE);
        mChronoMeter.setBase(SystemClock.elapsedRealtime());
        mStartStopBtn.setText(R.string.start);
    }

    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.start_stop) {
            if (mIsRunning) {
                stopChronoMeter();
            } else {
                startChronoMeter();
            }
            mIsRunning = !mIsRunning;
        }
    }
}
