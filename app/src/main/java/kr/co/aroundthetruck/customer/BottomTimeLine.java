package kr.co.aroundthetruck.customer;


import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

import kr.co.aroundthetruck.customer.data.Reply;
import kr.co.aroundthetruck.customer.data.Article;

/**
 * Created by sumin on 2014-12-01.
 */
public class BottomTimeLine extends Fragment {
    int mStart = 0;
    EditText et;
    ImageButton ib;


    public static BottomTimeLine newInstance(int start){
        BottomTimeLine cf = new BottomTimeLine();
        cf.mStart = start;
        return cf;
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle SavedInstanceState){
        View view = inflater.inflate(R.layout.bottom_timeline, null);
        ListView lv = (ListView)view.findViewById(R.id.listView);


        ArrayList<Article> Articles = new ArrayList<Article>();
        Articles.add(new Article(0,11111,"sumin",1,"수민이가 쓴글","수민's truck","0103"));
        Articles.add(new Article(1,11111,"sumin2",1,"수민이2가 쓴글","수민's truck","0103"));
        MyArticlesAdapter adapter = new MyArticlesAdapter(view.getContext(), Articles);

        lv.setAdapter(adapter);

        et = (EditText)view.findViewById(R.id.editText);
        ib = (ImageButton)view.findViewById(R.id.buttons);

        ib.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //댓글 올리는 버튼을 눌렀을때
                et.setVisibility(View.GONE);
                ib.setVisibility(View.GONE);
            }
        });

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
                holder.ok= (ImageButton) convertView.findViewById(R.id.article_ok); //댓글 다는 버튼
                convertView.setTag(holder);
            }else{

                holder = (ViewHolder) convertView.getTag();

            }
            //holder.articleImage.setImageResource(list.get(pos).getmenuImage());
            holder.articleName.setText(list.get(pos).getWriter());  //타임라인 글 쓴사람

            ArrayList<Reply> replies= new ArrayList<Reply>(); //댓글들
            replies.add(new Reply(0,"트럭좋아요","댓글쓴 사람 이름",0,list.get(pos).getIdx(),"1003"));
            replies.add(new Reply(1,"트럭싫어요","댓글쓴 사람 이름2",0,list.get(pos).getIdx(),"1003"));//5번째 칼럼 맞는 인덱스 지정

            holder.articlelist.setAdapter(new MyCommentLAdapter(this.mContext,replies));

            holder.ok.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                        //댓글쓰기 버튼 눌렀을때
                        et.setVisibility(View.VISIBLE);
                        ib.setVisibility(View.VISIBLE);

                }
            });

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
