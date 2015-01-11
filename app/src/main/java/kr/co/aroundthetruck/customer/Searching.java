package kr.co.aroundthetruck.customer;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;


public class Searching extends Activity {

    ImageButton searching;
    EditText seachEdit;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.searching);

        searching = (ImageButton)findViewById(R.id.button2);
        seachEdit = (EditText)findViewById(R.id.editText3);

        searching.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent data = new Intent();
                data.putExtra("search",seachEdit.getText().toString());
                setResult(Activity.RESULT_OK, data);
                finish();
            }
        });
    }

}
