import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Task2 extends Application {
    @Override
    public void start(Stage stage) {
        Label label = new Label("Введите имя:");
        TextField nameField = new TextField();
        Button helloButton = new Button("Привет");
        Label outputLabel = new Label();

        helloButton.setOnAction(e -> {
            String name = nameField.getText();
            outputLabel.setText("Привет, " + name + "!");
        });

        VBox root = new VBox(10, label, nameField, helloButton, outputLabel);
        root.setStyle("-fx-padding: 20; -fx-alignment: center;");

        Scene scene = new Scene(root, 300, 200);

        stage.setTitle("Задание 2");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}
