import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Вариант 11");

        Label title = new Label("Вариант 11");
        title.setStyle("-fx-font-size: 20px; -fx-font-weight: bold;");

        Label labelA = new Label("A =");
        TextField inputA = new TextField();

        Label labelB = new Label("B =");
        TextField inputB = new TextField();

        Label labelX = new Label("X =");
        TextField inputX = new TextField();

        Label labelY = new Label("Ответ:");
        Label outputY = new Label("");

        Button solveButton = new Button("Решить");
        Button clearButton = new Button("Очистить");
        Button exitButton = new Button("Выход");

        solveButton.setOnAction(e -> {
            try {
                double a = Double.parseDouble(inputA.getText());
                double b = Double.parseDouble(inputB.getText());
                double x = Double.parseDouble(inputX.getText());

                double y;
                if (x >= 4) {
                    y = (10 * (x + Math.pow(a, 2))) / (b + a);
                } else {
                    y = 5 * (x + Math.pow(a, 2) + b);
                }

                outputY.setText(String.valueOf(y));
            } catch (Exception ex) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Ошибка");
                alert.setHeaderText("Неверный ввод");
                alert.setContentText("Введите корректные числа в поля A, B, X.");
                alert.showAndWait();
            }
        });

        clearButton.setOnAction(e -> {
            inputA.clear();
            inputB.clear();
            inputX.clear();
            outputY.setText("");
        });

        exitButton.setOnAction(e -> primaryStage.close());

        Image image = new Image(getClass().getResourceAsStream("/formula.png"));
        ImageView imageView = new ImageView(image);
        imageView.setFitWidth(300);
        imageView.setPreserveRatio(true);

        GridPane grid = new GridPane();
        grid.setPadding(new Insets(10));
        grid.setHgap(10);
        grid.setVgap(10);

        grid.add(labelA, 0, 0);
        grid.add(inputA, 1, 0);
        grid.add(labelB, 0, 1);
        grid.add(inputB, 1, 1);
        grid.add(labelX, 0, 2);
        grid.add(inputX, 1, 2);
        grid.add(labelY, 0, 3);
        grid.add(outputY, 1, 3);

        HBox buttons = new HBox(15, solveButton, clearButton, exitButton);
        buttons.setAlignment(Pos.CENTER);

        VBox root = new VBox(15, title, imageView, grid, buttons);
        root.setAlignment(Pos.TOP_CENTER);
        root.setPadding(new Insets(10));

        Scene scene = new Scene(root, 500, 400);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
