package homepage;

public class sales_modal_ctr {

    String sales_id;
    String product_id;
    String customer_id;
    String s_date;
    int qty;

    public String getSales_id() {
        return sales_id;
    }

    public void setSales_id(String sales_id) {
        this.sales_id = sales_id;
    }

    public String getProduct_id() {
        return product_id;
    }

    public void setProduct_id(String product_id) {
        this.product_id = product_id;
    }

    public String getCustomer_id() {
        return customer_id;
    }

    public void setCustomer_id(String customer_id) {
        this.customer_id = customer_id;
    }

    public String getS_date() {
        return s_date;
    }

    public void setS_date(String s_date) {
        this.s_date = s_date;
    }

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }

    public sales_modal_ctr(String sales_id, String product_id, String customer_id, int qty, String s_date) {
        this.sales_id = sales_id;
        this.product_id = product_id;
        this.customer_id = customer_id;
        this.s_date = s_date;
        this.qty = qty;
    }
}
