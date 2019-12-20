package Administration;

import Persistence.UserEntity;
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
import java.util.ResourceBundle;

public class ModifyUserController implements Initializable {
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
    Button cancelBtn;
    @FXML
    Button saveBtn;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    public void initData(UserEntity user) {
        idLbl.setText(user.getId());
        firstNameLbl.setText(user.getFirstName());
        lastNameLbl.setText(user.getLastName());
        phoneLbl.setText(user.getPhone());
        emailLbl.setText(user.getEmail());
    }

    public void saveBtnClick(ActionEvent event){
        UserEntity user = new UserEntity();
        user.setId(idLbl.getText());
        user.setFirstName(firstNameLbl.getText());
        user.setLastName(lastNameLbl.getText());
        user.setPhone(phoneLbl.getText());
        user.setEmail(emailLbl.getText());
        Transaction tx=null;
        Session session = DatabaseConnection.get_sessionFactory().openSession();
        Query query = session.createQuery("update UserEntity set " +
                "firstName = :firstName, " +
                "lastName = :lastName, " +
                "phone = :phone, " +
                "email = :email " +
                "where id = :id");
        query.setParameter("firstName", user.getFirstName());
        query.setParameter("lastName", user.getLastName());
        query.setParameter("phone", user.getPhone());
        query.setParameter("email", user.getEmail());
        query.setParameter("id", user.getId());
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
