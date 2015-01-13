package kr.co.aroundthetruck.customer;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListAdapter;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by sumin on 2014-12-20.
 */
public class MyInfo extends Activity {

    String[] truckArea = {"학생, 직장인", "주부"};

    ImageButton image;
    TextView nameTextView;
    EditText name;
    EditText birth;
    RadioGroup radio;
    Boolean sex;
    Button loginBtn;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.my_info);

        getActionBar().setDisplayShowHomeEnabled(false);

        image = (ImageButton)findViewById(R.id.imageView11);
        name = (EditText)findViewById(R.id.m_name);
        birth = (EditText)findViewById(R.id.m_birth);

        radio = (RadioGroup)findViewById(R.id.m_sex);

        radio.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                if(radio.getCheckedRadioButtonId() == R.id.m_sex1)
                    sex = true;
                //남자 선택된 경우

                if(radio.getCheckedRadioButtonId() == R.id.m_sex2)
                    sex = false; //여자 선택된 경우
            }

        });

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        MenuInflater inflater = getMenuInflater();
        getMenuInflater().inflate(R.menu.profile, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle presses on the action bar items

        switch (item.getItemId()) {
            case R.id.next_button:

               Intent intent =  new Intent(MyInfo.this, ConfirmNum.class); // main.java 파일에서 이벤트를 발생시켜서 test를 불러옵니다.
                startActivity(intent);
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }


}
