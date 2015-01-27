package kr.co.aroundthetruck.customer;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONObject;

import java.net.URLEncoder;

import kr.co.aroundthetruck.customer.data.Truck;
import kr.co.aroundthetruck.customer.layoutController.AroundTheTruckApplication;
import kr.co.aroundthetruck.customer.layoutController.RoundedTransformation;
import kr.co.aroundthetruck.customer.network.HttpCommunication;

/**
 * Created by sumin on 2015-01-17.
 */
public class TruckMap extends Activity {

    GoogleMap googleMap;
    String truckName;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.map);

        truckName = getIntent().getStringExtra("brandName");
        Double brandLatitude = getIntent().getDoubleExtra("brandLatitude",1);
        Double brandLongitude = getIntent().getDoubleExtra("brandLongitude",1);


        createMapView();
        addMarker(brandLatitude,brandLongitude);



    }
    private void createMapView(){
        /**
         * Catch the null pointer exception that
         * may be thrown when initialising the map
         */
        try {
            if(null == googleMap){
                googleMap = ((MapFragment) getFragmentManager().findFragmentById(
                        R.id.mapView)).getMap();

                /**
                 * If the map is still null after attempted initialisation,
                 * show an error to the user
                 */
                if(null == googleMap) {
                    Toast.makeText(getApplicationContext(),
                            "Error creating map", Toast.LENGTH_SHORT).show();
                }
            }
        } catch (NullPointerException exception){
            Log.e("mapApp", exception.toString());
        }
    }

    private void addMarker(double brandLatitude, double brandLongitude){

        /** Make sure that the map has been initialised **/
        if(null != googleMap){
            // setting defalut mapzoom?
            googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(brandLatitude, brandLongitude
            ), 14.0f));
            googleMap.addMarker(new MarkerOptions()
                            .position(new LatLng(brandLatitude,brandLongitude))
                            .title(truckName)
                            .draggable(true)

            );
        }
    }


}
