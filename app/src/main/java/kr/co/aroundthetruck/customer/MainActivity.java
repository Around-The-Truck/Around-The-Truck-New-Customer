package kr.co.aroundthetruck.customer;
import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.Intent;
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

import org.apache.http.NameValuePair;

import java.sql.RowId;
import java.util.ArrayList;

import kr.co.aroundthetruck.customer.network.HttpCommunication;


public class MainActivity extends Activity implements View.OnClickListener {

    Intent intent;
    String thisBrand; //넘어온 브랜드

    FragmentManager fragm;
    Bundle bundle;
    FragmentTransaction fragmentTransaction;

    android.app.Fragment fragment1;
    android.app.Fragment fragment2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Log.d("onCreate()", "MainActivity");

        intent = getIntent();
        thisBrand = intent.getStringExtra("brand");


        // StrictMode (Thread Policy == All)
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder()
                .permitAll().build();
        StrictMode.setThreadPolicy(policy);

        // HTTP Connection
        HttpCommunication http = new HttpCommunication();
        String resStr = "";

        ImageView truckImage = (ImageView)findViewById(R.id.imageView);

        TextView truckName = (TextView)findViewById(R.id.textView5);
        TextView truckCate = (TextView)findViewById(R.id.textView6);
        TextView truckDis = (TextView)findViewById(R.id.textView7);
        TextView truckLike = (TextView)findViewById(R.id.textView8);

        truckName.setText(thisBrand);
        truckCate.setText("양식/피자, 햄버거");
        truckDis.setText("53m");
        truckLike.setText("127명");

        ImageButton truckInfoBtn = (ImageButton) findViewById(R.id.truckinfobtn);
        ImageButton menuBtn = (ImageButton) findViewById(R.id.menubtn);
        ImageButton mapBtn = (ImageButton) findViewById(R.id.mapbtn);

        truckInfoBtn.setOnClickListener(this);
        menuBtn.setOnClickListener(this);
        mapBtn.setOnClickListener(this);

        getActionBar().setDisplayHomeAsUpEnabled(true);
        getActionBar().setDisplayShowHomeEnabled(false);
        getActionBar().setTitle(thisBrand);

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

                //다른 화면 으로 넘어가게 map 아니라 정보 화면
                Intent intent = new Intent(this, BottomInfo.class);   // main.java 파일에서 이벤트를 발생시켜서 test를 불러옵니다.
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

}
