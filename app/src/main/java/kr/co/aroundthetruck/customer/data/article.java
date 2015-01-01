package kr.co.aroundthetruck.customer.data;

/**
 * Created by ebsud89 on 12/20/14.
 */
public class article {

    int idx;
    int photo_idx;

    String writer;
    int writer_type;

    String contents;
    String belong_to;   // 속한 트럭

    String reg_date;

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
}
