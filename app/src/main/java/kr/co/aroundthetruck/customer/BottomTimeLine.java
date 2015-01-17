package kr.co.aroundthetruck.customer;


import android.app.Fragment;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
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

import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONObject;

import java.net.URLEncoder;
import java.util.ArrayList;

import kr.co.aroundthetruck.customer.data.Article;
import kr.co.aroundthetruck.customer.data.Reply;
import kr.co.aroundthetruck.customer.data.Truck;
import kr.co.aroundthetruck.customer.layoutController.AroundTheTruckApplication;
import kr.co.aroundthetruck.customer.layoutController.LayoutMethod;
import kr.co.aroundthetruck.customer.layoutController.RoundedTransformation;
import kr.co.aroundthetruck.customer.network.HttpCommunication;

/**
 * Created by sumin on 2014-12-01.
 */
public class BottomTimeLine extends Fragment implements TruckCallback {
    int mStart = 0;
    String thisTruckIdx;
    SharedPreferences prefs;

    Truck truck;
    View view;

    ListView lv;
    MyArticlesAdapter adapter;

    String strColor = "#6d6d6d";
    String strColor2 = "#9a9a9a";

    ArrayList<Article> articles = new ArrayList<Article>();
    ArrayList<Reply> replies = new ArrayList<Reply>();
//    ArrayList<ArrayList<re>>

    public static BottomTimeLine newInstance(int start) {
        BottomTimeLine cf = new BottomTimeLine();
        cf.mStart = start;
        return cf;
    }

    public static BottomTimeLine newInstance() {
        BottomTimeLine fragment = new BottomTimeLine();
        return fragment;
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle SavedInstanceState) {

        view = inflater.inflate(R.layout.bottom_timeline, null);
        lv = (ListView) view.findViewById(R.id.listView);

        // Strcik Mode
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder()
                .permitAll().build();
        StrictMode.setThreadPolicy(policy);

        // HTTP Connection
        HttpCommunication http = new HttpCommunication();
        http.getArticlList(String.valueOf(truck.getIdx()), BottomTimeLine.this);

//                articles.add(new Article(1,11111,"Milano Express2",1,"수민이2가 쓴글","수민's truck","2시간전",10,11));





        return view;

    }

    private void parseJSON(String resStr) {

        Article tmp = null;
        HttpCommunication http2 = new HttpCommunication();

        try {
            JSONObject jsonObject = new JSONObject(resStr);
            JSONArray arr = new JSONArray(new String(jsonObject.getString("result")));
            for (int i = 0; i < arr.length(); i++) {
                Log.d("ebsud", "TimeLine - parseJSON - tostring (article) : " + arr.getJSONObject(i).toString());
//        Articles.add(new Article(1,11111,"Milano Express2",1,"수민이2가 쓴글","수민's truck","2시간전",10,11));




                tmp = new Article(
                        arr.getJSONObject(i).getInt("idx"),
                        URLEncoder.encode(arr.getJSONObject(i).getString("filename"), "UTF-8").replaceAll("\\+", "%20")
                        ,truck.getName(),
                        1,
                        //writer_type
                        arr.getJSONObject(i).getString("contents"),
                        "1",//arr.getJSONObject(i).getInt("truck_idx"),
                        arr.getJSONObject(i).getString("reg_date"),
                        arr.getJSONObject(i).getInt("like"),
                        1,
                        parseJSONReply(arr.getJSONObject(i).getString("reply")),
                        URLEncoder.encode(arr.getJSONObject(i).getString("truck_filename"), "UTF-8").replaceAll("\\+", "%20")
                        );

                Log.d("sssssssssssssssssssssssssssssssssssss",URLEncoder.encode(arr.getJSONObject(i).getString("filename"), "UTF-8").replaceAll("\\+", "%20"));

                articles.add(tmp);
            }

            adapter = new MyArticlesAdapter(view.getContext(), articles);

            lv.setAdapter(adapter);


            LayoutMethod.setListViewHeight(lv);

        } catch (Exception e) {
            Log.d("ebsud", "JSON error (MainActivity) : " + e);
            e.printStackTrace();

        }
    }

