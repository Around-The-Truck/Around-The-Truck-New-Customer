package kr.co.aroundthetruck.customer;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.widget.DrawerLayout;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by sumin on 2014-12-03.
 */
public class BrandListActivity extends Activity {

    String[] truckArea = {"서울시, 양재", "서울시 뭐뭐"};
    String[] navItems = {"이름", "나의 프로필", "나의 포인트",
            "팔로우한 트럭", "설정"};
    private ListView lvNavList;
    private FrameLayout flContainer;

    private DrawerLayout dlDrawer;
    private ActionBarDrawerToggle dtToggle;

    private ListView lv;
    private ArrayList<Brand> brands;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.brand_list);

        Spinner mySpinner = (Spinner) findViewById(R.id.spinner);
        mySpinner.setAdapter(new MyCustomAdapter(BrandListActivity.this, R.layout.text_row, truckArea));

        lv = (ListView) findViewById(R.id.brandList);
        brands = new ArrayList<Brand>();
        brands.add(new Brand(1, "s", "s", 2, "s"));
        lv.setAdapter(new BrandAdapter(BrandListActivity.this, brands));

        lvNavList = (ListView)findViewById(R.id.drawer_frame);
        flContainer = (FrameLayout)findViewById(R.id.main_frame);
        lvNavList.setAdapter( new ArrayAdapter<String>(BrandListActivity.this, android.R.layout.simple_list_item_1, navItems));
        lvNavList.setOnItemClickListener(new DrawerItemClickListener());
        dlDrawer = (DrawerLayout)findViewById(R.id.dl_activity_main_drawer);
        dtToggle = new ActionBarDrawerToggle(this, dlDrawer, R.drawable.ic_launcher, R.string .app_name,  R.string.app_name) {

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

    }

    private class DrawerItemClickListener implements ListView.OnItemClickListener{


        @Override
        public void onItemClick(AdapterView<?> adapter, View view, int position, long id) {

            Intent intent;

            switch(position){
                case 0:

                    //이름
                    break;
                case 1:
                    intent =  new Intent(BrandListActivity.this,MyInfo.class); // main.java 파일에서 이벤트를 발생시켜서 test를 불러옵니다.
                    startActivity(intent);
                    //나의 프로필
                    break;
                case 2:
                    intent =  new Intent(BrandListActivity.this,MyPoint.class); // main.java 파일에서 이벤트를 발생시켜서 test를 불러옵니다.
                    startActivity(intent);
                    //나의 포인트
                    break;
                case 3:
                    intent =  new Intent(BrandListActivity.this,MyFoodTruck.class); // main.java 파일에서 이벤트를 발생시켜서 test를 불러옵니다.
                    startActivity(intent);
                    //팔로우한 트럭
                    break;
                case 4:
                    intent =  new Intent(BrandListActivity.this,Setting.class); // main.java 파일에서 이벤트를 발생시켜서 test를 불러옵니다.
                    startActivity(intent);
                    //설정
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
            case R.id.menu_cate1:
//                //액션바에 한식 눌렀을 때
                return true;
            case R.id.menu_cate2:
                //액션바에 중식 눌렀을 대
//                return true;
            case R.id.search_button:
                Intent intent =  new Intent(BrandListActivity.this,Searching.class); // main.java 파일에서 이벤트를 발생시켜서 test를 불러옵니다.
                startActivity(intent);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }



    public class MyCustomAdapter extends ArrayAdapter<String> {

        public MyCustomAdapter(Context context, int textViewResourceId,
                               String[] objects) {
            super(context, textViewResourceId, objects);
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
            View row=inflater.inflate(R.layout.text_row, parent, false);
            TextView label=(TextView)row.findViewById(R.id.area);
            label.setText(truckArea[position]);
            return row;
        }
    }

    public class BrandAdapter extends BaseAdapter {
        Context mContext;
        ArrayList<Brand> list;

        public BrandAdapter(Context context, ArrayList<Brand> list) {
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

            final Brand mbrand = (Brand) this.getItem(pos);

            if (convertView == null) {
                holder = new ViewHolder();

                convertView = LayoutInflater.from(mContext).inflate(R.layout.brand_row, parent, false);

                holder.brandImage = (ImageView) convertView.findViewById(R.id.brandimage);
                holder.brandName = (TextView) convertView.findViewById(R.id.brandname);
                holder.brandDistance= (TextView) convertView.findViewById(R.id.distance);
                holder.like= (TextView) convertView.findViewById(R.id.like);
                holder.category= (TextView) convertView.findViewById(R.id.category);
                holder.likebtn= (Button) convertView.findViewById(R.id.likebtn);

                convertView.setTag(holder);
            }else{

                holder = (ViewHolder) convertView.getTag();

            }

            //holder.brandImage .setImageResource(mbrand.getBrandImage());
            holder.brandName.setText(mbrand.getBrandName());
            holder.brandDistance.setText(mbrand.getBrandDistance());
            holder.like.setText(mbrand.getBrandDistance());
            holder.category.setText(mbrand.getBrandDistance());

            convertView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                    intent.putExtra("brand", mbrand.getBrandName());
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
            Button likebtn;

        }





    }}
