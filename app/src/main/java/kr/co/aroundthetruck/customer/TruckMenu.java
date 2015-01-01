package kr.co.aroundthetruck.customer;

/**
 * Created by sumin on 2014-12-03.
 */
public class TruckMenu {
    private int menuImage;
    private String menuName;
    private  String menuPrice;

    public String getmenuName(){
        return menuName;
    }

    public void setmenuName(String menuName){
        this.menuName = menuName;
    }

    public int getmenuImage(){
        return  menuImage;
    }

    public void setmenuImage(int menuImage){
        this.menuImage = menuImage;
    }

    public String getmenuPrice(){
        return menuPrice;
    }

    public void setmenuPrice(String menuPrice){
        this.menuPrice = menuPrice;
    }

}
