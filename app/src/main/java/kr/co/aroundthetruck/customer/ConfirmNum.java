package kr.co.aroundthetruck.customer;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RadioGroup;
import android.widget.Spinner;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.apache.http.Header;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.FileNotFoundException;

/**
 * Created by sumin on 2015-01-03.
 */
public class ConfirmNum extends Activity {


    String name, birth, gender, file,phone;
    EditText phoneEdit;
    ImageButton imageButton;
    boolean confirm = false;

    SharedPreferences prefs;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.confirmnum);

        prefs = getSharedPreferences("ATT", MODE_PRIVATE);

        phoneEdit = (EditText)findViewById(R.id.editText2);
        imageButton = (ImageButton)findViewById(R.id.imageButton3);

        Intent intent =getIntent();

        name = intent.getStringExtra("name");
        birth = intent.getStringExtra("birth");
        file = intent.getStringExtra("file");
        gender = intent.getStringExtra("gender");


        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //자기 폰번호 가져와서 일치하는지 검사, 01066970644

                if (phoneEdit.getText().toString().equals(getMyPhoneNum())){

                     phone = phoneEdit.getText().toString();
                    requestServer();
                }

                phone = phoneEdit.getText().toString();
                requestServer();


            }
        });


        getActionBar().setHomeButtonEnabled(true);

    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        MenuInflater inflater = getMenuInflater();
        getMenuInflater().inflate(R.menu.ok, menu);
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
            case R.id.next_button:

                if(confirm){

                    Log.d("confirm","true");
                Intent intent =  new Intent(ConfirmNum.this, StartActivity.class);
                intent.putExtra("CHEKEDUSER",true);// main.java 파일에서 이벤트를 발생시켜서 test를 불러옵니다.
                setMySharedPreferences("CHEKEDUSER",phone);
                startActivity(intent);

                }
                else {

                    //confirm된 유저가 아닙니다.
                    Intent intent =  new Intent(ConfirmNum.this, StartActivity.class);
                    intent.putExtra("CHEKEDUSER",false);// main.java 파일에서 이벤트를 발생시켜서 test를 불러옵니다.
                    setMySharedPreferences("CHEKEDUSER","NO");
                    startActivity(intent);

                }

                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public String getMyPhoneNum(){

        TelephonyManager telManager = (TelephonyManager)ConfirmNum.this.getSystemService(ConfirmNum.this.TELEPHONY_SERVICE);
        String phoneNum = telManager.getLine1Number();

        return phoneNum;

    }

    private void setMySharedPreferences(String _key, String _value) {
        if(prefs == null){
            prefs = getSharedPreferences("ATT", MODE_PRIVATE);
        }
        SharedPreferences.Editor editor = prefs.edit();
        editor.putString(_key, _value);
        editor.commit();
    }


    public void requestServer () {

        RequestParams params = new RequestParams();

        Log.d("test",file);

        params.put("userName", name);
        params.put("birth", birth);   //1992-10-22
        params.put("phone", phone); // 01066970644
        params.put("gender",gender);

        try {
            params.put("file",new File(file));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        Log.d("ssskjfjsd",name + birth + phone + gender + file);
        AsyncHttpClient client = new AsyncHttpClient();
        client.post("http://165.194.35.161:3000/join", params, new AsyncHttpResponseHandler() {

            @Override
            public void onSuccess(int i, Header[] headers, byte[] bytes) {
                String raw = new String(bytes);
                Log.d("whaaaaaaaaaaaaaaaaat",raw);

              //  "{"code":100}"

                confirm = true;

                try {
                    JSONObject data = new JSONObject(new String(bytes));
//                    if (data.getString())
                } catch (JSONException e){

                }


            }

            @Override
            public void onFailure(int i, Header[] headers, byte[] bytes, Throwable throwable) {



            }
        });

    }

}
