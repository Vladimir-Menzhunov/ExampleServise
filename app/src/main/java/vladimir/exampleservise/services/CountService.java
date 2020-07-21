package vladimir.exampleservise.services;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.os.Build;
import android.os.IBinder;
import android.util.Log;
import androidx.annotation.Nullable;
import androidx.core.app.NotificationCompat;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import vladimir.exampleservise.R;
import vladimir.exampleservise.TempActivity;

public class CountService extends Service {

    public static final String TIME = "TIME";
    private final String TAG = this.getClass().getName();
    private ScheduledExecutorService executorService;
    private NotificationManager manager;
    private NotificationCompat.Builder mBuilder;

    public CountService() {
    }

    @Override
    public void onCreate() {
        Log.d(TAG, "onCreate:");

        executorService = Executors.newScheduledThreadPool(1);
        manager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        mBuilder = getNotificationBuilder();

        mBuilder.setContentTitle("Count service notification")
                .setSmallIcon(R.drawable.ic_launcher_foreground);

    }

    private NotificationCompat.Builder getNotificationBuilder() {
        if(Build.VERSION.SDK_INT < Build.VERSION_CODES.O) {
            return new NotificationCompat.Builder(this);
        } else {
            String channelId = "my_channel_id";

            if(manager.getNotificationChannel(channelId) == null) {
                NotificationChannel channel = new NotificationChannel(channelId, "Text for user", NotificationManager.IMPORTANCE_LOW);
                manager.createNotificationChannel(channel);
            }

            return new NotificationCompat.Builder(this, channelId);
        }
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.d(TAG, "onStartCommand:");

        //startForeground(123, getNotification("start notification")); // краш, пока не понял почему

        executorService.scheduleAtFixedRate(new Runnable() {

            private long currentTimeMillis;

            @Override
            public void run() {
                currentTimeMillis = System.currentTimeMillis();
                Log.d(TAG, "currentSecond: " + currentTimeMillis);

                manager.notify(123, getNotification("Time is: " + currentTimeMillis));

                Intent intentToSend = new Intent(SampleBroadcastReceiver.SAMPLE_ACTION);
                intentToSend.putExtra(TIME, currentTimeMillis);
                sendBroadcast(intentToSend);
            }
        },0, 4, TimeUnit.SECONDS);

        return START_STICKY; // Если сервис будет прерван устройством, то он будет ждать когда появится возможность востановить сервис

    }

    private Notification getNotification(String contentText) {
        Intent intent = new Intent(this, TempActivity.class);
        intent.putExtra(TIME, contentText);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 1234, intent, PendingIntent.FLAG_UPDATE_CURRENT);



        return mBuilder.setContentText(contentText)
                .setContentIntent(pendingIntent)
                .build();
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
