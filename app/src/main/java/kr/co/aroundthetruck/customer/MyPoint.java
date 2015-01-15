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

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

import kr.co.aroundthetruck.customer.data.Point;
import kr.co.aroundthetruck.customer.network.HttpCommunication;

/**
 * Created by sumin on 2014-12-20.
 */
public class MyPoint extends Activity {

    private ArrayList<Point> points;

    private ListView pointList;
    private TextView currebtP;
    private ArrayList<Point> pointdata;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.my_point);

        currebtP = (TextView) findViewById(R.id.textView2);
        pointList = (ListView) findViewById(R.id.listView2);
        SharedPreferences prefs = getSharedPreferences("ATT", MODE_PRIVATE);

        // StrictMode (Thread Policy == All)
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder()
                .permitAll().build();
        StrictMode.setThreadPolicy(policy);

        // HTTP Connection
        HttpCommunication http = new HttpCommunication();
        String resStr = "";

        resStr = http.getFollowList(prefs.getString("phoneNum", null));
        Log.d("ebsud", "resStr (MyPoint) :" + resStr);

        // parsing TruckList
//        parseJSON(resStr);
//        pointdata.add(new Point("테스트1", ""))

        pointList.setAdapter(new BrandAdapter(MyPoint.this, pointdata));


    }

    private void parseJSON (String str) {

        points = new ArrayList<Point>();

        try {
            JSONObject jsonObject = new JSONObject(str);
            JSONArray arr = new JSONArray(new String(jsonObject.getString("result")));
//            for (int i=0 ; i<arr.length(); i++) {
//                Log.d("ebsud", arr.getJSONObject(i).toString());
//                tmp = new Brand(arr.getJSONObject(i).getInt("idx"),
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

            //holder.brandImage .setImageResource(mbrand.getBrandImage());
            holder.brandName.setText(mpoint.getBrand());
            holder.brandPoint.setText(Integer.toString(mpoint.getMpoint()));
            holder.date.setText(mpoint.getDate());

            return convertView;

        }

        private class ViewHolder {
            ImageView brandImage;
            TextView brandName;
            TextView brandPoint;
            TextView date;
        }

    }

}
