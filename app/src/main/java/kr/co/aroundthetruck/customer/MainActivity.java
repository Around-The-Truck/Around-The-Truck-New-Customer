package kr.co.aroundthetruck.customer;
import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.os.StrictMode;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import org.apache.http.NameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.sql.RowId;
import java.util.ArrayList;


import kr.co.aroundthetruck.customer.layoutController.AroundTheTruckApplication;

import kr.co.aroundthetruck.customer.data.Samples;
import kr.co.aroundthetruck.customer.data.Truck;

import kr.co.aroundthetruck.customer.layoutController.LayoutMethod;

import kr.co.aroundthetruck.customer.network.HttpCommunication;


public class MainActivity extends Activity implements View.OnClickListener {

    ArrayList<Truck> truckList;
    Intent intent;
    String thisBrand; //넘어온 브랜드
    String thisTruckIdx;

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

        truckLike.setText("127명");
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
    public void jsonParser (String str) {

        JSONObject result = new JSONObject();
        try {
            result = new JSONObject(str);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        try {
            JSONArray jArr = result.getJSONArray("result");
            for (int i = 0; i < jArr.length(); i++) {
                JSONObject jObj = jArr.getJSONObject(i);

                Truck tempCVO = new Truck();

                truckList.add(tempCVO);
            }
        } catch (JSONException e) {
            Log.d("json exception", "error");
            Toast.makeText(MainActivity.this, e.getMessage(),
                    Toast.LENGTH_LONG).show();
            e.printStackTrace();
        }
    }
}
