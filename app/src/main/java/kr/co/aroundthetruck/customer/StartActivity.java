package kr.co.aroundthetruck.customer;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.graphics.Typeface;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import kr.co.aroundthetruck.customer.data.GPS;

/**
 * Created by sumin on 2014-12-20.
 */
public class StartActivity extends Activity {

    private SharedPreferences prefs;
    Editor editor;
    Boolean checkedUser = false;
    //회원가입한 유저인지 아닌지
    Intent intent;
    String usrPhone;

    private LocationManager mLocationManager;
    private String locationProvider = null;
    private LocationListener locationListener = null;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.start_activity);

        ActionBar actionBar = getActionBar();
        actionBar.hide();

        checkedUser = checkUser();

        editor = prefs.edit();
//
//        editor.putString("phoneNum", "01033400551");
//        editor.putString("cusName", "김희정");
//        editor.putString("cusLongitude", "");
//        editor.putString("cusLatitude", "");
//        editor.putString("cusAge", "24");
//        editor.putString("photo", "C360_2013-10-07-18-34-46-468_2.jpg");


        // get GPS
        GPS gps = new GPS(this);

        if(gps.canGetLocation()){
            gps.showSettingsAlert();
        }
//
//        editor.putString("latitude", Double.toString(gps.getLatitude()));
//        editor.putString("longitude", Double.toString(gps.getLongitude()));
//
//        editor.commit();
//
//        Log.d("GPS", "GPS long : " + Double.toString(gps.getLongitude()) + " / la : " + Double.toString(g));

        // create View

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);

        ImageButton startBtn = (ImageButton)findViewById(R.id.imageButton2);
        TextView welcomeMsg = (TextView)findViewById(R.id.textView4);
        TextView welcomeMsg2 = (TextView)findViewById(R.id.textView12);


        if(checkedUser){
            //회원가입 안 된 유저의 경우의 레이아웃
            //버튼 배경 '시작하기'로 주기
            startBtn.setImageResource(R.drawable.start_bt);

        }else {
            //버튼 배경 '푸트트럭 찾기'
            startBtn.setImageResource(R.drawable.finding);

            welcomeMsg.setText(prefs.getString("cusName", null)+" 님");
            welcomeMsg2.setText("지금 근처에 있는 푸드트럭을 찾아보세요!");
            welcomeMsg.setTypeface(Typeface.createFromAsset(getAssets(), "NanumBarunGothicBold.otf"));
            welcomeMsg2.setTypeface(Typeface.createFromAsset(getAssets(), "NanumBarunGothic.otf"));

        }

        startBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent;

                if(checkedUser){
                    //3번 화면으로 다음 화면 넘기기
                    intent =  new Intent(StartActivity.this,BrandListActivity.class); // main.java 파일에서 이벤트를 발생시켜서 test를 불러옵니다.
                    startActivity(intent);

                }else {

                    //로그인화면으로 다음 화면으로 넘기기
                    intent =  new Intent(StartActivity.this,MyInfo.class); // main.java 파일에서 이벤트를 발생시켜서 test를 불러옵니다.
                    startActivity(intent);
                }
            }
        });

    }

    private Boolean checkUser() {


        getMySharedPreferences("CHEKEDUSER");
        //editor = prefs.edit();

        intent = getIntent();  //전화번호 인증후 ConfirmNum 둘중 하나만 해도 되겠다

        Log.d("startActivity로 넘어온값",prefs.getString("CHEKEDUSER", "0"));

       // setMySharedPreferences("CHEKEDUSER","01066970644");

        if (prefs.getString("CHEKEDUSER", "0").equals("NO"))
                return false;

        else {
                    return true;}
    }

    private void getMySharedPreferences(String _key) {
        if(prefs == null){
            prefs = getSharedPreferences("ATT",MODE_PRIVATE);
        }
        prefs.getString(_key, "");
    }

//    private void setMySharedPreferences(String _key, String _value) {
//        if(prefs == null){
//            prefs = getSharedPreferences("ATT", MODE_PRIVATE);
//        }
//        SharedPreferences.Editor editor = prefs.edit();
//        editor.putString(_key, _value);
//        editor.commit();
//    }


}
