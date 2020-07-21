package vladimir.exampleservise.services;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import vladimir.exampleservise.MainActivity;

public class StaticReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        Intent launchIntent = new Intent(context, MainActivity.class);
        launchIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        context.startActivity(launchIntent);
    }
}
