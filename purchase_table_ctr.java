package homepage;

import com.jfoenix.controls.JFXButton;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
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

public class purchase_table_ctr implements Initializable {

    @FXML
    private TableView<purchase_modal_ctr> purchase_table;

    @FXML
    private TableColumn<purchase_modal_ctr, String> purchase_id_col;

    @FXML
    private TableColumn<purchase_modal_ctr, String> supplier_id_col;

    @FXML
    private TableColumn<purchase_modal_ctr, String> product_id_col;

    @FXML
    private TableColumn<purchase_modal_ctr, String> date_col;

    @FXML
    private TableColumn<purchase_modal_ctr, Integer> quantity_col;

    @FXML
    public JFXButton add_purchase;

    @FXML
    public JFXButton purchase_back_btn1;

    @FXML
    public JFXButton purcahse_delete;

    @FXML
    public FontAwesomeIconView purchase_back1;


    ObservableList<purchase_modal_ctr> oblist6 = FXCollections.observableArrayList();

        @Override
        public void initialize(URL location, ResourceBundle resources) {
            ResultSet rs;
            try {
                Connection con = DriverManager.getConnection(
                        "jdbc:mysql://localhost:3306/inventory_db", "root", "");

                rs = con.createStatement().executeQuery("Select * from tbl_purchase_details");
           /* while(rs.next()) {
                System.out.println(rs.getString(1)+" "+rs.getString(2)+" "+rs.getInt(3)+" "+rs.getString(4)+" "+rs.getString(5)+" "+rs.getString(6));
            }*/

                while (rs.next()) {
                    oblist6.add(new purchase_modal_ctr(rs.getString(1), rs.getString(
                            2) , rs.getString(3), rs.getInt(
                            4), rs.getString(5)));
                }
            } catch (
                    SQLException e) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setHeaderText("Oops !!!!!!!!!!!!!!");
                alert.setContentText(e.getMessage());
                alert.show();
            }
           /* for(purchase_modal_ctr oblis : oblist) {
                System.out.println(oblis.getSelling_price());
            }*/

            purchase_id_col.setCellValueFactory(new PropertyValueFactory<>("purchase_id"));
            supplier_id_col.setCellValueFactory(new PropertyValueFactory<>("supplier_id"));
            product_id_col.setCellValueFactory(new PropertyValueFactory<>("product_id"));
            date_col.setCellValueFactory(new PropertyValueFactory<>("p_date"));
            quantity_col.setCellValueFactory(new PropertyValueFactory<>("qty"));

            purchase_table.setItems(oblist6);
        }

        public void add_purchase(ActionEvent event) throws Exception,NullPointerException{
            Parent parent =  FXMLLoader.load(this.getClass().getResource("Purchase.fxml"));
            Scene scene = new Scene(parent,800,500);
            Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
            window.setScene(scene);
            window.show();

        }

        public void purchase_back_btn1(ActionEvent event) {

            Parent parent = null;
            try {
                parent = FXMLLoader.load(this.getClass().getResource("homepage.fxml"));
            } catch (IOException e) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setHeaderText("Oops something went wrong");
                alert.setContentText(e.getMessage());
                alert.show();
            }
            Scene scene = new Scene(parent,800,500);
            Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
            window.setScene(scene);
            window.show();
        }

    @FXML
    void purchase_delete(ActionEvent event) {

        int selectedIndex = purchase_table.getSelectionModel().getSelectedIndex();
        purchase_modal_ctr id1 =purchase_table.getSelectionModel().getSelectedItem();

        String id = id1.getPurchase_id();

        ObservableList<purchase_modal_ctr>  single_product;

        single_product=purchase_table.getSelectionModel().getSelectedItems();
        System.out.print(id);
        System.out.println(single_product);
        if(selectedIndex >= 0){
            try {
                Connection con = DriverManager.getConnection(
                        "jdbc:mysql://localhost:3306/inventory_db", "root", "");

                String query1 = "DELETE FROM tbl_purchase_details WHERE fld_purchase_id = ?";
                PreparedStatement pst;
                pst = con.prepareStatement(query1);
                pst.setString(1, id);
                pst.execute();
                purchase_table.getItems().remove(selectedIndex);

            }catch (SQLException e){
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setHeaderText("Oops something went wrong!!");
                alert.setContentText(e.getMessage());
                alert.show();
            }
        }
    }
}


