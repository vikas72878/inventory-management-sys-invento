package homepage;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;

public class productentryctr implements Initializable {
    @FXML
    public Button product_entry_btn;
    @FXML
    public JFXTextField product_id;
    @FXML
    public JFXTextField product_name;
    @FXML
    public JFXTextField purchase_price;
    @FXML
    public JFXTextField selling_price;
    @FXML
    public JFXTextField description;

    @FXML
    private ChoiceBox<String> choice_box;
    @FXML
    private JFXButton product_entryback;

    @FXML
    private JFXTextField stock;


    String p1, p2, p5,p6;
    int p3, p4,p7;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            Connection con = null;

            con = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/inventory_db", "root", "");


            ResultSet count = con.createStatement().executeQuery("Select count(*)+1 from tbl_product_entry");
            if (count.next()) {
                String sum = "P";
                sum = sum + count.getString("count(*)+1");
                product_id.setText(sum);

                //System.out.println(sum);
                //System.out.println(count.getString("count(*)"));
            }
        } catch (SQLException e1) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText("Oops something went wrong!!!");
            alert.setContentText(e1.getMessage());
            alert.show();
        }

        // for drop down
        choice_box.getItems().add("Units");
        choice_box.getItems().add("Meters");
        choice_box.getItems().add("Liters");
        choice_box.getItems().add("KG");

        choice_box.setValue("Units");



    }

    public void product_entry_btn (ActionEvent event) throws Exception {

        p1 = product_id.getText();
        p2 = product_name.getText();
       /* p3 = Integer.parseInt(purchase_price.getText());*/


       p4 = Integer.parseInt(selling_price.getText());
        p5 = description.getText();

        p6=choice_box.getValue();
        p7 = Integer.parseInt(stock.getText());

        //System.out.println(p1);
        try {
            Connection con = null;

            con = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/inventory_db", "root", "");
            Statement stmt = con.createStatement();
            stmt.executeUpdate("insert into tbl_product_entry values('" + p1 + "','" + p2
                    + "','" + p4 + "','" + p5 + "','" + p6 + "','" + p7 + "')");
        } catch (SQLException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText("Oops something went wrong!!!");
            alert.setContentText(e.getMessage());
            alert.show();
        }
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setHeaderText("Status:");
        alert.setContentText("Product added successfully! ");
        alert.show();


    }

    @FXML
    void product_entryback(ActionEvent event) {

        Parent parent = null;
        try {
            parent = FXMLLoader.load(this.getClass().getResource("product1.fxml"));
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
