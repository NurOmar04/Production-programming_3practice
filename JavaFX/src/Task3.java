import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Task3 extends Application {
    @Override
    public void start(Stage stage) {
        Label title = new Label("Сложение чисел");

        TextField num1 = new TextField();
        num1.setPromptText("Число 1");

        TextField num2 = new TextField();
        num2.setPromptText("Число 2");

        HBox inputBox = new HBox(10, num1, num2);

        Button addButton = new Button("Сложить");
        Label result = new Label();

        addButton.setOnAction(e -> {
            try {
                double a = Double.parseDouble(num1.getText());
                double b = Double.parseDouble(num2.getText());
                result.setText("Сумма: " + (a + b));
            } catch (NumberFormatException ex) {
                result.setText("Ошибка: введите числа");
            }
        });

        VBox root = new VBox(15, title, inputBox, addButton, result);
        root.setStyle("-fx-padding: 20; -fx-alignment: center;");

        Scene scene = new Scene(root, 400, 200);
        stage.setTitle("Задание 3");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}
