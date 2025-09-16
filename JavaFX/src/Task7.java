import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextInputDialog;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.Optional;

public class Task7 extends Application {
    @Override
    public void start(Stage stage) {
        ListView<String> students = new ListView<>();

        Button addButton = new Button("Добавить");
        addButton.setOnAction(e -> {
            TextInputDialog dialog = new TextInputDialog();
            dialog.setTitle("Добавить студента");
            dialog.setHeaderText(null);
            dialog.setContentText("Введите имя:");
            Optional<String> result = dialog.showAndWait();
            result.ifPresent(name -> students.getItems().add(name));
        });

        VBox root = new VBox(10, students, addButton);
        root.setStyle("-fx-padding: 20;");

        Scene scene = new Scene(root, 300, 400);
        stage.setTitle("Задание 7. Список студентов");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}
