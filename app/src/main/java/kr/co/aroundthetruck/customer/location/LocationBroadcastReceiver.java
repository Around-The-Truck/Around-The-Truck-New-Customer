package kr.co.aroundthetruck.customer.location;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
/**
 * Created by ebsud89 on 12/20/14.
 */
public class LocationBroadcastReceiver extends BroadcastReceiver {


    @Override
    public void onReceive(Context context, Intent intent) {
        // TODO Auto-generated method stub

        Log.d("BroadcastReceiver Test","Location Boot Complete Recive");
        Intent serviceIntent = new Intent(context, LocationService.class);
        context.startService(serviceIntent);

    }

}
