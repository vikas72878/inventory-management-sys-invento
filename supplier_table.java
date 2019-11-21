package homepage;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXSpinner;
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

public class supplier_table implements Initializable {


    @FXML
    private TableView<supplier_modal_table> supplier_table;

    @FXML
    private TableColumn<supplier_modal_table, String> supplier_id_col;

    @FXML
    private TableColumn<supplier_modal_table, String> supplier_name_col;

    @FXML
    private TableColumn<supplier_modal_table, Integer> supplier_cnumber_col;

    @FXML
    private TableColumn<supplier_modal_table, String> supplier_address_col;

    @FXML
    private TableColumn<supplier_modal_table, Integer> supplier_accnumber_col;

    @FXML
    private TableColumn<supplier_modal_table, Integer> supplier_gstin_col;


    @FXML
    private JFXButton supplier_back_btn;

    @FXML
    private JFXButton add_supplier;


    @FXML
    private JFXSpinner suppiler_spin;

    @FXML
    private JFXButton modify_btn;



    ObservableList<supplier_modal_table> supp_oblist = FXCollections.observableArrayList();

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        try {
            Connection con = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/inventory_db", "root", "");

            ResultSet rs = con.createStatement().executeQuery("Select * from tbl_supplier_details");
           /* while(rs.next()) {
                System.out.println(rs.getString(1)+" "+rs.getString(2)+" "+rs.getInt(3)+" "+rs.getString(4)+" "+rs.getString(5)+" "+rs.getString(6));
            }*/

            while (rs.next()) {
                supp_oblist.add(new supplier_modal_table(rs.getString(1), rs.getString(
                        2) , rs.getInt(3), rs.getString(
                        4), rs.getInt(5),rs.getString
                        (6)));
            }

           /* for(ProductModalTable oblis : oblist) {
                System.out.println(oblis.getSelling_price());
            }*/
        } catch (SQLException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText("Oops something went wrong!!!");
            alert.setContentText(e.getMessage());
            alert.show();
        }
        supplier_id_col.setCellValueFactory(new PropertyValueFactory<supplier_modal_table,String>("supplier_id"));
        supplier_name_col.setCellValueFactory(new PropertyValueFactory<supplier_modal_table,String>("supplier_name"));
        supplier_address_col.setCellValueFactory(new PropertyValueFactory<supplier_modal_table,String>("address"));
        supplier_cnumber_col.setCellValueFactory(new PropertyValueFactory<supplier_modal_table,Integer>("ph_no"));
        supplier_accnumber_col.setCellValueFactory(new PropertyValueFactory<supplier_modal_table,Integer>("acc_no"));
        supplier_gstin_col.setCellValueFactory(new PropertyValueFactory<>("gstin"));
        supplier_table.setItems(supp_oblist);


    /*    supplier_cnumber_col.setCellValueFactory(Integer.toString(Integer.toString(TextFieldTableCell.forTableColumn())));
        supplier_accnumber_col.setCellValueFactory(TextFieldTableCell.forTableColumn());
        supplier_gstin_col.setCellFactory(TextFieldTableCell.forTableColumn());*/

     /*   supplier_table.setEditable(true);

        supplier_id_col.setCellFactory(TextFieldTableCell.forTableColumn());
        supplier_name_col.setCellFactory(TextFieldTableCell.forTableColumn());
        supplier_address_col.setCellFactory(TextFieldTableCell.forTableColumn());*/


    }

    public void add_supplier(ActionEvent event) throws Exception{

//        suppiler_spin.isVisible();
        Parent parent =  FXMLLoader.load(this.getClass().getResource("supplier.fxml"));
        Scene scene = new Scene(parent,800,500);
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(scene);
        window.show();

    }

    public void supplier_bck_act(ActionEvent event) {

        Parent parent = null;
        try {
            parent = FXMLLoader.load(this.getClass().getResource("homepage.fxml"));
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

    public void oneditname(TableColumn.CellEditEvent<supplier_modal_table, String> supplier_modal_tableStringCellEditEvent) {
        supplier_modal_table sup_mod_tab =supplier_table.getSelectionModel().getSelectedItem();
        sup_mod_tab.setSupplier_name(supplier_modal_tableStringCellEditEvent.getNewValue());
    }
    @FXML

    public void supplier_delete(ActionEvent event) {
        int selectedIndex =supplier_table.getSelectionModel().getSelectedIndex();
        supplier_modal_table id1 =supplier_table.getSelectionModel().getSelectedItem();

        String id = id1.getSupplier_id();

        ObservableList<supplier_modal_table>  single_product;

        single_product=supplier_table.getSelectionModel().getSelectedItems();
        System.out.print(id);
        System.out.println(single_product);
        if(selectedIndex >= 0){
            try {
                Connection con = DriverManager.getConnection(
                        "jdbc:mysql://localhost:3306/inventory_db", "root", "");

                String query1 = "DELETE FROM tbl_supplier_details WHERE fld_supplier_id = ?";
                PreparedStatement pst;
                pst = con.prepareStatement(query1);
                pst.setString(1, id);
                pst.execute();
               supplier_table.getItems().remove(selectedIndex);

            }catch (SQLException e){
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setHeaderText("Oops something went wrong!!");
                alert.setContentText(e.getMessage());
                alert.show();
            }
    }
}}
