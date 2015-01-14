package kr.co.aroundthetruck.customer;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;


public class Searching extends Activity {

    ImageButton searching;
    EditText seachEdit;
    Intent data;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.searching);

        searching = (ImageButton)findViewById(R.id.button2);
        seachEdit = (EditText)findViewById(R.id.editText3);

        searching.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                data = new Intent();
                data.putExtra("search",seachEdit.getText().toString());
                setResult(Activity.RESULT_OK, data);
                finish();
            }
        });

       getActionBar().setHomeButtonEnabled(true);
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle presses on the action bar items

        switch (item.getItemId()) {
            case android.R.id.home:

                data = new Intent();
                setResult(Activity.RESULT_CANCELED);
                finish();
                    return true;


            default:
                return super.onOptionsItemSelected(item);
        }
    }

}
