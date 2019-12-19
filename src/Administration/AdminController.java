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
        String content = TableContent.getValue();
        Session session = DatabaseConnection.getSession();
        setValues(session);
        String hql = "from UserEntity where type = 1";
        if (content == "l"){
            hql = "from UserEntity where type = 1";
        } else if (content == "s") {
            hql = "from UserEntity where type = 2";
        } else if (content == "b") {
            hql = "from BookEntity";
        }
        Query query = session.createQuery(hql);
        List result = query.list();

        for (UserEntity user : (List<UserEntity>) result ) {
            try{
                FXMLLoader loader = new FXMLLoader(getClass().getResource("Item.fxml"));
                ItemController controller = new ItemController();
                loader.setController(controller);

                Node node = loader.load();
                list_items.getChildren().add(node);
                controller.initData(user);

            } catch (Exception e){
                e.printStackTrace();
            }
        }
    }

    public void librarianBtnClick (ActionEvent event) {
        TableContent.setValue("l");
    }

    public void studentsBtnClick () {
        TableContent.setValue("s");
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
