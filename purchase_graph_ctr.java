package homepage;

import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Alert;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class purchase_graph_ctr implements Initializable {

    @FXML
    private LineChart<?, ?> purchase_line_graph;

    @FXML
    private CategoryAxis x;

    @FXML
    private NumberAxis y;

    @FXML
    private JFXButton purchase_graph_bck;


    @Override
    public void initialize(URL location, ResourceBundle resources) {

        try {
            Connection con = null;

            con = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/inventory_db", "root", "");

            ResultSet c = con.createStatement().executeQuery("Select a.fld_product_id," +
                    "a.fld_quantity*b.fld_selling_price,a.fld_date,b.fld_product_id,b.fld_selling_price" +
                    " from tbl_purchase_details a,tbl_product_entry b " +
                    "where a.fld_product_id = b.fld_product_id ");
            XYChart.Series series = new XYChart.Series();
            while(c.next()) {

                series.getData().add(new XYChart.Data<>(c.getString(3), c.getInt(2)));
            }
            purchase_line_graph.getData().addAll(series);
        } catch (SQLException e1) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText("Oops something went wrong!!!");
            alert.setContentText(e1.getMessage());
            alert.show();
        }

        try {
            Connection con = null;

            con = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/inventory_db", "root", "");

            ResultSet rr = con.createStatement().executeQuery("Select a.fld_product_id," +
                    "a.fld_quantity*b.fld_selling_price,a.fld_date,b.fld_product_id,b.fld_selling_price" +
                    " from tbl_sales_details a,tbl_product_entry b " +
                    "where a.fld_product_id = b.fld_product_id ");
            XYChart.Series series2 = new XYChart.Series();
            while(rr.next()) {

                series2.getData().add(new XYChart.Data<>(rr.getString(3), rr.getInt(2)));
            }
            purchase_line_graph.getData().addAll(series2);
        } catch (SQLException e1) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText("Oops something went wrong!!!");
            alert.setContentText(e1.getMessage());
            alert.show();
        }
    }
    @FXML
    void purchase_graph_bck(ActionEvent event) {

        Parent parent = null;
        try {
            parent = FXMLLoader.load(this.getClass().getResource("homepage.fxml"));
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
