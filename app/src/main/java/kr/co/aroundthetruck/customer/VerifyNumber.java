package kr.co.aroundthetruck.customer;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

/**
 * Created by sumin on 2014-12-25.
 */
public class VerifyNumber extends Activity{

    TextView phoneNumber;
    Button verifyBtn;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.verify_number);

        phoneNumber = (TextView)findViewById(R.id.m_phone);
        verifyBtn = (Button)findViewById(R.id.verifyBtn);

        verifyBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //인증하기 버튼 리스너
                Intent intent =  new Intent(VerifyNumber.this,MyInfo.class); // main.java 파일에서 이벤트를 발생시켜서 test를 불러옵니다.
                startActivity(intent);
            }
        });


    }
}
