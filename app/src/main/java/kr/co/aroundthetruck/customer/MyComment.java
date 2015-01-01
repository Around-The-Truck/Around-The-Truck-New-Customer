package kr.co.aroundthetruck.customer;

/**
 * Created by sumin on 2014-12-03.
 */
public class MyComment {
    private int userImage;
    private String userName;
    private  String userComment;

    public String getUserName(){
            return userName;
    }

    public void setUserName(String userName){
        this.userName = userName;
    }

    public int getUserImage(){
        return userImage;
    }

    public void setUserImage(int userImage){
        this.userImage = userImage;
    }

    public String getUserComment(){
        return userComment;
    }

    public void setUserComment(String userComment){
        this.userComment = userComment;
    }


}
