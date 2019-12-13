package Administration;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;

import javax.swing.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class AdminController implements Initializable {

    @FXML
    private Label label;

    @FXML
    private VBox list_items = null;
    @FXML
    private Label numS;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        //Added new font which is named Montserrat
        Font myFont = null;

        try {
            myFont = Font.loadFont(new FileInputStream(new File("Montserrat")), 10);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        //List of information,  here should be add functionaly
        Node[] nodes = new Node[5];
        for (int i=0;i<nodes.length; i++){
            try{
                nodes[i]=(Node) FXMLLoader.load(getClass().getResource("Item.fxml"));
                list_items.getChildren().add(nodes[i]);
            } catch (IOException e){
                e.printStackTrace();
            }
        }



    }

}
