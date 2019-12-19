package Administration;

import Persistence.UserEntity;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import Utils.*;
import org.hibernate.Query;
import org.hibernate.Session;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class AdminController implements Initializable {

    @FXML
    private Label label;

    @FXML
    private VBox list_items = null;
    @FXML
    private Label numS;
    @FXML
    private Label numS1;

    @FXML
    public Label userName;

    private UserEntity _user = Auth.getUser();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Session session = DatabaseConnection.getSession();
        setValues(session);
        displayTable(1);
    }

    public void librarianBtnClick () {
        displayTable(1);
    }

    public void studentsBtnClick () {
        displayTable(2);
    }

    public void displayTable(int type) {
        list_items.getChildren().clear();
        Session session = DatabaseConnection.getSession();
        Query query = session.createQuery("from UserEntity where type = :type");
        query.setParameter("type", type);
        List result = query.list();

        for (UserEntity user : (List<UserEntity>) result ) {
            try{
                FXMLLoader loader = new FXMLLoader(getClass().getResource("Item.fxml"));
                Node node = loader.load();
                ItemController controller = loader.<ItemController>getController();
                controller.initData(user);
                list_items.getChildren().add(node);


            } catch (Exception e){
                e.printStackTrace();
            }
        }
    }

    public void booksBtnClick () {
        TableContent.setValue("b");
    }

    public void setValues(Session session){
        //Query query = session.createQuery("select count(*) from user where type = 2");
        //String numberOfStudents = query.iterate().next().toString();
        userName.setText(_user.getFirstName() + " " + _user.getLastName());
        //numS1.setText(numberOfStudents);
    }
    public void setFont () {
        Font myFont ;
        try {
            myFont = Font.loadFont(new FileInputStream(new File("Montserrat")), 10);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
