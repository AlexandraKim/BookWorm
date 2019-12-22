package Librarian;

import Persistence.BookEntity;
import Persistence.UserEntity;
import Persistence.UserToBookEntity;
import Utils.Auth;
import Utils.DatabaseConnection;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import org.hibernate.Query;
import org.hibernate.Session;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class LibrarianController implements Initializable {
    @FXML
    private VBox list_items = null;
    @FXML
    private Label titleLbl;
    @FXML
    private Button booksBtn;
    @FXML
    private Button checkoutsBtn;
    @FXML
    private Button finesBtn;
    @FXML
    private Button logoutBtn;
    @FXML
    public Label userNameLbl;
    @FXML
    public HBox checkoutsHeader;
    @FXML
    public HBox booksHeader;
    @FXML
    public Button addBtn;

    private UserEntity _user = Auth.getUser();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        userNameLbl.setText(_user.getFirstName() + " " + _user.getLastName());
        TableContent.setValue("b");
        switchTable();
    }

    public void switchTable(){
        String hoverOrActiveBtnStyle = "-fx-background-color: #5A00B4;";
        String btnStyle = "-fx-background-color: #7242DB;";
        if (TableContent.getValue() == "b") {
            titleLbl.setText("Books");
            displayBooksTable();
            booksHeader.setVisible(true);
            checkoutsHeader.setVisible(false);
            booksBtn.setStyle(hoverOrActiveBtnStyle);
            checkoutsBtn.setStyle(btnStyle);
            finesBtn.setStyle(btnStyle);
            addBtn.setVisible(true);
        } else if (TableContent.getValue() == "c") {
            titleLbl.setText("Checkouts");
            displayCheckoutsTable();
            checkoutsHeader.setVisible(true);
            booksHeader.setVisible(false);
            checkoutsBtn.setStyle(hoverOrActiveBtnStyle);
            finesBtn.setStyle(btnStyle);
            booksBtn.setStyle(btnStyle);
            addBtn.setVisible(false);
        } else if (TableContent.getValue() == "f") {
            titleLbl.setText("Fines");

            checkoutsHeader.setVisible(false);
            booksHeader.setVisible(true);
            finesBtn.setStyle(hoverOrActiveBtnStyle);
            booksBtn.setStyle(btnStyle);
            checkoutsBtn.setStyle(btnStyle);
            addBtn.setVisible(false);
        }
    }

    public void finesBtnClick () {
        TableContent.setValue("f");
        switchTable();
    }

    public void checkoutsBtnClick () {
        TableContent.setValue("c");

        switchTable();
    }

    public void booksBtnClick () {
        TableContent.setValue("b");
        switchTable();
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

    public void displayCheckoutsTable(){
        list_items.getChildren().clear();
        Session session = DatabaseConnection.get_sessionFactory().openSession();
        Query query = session.createQuery("from UserToBookEntity");
        List result = query.list();
        session.close();

        for (UserToBookEntity checkout : (List<UserToBookEntity>) result ) {
            try{
                FXMLLoader loader = new FXMLLoader(getClass().getResource("ItemCheckouts.fxml"));
                Node node = loader.load();
                ItemCheckoutsController controller = loader.<ItemCheckoutsController>getController();
                controller.initData(checkout);
                list_items.getChildren().add(node);
            } catch (Exception e){
                e.printStackTrace();
            }
        }
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
    public void addBtnClick() {
        String title = "librarian";
        String path = "Add.fxml";
        int type = 1;
        if (TableContent.getValue() == "s") {
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
//                displayUsersTable(type);
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
