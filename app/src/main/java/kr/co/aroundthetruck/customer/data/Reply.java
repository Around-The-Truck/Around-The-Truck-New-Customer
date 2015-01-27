package kr.co.aroundthetruck.customer.data;

/**
 * Created by ebsud89 on 12/20/14.
 */
public class Reply {

    int r_idx;

    String r_contents;
    String r_writer_name;
    String r_writer_filename;
    String r_reg_date;


    public Reply(int r_idx, String r_contents, String r_writer_name, String r_writer_filename, String r_reg_date) {

        this.r_idx = r_idx;
        this.r_contents = r_contents;
        this.r_writer_name = r_writer_name;
        this.r_writer_filename = r_writer_filename;
        this.r_reg_date = r_reg_date;

    }

    public String getR_contents(){return r_contents;}
    public String getR_writer_name(){return r_writer_name;}
    public String getR_writer_filename(){return r_writer_filename;}


}
