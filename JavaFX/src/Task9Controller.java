import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class Task9Controller {
    @FXML private TextField nameField;
    @FXML private TextField emailField;
    @FXML private PasswordField passwordField;

    @FXML
    protected void onRegister() {
        System.out.println("Имя: " + nameField.getText());
        System.out.println("Email: " + emailField.getText());
        System.out.println("Пароль: " + passwordField.getText());
    }
}
