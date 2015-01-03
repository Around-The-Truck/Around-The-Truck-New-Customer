package kr.co.aroundthetruck.customer;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RadioGroup;
import android.widget.Spinner;

/**
 * Created by sumin on 2015-01-03.
 */
public class ConfirmNum extends Activity {

    String[] truckArea = {"학생, 직장인", "주부"};

    ImageButton image;
    EditText name;
    EditText birth;
    RadioGroup radio;
    Boolean sex;
    Spinner job;
    Button loginBtn;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.confirmnum);

        image = (ImageButton) findViewById(R.id.imageView11);
        name = (EditText) findViewById(R.id.m_name);
        birth = (EditText) findViewById(R.id.m_birth);

        radio = (RadioGroup) findViewById(R.id.m_sex);


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

                Intent intent =  new Intent(ConfirmNum.this, BrandListActivity.class); // main.java 파일에서 이벤트를 발생시켜서 test를 불러옵니다.
                startActivity(intent);
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }


}
