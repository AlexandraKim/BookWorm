package Student;

import Persistence.BookEntity;
import Persistence.UserToBookEntity;
import Utils.Auth;
import Utils.DatabaseConnection;
import Utils.DateFormat;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import org.hibernate.Session;
import org.hibernate.Transaction;

import java.net.URL;
import java.util.List;
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
        for(UserToBookEntity utb : Auth.getUser().getCheckouts()){
            if(utb.getBook().getId() == _book.getId()){
                if(utb.getIssueDate() == null){
                    checkoutBtn.setDisable(true);
                    checkoutBtn.setText("Reserved");
                } else if (utb.getReturnDate() == null){
                    checkoutBtn.setDisable(true);
                    checkoutBtn.setText("Borrowed");
                }
            }
        }
    }

    public void checkoutBtnClick(){
        Transaction tx=null;
        Session session = DatabaseConnection.get_sessionFactory().openSession();

        UserToBookEntity utb = new UserToBookEntity();
        utb.setBook(_book);
        utb.setUser(Auth.getUser());

        session.save(utb);
        tx = session.beginTransaction();
        tx.commit();

        List<UserToBookEntity> checkouts = Auth.getUser().getCheckouts();
        checkouts.add(utb);
        Auth.getUser().setCheckouts(checkouts);
        TableContent.setValue("b");
        refreshParent();
    }

    public void refreshParent(){
        try {
            Parent pageViewParent = FXMLLoader.load(getClass().getResource("Student.fxml"));
            Scene pageViewScene = new Scene(pageViewParent, 1366, 768);
            Stage window = (Stage) isbnLbl.getScene().getWindow();
            window.setScene(pageViewScene);
            window.show();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
