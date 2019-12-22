package Librarian;

import Persistence.BookEntity;
import Persistence.UserEntity;
import Utils.DatabaseConnection;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.net.URL;
import java.util.ResourceBundle;

public class CheckoutWIndowController implements Initializable {
    @FXML
    Label nameLbl;
    @FXML
    Label phoneLbl;
    @FXML
    Label emailLbl;
    @FXML
    Button OKBtn;

    private UserEntity _user;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {}

    public void initData(UserEntity user) {
        _user = user;
        nameLbl.setText(user.getFirstName() + " " + user.getLastName());
        phoneLbl.setText(user.getPhone());
        emailLbl.setText(user.getEmail());
    }

    public void cancelBtnClick(){
        closeDialog(OKBtn);
    }

    public void closeDialog(Button btn) {
        Stage stage = (Stage) btn.getScene().getWindow();
        stage.close();
    }
}
