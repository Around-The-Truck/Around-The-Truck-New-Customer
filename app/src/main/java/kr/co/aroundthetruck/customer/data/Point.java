package kr.co.aroundthetruck.customer.data;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by sumin on 2014-12-28.
 */
public class Point {

    int point;
    String date;

    public Point(int point,String date) {
        this.point = point;
        this.date = date;
    }

    public int getPoint(){
        return point;
    }

    public String getDate() throws ParseException {

        return transFormatDate(date);
    }

    public String transFormatDate(String date) throws ParseException {
        SimpleDateFormat transFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date to = transFormat.parse(date);

        transFormat = new SimpleDateFormat("yyyy.MM.dd");

        return transFormat.format(to);
    }

}
