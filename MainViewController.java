package homepage;


import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import org.krysalis.barcode4j.impl.code128.Code128Bean;
import org.krysalis.barcode4j.output.bitmap.BitmapCanvasProvider;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MainViewController implements Initializable {


    @FXML
    private Button btn;
    @FXML
    private TextField id;
    @FXML
    private Label code;
    @FXML
    private ImageView img;

    public MainViewController() {
    }

    public void initialize(URL url, ResourceBundle rb) {
        this.btn.setOnAction((e) -> {
            try {
                this.code();
            } catch (IOException var3) {
                Logger.getLogger(MainViewController.class.getName()).log(Level.SEVERE, (String) null, var3);
            }

        });
    }

    private void code() throws FileNotFoundException, IOException {
        Code128Bean code128 = new Code128Bean();
        code128.setHeight(15.0D);
        code128.setModuleWidth(0.3D);
        code128.setQuietZone(10.0D);
        code128.doQuietZone(true);
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        BitmapCanvasProvider canvas = new BitmapCanvasProvider(baos, "image/x-png", 400, 12, false, 0);
        code128.generateBarcode(canvas, this.id.getText());
        canvas.finish();
        FileOutputStream fos = new FileOutputStream("barcode.png");
        fos.write(baos.toByteArray());
        fos.flush();
        fos.close();

    }
}