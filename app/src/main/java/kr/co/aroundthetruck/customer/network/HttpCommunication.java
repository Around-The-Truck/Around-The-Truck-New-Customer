package kr.co.aroundthetruck.customer.network;

/**
 * Created by ebsud89 on 12/20/14.
 */

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URLEncodedUtils;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.HttpParams;
import org.apache.http.util.EntityUtils;

import android.util.Log;

import kr.co.aroundthetruck.customer.data.Reply;

public class HttpCommunication {

    private static final String serverURL = "http://165.194.35.161:3000/";
    private URI uri = null;

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
            Log.d("exception!", "exception");
            e.printStackTrace();
            buf = "Error";

        }
        return buf;

    }

    public String getAllTruck() {

        String resStr = "";
        String url = serverURL + "getTruckList";
        url = "http://165.194.35.161:3000/getTruckList";
        List<NameValuePair> param = new ArrayList<NameValuePair>();

        try {

            HttpClient http = new DefaultHttpClient();

            HttpParams params = http.getParams();

            HttpGet httpGet = new HttpGet(url);
            UrlEncodedFormEntity entityRequest = new UrlEncodedFormEntity(param, "UTF-8");

            HttpResponse responsePost = http.execute(httpGet);
            HttpEntity resEntity = responsePost.getEntity();

            resStr = EntityUtils.toString(resEntity);
            resStr = resStr.trim();

        } catch (Exception e) {
            Log.d("exception!", "exception");
            e.printStackTrace();
            resStr = "Error";

        }
        return resStr;
    }

    public String getTruckInfo(String truckIdx) {

        String resStr = "";
        String url = "http://165.194.35.161:3000/getTruckInfo";
        ArrayList<NameValuePair> param = new ArrayList<NameValuePair>();

        param.add(new BasicNameValuePair("truckIdx", truckIdx));

        try {

            HttpClient http = new DefaultHttpClient();

            HttpParams params = http.getParams();

            HttpGet httpGet = new HttpGet(url+"?"+URLEncodedUtils.format(param, "UTF-8"));

            HttpResponse responsePost = http.execute(httpGet);
            HttpEntity resEntity = responsePost.getEntity();

            resStr = EntityUtils.toString(resEntity);
            resStr = resStr.trim();

        } catch (Exception e) {
            Log.d("exception!", "exception");
            e.printStackTrace();
            resStr = "Error";

        }

        return resStr;
    }

    public String getFollowList(String phoneNum) {

        String resStr = "";
        String url = "http://165.194.35.161:3000/getFollowList";
        ArrayList<NameValuePair> param = new ArrayList<NameValuePair>();

        param.add(new BasicNameValuePair("phoneNum", phoneNum));

        try {

            HttpClient http = new DefaultHttpClient();

            HttpParams params = http.getParams();

            HttpGet httpGet = new HttpGet(url+"?"+URLEncodedUtils.format(param, "UTF-8"));

            HttpResponse responsePost = http.execute(httpGet);
            HttpEntity resEntity = responsePost.getEntity();

            resStr = EntityUtils.toString(resEntity);
            resStr = resStr.trim();

        } catch (Exception e) {
            Log.d("exception!", "exception");
            e.printStackTrace();
            resStr = "Error";

        }

        return resStr;
    }

    public String getPointHistory(String phoneNum) {

        String resStr = "";
        String url = serverURL + "getFollowList";
        ArrayList<NameValuePair> param = new ArrayList<NameValuePair>();

        param.add(new BasicNameValuePair("phoneNum", phoneNum));

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
            Log.d("exception!", "exception");
            e.printStackTrace();
            resStr = "Error";

        }
        return resStr;
    }

    public String addReply (Reply reply) {

        String resStr = "";


        return resStr;
    }

    public  String getReplyList (String articleIdx) {

        String resStr = "";




        return resStr;
    }

    public String getArticlList (String truckIdx) {

        String resStr = "";

        String writerType = "0";

        String url = "http://165.194.35.161:3000/getArticleList";
        ArrayList<NameValuePair> param = new ArrayList<NameValuePair>();

        param.add(new BasicNameValuePair("writer", truckIdx));
//        param.add(new BasicNameValuePair("writer_type"), )


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
            Log.d("exception!", "exception");
            e.printStackTrace();
            resStr = "Error";

        }
        return resStr;
    }

}
