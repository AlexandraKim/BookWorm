package Administration;

import Persistence.UserEntity;
import javafx.beans.binding.StringBinding;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
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

    public void modifyBtnClick(){
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("Modify.fxml"));
            Stage stage = new Stage();
            stage.setTitle("Modify " + user.getId());
            stage.setScene(new Scene(loader.load(), 409, 411));
            ModifyController controller = loader.<ModifyController>getController();
            controller.initData(user);
            StringBinding strBinding = new StringBinding() {
                {
                    bind(controller.firstNameLbl.textProperty(), controller.lastNameLbl.textProperty());
                }

                @Override
                protected String computeValue() {
                    return controller.firstNameLbl.textProperty().getValue() + " " + controller.lastNameLbl.textProperty().getValue();
                }
            };

            nameLbl.textProperty().bind(strBinding);
            phoneLbl.textProperty().bind(controller.phoneLbl.textProperty());
            emailLbl.textProperty().bind(controller.emailLbl.textProperty());
            stage.show();
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

    public void deleteBtnClick(){
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("Confirm.fxml"));
            Stage stage = new Stage();
            stage.setTitle("Confirm deletion of " + user.getId());
            stage.setScene(new Scene(loader.load(), 409, 274));
            DeleteController controller = loader.<DeleteController>getController();
            controller.initData(user);
            stage.showAndWait();
            refreshParent();
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

    public void refreshParent(){
        try {
            Parent pageViewParent = FXMLLoader.load(getClass().getResource("Admin.fxml"));
            Scene pageViewScene = new Scene(pageViewParent, 1366, 768);
            Stage window = (Stage) idLbl.getScene().getWindow();
            window.setScene(pageViewScene);
            String type = "l";
            if(user.getType() == 1){
                type = "l";
            }
            if(user.getType() == 2){
                type = "s";
            }
            TableContent.setValue(type);
            window.show();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
