package homepage;

import com.jfoenix.controls.JFXButton;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.*;

public class loginController {


        @FXML
        public Label btnForgot;
        @FXML
        public TextField txtUsername;
        @FXML
        public   TextField  txtPassword;
        @FXML
        private JFXButton forgot_btn;
        @FXML
        private FontAwesomeIconView exla1;
        @FXML
        private Button login_signup_btn;



    public loginController() {
    }


    public void Login(ActionEvent event) throws Exception{

          //  txtUsername.setText("vikas");
           // txtPassword.setText("1234");
        ResultSet r4;
        int i;
        try {

            Connection con111 = null;

            con111 = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/inventory_db", "root", "");

            Statement stmt111 = con111.createStatement();
             r4=stmt111.executeQuery("select count(*) from login_details where user_name='"+txtUsername.getText()+"' and password ='"+txtPassword.getText()+"'");
             r4.next();
           i=r4.getInt(1);
        } catch (SQLException e12) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText("Oops Something went wrong!!!");
            alert.setContentText(e12.getMessage());
            alert.show();
            return;
        }
//        System.out.println(r4.getInt(1));

            if(txtUsername.getText()=="" || txtPassword.getText()==""){
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setHeaderText("Oops Something went wrong!!!");
                alert.setContentText("Enter required fields");
                alert.show();
                return;

            }
            if (1 == i) {
                btnForgot.setText("login success");

                Parent parent =  FXMLLoader.load(this.getClass().getResource("homepage.fxml"));
                Scene scene = new Scene(parent,800,500);
                Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
                window.setScene(scene);
                window.show();
            }
            else {
                btnForgot.setText("Wrong Credentials");
                btnForgot.setOpacity(1);
                exla1.setOpacity(1);
         }
    }

    @FXML
    void forgot_btn(ActionEvent event) throws Exception {
        Parent parent =  FXMLLoader.load(this.getClass().getResource("forgot.fxml"));
        Scene scene = new Scene(parent,800,500);
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(scene);
        window.show();

    }
    @FXML
    void login_signup_btn(ActionEvent event) {
        Parent parent = null;
        try {
            parent = FXMLLoader.load(this.getClass().getResource("sign.fxml"));
        } catch (IOException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText("Oops something went wrong !!");
            alert.setContentText(e.getMessage());
            alert.show();
        }
        Scene scene = new Scene(parent,800,500);
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(scene);
        window.show();

    }

}


