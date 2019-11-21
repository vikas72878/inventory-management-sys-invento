package homepage;

public class purchase_modal_ctr {

    String purchase_id;
    String product_id;
    String supplier_id;

    public String getPurchase_id() {
        return purchase_id;
    }

    public void setPurchase_id(String purchase_id) {
        this.purchase_id = purchase_id;
    }

    public String getProduct_id() {
        return product_id;
    }

    public void setProduct_id(String product_id) {
        this.product_id = product_id;
    }

    public String getSupplier_id() {
        return supplier_id;
    }

    public void setSupplier_id(String supplier_id) {
        this.supplier_id = supplier_id;
    }

    public String getP_date() {
        return p_date;
    }

    public void setP_date(String p_date) {
        this.p_date = p_date;
    }

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }

    public purchase_modal_ctr(String purchase_id, String product_id, String supplier_id, int qty, String p_date) {
        this.purchase_id = purchase_id;
        this.product_id = product_id;
        this.supplier_id = supplier_id;
        this.p_date = p_date;
        this.qty = qty;
    }

    String p_date;
    int qty;
}
