package kr.co.aroundthetruck.customer;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;
import android.view.KeyEvent;
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

import java.net.URLEncoder;
import java.util.ArrayList;

import kr.co.aroundthetruck.customer.data.Point;
import kr.co.aroundthetruck.customer.layoutController.AroundTheTruckApplication;
import kr.co.aroundthetruck.customer.layoutController.RoundedTransformation;
import kr.co.aroundthetruck.customer.network.HttpCommunication;

/**
 * Created by sumin on 2014-12-20.
 */
public class MyFoodTruck extends Activity implements TruckCallback{

    private ListView brandList;
    private ArrayList<Brand> brands;
    private String phoneNum;

    private SharedPreferences prefs;

    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.my_foodtruck);

        getActionBar().setDisplayShowHomeEnabled(false);

        phoneNum = getMySharedPreferences("CHEKEDUSER");

        brandList = (ListView)findViewById(R.id.listView3);

        SharedPreferences prefs = getSharedPreferences("ATT", MODE_PRIVATE);

        // StrictMode (Thread Policy == All)
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder()
                .permitAll().build();
        StrictMode.setThreadPolicy(policy);

        // HTTP Connection
        HttpCommunication http = new HttpCommunication();

        //http.getFollowList(prefs.getString("phoneNum", null), MyFoodTruck.this);
        http.getFollowList(phoneNum, MyFoodTruck.this);


    }

    private void parseJSON (String str) {

        brands = new ArrayList<Brand>();
        Brand tmp = null;

        try {
            JSONObject jsonObject = new JSONObject(str);
            JSONArray arr = new JSONArray(new String(jsonObject.getString("result")));
            for (int i=0 ; i<arr.length(); i++) {
//                Log.d("ebsud", arr.getJSONObject(i).toString());
                tmp = new Brand(arr.getJSONObject(i).getInt("idx"),
                                URLEncoder.encode(arr.getJSONObject(i).getString("filename"), "UTF-8").replaceAll("\\+", "%20"),
                                arr.getJSONObject(i).getString("name"),
                                "50m",
                                arr.getJSONObject(i).getInt("follow_count"),
                                arr.getJSONObject(i).getString("cat_name_big"),arr.getJSONObject(i).getString("cat_name_small"),false,1);
                brands.add(tmp);
            }

            brandList.setAdapter(new BrandAdapter(MyFoodTruck.this, brands));

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
                holder.fdis = (TextView) convertView.findViewById(R.id.fdis);

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

            holder.fdis.setTypeface(AroundTheTruckApplication.nanumGothic);
            holder.fdis.setTextColor(AroundTheTruckApplication.color9a);

            Picasso.with(MyFoodTruck.this).load("http://165.194.35.161:3000/upload/" + mbrand.getBrandImage()).fit().transform(new RoundedTransformation(300)).into(holder.brandImage);

            holder.fbrand.setText(mbrand.getBrandName());
            holder.flike.setText(Integer.toString(mbrand.getLike()));
            holder.fcate.setText(mbrand.getCategory());
            holder.fdis.setText(mbrand.getBrandDistance());

            return convertView;

        }

        private class ViewHolder
        {
            ImageView brandImage;
            TextView fbrand;
            TextView flike;
            TextView fcate;
            TextView fdis;

        }

    }


    @Override
    public void onTruckLoad(byte[] bytes) {
        String raw = new String(bytes);
        Log.d("ebsud", "myfoodtruck - callback : " + raw);
        parseJSON(raw);
    }

    private String getMySharedPreferences(String _key) {
        if(prefs == null){
            prefs = getSharedPreferences("ATT",MODE_PRIVATE);
        }
        return prefs.getString(_key, "NO");
    }

}
