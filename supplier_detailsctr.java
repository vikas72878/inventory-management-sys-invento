package homepage;


import com.jfoenix.controls.JFXTextField;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;

public class supplier_detailsctr implements Initializable {
    @FXML
    public Button product_entry_btn;
    @FXML
    public JFXTextField supplier_id;
    @FXML
    public JFXTextField supplier_name;
    @FXML
    public JFXTextField phone_no;
    @FXML
    public JFXTextField address;
    @FXML
    public JFXTextField account_no;
    @FXML
    public JFXTextField gstin;
    @FXML
    private FontAwesomeIconView supplier_add_back;


    String p4,p1,p2,p6;
    int p3,p5;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            Connection con = null;

            con = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/inventory_db", "root", "");

            ResultSet count = con.createStatement().executeQuery("Select count(*)+1 from tbl_supplier_details");
            if (count.next()) {
                String sum = "S";
                sum = sum + count.getString("count(*)+1");
                supplier_id.setText(sum);

               // System.out.println(sum);
                //System.out.println(count.getString("count(*)"));
            }
        } catch (SQLException e1) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText("Oops something went wrong!!!");
            alert.setContentText(e1.getMessage());
            alert.show();
        }



    }


    public void supplier_details_btn(ActionEvent event) throws Exception {

        Connection con=DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/inventory_db","root","");
        Statement stmt=con.createStatement();
        p1=supplier_id.getText();
        p2=supplier_name.getText();
        p3= Integer.parseInt(phone_no.getText());
        p4= address.getText();
        p5=Integer.parseInt(account_no.getText());
        p6=gstin.getText();

        //System.out.println(p1);
        stmt.executeUpdate("insert into tbl_supplier_details values('"+p1+"','"+p2+"','"+p3+"','"+p4+"','"+p5+"'," +
                "'"+p6+"')");

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setHeaderText("Status:");
        alert.setContentText("Product added successfully! ");
        alert.show();


    }

    public void supplier_add_back(ActionEvent event) {

        Parent parent = null;
        try {
            parent = FXMLLoader.load(this.getClass().getResource("suppliertable.fxml"));
        } catch (IOException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText("Oops something went wrong!!!");
            alert.setContentText(e.getMessage());
            alert.show();
        }
        Scene scene = new Scene(parent,800,500);
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(scene);
        window.show();
    }

}
