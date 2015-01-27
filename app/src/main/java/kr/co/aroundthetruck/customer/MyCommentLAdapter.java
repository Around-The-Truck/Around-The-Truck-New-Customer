package kr.co.aroundthetruck.customer;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import kr.co.aroundthetruck.customer.data.Reply;
import kr.co.aroundthetruck.customer.layoutController.AroundTheTruckApplication;
import kr.co.aroundthetruck.customer.layoutController.RoundedTransformation;

/**
 * Created by sumin on 2014-12-03.
 */
class MyCommentLAdapter extends BaseAdapter {
    Context mContext;
    ArrayList<Reply> list;

    String strColor = "#6d6d6d";

    public MyCommentLAdapter(Context context, ArrayList<Reply> list) {
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
            convertView = LayoutInflater.from(mContext).inflate(R.layout.comment_row, parent, false);
            holder.userImg = (ImageView) convertView.findViewById(R.id.userImage);
            holder.userName = (TextView) convertView.findViewById(R.id.userName);
            holder.userComment= (TextView) convertView.findViewById(R.id.userComment);
            convertView.setTag(holder);
        }else{

            holder = (ViewHolder) convertView.getTag();

        }
        //holder.userImg .setImageResource(list.get(pos).getUserImage());
        holder.userName.setText(list.get(pos).getR_writer_name());
        holder.userName.setTypeface(AroundTheTruckApplication.nanumGothicBold);
        holder.userName.setTextColor(Color.parseColor(strColor));
        holder.userComment.setText(list.get(pos).getR_contents());
        holder.userComment.setTypeface(AroundTheTruckApplication.nanumGothic);
        holder.userComment.setTextColor(Color.parseColor(strColor));

        // writer_image
        Picasso.with(mContext).load("http://165.194.35.161:3000/upload/" + list.get(pos).getR_writer_filename()).fit().transform(new RoundedTransformation(300)).into(holder.userImg);


        return convertView;

    }

    private static class ViewHolder
    {
        ImageView userImg;
        TextView userName;
        TextView userComment;
    }

}
