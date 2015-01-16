package kr.co.aroundthetruck.customer;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.graphics.Color;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.widget.DrawerLayout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;
import com.squareup.picasso.Picasso;

import org.apache.http.Header;
import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONObject;

import java.net.URLEncoder;
import java.util.ArrayList;

import kr.co.aroundthetruck.customer.Utils.DistancCaculator;
import kr.co.aroundthetruck.customer.data.GPS;
import kr.co.aroundthetruck.customer.layoutController.AroundTheTruckApplication;
import kr.co.aroundthetruck.customer.data.Truck;
import kr.co.aroundthetruck.customer.layoutController.RoundedTransformation;
import kr.co.aroundthetruck.customer.network.HttpCommunication;

/**
 * Created by sumin on 2014-12-03.
 */
public class BrandListActivity extends Activity implements TruckCallback{

    public static final int REQUEST_WANT_TO_FIND = 1;

    String[] truckArea = {"  신천, 잠실", "  강남, 양재", "  신사, 압구정", "  신촌, 이대, 홍대", "  이태원","  건대", "  종로, 명동"};
    String[] navItems = {"나의 프로필", "나의 포인트",
            "팔로우한 트럭"};
    private ListView lvNavList;
    private FrameLayout flContainer;

    private DrawerLayout dlDrawer;
    private ActionBarDrawerToggle dtToggle;

    private ListView lv;
    private BrandAdapter adapter;
    private ArrayList<Brand> brands =null;
    private ArrayList<Integer> followbrands;

    private ImageView cateIcon;

    String strColor = "#6d6d6d";
    String strColor2 = "#9a9a9a";

    HttpCommunication http;

    Boolean spinnerselected = false;

    String phoneNum;
    private SharedPreferences prefs;

    DistancCaculator dc;
    GPS gps;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.brand_list);

        phoneNum = getMySharedPreferences("CHEKEDUSER");

        getFollowList(phoneNum);
//        dc = new DistancCaculator();
        gps = new GPS(this);

