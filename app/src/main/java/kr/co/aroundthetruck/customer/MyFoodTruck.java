package kr.co.aroundthetruck.customer;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

import kr.co.aroundthetruck.customer.data.Point;

/**
 * Created by sumin on 2014-12-20.
 */
public class MyFoodTruck extends Activity {

    private ListView brandList;
    private ArrayList<Brand> brandData;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.my_foodtruck);

        brandList = (ListView)findViewById(R.id.listView3);

        brandData = new ArrayList<Brand>();
        brandData.add(new Brand(1, "수민카페", "s", 2, "s"));
        brandList.setAdapter(new BrandAdapter(MyFoodTruck.this, brandData));




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

                holder.brandImage = (ImageView) convertView.findViewById(R.id.brandimage);
                holder.fbrand = (TextView) convertView.findViewById(R.id.fbrand);
                holder.flike= (TextView) convertView.findViewById(R.id.flike);
                holder.fcate= (TextView) convertView.findViewById(R.id.fcate);

                convertView.setTag(holder);
            }else{

                holder = (ViewHolder) convertView.getTag();

            }

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
