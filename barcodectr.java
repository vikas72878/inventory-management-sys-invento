package homepage;


import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import org.krysalis.barcode4j.impl.code128.Code128Bean;
import org.krysalis.barcode4j.output.bitmap.BitmapCanvasProvider;

import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.*;


public class barcodectr {

    @FXML
    private JFXTextField data;

    @FXML
    private Label label;

    @FXML
    public JFXButton butt;

    @FXML
    private JFXButton add_barcode;

    @FXML
    private JFXButton barcode_back;

    @FXML
    private FontAwesomeIconView barcode_back_icon;

    @FXML
    private JFXTextField product_id1;




    @FXML
    void barcode_back(ActionEvent event) {
        Parent parent = null;
        try {
            parent = FXMLLoader.load(this.getClass().getResource("homepage.fxml"));
        } catch (IOException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText("Oops something   went wrong!!");
            alert.setContentText(e.getMessage());
            alert.show();
        }
        Scene scene = new Scene(parent,800,500);
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(scene);
        window.show();

    }

    @FXML
    void add_barcode(ActionEvent event) {

       String s= product_id1.getText();

System.out.println(s);
        ResultSet roo;
        try {
            Connection con = null;

            con = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/inventory_db", "root", "");
            Statement stmt = con.createStatement();
             roo  = stmt.executeQuery("select count(*) from tbl_product_entry where fld_product_id='" + s +"'");
             roo.next();
            if(roo.getInt(1)!=1){
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setHeaderText("Oops something went  wrong!!!");
                alert.setContentText("Wrong Product Id");
                alert.show();
                return;

            }

        } catch (SQLException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText("Oops something  went wrong!!!");
            alert.setContentText(e.getMessage());
            alert.show();
            return;
        }


        Code128Bean code128 = new Code128Bean();
        code128.setHeight(15.0D);
        code128.setModuleWidth(0.3D);
        code128.setQuietZone(10.0D);
        code128.doQuietZone(true);
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        BitmapCanvasProvider canvas = new BitmapCanvasProvider(baos, "image/x-png", 400, 12, false, 0);
        code128.generateBarcode(canvas, this.product_id1.getText());

    try {
            canvas.finish();
    FileOutputStream fos = new FileOutputStream("barcodes/"+s+".png");
            fos.write(baos.toByteArray());
            fos.flush();
            fos.close();

        } catch (IOException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText("Oops something went wrong!!");
            alert.setContentText(e.getMessage());
            alert.show();
            return;
        }
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setHeaderText("Status:");
        alert.setContentText("Barcode  added successfully To Barcode Folder ");
        alert.show();

    }

}
