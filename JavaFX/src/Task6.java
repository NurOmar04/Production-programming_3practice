import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Task6 extends Application {
    @Override
    public void start(Stage stage) {
        TextField num1 = new TextField();
        num1.setPromptText("Число 1");

        TextField num2 = new TextField();
        num2.setPromptText("Число 2");

        Label result = new Label("Результат: ");

        Button add = new Button("+");
        Button sub = new Button("-");
        Button mul = new Button("*");
        Button div = new Button("/");

        add.setOnAction(e -> calculate(num1, num2, result, "+"));
        sub.setOnAction(e -> calculate(num1, num2, result, "-"));
        mul.setOnAction(e -> calculate(num1, num2, result, "*"));
        div.setOnAction(e -> calculate(num1, num2, result, "/"));

        HBox buttons = new HBox(10, add, sub, mul, div);
        buttons.setStyle("-fx-alignment: center;");

        VBox root = new VBox(15, num1, num2, buttons, result);
        root.setStyle("-fx-padding: 20; -fx-alignment: center;");

        Scene scene = new Scene(root, 400, 250);
        stage.setTitle("Задание 6. Калькулятор");
        stage.setScene(scene);
        stage.show();
    }

    private void calculate(TextField num1, TextField num2, Label result, String op) {
        try {
            double a = Double.parseDouble(num1.getText());
            double b = Double.parseDouble(num2.getText());
            double res = switch (op) {
                case "+" -> a + b;
                case "-" -> a - b;
                case "*" -> a * b;
                case "/" -> (b != 0) ? a / b : Double.NaN;
                default -> 0;
            };
            result.setText("Результат: " + res);
        } catch (NumberFormatException ex) {
            result.setText("Ошибка: введите числа");
        }
    }

    public static void main(String[] args) {
        launch();
    }
}
