package au.edu.jcu.cp3406.stopwatch;

import android.content.Intent;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


public class StopwatchActivity extends AppCompatActivity {
    private boolean isRunning;
    private Stopwatch stopwatch = new Stopwatch();
    final Handler handler = new Handler();
    public int speed;
    public TextView display;
    public Button toggle;
    public String timerText;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stopwatch);

        display=findViewById(R.id.timeTextDisplay);
        toggle=findViewById(R.id.toggle);
        timerText=stopwatch.toString();

        isRunning=false;
        if (savedInstanceState == null) {
            stopwatch = new Stopwatch();
        } else {
            stopwatch=new Stopwatch();
            boolean running=true;
            if (running) {
                enableStopwatch();
            }
        }
        runTimer();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString("timerText", stopwatch.toString());
        outState.putBoolean("running", isRunning);

    }

    private void enableStopwatch() {
        isRunning = true;
    }

    private void disableStopwatch() {
        isRunning=false;
        stopwatch.reset();
//        speed=1000;
        display.setText("00:00:00");

    }

    private void runTimer() {
        handler.post(new Runnable() {
            @Override
            public void run() {
                TextView view = findViewById(R.id.timeTextDisplay);

                if (isRunning) {
                    stopwatch.tick();
                    view.setText(stopwatch.toString());
                }
                handler.postDelayed(this, speed);
            }
        });
    }


    public void buttonClicked(View view) {
        String btnLabel=toggle.getText().toString();

        if (btnLabel.equals("Start")) {
            enableStopwatch();
            toggle.setText("Stop/Reset");
        } else {
            disableStopwatch();
            toggle.setText("Start");
        }
    }

    public void settingsClicked(View view) {
        Intent intent = new Intent(this, SettingsActivity.class);
        startActivity(intent);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == SettingsActivity.SETTINGS_REQUEST) {
            if (resultCode == RESULT_OK) {
                if (data != null) {
                    speed = data.getIntExtra("speed", 1000);
                }
            }
        }
    }

}
