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
public class MyPoint extends Activity {

    private ListView pointList;
    private TextView currebtP;
    private ArrayList<Point> pointdata;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.my_point);

        currebtP = (TextView) findViewById(R.id.TextView02);
        pointList = (ListView) findViewById(R.id.listView2);

        pointdata = new ArrayList<Point>();
        pointdata.add(new Point("수민카페",200,"11/25"));
        pointList.setAdapter(new BrandAdapter(MyPoint.this, pointdata));


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
