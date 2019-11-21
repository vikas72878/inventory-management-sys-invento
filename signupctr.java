package homepage;

//import com.jfoenix.controls.JFXButton;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXCheckBox;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class signupctr {
    @FXML
    public JFXTextField first_name;

    @FXML
    public JFXTextField email;

    @FXML
    public JFXButton signup_entry_btn;

    @FXML
    private JFXButton signup_back_btn1;

    @FXML
    private FontAwesomeIconView supplier_back1;

    @FXML
    private JFXPasswordField password;

    @FXML
    private JFXPasswordField repassword;

    @FXML
    private JFXCheckBox check_box;

    @FXML
    void signup_back_btn1(ActionEvent event) {

        Parent parent = null;
        try {
            parent = FXMLLoader.load(this.getClass().getResource("login.fxml"));
        } catch (IOException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText("OOps something  went wrong!!");
            alert.setContentText(e.getMessage());
            alert.show();
        }
        Scene scene = new Scene(parent,800,500);
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(scene);
        window.show();

    }

    @FXML
    void signup_entry_btn(ActionEvent event) {

       String p1=first_name.getText();
        String p2=password.getText();
        String p3=email.getText();
        String p4 =repassword.getText();

        if(first_name.getText()=="" || password.getText()==""){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText("Oops Something went wrong!!");
            alert.setContentText("Enter required fields");
            alert.show();
            return;

        }


        if(!check_box.isSelected()){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText("OOps something  went wrong!!");
            alert.setContentText(" Enable terms and conditions");
            alert.show();
            return;

        }
        if (!p4.equals(p2)) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText("OOps something  went wrong!!");
            alert.setContentText("Different passwords in fields password and confirm password");
            alert.show();
            return;

        }
        try {

            Connection con111 = null;

            con111 = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/inventory_db", "root", "");

        Statement stmt11 = con111.createStatement();
            stmt11.executeUpdate("insert into login_details values('" + p1 + "','" + p2
                    + "','" + p3 + "')");
        } catch (SQLException e12) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText("Oops something went wrong!!!");
            alert.setContentText(e12.getMessage());
            alert.show();
            return;
        }
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setHeaderText("Status:");
        alert.setContentText("User added successfully! ");
        alert.show();

    }

}
