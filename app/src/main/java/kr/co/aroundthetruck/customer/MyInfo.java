package kr.co.aroundthetruck.customer;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.Editable;
import android.util.Log;
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

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;
import com.squareup.picasso.Picasso;

import org.apache.http.Header;

import java.io.File;

import kr.co.aroundthetruck.customer.layoutController.AroundTheTruckApplication;
import kr.co.aroundthetruck.customer.layoutController.RoundedTransformation;

/**
 * Created by sumin on 2014-12-20.
 */
public class MyInfo extends Activity {

    String[] truckArea = {"학생, 직장인", "주부"};

    ImageButton image;
    TextView nameTextView, birthText, sexText;
    EditText name;
    EditText birth;
    RadioGroup radio;
    String sex;
    Button loginBtn;

    String file;

    final int REQUEST_IMAGE = 10;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.my_info);

        getActionBar().setDisplayShowHomeEnabled(false);


        SharedPreferences prefs = getSharedPreferences("ATT", MODE_PRIVATE);


        nameTextView = (TextView)findViewById(R.id.lala1);
        birthText = (TextView)findViewById(R.id.lala2);
        sexText = (TextView)findViewById(R.id.lala3);

        nameTextView.setTypeface(AroundTheTruckApplication.nanumGothic);
        birthText.setTypeface(AroundTheTruckApplication.nanumGothic);
        sexText.setTypeface(AroundTheTruckApplication.nanumGothic);


        image = (ImageButton)findViewById(R.id.imageView11);
        name = (EditText)findViewById(R.id.m_name);
        birth = (EditText)findViewById(R.id.m_birth);

        radio = (RadioGroup)findViewById(R.id.m_sex);

        image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(Intent.ACTION_PICK,
                        android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                MyInfo.this.startActivityForResult(intent, REQUEST_IMAGE);

            }
        });

        radio.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                if(radio.getCheckedRadioButtonId() == R.id.m_sex1)
                    sex = "1";
                //남자 선택된 경우

                if(radio.getCheckedRadioButtonId() == R.id.m_sex2)
                    sex = "0"; //여자 선택된 경우
            }

        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        // TODO Auto-generated method stub

        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == REQUEST_IMAGE && resultCode == RESULT_OK && data != null) {
            Log.d("YoonTag", "====== OnActivityResult is start ========= \n");

              Uri selPhotoUri = data.getData();
              file = getRealPathFromURI(this,selPhotoUri);

            try {

                Picasso.with(MyInfo.this)
                        .load(selPhotoUri)
                        .skipMemoryCache().fit()
                        .transform(new RoundedTransformation(211))
                        .into(image);


                // sangho
                //  fullPath = getRealPathFromURI(this, selPhotoUri);
            } catch (Exception e) {
                // TODO Auto-generated catch block
                e.printStackTrace();

            }

        }

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
                Intent intent =  new Intent(MyInfo.this, ConfirmNum.class);
                intent.putExtra("name",name.getText().toString());
                intent.putExtra("birth", birth.getText().toString());
                intent.putExtra("gender",sex);
                intent.putExtra("file",file);

                Log.d("test",name.getText().toString() + birth.getText().toString() + sex);

                startActivity(intent);
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public String getRealPathFromURI(Context context, Uri contentUri) {
        Cursor cursor = null;
        try {
            String[] proj = {MediaStore.Images.Media.DATA};
            cursor = context.getContentResolver().query(contentUri, proj, null, null, null);
            int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
            cursor.moveToFirst();
            return cursor.getString(column_index);
        } finally {
            if (cursor != null) {
                cursor.close();
            }
        }
    }


}
