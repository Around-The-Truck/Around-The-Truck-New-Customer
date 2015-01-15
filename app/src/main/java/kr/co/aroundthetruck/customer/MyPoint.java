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


import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONObject;
import org.w3c.dom.Text;


import java.util.ArrayList;

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
    private ArrayList<Point> pointdata;

    String strColor = "#6d6d6d";
    String strColor2 = "#9a9a9a";

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.my_point);

        user = (TextView) findViewById(R.id.textView14);  //김희정
        totalP = (TextView) findViewById(R.id.textView2);  //1450
        textview =(TextView) findViewById(R.id.textView13);  //님의 현재 누적 포인트

        user.setTypeface(AroundTheTruckApplication.nanumGothicBold);
        totalP.setTypeface(AroundTheTruckApplication.nanumGothicBold);
        textview.setTypeface(AroundTheTruckApplication.nanumGothic);

        user.setTextColor(AroundTheTruckApplication.color6d);
        textview.setTextColor(AroundTheTruckApplication.color9a);

        pointList = (ListView) findViewById(R.id.listView2);
        pointdata = new ArrayList<Point>();

        setData();


        pointList.setAdapter(new BrandAdapter(MyPoint.this, pointdata));

        getActionBar().setDisplayShowHomeEnabled(false);


    }

    public void setData(){

        user.setText("김희정");
        totalP.setText("1450");
        pointdata.add(new Point("수민카페",200,"최종방문일 2015.01.15"));

    }

    private void parseJSON (String str) {

        points = new ArrayList<Point>();

        Point tmp = null;

        try {
            JSONObject jsonObject = new JSONObject(str);
            JSONArray arr = new JSONArray(new String(jsonObject.getString("result")));
//                for (int i=0 ; i<arr.length(); i++) {
//                Log.d("ebsud", arr.getJSONObject(i).toString());
//                tmp = new Point(arr.getJSONObject(i).getInt("idx"),
//                        arr.getJSONObject(i).getString("filename"),
//                        arr.getJSONObject(i).getString("name"),
//                        "50m",
//                        arr.getJSONObject(i).getInt("follow_count"),
//                        arr.getJSONObject(i).getString("cat_name_big"),
//                        arr.getJSONObject(i).getString("cat_name_small")
//                );
//                brands.add(tmp);
//            }

        } catch (Exception e) {
            Log.d("ebsud", "JSON error (MyFoodTruck) : " + e);
            e.printStackTrace();
            ;
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

                holder.brandImage = (ImageView) convertView.findViewById(R.id.imageView12);
                holder.brandName = (TextView) convertView.findViewById(R.id.pointbrand);
                holder.brandPoint = (TextView) convertView.findViewById(R.id.point);
                holder.date = (TextView) convertView.findViewById(R.id.pointdate);
                convertView.setTag(holder);
            } else {

                holder = (ViewHolder) convertView.getTag();

            }

            Picasso.with(MyPoint.this).load("http://165.194.35.161:3000/upload/" + mpoint.getBrand_image()).fit().transform(new RoundedTransformation(207)).into(holder.brandImage);

            holder.brandName.setText(mpoint.getBrand());
            holder.brandName.setTypeface(AroundTheTruckApplication.nanumGothicBold);
            holder.brandName.setTextColor(AroundTheTruckApplication.color6d);

            holder.brandPoint.setText(Integer.toString(mpoint.getMpoint()));
            holder.brandPoint.setTypeface(AroundTheTruckApplication.nanumGothic);
           // holder.brandPoint.setTextColor(AroundTheTruckApplication.color6d);

            holder.date.setText(mpoint.getDate());
            holder.date.setTypeface(AroundTheTruckApplication.nanumGothic);
            holder.date.setTextColor(AroundTheTruckApplication.color9a);

            return convertView;

        }

        private class ViewHolder {
            ImageView brandImage;
            TextView brandName;
            TextView brandPoint;
            TextView date;
        }

    }

    @Override
    public void onTruckLoad(byte[] bytes) {
        String raw = new String(bytes);

        parseJSON(raw);
    }


}
