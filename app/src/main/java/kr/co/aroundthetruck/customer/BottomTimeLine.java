package kr.co.aroundthetruck.customer;


import android.app.Fragment;
import android.content.Context;
import android.media.Image;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

import kr.co.aroundthetruck.customer.data.Article;

/**
 * Created by sumin on 2014-12-01.
 */
public class BottomTimeLine extends Fragment {
    int mStart = 0;

    public static BottomTimeLine newInstance(int start){
        BottomTimeLine cf = new BottomTimeLine();
        cf.mStart = start;
        return cf;
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle SavedInstanceState){
        View view = inflater.inflate(R.layout.bottom_timeline, null);
        ListView lv = (ListView)view.findViewById(R.id.listView);
        ArrayList<Article> articles= new ArrayList<Article>();
        MyArticlesAdapter adapter = new MyArticlesAdapter(view.getContext(),articles);
        lv.setAdapter(adapter);

        return view;

    }

    public void onSaveInstanceState(Bundle outState){
        super.onSaveInstanceState(outState);

    }

    public class MyArticlesAdapter extends BaseAdapter {
        Context mContext;
        ArrayList<Article> list;

        public MyArticlesAdapter(Context context, ArrayList<Article> list) {
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
                convertView = LayoutInflater.from(mContext).inflate(R.layout.article_row, parent, false);
                holder.articleImage = (ImageView) convertView.findViewById(R.id.imageView2);
                holder.articleName = (TextView) convertView.findViewById(R.id.textView3);
                holder.articlelist= (ListView) convertView.findViewById(R.id.listView4);
                holder.like= (ImageButton) convertView.findViewById(R.id.article_like);
                holder.ok= (ImageButton) convertView.findViewById(R.id.article_ok);
                convertView.setTag(holder);
            }else{

                holder = (ViewHolder) convertView.getTag();

            }
            //holder.articleImage.setImageResource(list.get(pos).getmenuImage());
            //holder.articleName.setText(list.get(pos).getmenuName());
            //holder.articlelist.set(list.get(pos).getmenuPrice());

            return convertView;

        }

        private  class ViewHolder
        {
            ImageView articleImage;
            TextView articleName;
            ListView articlelist;
            ImageButton ok;
            ImageButton like;
        }

    }

}
