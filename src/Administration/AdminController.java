package Administration;

import Persistence.BookEntity;
import Persistence.UserEntity;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
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
    private Button logoutBtn;

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

    public void logoutBtnClick (ActionEvent event) {
        Auth.setUser(null);
        try {
            Parent pageViewParent = FXMLLoader.load(getClass().getResource("../SignUP/SignUp.fxml"));
            Scene pageViewScene = new Scene(pageViewParent, 950, 630);
            Stage window = (Stage)((javafx.scene.Node)event.getSource()).getScene().getWindow();
            window.setScene(pageViewScene);
            window.show();
        }

        catch (Exception e) {
            System.out.println(e.getMessage());
        }
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
        String path = "Add.fxml";
        int type = 1;
        if (TableContent.getValue() == "l") {
            title = "librarian";
            type = 1;
            path = "Add.fxml";
        } else if (TableContent.getValue() == "s") {
            title = "student";
            type = 2;
            path = "Add.fxml";
        } else if (TableContent.getValue() == "b") {
            title = "book";
            path = "AddBook.fxml";
        }
        FXMLLoader loader = new FXMLLoader(getClass().getResource(path));
        Stage stage = new Stage();
        stage.setTitle("Add " + title);
        try {
            stage.setScene(new Scene(loader.load(), 409, 411));
            stage.showAndWait();
            if (TableContent.getValue() != "b"){
                displayUsersTable(type);
            } else {
                displayBooksTable();
            }
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
