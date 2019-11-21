package homepage;

import com.jfoenix.controls.JFXButton;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;

public class consumer_table implements Initializable {

    @FXML
    private TableView<consumer_modal_table> consumer_table;

    @FXML
    private TableColumn<consumer_modal_table, String> consumer_id_col;

    @FXML
    private TableColumn<consumer_modal_table, String> consumer_name_col;

    @FXML
    private TableColumn<consumer_modal_table, Integer> consumer_cnumber_col;

    @FXML
    private TableColumn<consumer_modal_table, String> consumer_address_col;

    @FXML
    private TableColumn<consumer_modal_table, String> consumer_accnumber_col;

    @FXML
    private TableColumn<consumer_modal_table, String> consumer_gstin_col;


    @FXML
    public JFXButton add_product;

    @FXML
    private JFXButton product_delete;


    @FXML
    private JFXButton product1back;



    ObservableList<consumer_modal_table> oblist1 = FXCollections.observableArrayList();

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        try {
            Connection con = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/inventory_db", "root", "");

            ResultSet rs = con.createStatement().executeQuery("Select * from tbl_customer_details");
           /* while(rs.next()) {
                System.out.println(rs.getString(1)+" "+rs.getString(2)+" "+rs.getInt(3)+" "+rs.getString(4)+" "+rs.getString(5)+" "+rs.getString(6));
            }*/

            while (rs.next()) {
                oblist1.add(new consumer_modal_table(rs.getString(1), rs.getString(
                        2) , rs.getInt(3), rs.getString(
                        4), rs.getString(5),rs.getString(6)));
            }

           /* for(consumer_modal_table oblis : oblist) {
                System.out.println(oblis.getSelling_price());
            }*/
        } catch (
                SQLException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText("Oops something went wrong!!");
            alert.setContentText(e.getMessage());
            alert.show();
        }
        consumer_id_col.setCellValueFactory(new PropertyValueFactory<>("consumer_id"));
        consumer_name_col.setCellValueFactory(new PropertyValueFactory<>("consumer_name"));
        consumer_cnumber_col.setCellValueFactory(new PropertyValueFactory<>("address"));
        consumer_address_col.setCellValueFactory(new PropertyValueFactory<>("ph_no"));
        consumer_accnumber_col.setCellValueFactory(new PropertyValueFactory<>("acc_no"));
        consumer_gstin_col.setCellValueFactory(new PropertyValueFactory<>("gstin"));


        consumer_table.setItems(oblist1);
    }

    public void add_consumer(ActionEvent event) throws Exception{

        Parent parent =  FXMLLoader.load(this.getClass().getResource("consumer.fxml"));
        Scene scene = new Scene(parent,800,500);
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(scene);
        window.show();

    }

    public void consumer_bck_act(ActionEvent event) {

        Parent parent = null;
        try {
            parent = FXMLLoader.load(this.getClass().getResource("homepage.fxml"));
        } catch (IOException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText("Oops something went wrong!!");
            alert.setContentText(e.getMessage());
            alert.show();
        }
        Scene scene = new Scene(parent,800,500);
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(scene);
        window.show();
    }

    @FXML
    void consumer_delete(ActionEvent event) {

        int selectedIndex = consumer_table.getSelectionModel().getSelectedIndex();
        consumer_modal_table id1 =consumer_table.getSelectionModel().getSelectedItem();

        String id = id1.getConsumer_id();

        ObservableList<consumer_modal_table>  single_product;

        single_product=consumer_table.getSelectionModel().getSelectedItems();
         System.out.print(id);
      System.out.println(single_product);
        if(selectedIndex >= 0){
     try {
         Connection con = DriverManager.getConnection(
                 "jdbc:mysql://localhost:3306/inventory_db", "root", "");

         String query1 = "DELETE FROM tbl_customer_details WHERE fld_customer_id = ?";
         PreparedStatement pst;
         pst = con.prepareStatement(query1);
         pst.setString(1, id);
         pst.execute();
         consumer_table.getItems().remove(selectedIndex);

     }catch (SQLException e){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText("Oops something went wrong!!");
            alert.setContentText(e.getMessage());
            alert.show();
        }
    }
}}
