package kr.co.aroundthetruck.customer.location;

/**
 * Created by ebsud89 on 12/20/14.
 */

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.IBinder;
import android.text.format.Formatter;
import android.util.Log;

import kr.co.aroundthetruck.customer.network.HttpCommunication;


public class LocationService extends Service {
    public LocationManager locationManager;
    public MyLocationListenr listener;
    public Location previousBestLoaction = null;

    private double myKyungdo = 0;
    private double myWedo = 0;

    private SharedPreferences prefs;
    private Editor editor;


    @Override
    public void onCreate() {
        // TODO Auto-generated method stub

        Log.d("Testing", "Location Service got created");
        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        listener = new MyLocationListenr();
        prefs = getSharedPreferences("ATT", MODE_PRIVATE);
        editor = prefs.edit();
        super.onCreate();
    }

    // 일정 시간마다 호출되는 서비스
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        // TODO Auto-generated method stub

        // Send to Server. 현재 사용자의 위치 정보
        Log.d("ebsud", "location - service - Location Service Running");

        locationManager.requestLocationUpdates(
                LocationManager.NETWORK_PROVIDER, 500, 0, listener);

        // For Communication
        HttpCommunication http = new HttpCommunication();
        ArrayList<NameValuePair> nvpList = new ArrayList<NameValuePair>();
        String resultStr = "";

        // Get User ID
        String UID = prefs.getString("id", "DEFAULT_ID");

        NumberFormat formatter = new DecimalFormat("#0.000000");

        editor.putString("Latitude", formatter.format(myWedo));
        editor.putString("Longtitude", formatter.format(myKyungdo));
        editor.commit();

//        nvpList.add( new BasicNameValuePair("UID", UID));
//        nvpList.add( new BasicNameValuePair("Latitude", formatter.format(myWedo)));
//        nvpList.add( new BasicNameValuePair("Longtitude",formatter.format(myKyungdo)));
//        resultStr = http.doPost(nvpList, "user/registerPoint");

//        Log.d("LocationService", resultStr);

        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO Auto-generated method stub
        return null;
    }

    public class MyLocationListenr implements LocationListener {

        @Override
        public void onLocationChanged(Location location) {
            // TODO Auto-generated method stub
            Log.i("*********IN LOCATION SERVICE************",
                    "Location change listener");

            myKyungdo =  location.getLongitude();
            myWedo = location.getLatitude();

            NumberFormat formatter = new DecimalFormat("#0.000000");
            Log.d("service_kyungdo",formatter.format(myKyungdo));
            Log.d("service_wedo",formatter.format(myWedo));
        }

        @Override
        public void onProviderDisabled(String provider) {
            // TODO Auto-generated method stub

        }

        @Override
        public void onProviderEnabled(String provider) {
            // TODO Auto-generated method stub

        }

        @Override
        public void onStatusChanged(String provider, int status, Bundle extras) {
            // TODO Auto-generated method stub

        }

    }



}

