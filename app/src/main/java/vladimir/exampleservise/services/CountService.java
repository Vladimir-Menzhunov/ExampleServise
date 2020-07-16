package vladimir.exampleservise.services;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

import androidx.annotation.Nullable;

import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class CountService extends Service {

    public static final String TIME = "TIME";
    private final String TAG = this.getClass().getName();
    private ScheduledExecutorService executorService;

    public CountService() {
    }

    @Override
    public void onCreate() {
        Log.d(TAG, "onCreate:");
        executorService = Executors.newSingleThreadScheduledExecutor();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.d(TAG, "onStartCommand:");

        executorService.scheduleAtFixedRate(new Runnable() {

            private long currentTimeMillis;

            @Override
            public void run() {
                currentTimeMillis = System.currentTimeMillis();
                Log.d(TAG, "currentSecond: " + currentTimeMillis);
                Intent intentToSend = new Intent(SampleBroadcastReceiver.SAMPLE_ACTION);
                intentToSend.putExtra(TIME, currentTimeMillis);
                sendBroadcast(intentToSend);
            }
        },0, 4, TimeUnit.SECONDS);

        return START_STICKY; // Если сервис будет прерван устройством, то он будет ждать когда появится возможность востановить сервис

    }


    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onDestroy() {
        executorService.shutdownNow();
        Log.d(TAG, "onDestroy:");
    }
}
