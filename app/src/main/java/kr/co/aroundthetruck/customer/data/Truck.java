package kr.co.aroundthetruck.customer.data;

/**
 * Created by ebsud89 on 12/20/14.
 */
public class Truck {

    int idx;
    String name; //

    double gps_longtitude;
    double gps_latitude;
    double gps_altitude;
    String gps_address;

    int todays_sum;

    int follow_count;

    int start_yn;
    int takeout_yn;
    int cansit_yn;
    int card_yn;
    int reserve_yn;


    int group_order_yn;
    int always_open_yn;
    String photo_id;

    String main_position;
    String category;

    String reg_date;

    // Constructor (ebsud89 _ 150103)
    public Truck() {
    }

    public int getFollow_count() {
        return follow_count;
    }

    public void setFollow_count(int follow_count) {
        this.follow_count = follow_count;
    }

    public Truck(int idx, String name, double gps_longtitude, double gps_latitude, double gps_altitude, String gps_address, int todays_sum, int follow_count, int start_yn, int takeout_yn, int cansit_yn, int card_yn, int reserve_yn, int group_order_yn, int always_open_yn, String photo_id, String main_position, String category, String reg_date) {
        this.idx = idx;
        this.name = name;
        this.gps_longtitude = gps_longtitude;
        this.gps_latitude = gps_latitude;
        this.gps_altitude = gps_altitude;
        this.gps_address = gps_address;
        this.todays_sum = todays_sum;
        this.follow_count = follow_count;
        this.start_yn = start_yn;
        this.takeout_yn = takeout_yn;
        this.cansit_yn = cansit_yn;
        this.card_yn = card_yn;
        this.reserve_yn = reserve_yn;
        this.group_order_yn = group_order_yn;
        this.always_open_yn = always_open_yn;
        this.photo_id = photo_id;
        this.main_position = main_position;
        this.category = category;
        this.reg_date = reg_date;
    }

    public int getIdx() {
        return idx;
    }

    public void setIdx(int idx) {
        this.idx = idx;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getGps_longtitude() {
        return gps_longtitude;
    }

    public void setGps_longtitude(double gps_longtitude) {
        this.gps_longtitude = gps_longtitude;
    }

    public double getGps_latitude() {
        return gps_latitude;
    }

    public void setGps_latitude(double gps_latitude) {
        this.gps_latitude = gps_latitude;
    }

    public double getGps_altitude() {
        return gps_altitude;
    }

    public void setGps_altitude(double gps_altitude) {
        this.gps_altitude = gps_altitude;
    }

    public String getGps_address() {
        return gps_address;
    }

    public void setGps_address(String gps_address) {
        this.gps_address = gps_address;
    }

    public int getTodays_sum() {
        return todays_sum;
    }

    public void setTodays_sum(int todays_sum) {
        this.todays_sum = todays_sum;
    }

    public int getStart_yn() {
        return start_yn;
    }

    public void setStart_yn(int start_yn) {
        this.start_yn = start_yn;
    }

    public int getTakeout_yn() {
        return takeout_yn;
    }

    public void setTakeout_yn(int takeout_yn) {
        this.takeout_yn = takeout_yn;
    }

    public int getCansit_yn() {
        return cansit_yn;
    }

    public void setCansit_yn(int cansit_yn) {
        this.cansit_yn = cansit_yn;
    }

    public int getCard_yn() {
        return card_yn;
    }

    public void setCard_yn(int card_yn) {
        this.card_yn = card_yn;
    }

    public int getReserve_yn() {
        return reserve_yn;
    }

    public void setReserve_yn(int reserve_yn) {
        this.reserve_yn = reserve_yn;
    }

    public int getGroup_order_yn() {
        return group_order_yn;
    }

    public void setGroup_order_yn(int group_order_yn) {
        this.group_order_yn = group_order_yn;
    }

    public int getAlways_open_yn() {
        return always_open_yn;
    }

    public void setAlways_open_yn(int always_open_yn) {
        this.always_open_yn = always_open_yn;
    }

    public String getPhoto_id() {
        return photo_id;
    }

    public void setPhoto_id(String photo_id) {
        this.photo_id = photo_id;
    }

    public String getMain_position() {
        return main_position;
    }

    public void setMain_position(String main_position) {
        this.main_position = main_position;
    }



    public String getCategory() {
        return category;
    }


    public String getReg_date() {
        return reg_date;
    }

    public void setReg_date(String reg_date) {
        this.reg_date = reg_date;
    }

}
