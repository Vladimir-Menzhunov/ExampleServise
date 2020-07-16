package vladimir.exampleservise.services;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import vladimir.exampleservise.TempActivity;

public class SampleBroadcastReceiver extends BroadcastReceiver {

    public static final String SAMPLE_ACTION = "vladimir.exampleservise.services";

    @Override
    public void onReceive(Context context, Intent intent) {
        long count = intent.getLongExtra(CountService.TIME, 0);
        Toast.makeText(context, "Current time is " + count, Toast.LENGTH_SHORT).show();

        Intent newIntentSendTimeActivity = new Intent(context, TempActivity.class);
        newIntentSendTimeActivity.putExtra(CountService.TIME, count);

        context.startActivity(newIntentSendTimeActivity);


    }
}
