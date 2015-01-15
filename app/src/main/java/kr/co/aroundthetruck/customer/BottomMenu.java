package kr.co.aroundthetruck.customer;


import android.app.Fragment;
import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

import kr.co.aroundthetruck.customer.data.Menu;
import kr.co.aroundthetruck.customer.data.Truck;
import kr.co.aroundthetruck.customer.layoutController.LayoutMethod;
import kr.co.aroundthetruck.customer.network.HttpCommunication;

/**
 * Created by sumin on 2014-12-01.
 */
public class BottomMenu extends Fragment implements TruckCallback {
    int mStart = 0;

    Truck truck;
    View view;

    ListView lv;

    ArrayList<Menu> menus = new ArrayList<Menu>();

    public static BottomMenu newInstance() {
        BottomMenu fragment = new BottomMenu();
        return fragment;
    }


    public static BottomMenu newInstance(int start) {
        BottomMenu cf = new BottomMenu();
        cf.mStart = start;
        return cf;
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle SavedInstanceState) {

        view = inflater.inflate(R.layout.bottom_menu, null);

        lv = (ListView) view.findViewById(R.id.menulist);

        // StrictMode (Thread Policy == All)
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder()
                .permitAll().build();
        StrictMode.setThreadPolicy(policy);

        // HTTP Connection
        HttpCommunication http = new HttpCommunication();
        String resStr = "";

        http.getMenuList(String.valueOf(truck.getIdx()), BottomMenu.this);

        return view;

    }

    private void parseJSON(String resStr) {

        Menu tmp;

        try {
            JSONObject jsonObject = new JSONObject(resStr);
            JSONArray arr = new JSONArray(new String(jsonObject.getString("result")));
            for (int i = 0; i < arr.length(); i++) {
                Log.d("ebsud", arr.getJSONObject(i).toString());
                tmp = new Menu(arr.getJSONObject(i).getInt("idx"),
                        arr.getJSONObject(i).getString("name"),
                        arr.getJSONObject(i).getInt("price"),
                        arr.getJSONObject(i).getInt("truck_idx"),
                        arr.getJSONObject(i).getString("truck_name"),
                        arr.getJSONObject(i).getString("photo_filename"),
                        arr.getJSONObject(i).getString("description"),
                        arr.getJSONObject(i).getString("ingredients")
                );
                menus.add(tmp);
            }

            TruckMenuLAdapter adapter = new TruckMenuLAdapter(view.getContext(), menus);
            lv.setAdapter(adapter);

            LayoutMethod.setListViewHeight(lv);


        } catch (Exception e) {
            Log.d("ebsud", "JSON error (BottomMenu) : " + e);
            e.printStackTrace();
            ;
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

    @Override
    public void onTruckLoad(byte[] bytes) {
        String raw = new String(bytes);

        Log.d("ebsud", "Menu - callback - raw : " + raw);

        parseJSON(raw);
    }


}