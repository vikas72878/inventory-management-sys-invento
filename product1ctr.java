 package homepage;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
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

public class product1ctr implements Initializable {

    @FXML
    private TableView<ProductModalTable> table;

    @FXML
    private TableColumn<ProductModalTable, String> product_id_col;

    @FXML
    private TableColumn<ProductModalTable, String> product_name_col;

    @FXML
    private TableColumn<ProductModalTable, String> product_unit_col;

    @FXML
    private TableColumn<ProductModalTable, Integer> product_sellingp_col;

    @FXML
    private TableColumn<ProductModalTable, Integer> product_stock_col;

    @FXML
    private TableColumn<ProductModalTable, String> product_remarks_col;


    @FXML
    public JFXButton add_product;

    @FXML
    private JFXButton product_delete;


    @FXML
    private JFXButton product1back;


    @FXML
    private JFXButton add_barcode;

    @FXML
    private JFXButton barcode_back;

    @FXML
    private FontAwesomeIconView barcode_back_icon;

    @FXML
    private JFXTextField product_id1;





    ObservableList<ProductModalTable> oblist2 = FXCollections.observableArrayList();

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        try {
            Connection con = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/inventory_db", "root", "");

            ResultSet rs = con.createStatement().executeQuery("Select * from tbl_product_entry");
           /* while(rs.next()) {
                System.out.println(rs.getString(1)+" "+rs.getString(2)+" "+rs.getInt(3)+" "+rs.getString(4)+" "+rs.getString(5)+" "+rs.getString(6));
            }*/

            while (rs.next()) {
                oblist2.add(new ProductModalTable(rs.getString(1), rs.getString(
                        2) , rs.getInt(3), rs.getString(
                        4), rs.getString(5),rs.getInt(6)));
            }

           /* for(ProductModalTable oblis : oblist) {
                System.out.println(oblis.getSelling_price());
            }*/
        } catch (
                SQLException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText("Oops something went wrong!!");
            alert.setContentText(e.getMessage());
            alert.show();
        }
        product_id_col.setCellValueFactory(new PropertyValueFactory<>("product_id"));
        product_name_col.setCellValueFactory(new PropertyValueFactory<>("product_name"));
        product_unit_col.setCellValueFactory(new PropertyValueFactory<>("unit"));
        product_sellingp_col.setCellValueFactory(new PropertyValueFactory<>("selling_price"));
        product_stock_col.setCellValueFactory(new PropertyValueFactory<>("stock"));
        product_remarks_col.setCellValueFactory(new PropertyValueFactory<>("remarks"));


        table.setItems(oblist2);
    }

    public void product_entry1(ActionEvent event) throws Exception{

        Parent parent =  FXMLLoader.load(this.getClass().getResource("product_entry.fxml"));
        Scene scene = new Scene(parent,800,500);
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(scene);
        window.show();

    }

    public void product1back(ActionEvent event) {

        Parent parent = null;
        try {
            parent = FXMLLoader.load(this.getClass().getResource("homepage.fxml"));
        } catch (IOException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText("Oops something  went wrong!!");
            alert.setContentText(e.getMessage());
            alert.show();
        }
        Scene scene = new Scene(parent,800,500);
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(scene);
        window.show();
    }

    @FXML
    void product_delete(ActionEvent event) {

        int selectedIndex = table.getSelectionModel().getSelectedIndex();
        ProductModalTable id1 =table.getSelectionModel().getSelectedItem();

        String id = id1.getProduct_id();

        ObservableList<ProductModalTable>  single_product;

        single_product=table.getSelectionModel().getSelectedItems();
         System.out.print(id);
      System.out.println(single_product);
        if(selectedIndex >= 0){
     try {
         Connection con = DriverManager.getConnection(
                 "jdbc:mysql://localhost:3306/inventory_db", "root", "");

         String query1 = "DELETE FROM tbl_product_entry WHERE fld_product_id = ?";
         PreparedStatement pst;
         pst = con.prepareStatement(query1);
         pst.setString(1, id);
         pst.execute();
         table.getItems().remove(selectedIndex);

     }catch (SQLException e){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText("Oops something went wrong!!");
            alert.setContentText(e.getMessage());
            alert.show();
        }
    }
}




}
