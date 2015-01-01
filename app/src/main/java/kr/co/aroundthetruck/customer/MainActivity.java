package kr.co.aroundthetruck.customer;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;


public class MainActivity extends ActionBarActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button truckInfoBtn = (Button)findViewById(R.id.truckinfobtn);
        Button menuBtn = (Button)findViewById(R.id.menubtn);
        Button mapBtn = (Button)findViewById(R.id.mapbtn);

        truckInfoBtn.setOnClickListener(this);
        menuBtn.setOnClickListener(this);
        mapBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {

        FragmentManager fragm = getFragmentManager();
        Bundle bundle = new Bundle();
        FragmentTransaction fragmentTransaction = fragm.beginTransaction();

        android.app.Fragment fragment1 =  fragm.findFragmentById(R.id.fragment);
        android.app.Fragment fragment2 =  fragm.findFragmentById(R.id.fragment_menu);

        switch(view.getId()){
            case R.id.truckinfobtn :

                fragmentTransaction.show(fragment1);
                fragmentTransaction.hide(fragment2);
                break;

            case R.id.menubtn :

                fragmentTransaction.show(fragment2);
                fragmentTransaction.hide(fragment1);
                break;


            case R.id.mapbtn :

                //다른 화면 으로 넘어가게 map 아니라 정보 화면
                Intent intent =  new Intent(this, BottomInfo.class);   // main.java 파일에서 이벤트를 발생시켜서 test를 불러옵니다.
                startActivity(intent);
                break;
        }
        fragmentTransaction.commit();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}


