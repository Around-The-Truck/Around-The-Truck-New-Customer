package kr.co.aroundthetruck.customer.network;

/**
 * Created by ebsud89 on 12/20/14.
 */

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;

public class ServerRequest extends Thread {
    private HttpClient http = null;
    private HttpPost post = null;
    private String url = null;

    private ResponseHandler<String> mResHandler = null;
    private Handler mHandler = null;

    private HashMap<Object, Object> param = null; // 파라미터 임시변수

    public ServerRequest(String url, HashMap<Object, Object> param,
                         ResponseHandler<String> mResHandler, Handler mHandler) {
        this.url = url;
        this.param = param;
        this.mResHandler = mResHandler;
        this.mHandler = mHandler;
    }

    public void run() {

        try {
            http = new DefaultHttpClient();

            HttpParams params = http.getParams();
            HttpConnectionParams.setConnectionTimeout(params, 10000);
            HttpConnectionParams.setSoTimeout(params, 10000);

            Log.d("test", "요청 URL : " + url);
            post = new HttpPost(url);
            setParameter(param);

            http.execute(post, mResHandler);

        } catch (Exception e) {
            Message message = mHandler.obtainMessage();
            Bundle bundle = new Bundle();
            bundle.putString("result", "fail");
            message.setData(bundle);
            mHandler.sendMessage(message);
            Log.d("test", "요청 실패");
            Log.d("test", e.toString());
        }
    }

    public void setParameter(HashMap<Object, Object> param)
            throws UnsupportedEncodingException {
        if (param == null) {
            Log.d("test", "파라미터없음");
            return;
        }
        List<NameValuePair> nameValueParis = null; // 파라미터를 담는 리스트

        String hashKey = null;
        Iterator<Object> iter = null;
        nameValueParis = new ArrayList<NameValuePair>();

        iter = param.keySet().iterator();

        while (iter.hasNext()) {
            hashKey = (String) iter.next();
            nameValueParis.add(new BasicNameValuePair(hashKey, param.get(hashKey).toString()));
        }
        UrlEncodedFormEntity entityRequest = new UrlEncodedFormEntity(
                nameValueParis, "UTF-8");
        post.setEntity(entityRequest);
    }

}
