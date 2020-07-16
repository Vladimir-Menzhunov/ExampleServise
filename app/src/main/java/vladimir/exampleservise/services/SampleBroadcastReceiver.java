package vladimir.exampleservise.services;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

public class SampleBroadcastReceiver extends BroadcastReceiver {

    public static final String SAMPLE_ACTION = "vladimir.exampleservise.services";

    @Override
    public void onReceive(Context context, Intent intent) {
        Toast.makeText(context, intent.getAction(), Toast.LENGTH_SHORT).show();

    }
}
