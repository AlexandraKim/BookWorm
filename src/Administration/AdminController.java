package Administration;

import Persistence.UserEntity;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import Utils.*;
import javafx.stage.Stage;
import org.hibernate.Query;
import org.hibernate.Session;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.net.URL;
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
    private Label titleLbl;
    @FXML
    private Button librarianBtn;
    @FXML
    private Button studentsBtn;
    @FXML
    private Button booksBtn;

    @FXML
    public Label userNameLbl;

    private UserEntity _user = Auth.getUser();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        userNameLbl.setText(_user.getFirstName() + " " + _user.getLastName());
        TableContent.setValue("l");
        titleLbl.setText("Librarians");
        //librarianBtn.setStyle("-fx-background-color: #5A00B4;");
        displayTable(1);

        if (TableContent.getValue() == "s") {

        }
        if (TableContent.getValue() == "b") {
            titleLbl.setText("Books");
            librarianBtn.setStyle("-fx-background-color: #5A00B4;");
        }
    }

    public void librarianBtnClick () {
        TableContent.setValue("l");
        displayTable(1);
        titleLbl.setText("Librarians");
        //librarianBtn.setStyle("-fx-background-color: #5A00B4;");
    }

    public void studentsBtnClick () {
        TableContent.setValue("s");
        displayTable(2);
        titleLbl.setText("Students");
        //studentsBtn.setStyle("-fx-background-color: #5A00B4;");
    }

    public void displayTable(int type) {
        list_items.getChildren().clear();
        Session session = DatabaseConnection.get_sessionFactory().openSession();
        Query query = session.createQuery("from UserEntity where type = :type");
        query.setParameter("type", type);
        List result = query.list();
        session.close();

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

    public void addBtnClick() {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Add.fxml"));
        Stage stage = new Stage();
        String title = "librarian";
        int type = 1;
        if (TableContent.getValue() == "l") {
            title = "librarian";
            type = 1;
        }
        if (TableContent.getValue() == "s") {
            title = "student";
            type = 2;
        }
        if (TableContent.getValue() == "b") {
            title = "book";
        }
        stage.setTitle("Add " + title);
        try {
            stage.setScene(new Scene(loader.load(), 409, 411));
            AddController controller = loader.<AddController>getController();
            stage.showAndWait();
            displayTable(type);
        } catch (Exception e){
            e.printStackTrace();
        }

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
