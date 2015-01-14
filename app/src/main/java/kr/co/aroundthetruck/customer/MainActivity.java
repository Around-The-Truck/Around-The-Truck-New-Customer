package kr.co.aroundthetruck.customer;
import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.StrictMode;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;


import kr.co.aroundthetruck.customer.layoutController.AroundTheTruckApplication;

import kr.co.aroundthetruck.customer.data.Truck;

import kr.co.aroundthetruck.customer.layoutController.LayoutMethod;

import kr.co.aroundthetruck.customer.network.HttpCommunication;


public class MainActivity extends Activity implements View.OnClickListener {

    Intent intent;
    String thisBrand; //넘어온 브랜드
    String thisTruckIdx;

    Truck truck;

    FragmentManager fragm;
    Bundle bundle;
    FragmentTransaction fragmentTransaction;

    android.app.Fragment fragment1;
    android.app.Fragment fragment2;

    ImageView truckImage;
    TextView truckName;
    TextView truckCate;
    TextView truckDis;
    TextView truckLike;

    Bitmap bitmapsp;

    String strColor = "#6d6d6d";
    String strColor2 = "#9a9a9a";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Log.d("onCreate()", "MainActivity");

        intent = getIntent();
        thisBrand = intent.getStringExtra("brandName");
        thisTruckIdx = intent.getStringExtra("brandIdx");


        // StrictMode (Thread Policy == All)
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder()
                .permitAll().build();
        StrictMode.setThreadPolicy(policy);

        // HTTP Connection
        HttpCommunication http = new HttpCommunication();
        String resStr = "";

        resStr = http.getTruckInfo(thisTruckIdx);

        parseJSON(resStr);

//        Log.d("ebsud", "fc : " + String.valueOf(truck.getFollow_count()));

        // create layout
        ImageView truckImage = (ImageView)findViewById(R.id.imageView);
        truckImage = (ImageView)findViewById(R.id.imageView);

        truckName = (TextView)findViewById(R.id.textView5);
        truckCate = (TextView)findViewById(R.id.textView6);
        truckDis = (TextView)findViewById(R.id.textView7);
        truckLike = (TextView)findViewById(R.id.textView8);

        bitmapsp = BitmapFactory.decodeResource(getResources(),R.drawable.bitmapsp);
        bitmapsp = LayoutMethod.getCircleBitmap(bitmapsp);

        truckImage.setImageBitmap(bitmapsp);

        truckName.setText(thisBrand);
        truckName.setTypeface(AroundTheTruckApplication.nanumGothicBold);
        truckName.setTextColor(Color.parseColor(strColor));

        truckCate.setText("양식/피자, 햄버거");
        truckCate.setTypeface(AroundTheTruckApplication.nanumGothic);
        truckCate.setTextColor(Color.parseColor(strColor));

        truckDis.setText("53m");
        truckDis.setTypeface(AroundTheTruckApplication.nanumGothic);
        truckDis.setTextColor(Color.parseColor(strColor2));

        truckLike.setText(String.valueOf(truck.getFollow_count()));
        truckLike.setTypeface(AroundTheTruckApplication.nanumGothic);
        truckLike.setTextColor(Color.parseColor(strColor2));

        ImageButton truckInfoBtn = (ImageButton) findViewById(R.id.truckinfobtn);
        ImageButton menuBtn = (ImageButton) findViewById(R.id.menubtn);
        ImageButton mapBtn = (ImageButton) findViewById(R.id.mapbtn);

        truckInfoBtn.setOnClickListener(this);
        menuBtn.setOnClickListener(this);
        mapBtn.setOnClickListener(this);

        getActionBar().setHomeButtonEnabled(true);

        getActionBar().setTitle("   " +thisBrand);

        onClick(truckInfoBtn);
    }

    @Override
    public void onClick(View view) {

        fragm = getFragmentManager();
        bundle = new Bundle();
        fragmentTransaction = fragm.beginTransaction();

        fragment1 = (android.app.Fragment) fragm.findFragmentById(R.id.fragment);
        fragment2 = (android.app.Fragment)fragm.findFragmentById(R.id.fragment_menu);

        switch (view.getId()) {
            case R.id.truckinfobtn:

                fragmentTransaction.show(fragment1);
                fragmentTransaction.hide(fragment2);
                break;

            case R.id.menubtn:

                fragmentTransaction.show(fragment2);
                fragmentTransaction.hide(fragment1);
                break;


            case R.id.mapbtn:

                Intent intent = new Intent(this, BottomInfo.class);   // main.java 파일에서 이벤트를 발생시켜서 test를 불러옵니다.
                intent.putExtra("brand", thisBrand);
                startActivity(intent);
                break;
        }
        fragmentTransaction.commit();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        MenuInflater inflater = getMenuInflater();
        getMenuInflater().inflate(R.menu.main_action, menu);
        return super.onCreateOptionsMenu(menu);
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle presses on the action bar items

        switch (item.getItemId()) {
            case android.R.id.home:
                //뒤로가기 버튼
                finish();
                return true;


            case R.id.mapbtn:
                //map버튼 눌렀을때 이벤트

                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }

    // HTTP return value (JSON) parse method
    public void parseJSON (String str) {


        try {
            JSONObject jsonObject = new JSONObject(str);
            JSONArray arr = new JSONArray(new String(jsonObject.getString("result")));
            for (int i=0 ; i<arr.length(); i++) {
                Log.d("ebsud", arr.getJSONObject(i).toString());
                truck = new Truck(arr.getJSONObject(i).getInt("idx"),
                        arr.getJSONObject(i).getString("name"),
                        arr.getJSONObject(i).getDouble("gps_longitude"),
                        arr.getJSONObject(i).getDouble("gps_latitude"),
                        arr.getJSONObject(i).getDouble("gps_altitude"),
                        arr.getJSONObject(i).getString("gps_address"),
                        arr.getJSONObject(i).getInt("todays_sum"),
                        arr.getJSONObject(i).getInt("follow_count"),
                        arr.getJSONObject(i).getInt("start_yn"),
                        arr.getJSONObject(i).getInt("takeout_yn"),
                        arr.getJSONObject(i).getInt("cansit_yn"),
                        arr.getJSONObject(i).getInt("card_yn"),
                        arr.getJSONObject(i).getInt("reserve_yn"),
                        arr.getJSONObject(i).getInt("group_order_yn"),
                        arr.getJSONObject(i).getInt("always_open_yn"),
                        arr.getJSONObject(i).getString("photo_filename"),
                        arr.getJSONObject(i).getString("main_position"),
                        arr.getJSONObject(i).getInt("category_id"),
                        arr.getJSONObject(i).getString("category_small"),
                        arr.getJSONObject(i).getString("reg_date")
                        );
//                brands.add(tmp);
            }

        } catch (Exception e) {
            Log.d("ebsud", "JSON error (MainActivity) : " + e);
            e.printStackTrace();
            ;
        }
    }
}
