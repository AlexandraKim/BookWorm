package Librarian;

import Persistence.BookEntity;
import Student.TableContent;
import Utils.Auth;
import Utils.DateFormat;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.net.URL;
import java.util.Date;
import java.util.ResourceBundle;

public class ItemBooksController implements Initializable {
    @FXML
    Label isbnLbl;
    @FXML
    Label titleLbl;
    @FXML
    Label genreLbl;
    @FXML
    Label authorLbl;
    @FXML
    Label publicationDateLbl;
    @FXML
    Button checkoutBtn;

    private BookEntity _book;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    void initData(BookEntity obj) {
        _book = obj;
        isbnLbl.setText(obj.getIsbn());
        titleLbl.setText(obj.getTitle());
        genreLbl.setText(obj.getGenre());
        authorLbl.setText(obj.getAuthor());
        publicationDateLbl.setText(DateFormat.convert(obj.getPublicationDate()));
        if (Auth.getUser().getBooks().contains(obj)){
            checkoutBtn.setDisable(true);
        }
    }

    public void modifyBtnClick(){
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("ModifyBook.fxml"));
            Stage stage = new Stage();
            stage.setTitle("Modify " + _book.getTitle());
            stage.setScene(new Scene(loader.load(), 409, 411));
            ModifyBookController controller = loader.<ModifyBookController>getController();
            controller.initData(_book);

            isbnLbl.textProperty().bind(controller.isbnTxt.textProperty());
            titleLbl.textProperty().bind(controller.titleTxt.textProperty());
            genreLbl.textProperty().bind(controller.genreTxt.textProperty());
            authorLbl.textProperty().bind(controller.authorTxt.textProperty());
            publicationDateLbl.textProperty().bind(controller.publicationDateTxt.textProperty());

            stage.show();
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

    public void deleteBtnClick(){
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("Confirm.fxml"));
            Stage stage = new Stage();
            stage.setTitle("Confirm deletion of " + _book.getTitle());
            stage.setScene(new Scene(loader.load(), 409, 274));
            DeleteController controller = loader.<DeleteController>getController();
            controller.initData(_book);
            stage.showAndWait();
            refreshParent();
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

    public void refreshParent(){
        try {
            Parent pageViewParent = FXMLLoader.load(getClass().getResource("Librarian.fxml"));
            Scene pageViewScene = new Scene(pageViewParent, 1366, 768);
            Stage window = (Stage) isbnLbl.getScene().getWindow();
            window.setScene(pageViewScene);
            TableContent.setValue("all");
            window.show();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
