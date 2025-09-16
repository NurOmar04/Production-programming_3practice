import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;

public class Task5 extends Application {
    @Override
    public void start(Stage stage) {
        Circle circle = new Circle(60, Color.GRAY);

        Button redButton = new Button("Красный");
        Button yellowButton = new Button("Жёлтый");
        Button greenButton = new Button("Зелёный");

        redButton.setOnAction(e -> circle.setFill(Color.RED));
        yellowButton.setOnAction(e -> circle.setFill(Color.YELLOW));
        greenButton.setOnAction(e -> circle.setFill(Color.GREEN));

        HBox buttons = new HBox(10, redButton, yellowButton, greenButton);
        buttons.setStyle("-fx-alignment: center;");

        VBox root = new VBox(20, circle, buttons);
        root.setStyle("-fx-padding: 20; -fx-alignment: center;");

        Scene scene = new Scene(root, 400, 300);
        stage.setTitle("Задание 5. Светофор");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}