//        lv.setAdapter(adapter);

        // HTTP Connection
        http = new HttpCommunication();


        final Spinner mySpinner = (Spinner) findViewById(R.id.spinner);
        mySpinner.setAdapter(new MyCustomAdapter(BrandListActivity.this, R.layout.text_row, truckArea));
        mySpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if(spinnerselected){
                http.getTruckByAddress(mySpinner.getSelectedItem().toString(), BrandListActivity.this);}
                else {

                    http.getAllTruck(BrandListActivity.this); spinnerselected = true;}

                }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {


            }
        });

        http.getAllTruck(BrandListActivity.this);



        // StrictMode (Thread Policy == All)
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder()
                .permitAll().build();
        StrictMode.setThreadPolicy(policy);



        cateIcon = (ImageView)findViewById(R.id.imageView10);

        // Brand List view
        lv = (ListView) findViewById(R.id.brandList);

        // Drawer list view
        lvNavList = (ListView)findViewById(R.id.drawer_frame);
        flContainer = (FrameLayout)findViewById(R.id.main_frame);
       // lvNavList.setAdapter( new ArrayAdapter<String>(BrandListActivity.this, android.R.layout.simple_list_item_1, navItems));
        lvNavList.setAdapter(new MyCustomAdapter(BrandListActivity.this, R.layout.text_row2,  navItems));
        lvNavList.setOnItemClickListener(new DrawerItemClickListener());
        dlDrawer = (DrawerLayout)findViewById(R.id.dl_activity_main_drawer);
        dtToggle = new ActionBarDrawerToggle(this, dlDrawer, R.drawable.threeline, R.string .app_name,  R.string.app_name) {

            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);

            }


            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);

            }

        };
        dlDrawer.setDrawerListener(dtToggle);

        getActionBar().setDisplayHomeAsUpEnabled(false);
        getActionBar().setHomeButtonEnabled(true);

    }


    protected void onPostCreate(Bundle savedInstanceState){
        super.onPostCreate(savedInstanceState);
        dtToggle.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        dtToggle.onConfigurationChanged(newConfig);
    }

    private void parseJSON (String str) {


        brands = new ArrayList<Brand>();
        Brand tmp = new Brand();

        try {
            JSONObject jsonObject = new JSONObject(str);
            JSONArray arr = new JSONArray(new String(jsonObject.getString("result")));
            for (int i=0 ; i<arr.length(); i++) {
//                Log.d("ebsud", "brandlist - parseJSON - toString" + arr.getJSONObject(i).toString());
                tmp = new Brand(arr.getJSONObject(i).getInt("idx"),
                                URLEncoder.encode(arr.getJSONObject(i).getString("photo_filename"), "UTF-8").replaceAll("\\+","%20"),
                                arr.getJSONObject(i).getString("name"),
                                String.valueOf(1000 + 150*(-i)) + " m",//(new DistancCaculator(arr.getJSONObject(i).getDouble("gps_longitude"), gps.getLongitude(), arr.getJSONObject(i).getDouble("gps_latitude"), gps.getLatitude()).calculate()) + " m",
                                arr.getJSONObject(i).getInt("follow_count"), arr.getJSONObject(i).getString("cat_name_big")+" / "+arr.getJSONObject(i).getString("cat_name_small"),
                                false);

//                Log.d("sssssssssssssfileencoder",URLEncoder.encode(arr.getJSONObject(i).getString("photo_filename"), "UTF-8"));
                brands.add(tmp);
            }
            adapter = new BrandAdapter(BrandListActivity.this, brands);
            adapter.setFollowbrands(followbrands);
            lv.setAdapter(adapter);


        } catch (Exception e) {
            Log.d("ebsud", "JSON error (BrandList) : " + e);
            e.printStackTrace();

        }


    }

    private void parseJSON2 (String str) {


        followbrands = new ArrayList<>();

        try {
            JSONObject jsonObject = new JSONObject(str);
            JSONArray arr = new JSONArray(new String(jsonObject.getString("result")));
            for (int i=0 ; i<arr.length(); i++) {
                Log.d("parseJSON2",Integer.toString(arr.getJSONObject(i).getInt("idx")));
                followbrands.add(arr.getJSONObject(i).getInt("idx"));
            }


        } catch (Exception e) {
            Log.d("ebsud", "JSON error (BrandList) : " + e);
            e.printStackTrace();

        }


    }


    private class DrawerItemClickListener implements ListView.OnItemClickListener{


        @Override
        public void onItemClick(AdapterView<?> adapter, View view, int position, long id) {

            Intent intent;

            switch(position){
                case 0:
                    intent =  new Intent(BrandListActivity.this,MyProfile.class); // main.java 파일에서 이벤트를 발생시켜서 test를 불러옵니다.
                    startActivity(intent);
                    //나의 프로필
                    break;
                case 1:
                    intent =  new Intent(BrandListActivity.this,MyPoint.class); // main.java 파일에서 이벤트를 발생시켜서 test를 불러옵니다.
                    startActivity(intent);
                    //나의 포인트
                    break;
                case 2:
                    intent =  new Intent(BrandListActivity.this,MyFoodTruck.class); // main.java 파일에서 이벤트를 발생시켜서 test를 불러옵니다.
                    startActivity(intent);
                    //팔로우한 트럭
                    break;
            }
            dlDrawer.closeDrawer(lvNavList);
        }

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        MenuInflater inflater = getMenuInflater();
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return super.onCreateOptionsMenu(menu);
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle presses on the action bar items

        switch (item.getItemId()) {
            case android.R.id.home:
                if(dtToggle.onOptionsItemSelected(item)){
                    return true;
                }

            case R.id.menu_cate1:
                cateIcon.setImageResource(R.drawable.west);

                return true;
            case R.id.menu_cate2:
                cateIcon.setImageResource(R.drawable.chinese);
                //액션바에 중식 눌렀을 대
                return true;

            case R.id.menu_cate3:
                //cateIcon.setImageResource(R.drawable.);
                //액션바에 한식 눌렀을 대
                return true;

            case R.id.menu_cate4:
                cateIcon.setImageResource(R.drawable.japan);
                //액션바에 중식 눌렀을 대
                return true;
            case R.id.menu_cate5:
                cateIcon.setImageResource(R.drawable.snack);
                //액션바에 중식 눌렀을 대
                return true;
            case R.id.menu_cate6:
                cateIcon.setImageResource(R.drawable.fushion);
                //액션바에 중식 눌렀을 대
                return true;
            case R.id.menu_cate7:
                cateIcon.setImageResource(R.drawable.spe);
                //액션바에 중식 눌렀을 대
                return true;

            case R.id.search_button:
                Intent intent =  new Intent(BrandListActivity.this,Searching.class); // main.java 파일에서 이벤트를 발생시켜서 test를 불러옵니다.
                startActivityForResult(intent, REQUEST_WANT_TO_FIND);

                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        switch(requestCode){
            case  REQUEST_WANT_TO_FIND :

                if(resultCode == Activity.RESULT_OK){

                //액션바에있는 찾기 버튼 누르고 왔을때
                Log.d("User_want_to_find",data.getStringExtra("search"));
                http.getTruckListByName(data.getStringExtra("search"),BrandListActivity.this);
                }
        }
    }

    public class MyCustomAdapter extends ArrayAdapter<String> {

        String[] objects;
        int textViewResourceId;
        Context context;

        public MyCustomAdapter(Context context, int textViewResourceId,
                               String[] objects) {
            super(context, textViewResourceId, objects);

            this.context = context;
            this.objects = objects;
            this.textViewResourceId = textViewResourceId;
        }
        @Override
        public View getDropDownView(int position, View convertView,
                                    ViewGroup parent) {
            return getCustomView(position, convertView, parent);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            // TODO Auto-generated method stub
            return getCustomView(position, convertView, parent);
        }

        public View getCustomView(int position, View convertView, ViewGroup parent) {
            // TODO Auto-generated method stub
            //return super.getView(position, convertView, parent);

            LayoutInflater inflater=getLayoutInflater();
            View row=inflater.inflate(textViewResourceId, parent, false);

            TextView label=(TextView)row.findViewById(R.id.area);
            label.setTypeface(AroundTheTruckApplication.nanumGothic);

            label.setText(objects[position]);
          //  label.setTextColor(Color.parseColor(strColor));

            return row;
        }
    }

    public class BrandAdapter extends BaseAdapter {
        Context mContext;
        ArrayList<Brand> list;
        ArrayList<Integer> followbrands;

        public BrandAdapter(Context context, ArrayList<Brand> list) {
            super();
            this.mContext = context;
            this.list = list;
            this.followbrands = new ArrayList<>();
//            if (list == null){
//                list = new ArrayList<>();
//            }
        }

        public void setList(ArrayList<Brand> list){
            this.list = list;
            notifyDataSetChanged();
        }

        public void setFollowbrands(ArrayList<Integer> followbrands){
            this.followbrands = followbrands;
            notifyDataSetChanged();
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

            final ViewHolder holder;

            final Brand mbrand = (Brand) this.getItem(pos);

            if (convertView == null) {
                holder = new ViewHolder();

                convertView = LayoutInflater.from(mContext).inflate(R.layout.brand_row, parent, false);

                holder.brandImage = (ImageView) convertView.findViewById(R.id.brandimage);
                holder.brandName = (TextView) convertView.findViewById(R.id.brandname);

                holder.brandDistance= (TextView) convertView.findViewById(R.id.distance);
                holder.like= (TextView) convertView.findViewById(R.id.like);
                holder.category= (TextView) convertView.findViewById(R.id.category);
                holder.likebtn= (ImageButton) convertView.findViewById(R.id.imageButton4);

                convertView.setTag(holder);
            }else{

                holder = (ViewHolder) convertView.getTag();

            }

            Picasso.with(BrandListActivity.this).load("http://165.194.35.161:3000/upload/" + mbrand.getBrandImage()).fit().transform(new RoundedTransformation(300)).into(holder.brandImage);

            holder.brandName.setText(mbrand.getBrandName());
            holder.brandName.setTypeface(AroundTheTruckApplication.nanumGothicBold);
            holder.brandName.setTextColor(Color.parseColor(strColor));


            holder.brandDistance.setText(mbrand.getBrandDistance());
            holder.brandDistance.setTypeface(AroundTheTruckApplication.nanumGothic);
            holder.brandDistance.setTextColor(Color.parseColor(strColor2));

            holder.like.setText(Integer.toString(mbrand.getLike()));
            holder.like.setTypeface(AroundTheTruckApplication.nanumGothic);
            holder.like.setTextColor(Color.parseColor(strColor2));


            holder.category.setText(mbrand.getCategory());
            holder.category.setTypeface(AroundTheTruckApplication.nanumGothic);
            holder.category.setTextColor(Color.parseColor(strColor2));

            holder.likebtn.setImageResource(R.drawable.unlike);


            try {

                for (int i = 0; i < followbrands.size(); i++) {

//                    Log.d("followbrandidx", Integer.toString(followbrands.get(i)));
//                    Log.d("현재 리스트 브랜드 idx", Integer.toString(mbrand.getBrandIdx()));
                    if (mbrand.getBrandIdx() == followbrands.get(i)) {

                        mbrand.setLikeOrNot(true);
                        holder.likebtn.setImageResource(R.drawable.like);

                    }

                }

            }catch(Exception e) {

        }

            holder.likebtn.setFocusable(false);
            holder.likebtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    if(mbrand.getLikeOrNot()){

                        RequestParams params = new RequestParams();

                        params.add("truckIdx",Integer.toString(mbrand.getBrandIdx()));
                        params.add("phoneNum",phoneNum);


                        AsyncHttpClient client = new AsyncHttpClient();
                        client.get("http://165.194.35.161:3000/unfollowTruck", params , new AsyncHttpResponseHandler() {

                            @Override
                            public void onSuccess(int i, Header[] headers, byte[] bytes) {
                                String raw = new String(bytes);

//                                Log.d("sssssssssssssssssssss",raw);
                                holder.likebtn.setImageResource(R.drawable.unlike);
                                mbrand.setLikeOrNot(false);
                            }

                            @Override
                            public void onFailure(int i, Header[] headers, byte[] bytes, Throwable throwable) {

                            }
                        });



                    }
                    else {

                        RequestParams params = new RequestParams();

                        params.add("truckIdx",Integer.toString(mbrand.getBrandIdx()));
                        params.add("phoneNum",phoneNum);


                        AsyncHttpClient client = new AsyncHttpClient();
                        client.get("http://165.194.35.161:3000/followTruck", params , new AsyncHttpResponseHandler() {

                            @Override
                            public void onSuccess(int i, Header[] headers, byte[] bytes) {

                                String raw = new String(bytes);

                                Log.d("sssssssssssssssssssss",raw);
                                holder.likebtn.setImageResource(R.drawable.like);
                                mbrand.setLikeOrNot(true);



                            }

                            @Override
                            public void onFailure(int i, Header[] headers, byte[] bytes, Throwable throwable) {

                            }
                        });
                    holder.likebtn.setImageResource(R.drawable.like);

                   }

                }
            });

            convertView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                    intent.putExtra("brandName", mbrand.getBrandName());
                    intent.putExtra("brandIdx", mbrand.getBrandIdxString());
                    startActivity(intent);

                }
            });

            return convertView;

        }

        private class ViewHolder
        {
            ImageView brandImage;
            TextView brandName;
            TextView brandDistance;
            TextView like;
            TextView category;
            ImageButton likebtn;

        }

    }



    @Override
    public void onTruckLoad(byte[] bytes) {
        String raw = new String(bytes);
        Log.d("ebsud", "raw (BrandList) : " + raw);
        parseJSON(raw);
    }

    public void getFollowList(String phone){

        String url = "http://165.194.35.161:3000/getFollowList?phoneNum=" + phone;
        //        ArrayList<NameValuePair> param = new ArrayList<NameValuePair>();

        //      param.add(new BasicNameValuePair("phoneNum", phoneNum));

        AsyncHttpClient client = new AsyncHttpClient();
        client.get(url, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int i, Header[] headers, byte[] bytes) {

                parseJSON2(new String(bytes));
                http.getAllTruck(BrandListActivity.this);

            }

            @Override
            public void onFailure(int i, Header[] headers, byte[] bytes, Throwable throwable) {

            }
        });
    }

    private String getMySharedPreferences(String _key) {
        if(prefs == null){
            prefs = getSharedPreferences("ATT",MODE_PRIVATE);
        }
        return prefs.getString(_key, "NO");
    }

}
