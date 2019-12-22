package Student;

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
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.net.URL;
import java.util.ResourceBundle;

public class ItemCheckoutsController implements Initializable {
    @FXML
    Label isbnLbl;
    @FXML
    Label titleLbl;
    @FXML
    Label authorLbl;
    @FXML
    Label issueDateLbl;
    @FXML
    Label returnDateLbl;

    private UserToBookEntity _checkout;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    void initData(UserToBookEntity checkout) {
        _checkout = checkout;
        isbnLbl.setText(checkout.getBook().getIsbn());
        titleLbl.setText(checkout.getBook().getTitle());
        authorLbl.setText(checkout.getBook().getAuthor());
        issueDateLbl.setText(DateFormat.convert(checkout.getIssueDate()));
        returnDateLbl.setText(DateFormat.convert(checkout.getReturnDate()));
    }
}
