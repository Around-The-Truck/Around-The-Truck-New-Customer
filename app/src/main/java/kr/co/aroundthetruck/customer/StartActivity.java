package kr.co.aroundthetruck.customer;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * Created by sumin on 2014-12-20.
 */
public class StartActivity extends Activity {

    private SharedPreferences prefs;
    Editor editor;
    Boolean checkedUser = true;
    //회원가입한 유저인지 아닌지

    String usrPhone;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.start_activity);

        ActionBar actionBar = getActionBar();
        actionBar.hide();

        ImageButton startBtn = (ImageButton)findViewById(R.id.imageButton2);
        TextView welcomeMsg = (TextView)findViewById(R.id.textView4);


        checkedUser = checkUser();
        if(!checkedUser){
            //회원가입 안 된 유저의 경우의 레이아웃
            //버튼 배경 '시작하기'로 주기
            startBtn.setBackgroundResource(R.drawable.start_find);

        }else {
            //버튼 배경 '푸트트럭 찾기'
            startBtn.setBackgroundResource(R.drawable.start_find);

            welcomeMsg.setText("오수민 환영");
        }

        startBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent;

                if(!checkedUser){
                    //로그인화면으로 다음 화면으로 넘기기
                    intent =  new Intent(StartActivity.this,MyInfo.class); // main.java 파일에서 이벤트를 발생시켜서 test를 불러옵니다.
                    startActivity(intent);

                }else {
                    //3번 화면으로 다음 화면 넘기기
                    intent =  new Intent(StartActivity.this,BrandListActivity.class); // main.java 파일에서 이벤트를 발생시켜서 test를 불러옵니다.
                    startActivity(intent);
                }
            }
        });

    }

    private Boolean checkUser() {

        prefs = getSharedPreferences("ATT", MODE_PRIVATE);
        editor = prefs.edit();

        if (prefs.getString("cusPhone", "0").length() == 0)
            return true;

        return false;
    }
}
