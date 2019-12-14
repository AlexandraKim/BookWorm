package Librarian;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class LibrarianController implements Initializable {

    @FXML
    private Label label;

    @FXML
    private VBox list_items = null;
    @FXML
    private Label numS;
    @FXML
    private Label numS1;



    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        //Added new font which is named Montserrat

        //List of information,  here should be add functionaly
        Node[] nodes = new Node[15];
        for (int i = 0; i < nodes.length ; i++){
            try{
                nodes[i]=(Node) FXMLLoader.load(getClass().getResource("Item.fxml"));
                list_items.getChildren().add(nodes[i]);
            } catch (IOException e){
                e.printStackTrace();
            }
        }



    }

}
