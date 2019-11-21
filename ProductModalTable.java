package homepage;

public class ProductModalTable {
    String product_id,product_name,remarks,unit;
    int stock,selling_price;

    public String getProduct_id() {
        return product_id;
    }

    public void setProduct_id(String product_id) {
        this.product_id = product_id;
    }

    public String getProduct_name() {
        return product_name;
    }

    public void setProduct_name(String product_name) {
        this.product_name = product_name;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public int getSelling_price() {
        return selling_price;
    }

    public void setSelling_price(int selling_price) {
        this.selling_price = selling_price;
    }

    public ProductModalTable(String product_id, String product_name, int selling_price, String remarks, String unit, int stock  ) {
        this.product_id = product_id;
        this.product_name = product_name;
        this.unit = unit;
        this.selling_price = selling_price;
        this.stock = stock;
        this.remarks = remarks;

    }



}
