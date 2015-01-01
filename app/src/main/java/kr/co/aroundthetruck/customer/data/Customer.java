package kr.co.aroundthetruck.customer.data;

/**
 * Created by ebsud89 on 12/20/14.
 */
public class Customer {

    String name;
    String phone;

    int gender;
    int age;

    String job;

    int point;
    int photo_profile;

    String reg_date;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public int getGender() {
        return gender;
    }

    public void setGender(int gender) {
        this.gender = gender;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }

    public int getPoint() {
        return point;
    }

    public void setPoint(int point) {
        this.point = point;
    }

    public int getPhoto_profile() {
        return photo_profile;
    }

    public void setPhoto_profile(int photo_profile) {
        this.photo_profile = photo_profile;
    }

    public String getReg_date() {
        return reg_date;
    }

    public void setReg_date(String reg_date) {
        this.reg_date = reg_date;
    }
}

