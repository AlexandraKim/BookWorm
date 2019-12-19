package Administration;

import Persistence.UserEntity;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class ItemController implements Initializable {
    @FXML
    Label idLbl;
    @FXML
    Label nameLbl;
    @FXML
    Label phoneLbl;
    @FXML
    Label departmentLbl;
    @FXML
    Label emailLbl;
    @FXML
    Button modifyBtn;
    @FXML
    Button deleteBtn;

    private UserEntity user;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    void initData(UserEntity obj) {
        user = obj;
        idLbl.setText(obj.getId());
        nameLbl.setText(obj.getFirstName() + " " + obj.getLastName());
        phoneLbl.setText(obj.getPhone());
        emailLbl.setText(obj.getEmail());
    }

    public void modifyBtnClick(ActionEvent event){
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("ADD.fxml"));
            AddController controller = new AddController();
            loader.setController(controller);
            Stage stage = new Stage();
            stage.setTitle("Modify " + user.getId());
            stage.setScene(new Scene(loader.load(), 409, 411));
            stage.show();
           // ((Node)(event.getSource())).getScene().getWindow().hide();
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }
}
