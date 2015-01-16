package kr.co.aroundthetruck.customer;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONObject;

import kr.co.aroundthetruck.customer.layoutController.AroundTheTruckApplication;
import kr.co.aroundthetruck.customer.network.HttpCommunication;

/**
 * Created by sumin on 2014-12-20.
 */
public class MyProfile extends Activity implements TruckCallback {


    ImageButton image;
    TextView nameTextView, birthText, sexText;
    TextView name;
    TextView birth;
    TextView psex;

    Boolean sex;
    Button loginBtn;

    HttpCommunication http;

    SharedPreferences prefs;
    String myPhone;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.my_profile);

        http = new HttpCommunication();

        myPhone = getMySharedPreferences("CHEKEDUSER");

        Log.d("모니",myPhone);

        http.getCustomerInfo(myPhone, MyProfile.this);



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

    }

    @Override
    public void onTruckLoad(byte[] bytes) {
        String raw = new String(bytes);
        Log.d("ebsud", "raw (BrandList) : " + raw);
        parseJSON(raw);
    }

    private String getMySharedPreferences(String _key) {
        if(prefs == null){
            prefs = getSharedPreferences("ATT",MODE_PRIVATE);
        }

        return prefs.getString(_key, "");
    }

    private void parseJSON (String str) {


        try {
            JSONObject jsonObject = new JSONObject(str);
            JSONArray arr = new JSONArray(new String(jsonObject.getString("result")));
            for (int i = 0; i < arr.length(); i++) {
                Log.d("ebsud", arr.getJSONObject(i).toString());


                //birth.setText(arr.getJSONObject(i).getString("name"));
                //totalP.setText( arr.getJSONObject(i).getString("point"));

                name.setText(arr.getJSONObject(i).getString("name"));
                birth.setText(Integer.toString(arr.getJSONObject(i).getInt("age")));
                int g = arr.getJSONObject(i).getInt("age");

                if(g == 0) psex.setText("여자"); else { psex.setText("남자");}


            }


        } catch (Exception e) {
            Log.d("ebsud", "JSON error (MyFoodTruck) : " + e);
            e.printStackTrace();

        }

    }

}
