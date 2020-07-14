package vladimir.exampleservise;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import vladimir.exampleservise.services.CountService;
import vladimir.exampleservise.services.SampleBroadcastReceiver;

public class MainActivity extends AppCompatActivity {

    private Button butStart;
    private Button butStop;
    private SampleBroadcastReceiver sampleBrodcastResiver;
    private IntentFilter intentFilter;
    private Button butCatch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        butStart = findViewById(R.id.but_start_service);
        butStop = findViewById(R.id.but_stop_service);
        butCatch = findViewById(R.id.but_catch_service);

        butStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, CountService.class);
                startService(intent);
            }
        });

        butStop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, CountService.class);
                stopService(intent);
            }
        });

        butCatch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendBroadcast(new Intent(SampleBroadcastReceiver.SAMPLE_ACTION));
            }
        });


        sampleBrodcastResiver = new SampleBroadcastReceiver();
        intentFilter = new IntentFilter(SampleBroadcastReceiver.SAMPLE_ACTION);
    }

    @Override
    protected void onResume() {
        super.onResume();
        registerReceiver(sampleBrodcastResiver, intentFilter);

    }

    @Override
    protected void onPause() {
        super.onPause();
        unregisterReceiver(sampleBrodcastResiver);
    }
}