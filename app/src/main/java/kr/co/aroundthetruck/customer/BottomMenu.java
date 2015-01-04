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

import kr.co.aroundthetruck.customer.data.Menu;

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

        ArrayList<Menu> menus= new ArrayList<Menu>();
        menus.add(new Menu(0,"짬뽕",7000,1,1111,"면,버섯","매워요"));

        TruckMenuLAdapter adapter = new TruckMenuLAdapter(view.getContext(),menus);
        lv.setAdapter(adapter);

        return view;

    }

    public void onSaveInstanceState(Bundle outState){
        super.onSaveInstanceState(outState);

    }

}