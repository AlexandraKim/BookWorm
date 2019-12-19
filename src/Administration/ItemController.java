package Administration;

import Persistence.UserEntity;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

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

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    void initData(UserEntity obj) {
        idLbl.setText(obj.getId());
        nameLbl.setText(obj.getFirstName() + " " + obj.getLastName());
        phoneLbl.setText(obj.getPhone());
        emailLbl.setText(obj.getEmail());
    }
}
