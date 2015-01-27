package kr.co.aroundthetruck.customer.data;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by ebsud89 on 12/20/14.
 */
public class Article {

    int idx;
    String fileName; //acticle 사진
    String truck_filename; //트럭 사진 = 주인사진

    int truckIdx;
    String contents; //내용

    int like;
    String reg_date;

    public String replies;

    //
    public int repliesCount;

    public Article(int idx, String fileName,String truck_filename,int truckIdx,String contents, int like,String reg_date,String replies){

        this.idx = idx;
        this.fileName = fileName;
        this.truck_filename = truck_filename;
        this.truckIdx = truckIdx;
        this.contents = contents;
        Log.d("sumin",contents);
        this.like = like;
        this.reg_date = reg_date;
        this.replies = replies;
        Log.d("sumin_replies",replies);
    }

    public int getIdx(){return idx;}
    public String getFileName(){return fileName;}
    public String getTruck_filename(){return truck_filename;}
    public String getReg_date(){
        //가공 해야함
        return reg_date;}
    public String getLike(){ return String.valueOf(like);
    }
    public ArrayList<Reply> getReplyArrayList() throws UnsupportedEncodingException, JSONException {

        return settingReplyList(replies);
    }
    public String getRepliesCount(){ return String.valueOf(repliesCount);}

    public ArrayList<Reply> settingReplyList(String bytes) throws JSONException, UnsupportedEncodingException {

        ArrayList<Reply> replyArrayList = new ArrayList<>();

        JSONArray arr = new JSONArray(new String(bytes));

        Reply reply = null;
        repliesCount = arr.length();

        for (int j=0; j<arr.length(); j++) {

            reply = new Reply(
                    arr.getJSONObject(j).getInt("r_idx"),
                    arr.getJSONObject(j).getString("r_contents"),
                    arr.getJSONObject(j).getString("r_writer_name"),
                    URLEncoder.encode(arr.getJSONObject(j).getString("r_writer_filename"), "UTF-8").replaceAll("\\+", "%20"),
                    arr.getJSONObject(j).getString("r_reg_date")
            );


            replyArrayList.add(reply);
        }

        return replyArrayList;
    }


}
