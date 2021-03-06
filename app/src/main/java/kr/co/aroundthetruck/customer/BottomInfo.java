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

import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONObject;

import java.net.URLEncoder;
import java.util.ArrayList;

import kr.co.aroundthetruck.customer.data.Truck;
import kr.co.aroundthetruck.customer.layoutController.AroundTheTruckApplication;
import kr.co.aroundthetruck.customer.layoutController.LayoutMethod;
import kr.co.aroundthetruck.customer.layoutController.RoundedTransformation;
import kr.co.aroundthetruck.customer.network.HttpCommunication;

public class BottomInfo extends Activity implements TruckCallback {

    Intent intent;
    String strColor = "#6d6d6d";
    Bitmap bitmapsp;

    String thisTruckIdx;
    String thisBrand;

    Truck truck;

    ImageView item1,item2,item3,item4,item5,item6;
    TextView bandName,phoneNumber,openDate;

    ImageView brandImage;

    ArrayList<ImageView> imageViews;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.bottom_info);

        thisTruckIdx = getIntent().getStringExtra("brandIdx");
        thisBrand = getIntent().getStringExtra("brand");

        HttpCommunication hc = new HttpCommunication();
        hc.getTruckInfo(thisTruckIdx,BottomInfo.this);

        brandImage = (ImageView)findViewById(R.id.imageView3);

        bandName = (TextView)findViewById(R.id.phone);
        phoneNumber = (TextView)findViewById(R.id.brand);
        openDate = (TextView)findViewById(R.id.textView);



        bandName.setText(thisBrand);
        bandName.setTypeface(AroundTheTruckApplication.nanumGothicBold);
        bandName.setTextColor(Color.parseColor(strColor));


        imageViews = new ArrayList<>();

        item1 = (ImageView)findViewById(R.id.imageView4); imageViews.add(item1);
        item2 = (ImageView)findViewById(R.id.imageView5); imageViews.add(item2);
        item3 = (ImageView)findViewById(R.id.imageView6); imageViews.add(item3);
        item4 = (ImageView)findViewById(R.id.imageView7); imageViews.add(item4);
        item5 = (ImageView)findViewById(R.id.imageView8); imageViews.add(item5);
        item6 = (ImageView)findViewById(R.id.imageView9); imageViews.add(item6);





        getActionBar().setHomeButtonEnabled(true);

    }

    public void setData(){

        phoneNumber.setText(truck.getPhone_num());
        phoneNumber.setTypeface(AroundTheTruckApplication.nanumGothic);
        phoneNumber.setTextColor(Color.parseColor(strColor));

        openDate.setText(truck.getGps_address());
        openDate.setTypeface(AroundTheTruckApplication.nanumGothic);
        openDate.setTextColor(Color.parseColor(strColor));

        Picasso.with(BottomInfo.this).load("http://165.194.35.161:3000/upload/" + truck.getPhoto_id()).fit().transform(new RoundedTransformation(300)).into(brandImage);


    }


        @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle presses on the action bar items

        switch (item.getItemId()) {
            case android.R.id.home:
                Intent intent =  new Intent(BottomInfo.this,MainActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                intent.putExtra("thisTruckIdx", thisTruckIdx);
                startActivity(intent);
                finish();

            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void parseJSON (String str) {

        try {
            JSONObject jsonObject = new JSONObject(str);
            JSONArray arr = new JSONArray(new String(jsonObject.getString("result")));
            for (int i=0 ; i<arr.length(); i++) {
                Log.d("ebsud", arr.getJSONObject(i).toString());
                truck = new Truck(

                        arr.getJSONObject(i).getInt("idx"),
                        arr.getJSONObject(i).getString("name"),
                        arr.getJSONObject(i).getString("phone_num"),
                        arr.getJSONObject(i).getDouble("gps_longitude"),
                        arr.getJSONObject(i).getDouble("gps_latitude"),
                        arr.getJSONObject(i).getDouble("gps_altitude"),
                        arr.getJSONObject(i).getString("gps_address"),
                        arr.getJSONObject(i).getInt("todays_sum"),
                        arr.getJSONObject(i).getInt("follow_count"),
                        arr.getJSONObject(i).getInt("start_yn"),
                        arr.getJSONObject(i).getInt("takeout_yn"),
                        arr.getJSONObject(i).getInt("cansit_yn"),
                        arr.getJSONObject(i).getInt("card_yn"),
                        arr.getJSONObject(i).getInt("reserve_yn"),
                        arr.getJSONObject(i).getInt("group_order_yn"),
                        arr.getJSONObject(i).getInt("always_open_yn"),
                        URLEncoder.encode(arr.getJSONObject(i).getString("photo_filename"), "UTF-8").replaceAll("\\+", "%20"),
                        arr.getJSONObject(i).getString("main_position"),
                        arr.getJSONObject(i).getString("cat_name_big")+" / "+arr.getJSONObject(i).getString("cat_name_small"),
                        arr.getJSONObject(i).getString("reg_date")

                );

                //0false

                int count = 0;

                if(truck.getCard_yn() == 1){imageViews.get(count).setImageResource(R.drawable.card); count ++;}
                if(truck.getCansit_yn() == 1){imageViews.get(count).setImageResource(R.drawable.seat); count ++;}
                if(truck.getAlways_open_yn() == 1){imageViews.get(count).setImageResource(R.drawable.open); count++;}
                if(truck.getTakeout_yn() == 1){imageViews.get(count).setImageResource(R.drawable.take); count++;}
                if(truck.getGroup_order_yn() == 1){imageViews.get(count).setImageResource(R.drawable.group);count++;}
                if(truck.getReserve_yn() == 1 ){imageViews.get(count).setImageResource(R.drawable.reser); count++;}

                setData();



            }



        } catch (Exception e) {
            Log.d("ebsud", "JSON error (BrandList) : " + e);
            e.printStackTrace();

        }


    }


    @Override
    public void onTruckLoad(byte[] bytes) {
        String raw = new String(bytes);

        parseJSON(raw);
    }

}
