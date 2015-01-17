package kr.co.aroundthetruck.customer.Utils;

import android.location.Location;

/**
 * Created by ebsud89 on 1/14/15.
 */
public class DistancCaculator {

    double x1,x2,y1,y2;
    int distance;

    Location a,b;

    public DistancCaculator () {

    }

    public DistancCaculator(double x1, double x2, double y1, double y2) {
        this.x1 = x1;
        this.x2 = x2;
        this.y1 = y1;
        this.y2 = y2;

        a = new Location("truck");
        b = new Location("customer");
        a.setLatitude(y1);
        a.setLongitude(x1);
        b.setLatitude(y2);
        b.setLongitude(x2);

        this.distance = (int)a.distanceTo(b);
    }

    public String calculate() {


//        double dis = Math.sqrt(Math.pow(Math.abs(this.x1-this.x2), 2) + Math.pow(Math.abs(this.y1-this.y2), 2));
//        return String.valueOf((int)dis);

        return String.valueOf(this.distance);

    }

    public int getDistance() {
        return distance;
    }

    public void setDistance(int distance) {
        this.distance = distance;
    }

    public String getDistanceString() {

        if (distance < 1000)
            return distance + " m";
        else
            return (int)(distance/1000) + "." + ((int)(distance/100) - (int)(distance/1000)) + "km";
    }
}
