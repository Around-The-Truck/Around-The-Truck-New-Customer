package kr.co.aroundthetruck.customer.network;

/**
 * Created by ebsud89 on 12/20/14.
 */

import android.util.Log;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.apache.http.Header;
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

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

import kr.co.aroundthetruck.customer.TruckCallback;
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

    public void getAllTruck(final TruckCallback callback) {

        String resStr = "";
        String url = "http://165.194.35.161:3000/getTruckList";
        List<NameValuePair> param = new ArrayList<NameValuePair>();


        AsyncHttpClient client = new AsyncHttpClient();
        client.get(url, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int i, Header[] headers, byte[] bytes) {
                callback.onTruckLoad(bytes);

            }

            @Override
            public void onFailure(int i, Header[] headers, byte[] bytes, Throwable throwable) {

            }
        });
    }

    public void getTruckByAddress(String address,final TruckCallback callback) {

        String resStr = "";
        String url = "http://165.194.35.161:3000/getTruckList?addrStr="+address;
        List<NameValuePair> param = new ArrayList<NameValuePair>();

        param.add(new BasicNameValuePair("addStr", address));


        AsyncHttpClient client = new AsyncHttpClient();
        client.get(url, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int i, Header[] headers, byte[] bytes) {
                callback.onTruckLoad(bytes);

            }

            @Override
            public void onFailure(int i, Header[] headers, byte[] bytes, Throwable throwable) {

            }
        });
    }


    public void getTruckInfo(final String truckIdx, final TruckCallback callback) {

        String resStr = "";
        String url = "http://165.194.35.161:3000/getTruckInfo?truckIdx=" + truckIdx;
        ArrayList<NameValuePair> param = new ArrayList<NameValuePair>();

        param.add(new BasicNameValuePair("truckIdx", truckIdx));

        AsyncHttpClient client = new AsyncHttpClient();
        client.get(url, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int i, Header[] headers, byte[] bytes) {
                callback.onTruckLoad(bytes);

            }

            @Override
            public void onFailure(int i, Header[] headers, byte[] bytes, Throwable throwable) {

            }
        });
    }


    public void getFollowList(final String phoneNum,final TruckCallback callback) {

        String url = "http://165.194.35.161:3000/getFollowList?phoneNum=" + phoneNum;
        ArrayList<NameValuePair> param = new ArrayList<NameValuePair>();

        param.add(new BasicNameValuePair("phoneNum", phoneNum));

        AsyncHttpClient client = new AsyncHttpClient();
        client.get(url, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int i, Header[] headers, byte[] bytes) {
                callback.onTruckLoad(bytes);

            }

            @Override
            public void onFailure(int i, Header[] headers, byte[] bytes, Throwable throwable) {

            }
        });
    }

    public void getTruckListByName(final String truckName,final TruckCallback callback) {

        String url = "http://165.194.35.161:3000/getTruckList?truckName=" + truckName;
        ArrayList<NameValuePair> param = new ArrayList<NameValuePair>();

        param.add(new BasicNameValuePair("phoneNum", truckName));

        AsyncHttpClient client = new AsyncHttpClient();
        client.get(url, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int i, Header[] headers, byte[] bytes) {
                callback.onTruckLoad(bytes);

            }

            @Override
            public void onFailure(int i, Header[] headers, byte[] bytes, Throwable throwable) {

            }
        });
    }

    public void getPointHistory(String phoneNum,final TruckCallback callback) {

        String resStr = "";
        String url = "http://165.194.35.161:3000/getPointHistory?phoneNum=" + phoneNum;
        ArrayList<NameValuePair> param = new ArrayList<NameValuePair>();

        param.add(new BasicNameValuePair("phoneNum", phoneNum));

        AsyncHttpClient client = new AsyncHttpClient();
        client.get(url, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int i, Header[] headers, byte[] bytes) {
                callback.onTruckLoad(bytes);

            }

            @Override
            public void onFailure(int i, Header[] headers, byte[] bytes, Throwable throwable) {

            }
        });
    }

    public String addReply(Reply reply) {

        String resStr = "";


        return resStr;
    }

    public void getReplyList(String articleIdx, final TruckCallback callback) {

        String url = "http://165.194.35.161:3000/getReplyList";

        ArrayList<NameValuePair> param = new ArrayList<NameValuePair>();

//        param.add(new BasicNameValuePair("articleIdx", articleIdx));
        RequestParams rp = new RequestParams("articleIdx", articleIdx);


        AsyncHttpClient client = new AsyncHttpClient();
        client.post(url, rp, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int i, Header[] headers, byte[] bytes) {
                callback.onTruckLoad(bytes);
            }

            @Override
            public void onFailure(int i, Header[] headers, byte[] bytes, Throwable throwable) {

            }
        });

    }

    public String getArticlList(final String truckIdx, final TruckCallback callback) {

        String resStr = "";

        String writerType = "0";

        String url = "http://165.194.35.161:3000/getArticleList";

        ArrayList<NameValuePair> param = new ArrayList<NameValuePair>();

        RequestParams rp = new RequestParams("writer", truckIdx);

        AsyncHttpClient client = new AsyncHttpClient();
        client.post(url, rp, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int i, Header[] headers, byte[] bytes) {
                callback.onTruckLoad(bytes);
            }

            @Override
            public void onFailure(int i, Header[] headers, byte[] bytes, Throwable throwable) {

            }
        });
        return resStr;
    }

    public String getMenuList(final String truckIdx, final TruckCallback callback) {

        String resStr = "";

        String url = "http://165.194.35.161:3000/getMenuList";

        ArrayList<NameValuePair> param = new ArrayList<NameValuePair>();

        RequestParams rp = new RequestParams();

        rp.put("truckIdx",truckIdx);

        AsyncHttpClient client = new AsyncHttpClient();
        client.post(url, rp, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int i, Header[] headers, byte[] bytes) {
                callback.onTruckLoad(bytes);
            }

            @Override
            public void onFailure(int i, Header[] headers, byte[] bytes, Throwable throwable) {

            }
        });

        return resStr;
    }
}
