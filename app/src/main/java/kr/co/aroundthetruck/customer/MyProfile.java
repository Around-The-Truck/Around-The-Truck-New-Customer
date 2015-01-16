package kr.co.aroundthetruck.customer;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import kr.co.aroundthetruck.customer.layoutController.AroundTheTruckApplication;

/**
 * Created by sumin on 2014-12-20.
 */
public class MyProfile extends Activity {

    String[] truckArea = {"학생, 직장인", "주부"};

    ImageButton image;
    TextView nameTextView, birthText, sexText;
    TextView name;
    TextView birth;
    TextView psex;

    Boolean sex;
    Button loginBtn;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.my_profile);

        getActionBar().setDisplayShowHomeEnabled(false);

        nameTextView = (TextView)findViewById(R.id.lala1);
        birthText = (TextView)findViewById(R.id.lala2);
        sexText = (TextView)findViewById(R.id.lala3);

        nameTextView.setTypeface(AroundTheTruckApplication.nanumGothicBold);
        birthText.setTypeface(AroundTheTruckApplication.nanumGothicBold);
        sexText.setTypeface(AroundTheTruckApplication.nanumGothicBold);

        nameTextView.setTextColor(AroundTheTruckApplication.color6d);
        birthText.setTextColor(AroundTheTruckApplication.color6d);
        sexText.setTextColor(AroundTheTruckApplication.color6d);


        image = (ImageButton)findViewById(R.id.imageView11);
        name = (TextView)findViewById(R.id.p_name);
        birth = (TextView)findViewById(R.id.p_birth);
        psex = (TextView)findViewById(R.id.p_sex);

        name.setTypeface(AroundTheTruckApplication.nanumGothic);
        birth.setTypeface(AroundTheTruckApplication.nanumGothic);
        psex.setTypeface(AroundTheTruckApplication.nanumGothic);

        name.setTextColor(AroundTheTruckApplication.color6d);
        birth.setTextColor(AroundTheTruckApplication.color6d);
        psex.setTextColor(AroundTheTruckApplication.color6d);

        name.setText("김희정");
        birth.setText("1992.10.22");
        psex.setText("여자");

    }

}
