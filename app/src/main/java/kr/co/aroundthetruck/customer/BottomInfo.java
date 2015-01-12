package kr.co.aroundthetruck.customer;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import kr.co.aroundthetruck.customer.layoutController.AroundTheTruckApplication;
import kr.co.aroundthetruck.customer.layoutController.LayoutMethod;

public class BottomInfo extends Activity {

    Intent intent;
    String thisBrand;
    String strColor = "#6d6d6d";
    Bitmap bitmapsp;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.bottom_info);

        ImageView brandImage = (ImageView)findViewById(R.id.imageView3);

        bitmapsp = BitmapFactory.decodeResource(getResources(), R.drawable.bitmapsp);
        bitmapsp = LayoutMethod.getCircleBitmap(bitmapsp);

        brandImage.setImageBitmap(bitmapsp);

        TextView bandName = (TextView)findViewById(R.id.phone);
        TextView phoneNumber = (TextView)findViewById(R.id.brand);
        TextView openDate = (TextView)findViewById(R.id.textView);

        intent = getIntent();
        thisBrand = intent.getStringExtra("brand");

        bandName.setText(thisBrand);
        bandName.setTypeface(AroundTheTruckApplication.nanumGothicBold);
        bandName.setTextColor(Color.parseColor(strColor));

        phoneNumber.setText("010-1234-5678");
        phoneNumber.setTypeface(AroundTheTruckApplication.nanumGothic);
        phoneNumber.setTextColor(Color.parseColor(strColor));

        openDate.setText("이태원 경리단길");
        openDate.setTypeface(AroundTheTruckApplication.nanumGothic);
        openDate.setTextColor(Color.parseColor(strColor));

        ImageView item1 = (ImageView)findViewById(R.id.imageView4);
        ImageView item2 = (ImageView)findViewById(R.id.imageView5);
        ImageView item3 = (ImageView)findViewById(R.id.imageView6);
        ImageView item4 = (ImageView)findViewById(R.id.imageView7);
        ImageView item5 = (ImageView)findViewById(R.id.imageView8);
        ImageView item6 = (ImageView)findViewById(R.id.imageView9);


        getActionBar().setHomeButtonEnabled(true);

    }


        @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle presses on the action bar items

        switch (item.getItemId()) {
            case android.R.id.home:
//                Intent intent =  new Intent(BottomInfo.this,MainActivity.class);
//                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
//                startActivity(intent);
                finish();

            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
