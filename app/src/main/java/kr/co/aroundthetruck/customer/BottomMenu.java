package kr.co.aroundthetruck.customer;


import android.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;

/**
 * Created by sumin on 2014-12-01.
 */
public class BottomMenu extends Fragment {
    int mStart = 0;

    public static BottomMenu newInstance(int start){
        BottomMenu cf = new BottomMenu();
        cf.mStart = start;
        return cf;
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle SavedInstanceState){
        View view = inflater.inflate(R.layout.bottom_menu, null);
        ListView lv = (ListView)view.findViewById(R.id.menulist);
        ArrayList<TruckMenu> menus= new ArrayList<TruckMenu>();
        TruckMenuLAdapter adapter = new TruckMenuLAdapter(view.getContext(),menus);
        lv.setAdapter(adapter);

        return view;

    }

    public void onSaveInstanceState(Bundle outState){
        super.onSaveInstanceState(outState);

    }

}