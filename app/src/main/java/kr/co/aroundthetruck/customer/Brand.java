package kr.co.aroundthetruck.customer;

import kr.co.aroundthetruck.customer.data.Truck;

/**
 * Created by sumin on 2014-12-03.
 */
public class Brand {

    private int brandIdx;
    private int brandImage;
    private String brandName;
    private  String brandDistance;
    private int like;
    private String category;


    Brand(int brandIdx,int brandImage,String brandName, String brandDistance, int like, String category){

        this.brandIdx = brandIdx;
        this.brandImage = brandImage;
        //this.brandName = trucker.getName();
        //this.brandDistance = trucker.getGps_address();
        this.brandName = brandName;
        this.brandDistance = brandDistance;
        //현재위치에서 트럭까지의 거리
        this.like = like;
        //좋아요 수
        this.category = category;
        //트럭 카테고리
    }

    public int getBrandImage(){
        return brandImage;
    }
    public String getBrandName(){
        return brandName;
    }
    public String getBrandDistance(){
        return brandDistance;
    }
    public int getLike(){return  like; }
    public String getCategory(){return category; }

    public int getBrandIdx() {
        return brandIdx;
    }

    public void setBrandIdx(int brandIdx) {
        this.brandIdx = brandIdx;
    }
    public String getBrandIdxString() {
        return String.valueOf(getBrandIdx());
    }
}
