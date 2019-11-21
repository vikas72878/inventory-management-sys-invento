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

public class consumer_detailsctr implements Initializable {
    @FXML
    public Button product_entry_btn;
    @FXML
    public JFXTextField consumer_id;
    @FXML
    public JFXTextField consumer_name;
    @FXML
    public JFXTextField phone_no;
    @FXML
    public JFXTextField address;
    @FXML
    public JFXTextField account_no;
    @FXML
    public JFXTextField gstin;
    @FXML
    private FontAwesomeIconView consumer_add_back;


    String p4,p1,p2,p5,p6;
    int p3;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            Connection con = null;

            con = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/inventory_db", "root", "");

            ResultSet count = con.createStatement().executeQuery("Select count(*)+1 from tbl_customer_details");
            if (count.next()) {
                String sum = "C";
                sum = sum + count.getString("count(*)+1");
                consumer_id.setText(sum);

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

    public void consumer_details_btn(ActionEvent event) throws Exception {

        Connection con=DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/inventory_db","root","");
        Statement stmt=con.createStatement();
        p1=consumer_id.getText();
        p2=consumer_name.getText();
        p3= Integer.parseInt(phone_no.getText());
        p4= address.getText();
        p5=account_no.getText();
        p6=gstin.getText();

        //System.out.println(p1);
        stmt.executeUpdate("insert into tbl_customer_details values('"+p1+"','"+p2+"','"+p3+"','"+p4+"','"+p5+"'," +
                "'"+p6+"')");

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setHeaderText("Status:");
        alert.setContentText("Product added successfully! ");
        alert.show();


    }

    public void consumer_add_back(ActionEvent event) {

        Parent parent = null;
        try {
            parent = FXMLLoader.load(this.getClass().getResource("consumertable.fxml"));
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

}
