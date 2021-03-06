package kr.co.aroundthetruck.customer;

import kr.co.aroundthetruck.customer.data.Truck;

/**
 * Created by sumin on 2014-12-03.
 */
public class Brand {

    private int brandIdx;
    private String brandImage;
    private String brandName;
    private  String brandDistance;
    private int like;
    private String category_big;
    private String category_small;
    private boolean likeOrNot;
    private int truckOnOff;

    Brand(int brandIdx,String brandImage,String brandName, String brandDistance, int like,String category_big,String category_small,boolean likeOrNot, int truckOnOff){

        this.brandIdx = brandIdx;
        this.brandImage = brandImage;
        //this.brandName = trucker.getName();
        //this.brandDistance = trucker.getGps_address();
        this.brandName = brandName;
        this.brandDistance = brandDistance;
        //현재위치에서 트럭까지의 거리
        this.like = like;
        //좋아요 수
        this.category_big = category_big;
        this.category_small = category_small;
        //트럭 카테고리

        this.likeOrNot = likeOrNot;
        this.truckOnOff = truckOnOff;

    }

    public Brand() {

    }

    public void setLikeOrNot(boolean likeOrNot){
        this.likeOrNot = likeOrNot;
    }

    public boolean getLikeOrNot(){
        return likeOrNot;
    }


    public String getBrandImage(){
        return brandImage;
    }
    public String getBrandName(){
        return brandName;
    }
    public String getBrandDistance(){
        return brandDistance;
    }
    public int getLike(){return  like; }
    public String getCategory_big(){return category_big; }
    public String getCategory(){return category_big+" / "+category_small;}

    public int getBrandIdx() {
        return brandIdx;
    }

    public void setBrandIdx(int brandIdx) {
        this.brandIdx = brandIdx;
    }
    public String getBrandIdxString() {
        return String.valueOf(getBrandIdx());
    }
    public boolean getTruckOnOff(){

        if(truckOnOff == 1){return true;}
        else{return false;}
    }
}
