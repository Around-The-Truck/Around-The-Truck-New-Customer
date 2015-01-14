package kr.co.aroundthetruck.customer;


import android.app.Fragment;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.Typeface;
import android.location.GpsStatus;
import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

import kr.co.aroundthetruck.customer.data.Reply;
import kr.co.aroundthetruck.customer.data.Article;
import kr.co.aroundthetruck.customer.layoutController.AroundTheTruckApplication;
import kr.co.aroundthetruck.customer.layoutController.LayoutMethod;
import kr.co.aroundthetruck.customer.network.HttpCommunication;

/**
 * Created by sumin on 2014-12-01.
 */
public class BottomTimeLine extends Fragment {
    int mStart = 0;
    String thisTruckIdx;
    SharedPreferences prefs;

    ListView lv;
    MyArticlesAdapter adapter;

    String strColor = "#6d6d6d";
    String strColor2 = "#9a9a9a";

    ArrayList<Article> articles = new ArrayList<Article>();

    public static BottomTimeLine newInstance(int start){
        BottomTimeLine cf = new BottomTimeLine();
        cf.mStart = start;
        return cf;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // StrictMode (Thread Policy == All)
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder()
                .permitAll().build();
        StrictMode.setThreadPolicy(policy);

//        thisTruckIdx = prefs.getString("truckIdx", null);
////        thisTruckIdx = getArguments().getString("truckIdx");
//

        // HTTP Connection
        HttpCommunication http = new HttpCommunication();
        String resStr = "";

        resStr = http.getArticlList(thisTruckIdx);


        Log.d("ebsud", "resStr (TimeLine) : " + resStr);

        parseJSON(resStr);


    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle SavedInstanceState){
        View view = inflater.inflate(R.layout.bottom_timeline, null);
        lv = (ListView)view.findViewById(R.id.listView);


//        Articles.add(new Article(0,11111,"Milano Express",1,"수민이가 쓴글","수민's truck","1시간전",10,11));
//        Articles.add(new Article(1,11111,"Milano Express2",1,"수민이2가 쓴글","수민's truck","2시간전",10,11));
        adapter = new MyArticlesAdapter(view.getContext(), articles);

        lv.setAdapter(adapter);


        LayoutMethod.setListViewHeight(lv);



        return view;

    }

    private void parseJSON(String resStr) {

        Article tmp = null;

        try {
            JSONObject jsonObject = new JSONObject(resStr);
            JSONArray arr = new JSONArray(new String(jsonObject.getString("result")));
            for (int i=0 ; i<arr.length(); i++) {
                Log.d("ebsud", arr.getJSONObject(i).toString());
                tmp = new Article(arr.getJSONObject(i).getInt("idx"),
                        arr.getJSONObject(i).getString("filename"),
                        arr.getJSONObject(i).getString("writer"),
                        arr.getJSONObject(i).getInt("writer_type"),
                        arr.getJSONObject(i).getString("contents"),
                        arr.getJSONObject(i).getInt("like"),
                        arr.getJSONObject(i).getString("belong_to"),
                        arr.getJSONObject(i).getString("reg_date")
                );
                articles.add(tmp);
            }

        } catch (Exception e) {
            Log.d("ebsud", "JSON error (MainActivity) : " + e);
            e.printStackTrace();
            ;
        }
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

            final ViewHolder holder;
            final ArrayList<Reply> replies;

            if (convertView == null) {
                holder = new ViewHolder();
                convertView = LayoutInflater.from(mContext).inflate(R.layout.article_row, parent, false);

                holder.brandImage = (ImageView) convertView.findViewById(R.id.imageView2);
                holder.brandName = (TextView) convertView.findViewById(R.id.textView3);
                holder.articleTime = (TextView)convertView.findViewById(R.id.textView9);

               holder.articleImage = (ImageView) convertView.findViewById(R.id.imageView18);

                holder.likeNumber = (TextView) convertView.findViewById(R.id.textView10);
                holder.replyNumber = (TextView) convertView.findViewById(R.id.textView11);

                holder.articlelist= (ListView) convertView.findViewById(R.id.listView4);

                holder.like= (ImageButton) convertView.findViewById(R.id.article_like);
                holder.ok= (ImageButton) convertView.findViewById(R.id.article_ok); //댓글 다는 버튼

                holder.et = (EditText)convertView.findViewById(R.id.editText);
                holder.ib = (ImageButton)convertView.findViewById(R.id.buttons);


                convertView.setTag(holder);
            }else{

                holder = (ViewHolder) convertView.getTag();

            }
            //holder.brandImage.setImageResource(list.get(pos).getmenuImage());
            holder.brandName.setText(list.get(pos).getWriter());  //타임라인 글 쓴사람
            holder.brandName.setTypeface(AroundTheTruckApplication.nanumGothicBold);
            //holder.articleImage.setImageResource(list.get(pos).getWriter());
            holder.brandName.setTextColor(Color.parseColor(strColor));

            holder.articleTime.setTypeface(AroundTheTruckApplication.nanumGothic);
            holder.articleTime.setTextColor(Color.parseColor(strColor2));

            holder.likeNumber.setText(Integer.toString(list.get(pos).getLikeNumber()) + "명");
            holder.likeNumber.setTypeface(AroundTheTruckApplication.nanumGothic);
            holder.likeNumber.setTextColor(Color.parseColor(strColor));

            holder.replyNumber.setText(Integer.toString(list.get(pos).getReplyNumber()) + "명");
            holder.replyNumber.setTypeface(AroundTheTruckApplication.nanumGothic);
            holder.replyNumber.setTextColor(Color.parseColor(strColor));


            replies= new ArrayList<Reply>(); //댓글들
            replies.add(new Reply(0,"트럭좋아요","댓글쓴 사람 이름",0,list.get(pos).getIdx(),"1003"));
            replies.add(new Reply(1,"트럭싫어요","댓글쓴 사람 이름2",0,list.get(pos).getIdx(),"1003"));//5번째 칼럼 맞는 인덱스 지정

            holder.articlelist.setAdapter(new MyCommentLAdapter(convertView.getContext(),replies));
            LayoutMethod.setListViewHeight(holder.articlelist);


            holder.ok.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                        //댓글쓰기 버튼 눌렀을때
                        holder.et.setVisibility(View.VISIBLE);
                        holder.ib.setVisibility(View.VISIBLE);
                        holder.et.requestFocus();

                }
            });


            holder.ib.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    //댓글쓰기 버튼 눌렀을때
                    replies.add(new Reply(2, holder.et.getText().toString(), "현재로그인된사람", 0, 3, "1003"));
//                    adapter.notifyDataSetChanged();
                    LayoutMethod.setListViewHeight(holder.articlelist);
                   // LayoutMethod.setListViewHeight(lv);
                    holder.et.setVisibility(View.GONE);
                    holder.ib.setVisibility(View.GONE);

                }
            });

            return convertView;

        }



        private  class ViewHolder
        {
            ImageView brandImage;
            TextView brandName;
            TextView articleTime;

            ImageView articleImage;

            TextView likeNumber;
            TextView replyNumber;

            ListView articlelist;

            ImageButton ok;
            ImageButton like;

            EditText et;
            ImageButton ib;
        }

    }



}
