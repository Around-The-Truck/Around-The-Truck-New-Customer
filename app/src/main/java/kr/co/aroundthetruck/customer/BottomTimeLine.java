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
import android.widget.Toast;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;
import com.squareup.picasso.Picasso;

import org.apache.http.Header;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
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




    Truck truck;
    View view;

    ListView lv;
    MyArticlesAdapter adapter;

    String strColor = "#6d6d6d";
    String strColor2 = "#9a9a9a";

    ArrayList<Article> articles = null;

    String userPhone = null;


    public static BottomTimeLine newInstance() {
        BottomTimeLine fragment = new BottomTimeLine();
        return fragment;
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle SavedInstanceState) {

        Log.d("onCreateView()","BottomTimeLine");

        userPhone = getUserPhone("CHEKEDUSER");


        view = inflater.inflate(R.layout.bottom_timeline, null);
        lv = (ListView) view.findViewById(R.id.listView);

        // Strcik Mode
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder()
                .permitAll().build();
        StrictMode.setThreadPolicy(policy);

        // HTTP Connection
        HttpCommunication http = new HttpCommunication();
        http.getArticlList(String.valueOf(truck.getIdx()), BottomTimeLine.this);


        return view;

    }

    private void parseJSON(String resStr) {

        articles = new ArrayList<Article>();

        Article tmp = null;

        try {
            JSONObject jsonObject = new JSONObject(resStr);
            JSONArray arr = new JSONArray(new String(jsonObject.getString("result")));
            for (int i = 0; i < arr.length(); i++) {
                Log.d("bottomTimeLine", arr.getJSONObject(i).toString());


                tmp = new Article(
                        arr.getJSONObject(i).getInt("idx"),
                        URLEncoder.encode(arr.getJSONObject(i).getString("filename"), "UTF-8").replaceAll("\\+", "%20"),
                        URLEncoder.encode(arr.getJSONObject(i).getString("truck_filename"), "UTF-8").replaceAll("\\+", "%20"),
                        Integer.parseInt(arr.getJSONObject(i).getString("truckIdx")),
                        arr.getJSONObject(i).getString("contents"),
                        arr.getJSONObject(i).getInt("like"),
                        arr.getJSONObject(i).getString("reg_date"),
                        arr.getJSONObject(i).getString("reply")
                        );


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



    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

    }

    public void setTruck(Truck truck) {
        this.truck = truck;
    }

    private String getUserPhone(String _key) {

        SharedPreferences prefs;
        prefs = getActivity().getSharedPreferences("ATT",getActivity().MODE_PRIVATE);

        return prefs.getString(_key, "NO");
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
        MyCommentLAdapter commentLAdapter;


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
            final int mypos = pos;


            if (convertView == null) {

                holder = new ViewHolder();
                convertView = LayoutInflater.from(mContext).inflate(R.layout.article_row, parent, false);

                holder.brandImage = (ImageView) convertView.findViewById(R.id.imageView2);
                holder.brandName = (TextView) convertView.findViewById(R.id.aticle_row_truckname);
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

            Picasso.with(mContext).load("http://165.194.35.161:3000/upload/" + list.get(pos).getFileName()).fit().into(holder.articleImage);
            Picasso.with(mContext).load("http://165.194.35.161:3000/upload/" + list.get(pos).getTruck_filename()).fit().transform(new RoundedTransformation(96)).into(holder.brandImage);


            holder.brandName.setText(truck.getName());  //타임라인 글 쓴사람
            holder.brandName.setTypeface(AroundTheTruckApplication.nanumGothicBold);
            holder.brandName.setTextColor(Color.parseColor(strColor));

            holder.articleTime.setText(list.get(pos).getReg_date());
            holder.articleTime.setTypeface(AroundTheTruckApplication.nanumGothic);
            holder.articleTime.setTextColor(Color.parseColor(strColor2));

            holder.likeNumber.setText(list.get(pos).getLike() + "명");
            holder.likeNumber.setTypeface(AroundTheTruckApplication.nanumGothic);
            holder.likeNumber.setTextColor(Color.parseColor(strColor));


            try {

                commentLAdapter = new MyCommentLAdapter(convertView.getContext(), list.get(pos).getReplyArrayList());
                holder.articlelist.setAdapter(commentLAdapter);


            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            }

            holder.replyNumber.setText(list.get(pos).getRepliesCount() + "명");
            holder.replyNumber.setTypeface(AroundTheTruckApplication.nanumGothic);
            holder.replyNumber.setTextColor(Color.parseColor(strColor));

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
                    //댓글 올리기 버튼 눌렀을때

                    RequestParams params = new RequestParams();

                    params.add("articleIdx",Integer.toString(list.get(mypos).getIdx()));
                    params.add("writer",userPhone);
                    params.add("writerType","0");
                    params.add("contents",holder.et.getText().toString());

                    AsyncHttpClient client = new AsyncHttpClient();
                    client.post("http://165.194.35.161:3000/addReply", params , new AsyncHttpResponseHandler() {

                        @Override
                        public void onSuccess(int i, Header[] headers, byte[] bytes) {
                            String raw = new String(bytes);
                                Log.d("sumin_reply_test",raw);

                            holder.et.setVisibility(View.GONE);
                            holder.ib.setVisibility(View.GONE);

                            HttpCommunication http = new HttpCommunication();
                            http.getArticlList(String.valueOf(truck.getIdx()), BottomTimeLine.this);

                        }

                        @Override
                        public void onFailure(int i, Header[] headers, byte[] bytes, Throwable throwable) {
                            String raw = new String(bytes);
                            Log.d("sumin_reply_fail_test",raw);
                        }
                    });



                    //Reply(int r_idx, String r_contents, String r_writer_name, String r_writer_filename, String r_reg_date)

                   // LayoutMethod.setListViewHeight(holder.articlelist);
                   // LayoutMethod.setListViewHeight(lv);





                }
            });


            holder.like.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    //좋아요 버튼 눌렀을때
                    RequestParams params = new RequestParams();

                    params.add("articleNum",Integer.toString(list.get(mypos).getIdx()));
                    params.add("phoneNum",userPhone);


                    AsyncHttpClient client = new AsyncHttpClient();
                    client.get("http://165.194.35.161:3000/likeArticle", params , new AsyncHttpResponseHandler() {

                        @Override
                        public void onSuccess(int i, Header[] headers, byte[] bytes) {

                            String raw = new String(bytes);
                            Log.d("sumin_article_like_test",raw);


                            if(raw.contains("507")) {
                                Toast.makeText(getActivity().getApplicationContext(), "이미 누르셨습니다",  Toast.LENGTH_SHORT).show(); }
                            else if(raw.contains("500")) {
                                int plusLike = Integer.parseInt(list.get(mypos).getLike()) + 1;
                                holder.likeNumber.setText(Integer.toString(plusLike) + "명");
                            }

                        }

                        @Override
                        public void onFailure(int i, Header[] headers, byte[] bytes, Throwable throwable) {

                            String raw = new String(bytes);
                            Log.d("sumin_article_like_failtest",raw);

                        }
                    });

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
