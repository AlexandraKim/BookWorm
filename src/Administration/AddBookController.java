package Administration;

import Persistence.BookEntity;
import Persistence.UserEntity;
import Utils.DatabaseConnection;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ResourceBundle;

public class AddBookController implements Initializable {
    @FXML
    TextField isbnTxt;
    @FXML
    TextField titleTxt;
    @FXML
    TextField genreTxt;
    @FXML
    TextField authorTxt;
    @FXML
    TextField publicationDateTxt;
    @FXML
    Button cancelBtn;
    @FXML
    Button saveBtn;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {}

    public void saveBtnClick(ActionEvent event){
        BookEntity book = new BookEntity();
        book.setIsbn(isbnTxt.getText());
        book.setTitle(titleTxt.getText());
        book.setAuthor(authorTxt.getText());
        book.setGenre(genreTxt.getText());
        try {
            book.setPublicationDate(new SimpleDateFormat("yyyy-MM-dd").parse(publicationDateTxt.getText()));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Transaction tx = null;
        Session session = DatabaseConnection.get_sessionFactory().openSession();
        session.save(book);
        tx = session.beginTransaction();
        tx.commit();
        session.close();
        closeDialog(saveBtn);
    }

    public void cancelBtnClick(){
        closeDialog(cancelBtn);
    }

    public void closeDialog(Button btn) {
        Stage stage = (Stage) btn.getScene().getWindow();
        stage.close();
    }
}
