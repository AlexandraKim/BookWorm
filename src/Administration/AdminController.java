package Administration;

import Persistence.BookEntity;
import Persistence.UserEntity;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
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
    private VBox list_items = null;
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
    @FXML
    public HBox usersHeader;
    @FXML
    public HBox booksHeader;

    private UserEntity _user = Auth.getUser();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        userNameLbl.setText(_user.getFirstName() + " " + _user.getLastName());
        switchTable();
    }

    public void switchTable(){
        String hoverOrActiveBtnStyle = "-fx-background-color: #5A00B4;";
        String btnStyle = "-fx-background-color: #7242DB;";
        if (TableContent.getValue() == "l") {
            titleLbl.setText("Librarians");
            displayUsersTable(1);
            usersHeader.setVisible(true);
            booksHeader.setVisible(false);
            librarianBtn.setStyle(hoverOrActiveBtnStyle);
            studentsBtn.setStyle(btnStyle);
            booksBtn.setStyle(btnStyle);
        } else if (TableContent.getValue() == "s") {
            titleLbl.setText("Students");
            displayUsersTable(2);
            usersHeader.setVisible(true);
            booksHeader.setVisible(false);
            studentsBtn.setStyle(hoverOrActiveBtnStyle);
            librarianBtn.setStyle(btnStyle);
            booksBtn.setStyle(btnStyle);
        } else if (TableContent.getValue() == "b") {
            titleLbl.setText("Books");
            displayBooksTable();
            usersHeader.setVisible(false);
            booksHeader.setVisible(true);
            booksBtn.setStyle(hoverOrActiveBtnStyle);
            librarianBtn.setStyle(btnStyle);
            studentsBtn.setStyle(btnStyle);
        }
    }

    public void librarianBtnClick () {
        TableContent.setValue("l");
        switchTable();
    }

    public void studentsBtnClick () {
        TableContent.setValue("s");
        switchTable();
    }

    public void booksBtnClick () {
        TableContent.setValue("b");
        switchTable();
    }

    public void displayUsersTable(int type) {
        list_items.getChildren().clear();
        Session session = DatabaseConnection.get_sessionFactory().openSession();
        Query query = session.createQuery("from UserEntity where type = :type");
        query.setParameter("type", type);
        List result = query.list();
        session.close();

        for (UserEntity user : (List<UserEntity>) result ) {
            try{
                FXMLLoader loader = new FXMLLoader(getClass().getResource("ItemUsers.fxml"));
                Node node = loader.load();
                ItemUsersController controller = loader.<ItemUsersController>getController();
                controller.initData(user);
                list_items.getChildren().add(node);
            } catch (Exception e){
                e.printStackTrace();
            }
        }
    }

    public void displayBooksTable() {
        list_items.getChildren().clear();
        Session session = DatabaseConnection.get_sessionFactory().openSession();
        Query query = session.createQuery("from BookEntity");
        List result = query.list();
        session.close();

        for (BookEntity book : (List<BookEntity>) result ) {
            try{
                FXMLLoader loader = new FXMLLoader(getClass().getResource("ItemBooks.fxml"));
                Node node = loader.load();
                ItemBooksController controller = loader.<ItemBooksController>getController();
                controller.initData(book);
                list_items.getChildren().add(node);
            } catch (Exception e){
                e.printStackTrace();
            }
        }
    }



    public void addBtnClick() {

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
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Add.fxml"));
        Stage stage = new Stage();
        stage.setTitle("Add " + title);
        try {
            stage.setScene(new Scene(loader.load(), 409, 411));
            AddController controller = loader.<AddController>getController();
            stage.showAndWait();
            displayUsersTable(type);
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
