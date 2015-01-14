package kr.co.aroundthetruck.customer.data;

/**
 * Created by ebsud89 on 12/20/14.
 */
public class Article {

    int idx;
    int photo_idx;
    String filename;

    String writer;
    int writer_type;

    String contents;
    String belong_to; // 속한 트럭

    String reg_date;

    int likeNumber;
    int replyNumber;

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public void setLikeNumber(int likeNumber) {
        this.likeNumber = likeNumber;
    }

    public void setReplyNumber(int replyNumber) {
        this.replyNumber = replyNumber;
    }

    public Article(int idx, String filename, String writer, int writer_type, String contents, int likeNumber, String belong_to, String reg_date) {
        this.idx = idx;
        this.filename = filename;
        this.writer = writer;
        this.writer_type = writer_type;
        this.contents = contents;
        this.likeNumber = likeNumber;
        this.belong_to = belong_to;
        this.reg_date = reg_date;
    }

    public Article(int i, int i1, String sajhghn, int i2, String s, String s1, String s2, int likeNumber, int replyNumber) {
        this.idx = i;
        this.photo_idx =i1;
        this.writer = sajhghn;
        this.writer_type = i2;
        this.contents = s;
        this.belong_to = s1;
        this.reg_date = s2;
        this.likeNumber = likeNumber;
        this.replyNumber = replyNumber;

    }

    public int getIdx() {
        return idx;
    }

    public void setIdx(int idx) {
        this.idx = idx;
    }

    public int getPhoto_idx() {
        return photo_idx;
    }

    public void setPhoto_idx(int photo_idx) {
        this.photo_idx = photo_idx;
    }

    public String getWriter() {
        return writer;
    }

    public void setWriter(String writer) {
        this.writer = writer;
    }

    public int getWriter_type() {
        return writer_type;
    }

    public void setWriter_type(int writer_type) {
        this.writer_type = writer_type;
    }

    public String getContents() {
        return contents;
    }

    public void setContents(String contents) {
        this.contents = contents;
    }

    public String getBelong_to() {
        return belong_to;
    }

    public void setBelong_to(String belong_to) {
        this.belong_to = belong_to;
    }

    public String getReg_date() {
        return reg_date;
    }

    public void setReg_date(String reg_date) {
        this.reg_date = reg_date;
    }

    public int getLikeNumber(){ return likeNumber;}
    public int getReplyNumber(){return replyNumber;}
}
