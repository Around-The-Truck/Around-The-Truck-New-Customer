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

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

import kr.co.aroundthetruck.customer.data.Article;
import kr.co.aroundthetruck.customer.data.Reply;
import kr.co.aroundthetruck.customer.data.Truck;
import kr.co.aroundthetruck.customer.layoutController.AroundTheTruckApplication;
import kr.co.aroundthetruck.customer.layoutController.LayoutMethod;
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

//        articles.add(new Article(0,11111,"Milano Express",1,"수민이가 쓴글","수민's truck","1시간전",10,11));
//        articles.add(new Article(1,11111,"Milano Express2",1,"수민이2가 쓴글","수민's truck","2시간전",10,11));
        adapter = new MyArticlesAdapter(view.getContext(), articles);

        lv.setAdapter(adapter);


        LayoutMethod.setListViewHeight(lv);


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
                tmp = new Article(arr.getJSONObject(i).getInt("idx"),
                        arr.getJSONObject(i).getString("filename"),
                        arr.getJSONObject(i).getString("writer"),
                        arr.getJSONObject(i).getInt("writer_type"),
                        arr.getJSONObject(i).getString("writer_filename"),
                        arr.getJSONObject(i).getString("contents"),
                        arr.getJSONObject(i).getInt("like"),
                        arr.getJSONObject(i).getString("belong_to"),
                        arr.getJSONObject(i).getString("reg_date")
                );
//                final ArrayList<Reply> replies1 = new ArrayList<Reply>();
//
//                http2.getReplyList(arr.getJSONObject(i).getString("idx"), new TruckCallback() {
//                    @Override
//                    public void onTruckLoad(byte[] bytes) {
//                        Reply tmp1;
//                        try {
//                            JSONObject jsonObject1 = new JSONObject(new String(bytes));
//                            JSONArray arr1 = new JSONArray(new String(jsonObject1.getString("result")));
////                            replies1.clear();
//                            for (int j = 0; j < arr1.length(); j++) {
//
//                                Log.d("ebsud", "TimeLine - parseJSON - tostring (reply) : " + arr1.getJSONObject(j).toString());
//                                tmp1 = new Reply(
//                                        arr1.getJSONObject(j).getInt("idx"),
//                                        arr1.getJSONObject(j).getString("contents"),
//                                        arr1.getJSONObject(j).getString("writer"),
//                                        arr1.getJSONObject(j).getInt("writer_type"),
//                                        arr1.getJSONObject(j).getInt("article_idx"),
//                                        arr1.getJSONObject(j).getString("reg_date")
//                                );
////                                replies1.add(tmp1);
//////                                tmp.replies.add(tmp1);
////                             if (arr1.length() != 0)
////                                 articles.get(arr1.getJSONObject(0).getInt("article_idx")).setReplies(replies1);
//
//                            };
//                        } catch (Exception e) {
//                            Log.d("ebsud", "error (TimeLine - getReply - JSON)");
//                            e.printStackTrace();
//                        }
//                    }
//                });
//                articles.add(tmp.getIdx(), tmp);
                articles.add(tmp);
            }

        } catch (Exception e) {
            Log.d("ebsud", "JSON error (MainActivity) : " + e);
            e.printStackTrace();

        }
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
        Log.d("ebsud", "Timeline - callback - raw : " + resStr1);
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
//            final ArrayList<Reply> replies;

            if (convertView == null) {
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


            replies = new ArrayList<Reply>(); //댓글들
            replies.add(new Reply(0, "트럭좋아요", "댓글쓴 사람 이름", 0, list.get(pos).getIdx(), "1003"));
            replies.add(new Reply(1, "트럭싫어요", "댓글쓴 사람 이름2", 0, list.get(pos).getIdx(), "1003"));//5번째 칼럼 맞는 인덱스 지정

            holder.articlelist.setAdapter(new MyCommentLAdapter(convertView.getContext(), replies));
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
