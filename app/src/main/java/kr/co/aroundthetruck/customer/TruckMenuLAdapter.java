package kr.co.aroundthetruck.customer;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import kr.co.aroundthetruck.customer.data.Menu;
import kr.co.aroundthetruck.customer.layoutController.AroundTheTruckApplication;

/**
 * Created by sumin on 2014-12-03.
 */
public class TruckMenuLAdapter extends BaseAdapter {
    Context mContext;
    ArrayList<Menu> list;
    String strColor = "#6d6d6d";

    public TruckMenuLAdapter(Context context, ArrayList<Menu> list) {
        this.mContext = context;
        this.list = list;
    }

    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        // TODO Auto-generated method stub
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        // TODO Auto-generated method stub
        return position;
    }

    @Override
    public View getView(int pos, View convertView, ViewGroup parent) {

        ViewHolder holder;

        if (convertView == null) {
            holder = new ViewHolder();
            convertView = LayoutInflater.from(mContext).inflate(R.layout.menu_row, parent, false);
            holder.menuImg = (ImageView) convertView.findViewById(R.id.menuImg);
            holder.menuName = (TextView) convertView.findViewById(R.id.menuName);
            holder.menuPrice= (TextView) convertView.findViewById(R.id.menuPrice);
            convertView.setTag(holder);
        }else{

            holder = (ViewHolder) convertView.getTag();

        }
        //holder.menuImg .setImageResource(list.get(pos).);
        holder.menuName.setText(list.get(pos).getName());
        holder.menuName.setTypeface(AroundTheTruckApplication.nanumGothicBold);
        holder.menuName.setTextColor(Color.parseColor(strColor));

        holder.menuPrice.setText(Integer.toString(list.get(pos).getPrice()));
        holder.menuPrice.setTypeface(AroundTheTruckApplication.nanumGothic);
        holder.menuPrice.setTextColor(Color.parseColor(strColor));
        return convertView;

    }

    private static class ViewHolder
    {
        ImageView menuImg;
        TextView menuName;
        TextView menuPrice;
    }

}

