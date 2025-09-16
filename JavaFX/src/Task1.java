import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class Task1 extends Application {
    @Override
    public void start(Stage stage) {
        Button button = new Button("Нажми меня");

        button.setOnAction(e -> System.out.println("Кнопка нажата!"));

        StackPane root = new StackPane(button);
        Scene scene = new Scene(root, 300, 200);

        stage.setTitle("Задание 1");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}