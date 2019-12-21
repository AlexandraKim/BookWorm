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

    private BookEntity _book;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    public void initData(BookEntity book) {
        _book = book;
        isbnTxt.setText(book.getIsbn());
        titleTxt.setText(book.getTitle());
        genreTxt.setText(book.getGenre());
        authorTxt.setText(book.getAuthor());
        String pattern = "yyyy-MM-dd";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
        String date = simpleDateFormat.format(book.getPublicationDate());
        publicationDateTxt.setText(date);
    }

    public void saveBtnClick(ActionEvent event){
        _book.setIsbn(isbnTxt.getText());
        _book.setTitle(titleTxt.getText());
        _book.setGenre(genreTxt.getText());
        _book.setAuthor(authorTxt.getText());
        try {
            _book.setPublicationDate(new SimpleDateFormat("yyyy-MM-dd").parse(publicationDateTxt.getText()));
        } catch (ParseException e) {
            e.printStackTrace();
        }

        Transaction tx=null;
        Session session = DatabaseConnection.get_sessionFactory().openSession();
        Query query = session.createQuery("update BookEntity set " +
                "title = :title, " +
                "isbn = :isbn, " +
                "genre = :genre, " +
                "author = :author, " +
                "publicationDate = :publicationDate " +
                "where id = :id");
        query.setParameter("id", _book.getId());
        query.setParameter("title", _book.getTitle());
        query.setParameter("isbn", _book.getIsbn());
        query.setParameter("genre", _book.getGenre());
        query.setParameter("author", _book.getAuthor());
        query.setParameter("publicationDate", _book.getPublicationDate());

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
