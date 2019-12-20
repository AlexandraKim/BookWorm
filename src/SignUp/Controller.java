package SignUp;

import Administration.TableContent;
import Persistence.UserEntity;
import Utils.DatabaseConnection;
import Utils.Auth;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.event.ActionEvent;
import javafx.stage.Stage;
import org.hibernate.Query;
import org.hibernate.Session;

import java.util.List;

public class Controller {
    @FXML
    public TextField userId;
    public PasswordField password;
    public Label validationError;
    public Button signInBtn;

    public void signInBtnClick(ActionEvent event) {
        Session session = DatabaseConnection.get_sessionFactory().openSession();
        String hql = "from UserEntity where id = :id and password = :password";
        Query query = session.createQuery(hql);
        query.setParameter("id", userId.getText());
        query.setParameter("password", password.getText());
        List result = query.list();
        session.close();

        if (result.isEmpty()) {
            validationError.setVisible(true);
        } else {
            validationError.setVisible(false);
            for ( UserEntity user : (List<UserEntity>) result ) {
                Auth.setUser(user);
                String pagePath = "";
                if(user.getType() == 0){
                    pagePath = "../Administration/Admin.fxml";
                } else if (user.getType() == 1){
                    pagePath = "../Librarian/Librarian.fxml";
                }
                try {
                    Parent pageViewParent = FXMLLoader.load(getClass().getResource(pagePath));
                    Scene pageViewScene = new Scene(pageViewParent, 1366, 768);
                    Stage window = (Stage)((javafx.scene.Node)event.getSource()).getScene().getWindow();
                    window.setScene(pageViewScene);
                    TableContent.setValue("l");
                    window.show();
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
            }
        }
    }
}
