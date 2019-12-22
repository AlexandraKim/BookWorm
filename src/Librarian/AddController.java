package Librarian;

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
import java.util.ResourceBundle;

public class AddController implements Initializable {
    @FXML
    TextField idLbl;
    @FXML
    TextField firstNameLbl;
    @FXML
    TextField lastNameLbl;
    @FXML
    TextField phoneLbl;
    @FXML
    TextField emailLbl;
    @FXML
    TextField passwordTxt;
    @FXML
    Button cancelBtn;
    @FXML
    Button addBtn;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {}

    public void addBtnClick(ActionEvent event){
        int type = 0;
        if (TableContent.getValue() == "l") {
            type = 1;
        }
        if (TableContent.getValue() == "s") {
            type = 2;
        }
        UserEntity user = new UserEntity();
        user.setId(idLbl.getText());
        user.setFirstName(firstNameLbl.getText());
        user.setLastName(lastNameLbl.getText());
        user.setPhone(phoneLbl.getText());
        user.setEmail(emailLbl.getText());
        user.setPassword(passwordTxt.getText());
        user.setType(type);
        Transaction tx = null;
        Session session = DatabaseConnection.get_sessionFactory().openSession();
        session.save(user);
        tx = session.beginTransaction();
        tx.commit();
        session.close();
        closeDialog(addBtn);
    }

    public void cancelBtnClick(){
        closeDialog(cancelBtn);
    }

    public void closeDialog(Button btn) {
        Stage stage = (Stage) btn.getScene().getWindow();
        stage.close();
    }
}
