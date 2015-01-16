package kr.co.aroundthetruck.customer;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;


import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;
import com.squareup.picasso.Picasso;

import org.apache.http.Header;
import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONObject;
import org.w3c.dom.Text;


import java.text.ParseException;
import java.util.ArrayList;

import kr.co.aroundthetruck.customer.data.Customer;
import kr.co.aroundthetruck.customer.data.Point;

import kr.co.aroundthetruck.customer.layoutController.RoundedTransformation;
import kr.co.aroundthetruck.customer.network.HttpCommunication;

import kr.co.aroundthetruck.customer.layoutController.AroundTheTruckApplication;


/**
 * Created by sumin on 2014-12-20.
 */
public class MyPoint extends Activity implements TruckCallback {

    private ArrayList<Point> points;

    private ListView pointList;
    private TextView user,totalP,textview;

    String strColor = "#6d6d6d";
    String strColor2 = "#9a9a9a";

    private SharedPreferences prefs;

    String phoneNum;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.my_point);

        phoneNum = getMySharedPreferences("CHEKEDUSER");

        user = (TextView) findViewById(R.id.textView14);  //김희정
        totalP = (TextView) findViewById(R.id.textView2);  //1450
        textview =(TextView) findViewById(R.id.textView13);  //님의 현재 누적 포인트

        user.setTypeface(AroundTheTruckApplication.nanumGothicBold);
        totalP.setTypeface(AroundTheTruckApplication.nanumGothicBold);
        textview.setTypeface(AroundTheTruckApplication.nanumGothic);

        user.setTextColor(AroundTheTruckApplication.color6d);
        textview.setTextColor(AroundTheTruckApplication.color9a);

        pointList = (ListView) findViewById(R.id.listView2);


        String resStr = "";

        String writerType = "0";

        String url = "http://165.194.35.161:3000/getCustomerInfo";
        RequestParams param = new RequestParams();

        param.put("customerPhone",phoneNum);

        AsyncHttpClient client = new AsyncHttpClient();
        client.post(url, param, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int i, Header[] headers, byte[] bytes) {
                onTruckLoad2(bytes);
            }

            @Override
            public void onFailure(int i, Header[] headers, byte[] bytes, Throwable throwable) {

            }
        });

        getActionBar().setDisplayShowHomeEnabled(false);


    }

    public void setData(){



        pointList.setAdapter(new BrandAdapter(MyPoint.this, points));

    }

    private void parseJSON (String str) {

        points = new ArrayList<>();

        Point tmp = null;


        try {
            JSONObject jsonObject = new JSONObject(str);
            JSONArray arr = new JSONArray(new String(jsonObject.getString("result")));
            for (int i = 0; i < arr.length(); i++) {
                Log.d("ebsud", arr.getJSONObject(i).toString());
                tmp = new Point(arr.getJSONObject(i).getInt("sum"),
                        arr.getJSONObject(i).getString("date")
                );

                points.add(tmp);

            }


            }catch(Exception e){
                Log.d("ebsud", "JSON error (MyFoodTruck) : " + e);
                e.printStackTrace();

            }


        }

    private void parseJSON2 (String str) {


        try {
            JSONObject jsonObject = new JSONObject(str);
            JSONArray arr = new JSONArray(new String(jsonObject.getString("result")));
            for (int i = 0; i < arr.length(); i++) {
                Log.d("ebsud", arr.getJSONObject(i).toString());


                user.setText(arr.getJSONObject(i).getString("name"));
                totalP.setText( arr.getJSONObject(i).getString("point"));


            }

            HttpCommunication hc = new HttpCommunication();
            hc.getPointHistory(phoneNum, MyPoint.this);

        }catch(Exception e){
            Log.d("ebsud", "JSON error (MyFoodTruck) : " + e);
            e.printStackTrace();

        }


    }



    public class BrandAdapter extends BaseAdapter {
        Context mContext;
        ArrayList<Point> list;

        public BrandAdapter(Context context, ArrayList<Point> list) {
            super();
            this.mContext = context;
            this.list = list;
        }

        @Override
        public int getCount() {
            return list.size();
        }

        @Override
        public Object getItem(int pos) {
            return list.get(pos);
        }

        @Override
        public long getItemId(int i) {
            return i;
        }

        @Override
        public View getView(int pos, View convertView, ViewGroup parent) {

            ViewHolder holder;

            final Point mpoint = (Point) this.getItem(pos);

            if (convertView == null) {
                holder = new ViewHolder();

                convertView = LayoutInflater.from(mContext).inflate(R.layout.point_row, parent, false);

                holder.dayPoint = (TextView) convertView.findViewById(R.id.point);
                holder.date = (TextView) convertView.findViewById(R.id.pointdate);
                convertView.setTag(holder);
            } else {

                holder = (ViewHolder) convertView.getTag();

            }



            holder.dayPoint.setText(Integer.toString(mpoint.getPoint()));
            holder.dayPoint.setTypeface(AroundTheTruckApplication.nanumGothic);
           // holder.brandPoint.setTextColor(AroundTheTruckApplication.color6d);

            try {
                holder.date.setText(mpoint.getDate());
            } catch (ParseException e) {
                e.printStackTrace();
            }
            holder.date.setTypeface(AroundTheTruckApplication.nanumGothic);
            holder.date.setTextColor(AroundTheTruckApplication.color9a);

            return convertView;

        }

        private class ViewHolder {
            TextView dayPoint;
            TextView date;
        }

    }

    @Override
    public void onTruckLoad(byte[] bytes) {
        String raw = new String(bytes);

        parseJSON(raw);
    }

    public void onTruckLoad2(byte[] bytes) {
        String raw = new String(bytes);

        Log.d("sssssssssssssssssssss",raw);
        parseJSON2(raw);
    }

    private String getMySharedPreferences(String _key) {
        if(prefs == null){
            prefs = getSharedPreferences("ATT",MODE_PRIVATE);
        }
        return prefs.getString(_key, "NO");
    }


}
