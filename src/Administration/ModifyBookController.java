package Administration;

import Persistence.BookEntity;
import Utils.DatabaseConnection;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ResourceBundle;

public class ModifyBookController implements Initializable {
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
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    public void initData(BookEntity book) {
        isbnTxt.setText(book.getIsbn());
        titleTxt.setText(book.getTitle());
        genreTxt.setText(book.getGenre());
        authorTxt.setText(book.getAuthor());
        publicationDateTxt.setText(book.getPublicationDate().toString());
    }

    public void saveBtnClick(ActionEvent event){
        BookEntity book = new BookEntity();
        book.setIsbn(isbnTxt.getText());
        book.setTitle(titleTxt.getText());
        book.setGenre(genreTxt.getText());
        book.setAuthor(authorTxt.getText());
//        try {
//            book.setPublicationDate(new SimpleDateFormat("yyyy/MM/dd").parse(publicationDateTxt.getText()));
//        } catch (ParseException e) {
//            e.printStackTrace();
//        }

        // прописать дальше запрос на обновление BookEntity
        Transaction tx=null;
        Session session = DatabaseConnection.get_sessionFactory().openSession();
        Query query = session.createQuery("update BookEntity set " +
                "title = :title, " +
                "where id = :id");
        query.setParameter("title", book.getTitle());
        query.setParameter("id", book.getId());

        int result = query.executeUpdate();
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