    private ArrayList<Reply> parseJSONReply(String res) {

        Reply rtmp;
        ArrayList<Reply> rtmpList = new ArrayList<Reply>();

        try {
            JSONArray rarr = new JSONArray(res);
            for (int i = 0; i < rarr.length(); i++) {
                Log.d("ebsud", "timeline - parsejsonReply - forloop : " +res);
                rtmp = new Reply(
                        rarr.getJSONObject(i).getInt("r_idx"),
                        rarr.getJSONObject(i).getString("r_contents"),
                        rarr.getJSONObject(i).getString("r_writer"),
                        0,//writer_type
                        0,//writer_idx
                        rarr.getJSONObject(i).getString("r_reg_date"),
                        URLEncoder.encode(rarr.getJSONObject(i).getString("r_writer_filename"),"UTF-8").replaceAll("\\+", "%20"),
                        rarr.getJSONObject(i).getString("r_writer_name")
                );
                rtmpList.add(rtmp);
            }
        } catch (Exception e) {
            Log.d("ebsud", "parseJSONReply");
            e.printStackTrace();
        }
        return rtmpList;
    }

    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

    }

    public Truck getTruck() {
        return truck;
    }

    public void setTruck(Truck truck) {
        this.truck = truck;
    }

    // callback Method
    @Override
    public void onTruckLoad(byte[] bytes) {
        String resStr1 = new String(bytes);
        Log.d("ebsud", "Timeline - callback - raw 2 : " + resStr1);
        parseJSON(resStr1);
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


            if (convertView == null) {

                Log.d("ebsud", "timeline - getView - getholder");
                holder = new ViewHolder();
                convertView = LayoutInflater.from(mContext).inflate(R.layout.article_row, parent, false);

                holder.brandImage = (ImageView) convertView.findViewById(R.id.imageView2);
                holder.brandName = (TextView) convertView.findViewById(R.id.textView3);
                holder.articleTime = (TextView) convertView.findViewById(R.id.textView9);

                holder.articleImage = (ImageView) convertView.findViewById(R.id.imageView18);

                holder.likeNumber = (TextView) convertView.findViewById(R.id.textView10);
                holder.replyNumber = (TextView) convertView.findViewById(R.id.textView11);

                holder.articlelist = (ListView) convertView.findViewById(R.id.listView4);

                holder.like = (ImageButton) convertView.findViewById(R.id.article_like);
                holder.ok = (ImageButton) convertView.findViewById(R.id.article_ok); //댓글 다는 버튼

                holder.et = (EditText) convertView.findViewById(R.id.editText);
                holder.ib = (ImageButton) convertView.findViewById(R.id.buttons);


                convertView.setTag(holder);
            } else {

                holder = (ViewHolder) convertView.getTag();

            }

            Log.d("ebsud", "timeline - getView");

            //holder.brandImage.setImageResource(list.get(pos).getmenuImage());
            holder.brandName.setText(list.get(pos).getWriter());  //타임라인 글 쓴사람
            holder.brandName.setTypeface(AroundTheTruckApplication.nanumGothicBold);
           // holder.articleImage.setImageResource(list.get(pos));
            holder.brandName.setTextColor(Color.parseColor(strColor));

            holder.articleTime.setText(list.get(pos).getReg_date());
            holder.articleTime.setTypeface(AroundTheTruckApplication.nanumGothic);
            holder.articleTime.setTextColor(Color.parseColor(strColor2));

            holder.likeNumber.setText(Integer.toString(list.get(pos).getLikeNumber()) + "명");
            holder.likeNumber.setTypeface(AroundTheTruckApplication.nanumGothic);
            holder.likeNumber.setTextColor(Color.parseColor(strColor));

            holder.replyNumber.setText(Integer.toString(list.get(pos).getReplyNumber()) + "명");
            holder.replyNumber.setTypeface(AroundTheTruckApplication.nanumGothic);
            holder.replyNumber.setTextColor(Color.parseColor(strColor));

            Log.d("lkkkkkkkkkkkkkkkkkkkkkkkk","k"+list.get(pos).getFilename());

            //smallimage
            Picasso.with(mContext).load("http://165.194.35.161:3000/upload/" + list.get(pos).getWriter_filename()).fit().transform(new RoundedTransformation(440)).into(holder.brandImage);

            //bigimage
            Picasso.with(mContext).load("http://165.194.35.161:3000/upload/" + list.get(pos).getFilename()).fit().into(holder.articleImage);
//            replies.add(new Reply(0, "트럭좋아요", "댓글쓴 사람 이름", 0, list.get(pos).getIdx(), "1003"));
//            replies.add(new Reply(1, "트럭싫어요", "댓글쓴 사람 이름2", 0, list.get(pos).getIdx(), "1003"));//5번째 칼럼 맞는 인덱스 지정

            holder.articlelist.setAdapter(new MyCommentLAdapter(convertView.getContext(), list.get(pos).getReplies()));
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


        private class ViewHolder {
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
