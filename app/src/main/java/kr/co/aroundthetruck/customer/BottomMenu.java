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
public class BottomMenu extends Fragment {
    int mStart = 0;

    Truck truck;

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
        View view = inflater.inflate(R.layout.bottom_menu, null);

        ListView lv = (ListView) view.findViewById(R.id.menulist);

        // StrictMode (Thread Policy == All)
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder()
                .permitAll().build();
        StrictMode.setThreadPolicy(policy);

        // HTTP Connection
        HttpCommunication http = new HttpCommunication();
        String resStr = "";

//        resStr = http.getTruckInfo(thisTruckIdx);

        parseJSON(resStr);

//        menus.add(new Menu(0,"짬뽕",7000,1,1111,"면,버섯","매워요"));
//        menus.add(new Menu(1,"짬뽕2",7006,1,1111,"면,버섯","매워요"));

        TruckMenuLAdapter adapter = new TruckMenuLAdapter(view.getContext(), menus);
        lv.setAdapter(adapter);

        LayoutMethod.setListViewHeight(lv);

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
                        arr.getJSONObject(i).getInt("photo_idx"),
                        arr.getJSONObject(i).getString("photo_filename"),
                        arr.getJSONObject(i).getString("description"),
                        arr.getJSONObject(i).getString("ingredients")
                );
                menus.add(tmp);
            }

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
}