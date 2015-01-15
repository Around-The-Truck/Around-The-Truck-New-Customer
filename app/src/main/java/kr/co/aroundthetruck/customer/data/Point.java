package kr.co.aroundthetruck.customer.data;

/**
 * Created by sumin on 2014-12-28.
 */
public class Point {

    String brand;
    int mpoint;
    String date;
    String brand_image;

    public Point() {

    }

    public Point(String brand, String brand_image, int mpoint, String date){

        this.brand_image = brand_image;
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
