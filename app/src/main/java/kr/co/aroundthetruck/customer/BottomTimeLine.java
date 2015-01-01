package kr.co.aroundthetruck.customer;


import android.app.Fragment;
import android.os.Bundle;
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
public class BottomTimeLine extends Fragment {
    int mStart = 0;

    public static BottomTimeLine newInstance(int start){
        BottomTimeLine cf = new BottomTimeLine();
        cf.mStart = start;
        return cf;
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle SavedInstanceState){
        View view = inflater.inflate(R.layout.bottom_timeline, null);

        EditText et = (EditText)view.findViewById(R.id.editText);
        Button send = (Button)view.findViewById(R.id.button);

        ListView lv = (ListView)view.findViewById(R.id.listView);
        ArrayList<MyComment> comments= new ArrayList<MyComment>();
        MyCommentLAdapter adapter = new MyCommentLAdapter(view.getContext(),comments);
        lv.setAdapter(adapter);

        return view;
    }

    public void onSaveInstanceState(Bundle outState){
        super.onSaveInstanceState(outState);

    }

}
