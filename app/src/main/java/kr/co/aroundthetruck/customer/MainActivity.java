package kr.co.aroundthetruck.customer;

import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONObject;

import java.net.URLEncoder;

import kr.co.aroundthetruck.customer.data.Truck;
import kr.co.aroundthetruck.customer.layoutController.AroundTheTruckApplication;
import kr.co.aroundthetruck.customer.layoutController.LayoutMethod;
import kr.co.aroundthetruck.customer.layoutController.RoundedTransformation;
import kr.co.aroundthetruck.customer.network.HttpCommunication;


public class MainActivity extends Activity implements View.OnClickListener, TruckCallback {

    ImageButton truckInfoBtn;
    ImageButton menuBtn;
    ImageButton mapBtn;


    Intent intent;
    String thisBrand; //넘어온 브랜드
    String thisTruckIdx;

    Truck truck;

    FragmentManager fragm;
    Bundle bundle;
    FragmentTransaction fragmentTransaction;

    BottomTimeLine fragmentBottomTimeLine;
    BottomMenu fragmentBottomMenu;

    ImageView truckImage;
    TextView truckName;
    TextView truckCate;
    TextView truckDis;
    TextView truckLike;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Log.d("onCreate()", "MainActivity");


        // StrictMode (Thread Policy == All)
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder()
                .permitAll().build();
        StrictMode.setThreadPolicy(policy);

        // HTTP Connection
        HttpCommunication http = new HttpCommunication();
        String resStr = "";

        fragm = getFragmentManager();
        bundle = new Bundle();
        fragmentTransaction = fragm.beginTransaction();

        fragmentBottomTimeLine = BottomTimeLine.newInstance();
        fragmentBottomMenu = BottomMenu.newInstance();

        thisTruckIdx = getIntent().getStringExtra("brandIdx");
        thisBrand = getIntent().getStringExtra("brandName");


        http.getTruckInfo(thisTruckIdx, MainActivity.this);

        // create layout
        LinearLayout ly = (LinearLayout) findViewById(R.id.layout_back);
        ly.setBackgroundResource(R.drawable.back);

        truckImage = (ImageView) findViewById(R.id.imageView);

        truckName = (TextView) findViewById(R.id.textView5);
        truckCate = (TextView) findViewById(R.id.textView6);
        truckDis = (TextView) findViewById(R.id.textView7);
        truckLike = (TextView) findViewById(R.id.textView8);

        truckInfoBtn = (ImageButton) findViewById(R.id.truckinfobtn);
        menuBtn = (ImageButton) findViewById(R.id.menubtn);
        mapBtn = (ImageButton) findViewById(R.id.mapbtn);

        truckInfoBtn.setOnClickListener(this);
        menuBtn.setOnClickListener(this);
        mapBtn.setOnClickListener(this);

        onClick(truckInfoBtn);

        getActionBar().setHomeButtonEnabled(true);
        getActionBar().setTitle("   " + thisBrand);

    }

    @Override
    public void onClick(View view) {

        FragmentTransaction ft = getFragmentManager().beginTransaction();

        switch (view.getId()) {
            case R.id.truckinfobtn:
                ft.show(fragmentBottomTimeLine);
                ft.hide(fragmentBottomMenu);
                break;

            case R.id.menubtn:
                ft.show(fragmentBottomMenu);
                ft.hide(fragmentBottomTimeLine);
                break;


            case R.id.mapbtn:

                Intent intent = new Intent(this, BottomInfo.class);   // main.java 파일에서 이벤트를 발생시켜서 test를 불러옵니다.
                intent.putExtra("brand", thisBrand);
                intent.putExtra("brandIdx", thisTruckIdx);
                startActivity(intent);
                break;
        }
        ft.commit();
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
    public void parseJSON(String str) {


        try {
            JSONObject jsonObject = new JSONObject(str);
            JSONArray arr = new JSONArray(new String(jsonObject.getString("result")));
            for (int i = 0; i < arr.length(); i++) {
                Log.d("ebsud", arr.getJSONObject(i).toString());
                truck = new Truck(

                        arr.getJSONObject(i).getInt("idx"),
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
                        URLEncoder.encode(arr.getJSONObject(i).getString("photo_filename"), "UTF-8"),
                        arr.getJSONObject(i).getString("main_position"),
                        arr.getJSONObject(i).getString("cat_name_big")+" / "+arr.getJSONObject(i).getString("cat_name_small"),
                        arr.getJSONObject(i).getString("reg_date")
                );



                fragmentBottomTimeLine.setTruck(truck);
                fragmentBottomMenu.setTruck(truck);

                getFragmentManager().beginTransaction()
                        .add(R.id.fragment, fragmentBottomTimeLine)
                        .add(R.id.fragment_menu, fragmentBottomMenu)
                        .commit();

                Picasso.with(this).load("http://165.194.35.161:3000/upload/" + truck.getPhoto_id()).fit().transform(new RoundedTransformation(440)).into(truckImage);

                truckName.setText(thisBrand);
                truckName.setTypeface(AroundTheTruckApplication.nanumGothicBold);
                truckName.setTextColor(Color.WHITE);

                truckCate.setText(truck.getCategory());
                truckCate.setTypeface(AroundTheTruckApplication.nanumGothic);
                truckCate.setTextColor(Color.WHITE);

                truckDis.setText("53m");
                truckDis.setTypeface(AroundTheTruckApplication.nanumGothic);
                truckDis.setTextColor(Color.WHITE);

                truckLike.setText(String.valueOf(truck.getFollow_count()));
                truckLike.setTypeface(AroundTheTruckApplication.nanumGothic);
                truckLike.setTextColor(Color.WHITE);

                onClick(truckInfoBtn);
            }

        } catch (Exception e) {
            Log.d("ebsud", "JSON error (MainActivity) : " + e);
            e.printStackTrace();
            ;
        }
    }

    @Override
    public void onTruckLoad(byte[] bytes) {
        String raw = new String(bytes);

        parseJSON(raw);
    }


}
