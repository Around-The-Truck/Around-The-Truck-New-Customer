package kr.co.aroundthetruck.customer.data;

/**
 * Created by ebsud89 on 12/20/14.
 */
public class Reply {

    int idx;

    String contents;

    String writer;
    int writer_type;

    int article_idx;

    String reg_date;

    public Reply() {

    }

    public Reply(int idx, String contents, String writer, int writer_type, int article_idx, String reg_date) {
        this.idx = idx;
        this.contents = contents;
        this.writer = writer;
        this.writer_type = writer_type;
        this.article_idx = article_idx;
        this.reg_date = reg_date;
    }

    public int getIdx() {
        return idx;
    }

    public void setIdx(int idx) {
        this.idx = idx;
    }

    public String getContents() {
        return contents;
    }

    public void setContents(String contents) {
        this.contents = contents;
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

    public int getArticle_idx() {
        return article_idx;
    }

    public void setArticle_idx(int article_idx) {
        this.article_idx = article_idx;
    }

    public String getReg_date() {
        return reg_date;
    }

    public void setReg_date(String reg_date) {
        this.reg_date = reg_date;
    }
}
