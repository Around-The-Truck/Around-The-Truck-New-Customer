package kr.co.aroundthetruck.customer.data;

/**
 * Created by sumin on 2014-12-28.
 */
public class Point {

    String brand;
    int mpoint;
    String date;

    public Point() {

    }

    public Point(String brand, int mpoint, String date){

        this.brand = brand;
        this.mpoint = mpoint;
        this.date = date;
    }

    public String getBrand(){
        return brand;
    }

    public int getMpoint(){
        return mpoint;
    }

    public String getDate(){
        return date;
    }
}
