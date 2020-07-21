package vladimir.exampleservise.services;

import android.annotation.SuppressLint;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.TextView;
import android.widget.Toast;

import java.lang.ref.WeakReference;

import vladimir.exampleservise.TempActivity;

public class SampleBroadcastReceiver extends BroadcastReceiver {

    public static final String SAMPLE_ACTION = "vladimir.exampleservise.services";
    private WeakReference<TextView> weakReference;

    public SampleBroadcastReceiver(TextView textView) {
        weakReference = new WeakReference<>(textView);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onReceive(Context context, Intent intent) {
        long count = intent.getLongExtra(CountService.TIME, 0);
        Toast.makeText(context, "Current time is " + count, Toast.LENGTH_SHORT).show();

        TextView textView = weakReference.get();
        StringBuilder stringBuilder = new StringBuilder(textView.getText().toString());
        stringBuilder.append(count).append("\n");
        textView.setText(stringBuilder.toString());

//        Intent newIntentSendTimeActivity = new Intent(context, TempActivity.class);
//        newIntentSendTimeActivity.putExtra(CountService.TIME, count);
//        context.startActivity(newIntentSendTimeActivity);


    }
}
