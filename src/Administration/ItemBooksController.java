package Administration;

import Persistence.BookEntity;
import javafx.beans.binding.StringBinding;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.net.URL;
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
    Button modifyBtn;
    @FXML
    Button deleteBtn;

    private BookEntity book;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    void initData(BookEntity obj) {
        book = obj;
        isbnLbl.setText(obj.getIsbn());
        titleLbl.setText(obj.getTitle());
        genreLbl.setText(obj.getGenre());
        authorLbl.setText(obj.getAuthor());
        publicationDateLbl.setText(obj.getPublicationDate().toString());
    }

    public void modifyBtnClick(){
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("ModifyUser.fxml"));
            Stage stage = new Stage();
            stage.setTitle("Modify " + book.getTitle());
            stage.setScene(new Scene(loader.load(), 409, 411));
            ModifyBookController controller = loader.<ModifyBookController>getController();
            controller.initData(book);

            isbnLbl.textProperty().bind(controller.isbnTxt.textProperty());
            titleLbl.textProperty().bind(controller.titleTxt.textProperty());
            // тут продолжить привязывать
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
            stage.setTitle("Confirm deletion of " + book.getTitle());
            stage.setScene(new Scene(loader.load(), 409, 274));
            DeleteController controller = loader.<DeleteController>getController();
            controller.initData(book);
            stage.showAndWait();
            refreshParent();
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

    public void refreshParent(){
        try {
            Parent pageViewParent = FXMLLoader.load(getClass().getResource("Admin.fxml"));
            Scene pageViewScene = new Scene(pageViewParent, 1366, 768);
            Stage window = (Stage) isbnLbl.getScene().getWindow();
            window.setScene(pageViewScene);
            TableContent.setValue("b");
            window.show();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
