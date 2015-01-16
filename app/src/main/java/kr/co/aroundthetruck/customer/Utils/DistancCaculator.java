package kr.co.aroundthetruck.customer.Utils;

/**
 * Created by ebsud89 on 1/14/15.
 */
public class DistancCaculator {

    double x1,x2,y1,y2;
    int distance;

    public DistancCaculator () {

    }

    public DistancCaculator(double x1, double x2, double y1, double y2) {
        this.x1 = x1;
        this.x2 = x2;
        this.y1 = y1;
        this.y2 = y2;
    }

    public String calculate() {

        double dis = Math.sqrt(Math.pow(Math.abs(this.x1-this.x2), 2) + Math.pow(Math.abs(this.y1-this.y2), 2));
        return String.valueOf((int)dis);

    }
}
