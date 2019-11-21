package homepage;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDrawer;
import com.jfoenix.controls.JFXHamburger;
import com.jfoenix.transitions.hamburger.HamburgerSlideCloseTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable {




    @FXML
    public JFXDrawer drawer;
    @FXML
    public JFXButton product_entry1;

    @FXML
    public VBox vbox;

    @FXML
    public JFXButton purchase_details;

    @FXML
    public JFXHamburger hamburger;

    @FXML
    public JFXButton supplier_details;

    @FXML
    public JFXButton barcode;

    @FXML
    public JFXButton sales_table_btn;

    @FXML
    private JFXButton purchase_graph_btn;

    @Override
    public void initialize(URL location, ResourceBundle resources) {


        drawer.setSidePane(vbox);
        drawer.setDefaultDrawerSize(450);
        drawer.setOverLayVisible(false);
        drawer.setResizableOnDrag(true);


        //HamburgerBackArrowBasicTransition h1 = new HamburgerBackArrowBasicTransition(hamburger);
        HamburgerSlideCloseTransition burgerTask = new HamburgerSlideCloseTransition(hamburger);
        burgerTask.setRate(-1);
        hamburger.addEventHandler(MouseEvent.MOUSE_PRESSED, (e) -> {
            burgerTask.setRate(burgerTask.getRate() * -1);
            burgerTask.play();

            if(drawer.isClosed()){
                drawer.open();
            }
            else{
                drawer.close();
            }
        });

    }

    public void purchase_details(ActionEvent event) throws Exception{

        Parent parent = FXMLLoader.load(this.getClass().getResource("purchasetable.fxml"));
        Scene scene = new Scene(parent,800,500);
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(scene);
        window.show();

    }

    public void product_entry(ActionEvent event) throws Exception{

        Parent parent =  FXMLLoader.load(this.getClass().getResource("product1.fxml"));
        Scene scene = new Scene(parent,800,500);
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(scene);
        window.show();

    }

    public void supplier_details(ActionEvent event) throws Exception{
        Parent parent =  FXMLLoader.load(this.getClass().getResource("suppliertable.fxml"));
        Scene scene = new Scene(parent,800,500);
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(scene);
        window.show();

    }

    public void purchase_graph_btn(ActionEvent event) throws Exception{
        Parent parent =  FXMLLoader.load(this.getClass().getResource("purchase_graph.fxml"));
        Scene scene = new Scene(parent,800,500);
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(scene);
        window.show();

    }

/*    public void barcode(ActionEvent event) {
        Parent parent = null;
        try {
            parent = FXMLLoader.load(this.getClass().getResource("MainView.fxml"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        Scene scene = new Scene(parent,800,500);
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(scene);
        window.show();

    }*/

    public void customer_details(ActionEvent event) throws Exception{

        Parent parent =  FXMLLoader.load(this.getClass().getResource("consumertable.fxml"));
        Scene scene = new Scene(parent,800,500);
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(scene);
        window.show();

}
    public void barcode(ActionEvent event) throws Exception{

        Parent parent =  FXMLLoader.load(this.getClass().getResource("barcodep1 .fxml"));
        Scene scene = new  Scene(parent,800,500);
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(scene);
        window.show();

    }

    @FXML
    void logout_btn(ActionEvent event) throws IOException {
        Parent parent =  FXMLLoader.load(this.getClass().getResource("Login.fxml"));
        Scene scene = new Scene(parent,800,500);
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(scene);
        window.show();

    }

    public void sales_table_btn(ActionEvent event) throws Exception{

        Parent parent =  FXMLLoader.load(this.getClass().getResource("salestable.fxml"));
        Scene scene = new Scene(parent,800,500);
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(scene);
        window.show();

    }

}

