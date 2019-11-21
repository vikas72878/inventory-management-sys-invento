package homepage;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;

public class sales_ctr implements Initializable {

    @FXML
    private JFXTextField customer_id;

    @FXML
    private JFXTextField quantity;

    @FXML
    public JFXButton sales_details_btn;

    @FXML
    public JFXDatePicker date;

    @FXML
    private JFXTextField sales_id;

    @FXML
    private JFXTextField product_id;

    @FXML
    private JFXButton sales_add_back_btn;


    String p1,p2,p4;
    int p3;
    String date12 ;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            Connection con = null;

            con = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/inventory_db", "root", "");

            ResultSet count = con.createStatement().executeQuery("Select count(*)+1 from tbl_sales_details");
            if (count.next()) {
                String sum = "S";
                sum = sum + count.getString("count(*)+1");
                sales_id.setText(sum);

                //System.out.println(sum);
                //System.out.println(count.getString("count(*)"));
            }
        } catch (SQLException e1) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText("Oops something went wrong!!!");
            alert.setContentText(e1.getMessage());
            alert.show();
        }
    }



    public void sales_add_back_btn(ActionEvent event) {
        Parent parent = null;
        try {
            parent = FXMLLoader.load(this.getClass().getResource("salestable.fxml"));
        } catch (IOException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText("Oops Something went wrong!!!");
            alert.setContentText(e.getMessage());
            alert.show();
        }
        Scene scene = new Scene(parent,800,500);
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(scene);
        window.show();
    }

    public void sales_details_btn(ActionEvent event) {

        try {
            Connection con= DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/inventory_db","root","");
            Statement stmt=con.createStatement();
            p4=sales_id.getText();
            p1=product_id.getText();
            p2=customer_id.getText();
            p3= Integer.parseInt(quantity.getText());
            date12 =date.getValue().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
            // LocalDate value = date.getValue();
            //System.out.println(value);

            stmt.executeUpdate("insert into tbl_sales_details values('"+p4+"','"+p1+"','"+p2+"','"+p3+"','"+date12+"')");
            ResultSet r =stmt.executeQuery("select fld_stock from tbl_product_entry where fld_product_id='"+p1+"'");
            r.next();
            int i=   r.getInt(1);
            i=i-p3;
            stmt.executeUpdate("update tbl_product_entry SET fld_stock='"+i+"' where fld_product_id='"+p1+"'");
        } catch (SQLException e1) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText("Oops something went wrong!!!");
            alert.setContentText(e1.getMessage());
            alert.show();
        }

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setHeaderText("Status:");
        alert.setContentText("Purchase added successfully! ");
        alert.show();
    }
}
