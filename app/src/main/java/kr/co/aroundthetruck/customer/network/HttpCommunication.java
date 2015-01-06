package kr.co.aroundthetruck.customer.network;

/**
 * Created by ebsud89 on 12/20/14.
 */

import java.util.ArrayList;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.HttpParams;
import org.apache.http.util.EntityUtils;

import android.util.Log;

public class HttpCommunication {

    private static final String serverURL = "http://165.194.35.161:3000/";

    public String doPost(ArrayList<NameValuePair> param, String context) {

        String buf = "";
        String url = "http://165.194.35.161:3000/getTruckList";

        // fill in Context
        url += context;

        try {

            HttpClient http = new DefaultHttpClient();

            HttpParams params = http.getParams();

            HttpPost httpPost = new HttpPost(url);
            UrlEncodedFormEntity entityRequest = new UrlEncodedFormEntity(param, "UTF-8");

            httpPost.setEntity(entityRequest);

            HttpResponse responsePost = http.execute(httpPost);
            HttpEntity resEntity = responsePost.getEntity();

            buf = EntityUtils.toString(resEntity);
            buf = buf.trim();

        } catch (Exception e) {
            Log.d("exception!","exception");
            e.printStackTrace();
            buf = "Error";

        }
        return buf;

    }

    public String getAllTruck() {

        String resStr = "";
        String url  = serverURL + "getTruckList";
        ArrayList<NameValuePair> param = new ArrayList<NameValuePair>();

        try {

            HttpClient http = new DefaultHttpClient();

            HttpParams params = http.getParams();

            HttpPost httpPost = new HttpPost(url);
            UrlEncodedFormEntity entityRequest = new UrlEncodedFormEntity(param, "UTF-8");

            httpPost.setEntity(entityRequest);

            HttpResponse responsePost = http.execute(httpPost);
            HttpEntity resEntity = responsePost.getEntity();

            resStr = EntityUtils.toString(resEntity);
            resStr = resStr.trim();

        } catch (Exception e) {
            Log.d("exception!","exception");
            e.printStackTrace();
            resStr = "Error";

        }
        return resStr;
    }
}
