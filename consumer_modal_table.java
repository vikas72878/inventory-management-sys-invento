package homepage;

public class consumer_modal_table {
    public consumer_modal_table(String consumer_id, String consumer_name, int ph_no, String address, String acc_no, String gstin) {
        this.consumer_id = consumer_id;
        this.consumer_name = consumer_name;
        this.gstin = gstin;
        this.acc_no = acc_no;
        this.address = address;
        this.ph_no = ph_no;
    }

    String consumer_id;

    public String getConsumer_id() {
        return consumer_id;
    }

    public void setConsumer_id(String consumer_id) {
        this.consumer_id = consumer_id;
    }

    public String getConsumer_name() {
        return consumer_name;
    }

    public void setConsumer_name(String consumer_name) {
        this.consumer_name = consumer_name;
    }

    public String getGstin() {
        return gstin;
    }

    public void setGstin(String gstin) {
        this.gstin = gstin;
    }

    public String getAcc_no() {
        return acc_no;
    }

    public void setAcc_no(String acc_no) {
        this.acc_no = acc_no;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getPh_no() {
        return ph_no;
    }

    public void setPh_no(int ph_no) {
        this.ph_no = ph_no;
    }

    String consumer_name;
    String gstin;
    String acc_no;
    String address;
    int ph_no;


}
