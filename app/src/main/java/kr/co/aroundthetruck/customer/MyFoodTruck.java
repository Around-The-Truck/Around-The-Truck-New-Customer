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
import kr.co.aroundthetruck.customer.layoutController.AroundTheTruckApplication;
import kr.co.aroundthetruck.customer.network.HttpCommunication;

/**
 * Created by sumin on 2014-12-20.
 */
public class MyFoodTruck extends Activity {

    private ListView brandList;
    private ArrayList<Brand> brands;
    private String phoneNum;

    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.my_foodtruck);

        getActionBar().setDisplayShowHomeEnabled(false);

        brandList = (ListView)findViewById(R.id.listView3);

        SharedPreferences prefs = getSharedPreferences("ATT", MODE_PRIVATE);

        // StrictMode (Thread Policy == All)
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder()
                .permitAll().build();
        StrictMode.setThreadPolicy(policy);

        // HTTP Connection
        HttpCommunication http = new HttpCommunication();
        String resStr = "";

        resStr = http.getFollowList(prefs.getString("phoneNum", null));
        Log.d("ebsud", "resStr :" + resStr);

        // parsing TruckList
        parseJSON(resStr);

        brandList.setAdapter(new BrandAdapter(MyFoodTruck.this, brands));

    }

    private void parseJSON (String str) {

        brands = new ArrayList<Brand>();
        Brand tmp = null;

        try {
            JSONObject jsonObject = new JSONObject(str);
            JSONArray arr = new JSONArray(new String(jsonObject.getString("result")));
            for (int i=0 ; i<arr.length(); i++) {
                Log.d("ebsud", arr.getJSONObject(i).toString());
                tmp = new Brand(arr.getJSONObject(i).getInt("idx"), arr.getJSONObject(i).getString("filename"), arr.getJSONObject(i).getString("name"), "50m", arr.getJSONObject(i).getInt("follow_count"), arr.getJSONObject(i).getString("cat_name_big"), arr.getJSONObject(i).getString("cat_name_small"));
                brands.add(tmp);
            }

        } catch (Exception e) {
            Log.d("ebsud", "JSON error (MyFoodTruck) : " + e);
            e.printStackTrace();
            ;
        }


    }

    public class BrandAdapter extends BaseAdapter {
        Context mContext;
        ArrayList<Brand> list;

        public BrandAdapter(Context context, ArrayList<Brand> list) {
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

            final Brand mbrand = (Brand) this.getItem(pos);

            if (convertView == null) {
                holder = new ViewHolder();

                convertView = LayoutInflater.from(mContext).inflate(R.layout.follow_row, parent, false);

                holder.brandImage = (ImageView) convertView.findViewById(R.id.fimage);
                holder.fbrand = (TextView) convertView.findViewById(R.id.fbrand);
                holder.flike= (TextView) convertView.findViewById(R.id.flike);
                holder.fcate= (TextView) convertView.findViewById(R.id.fcate);

                convertView.setTag(holder);
            }else{

                holder = (ViewHolder) convertView.getTag();

            }

            holder.fbrand.setTypeface(AroundTheTruckApplication.nanumGothicBold);
            holder.fbrand.setTextColor(AroundTheTruckApplication.color6d);

            holder.fcate.setTypeface(AroundTheTruckApplication.nanumGothic);
            holder.fcate.setTextColor(AroundTheTruckApplication.color9a);

            holder.flike.setTypeface(AroundTheTruckApplication.nanumGothic);
            holder.flike.setTextColor(AroundTheTruckApplication.color9a);

            //holder.brandImage .setImageResource(mbrand.getBrandImage());
            holder.fbrand.setText(mbrand.getBrandName());
            holder.flike.setText(Integer.toString(mbrand.getLike()));
            holder.fcate.setText(mbrand.getCategory());

            return convertView;

        }

        private class ViewHolder
        {
            ImageView brandImage;
            TextView fbrand;
            TextView flike;
            TextView fcate;

        }

    }
}
