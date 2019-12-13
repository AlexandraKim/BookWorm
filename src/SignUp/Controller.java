package SignUp;

import Persistence.UserEntity;
import Utils.DatabaseConnection;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import org.hibernate.Query;
import org.hibernate.Session;

import java.util.List;

public class Controller {
    @FXML
    public TextField userId;
    public PasswordField password;
    public Label validationError;

    public void signInBtnClick(){
        Session session = DatabaseConnection.getSession();
        String hql = "from UserEntity where id = :id and password = :password";
        Query query = session.createQuery(hql);
        query.setParameter("id", userId.getText());
        query.setParameter("password", password.getText());
        List result = query.list();

        if (result.isEmpty()) {
            validationError.setVisible(true);
        } else {
            validationError.setVisible(false);
            for ( UserEntity user : (List<UserEntity>) result ) {
                if (user.getType() == 0){

                } else if (user.getType() == 1 )
                System.out.println("Welcome! " + user.getFirstName() + " " + user.getLastName());
            }
        }
    }
}
