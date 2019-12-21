package Student;

import Persistence.BookEntity;
import Persistence.UserToBookEntity;
import Utils.Auth;
import Utils.DatabaseConnection;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.text.SimpleDateFormat;
import java.util.Date;
import org.hibernate.Session;
import org.hibernate.Transaction;

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
        String pattern = "yyyy-MM-dd";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
        String date = simpleDateFormat.format(obj.getPublicationDate());
        publicationDateLbl.setText(date);
        if (Auth.getUser().getBooks().contains(obj)){
            checkoutBtn.setDisable(true);
        }
    }

    public void checkoutBtnClick(){
        Transaction tx=null;
        Session session = DatabaseConnection.get_sessionFactory().openSession();
        UserToBookEntity utb = new UserToBookEntity();
        utb.setBookId(_book.getId());
        utb.setUserid(Auth.getUser().getId());
        utb.setIssueDate(new Date());
        session.save(utb);
        tx = session.beginTransaction();
        tx.commit();
        session.close();
        refreshParent();
    }

    public void refreshParent(){
        try {
            Parent pageViewParent = FXMLLoader.load(getClass().getResource("Student.fxml"));
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
