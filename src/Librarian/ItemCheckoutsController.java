package Librarian;

import Persistence.UserEntity;
import Persistence.UserToBookEntity;
import Utils.DatabaseConnection;
import Utils.DateFormat;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.net.URL;
import java.util.Date;
import java.util.ResourceBundle;

public class ItemCheckoutsController implements Initializable {
    @FXML
    Label isbnLbl;
    @FXML
    Label titleLbl;
    @FXML
    Label authorLbl;
    @FXML
    Label issueDateLbl;
    @FXML
    Label returnDateLbl;
    @FXML
    Button checkoutBtn;
    @FXML
    Button studentViewBtn;

    private UserToBookEntity _checkout;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    void initData(UserToBookEntity checkout) {
        _checkout = checkout;
        isbnLbl.setText(checkout.getBook().getIsbn());
        studentViewBtn.setText(checkout.getUser().getId());
        titleLbl.setText(checkout.getBook().getTitle());
        authorLbl.setText(checkout.getBook().getAuthor());
        if (checkout.getIssueDate() == null){
            checkoutBtn.setText("CHECKOUT");
        } else if (checkout.getReturnDate() == null) {
            checkoutBtn.setText("RETURN");
        } else {
            checkoutBtn.setVisible(false);
        }

        issueDateLbl.setText(DateFormat.convert(checkout.getIssueDate()));
        returnDateLbl.setText(DateFormat.convert(checkout.getReturnDate()));

    }

    public void checkoutBtnClick (){
        Transaction tx = null;
        Session session = DatabaseConnection.get_sessionFactory().openSession();
        if (_checkout.getIssueDate() == null){
            Query q = session.createQuery("update UserToBookEntity set " +
                    "issueDate = :issueDate " +
                    "where id = :id");
            q.setParameter("issueDate", new Date());
            q.setParameter("id", _checkout.getId());
            int result = q.executeUpdate();
        } else if (_checkout.getReturnDate() == null) {
            Query q = session.createQuery("update UserToBookEntity set " +
                    "returnDate = :returnDate " +
                    "where id = :id");
            q.setParameter("returnDate", new Date());
            q.setParameter("id", _checkout.getId());
            int result = q.executeUpdate();
        } else {
            checkoutBtn.setVisible(false);

        }
        tx = session.beginTransaction();
        tx.commit();
        session.close();

        refreshParent();
    }

    public void studentInfoClick(){
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("CheckoutWindow.fxml"));
            Stage stage = new Stage();
            stage.setScene(new Scene(loader.load(), 409, 274));
            CheckoutWIndowController controller = loader.<CheckoutWIndowController>getController();
            controller.initData(_checkout.getUser());
            stage.showAndWait();
            refreshParent();
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

    public void refreshParent(){
        try {
            TableContent.setValue("c");
            Parent pageViewParent = FXMLLoader.load(getClass().getResource("Librarian.fxml"));
            Scene pageViewScene = new Scene(pageViewParent, 1366, 768);
            Stage window = (Stage) isbnLbl.getScene().getWindow();
            window.setScene(pageViewScene);
            window.show();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
