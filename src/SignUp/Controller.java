package SignUp;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Controller {
    public Button signIdBtn;
    @FXML
    public TextField userId;
    public PasswordField password;
    public Label validationError;

    public void signInBtnClick(){
        try (
            Connection conn = DatabaseConnection.getInstance().getConn();
            Statement stmt = conn.createStatement();
        )
        {
            ResultSet resultSet = stmt.executeQuery("select * from user where id = '" + userId.getText() + "'  and password = '" + password.getText() + "'");
            if (!resultSet.next()){
                validationError.setVisible(true);
            }
            while(resultSet.next()) {
                String firstname = resultSet.getString("firstname");
                String lastname = resultSet.getString("lastname");

                System.out.println("Welcome!" + firstname + " " + lastname );
            }
        } catch(SQLException ex) {
            ex.printStackTrace();
        }
    }
}
