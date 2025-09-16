import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class Task4 extends Application {
    @Override
    public void start(Stage stage) {
        Label nameLabel = new Label("Имя:");
        Label surnameLabel = new Label("Фамилия:");

        nameLabel.setPadding(new Insets(0, 0, 0, 20));
        surnameLabel.setPadding(new Insets(0, 0, 0, 20));

        TextField nameField = new TextField();
        TextField surnameField = new TextField();

        Button saveButton = new Button("Сохранить");
        saveButton.setOnAction(e -> {
            String name = nameField.getText();
            String surname = surnameField.getText();
            System.out.println("Сохранено: " + name + " " + surname);
        });

        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(20));

        grid.add(nameLabel, 0, 0);
        grid.add(nameField, 1, 0);

        grid.add(surnameLabel, 0, 1);
        grid.add(surnameField, 1, 1);

        grid.add(saveButton, 1, 2);

        Scene scene = new Scene(grid, 400, 200);
        stage.setTitle("Задание 4");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}
