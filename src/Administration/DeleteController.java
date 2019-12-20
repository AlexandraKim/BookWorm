package Administration;

import Persistence.UserEntity;
import Utils.DatabaseConnection;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.net.URL;
import java.util.ResourceBundle;

public class DeleteController implements Initializable {
    @FXML
    Label textLbl;

    @FXML
    Button cancelBtn;
    @FXML
    Button deleteBtn;

    private UserEntity _user;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {}

    public void initData(UserEntity user) {
        _user = user;
        textLbl.setText("Are you sure you want to delete " + _user.getFirstName() + " " + _user.getLastName() + "?");
    }

    public void deleteBtnClick(ActionEvent event){
        Transaction tx = null;
        Session session = DatabaseConnection.get_sessionFactory().openSession();
        Query q = session.createQuery("delete from UserEntity where id = :id ");
        q.setParameter("id", _user.getId());
        q.executeUpdate();
        tx = session.beginTransaction();
        tx.commit();
        session.close();
        closeDialog(deleteBtn);
    }

    public void cancelBtnClick(){
        closeDialog(cancelBtn);
    }

    public void closeDialog(Button btn) {
        Stage stage = (Stage) btn.getScene().getWindow();
        stage.close();
    }
}
