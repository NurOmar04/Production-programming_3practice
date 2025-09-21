import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

import java.util.Random;

public class Task_1_2 extends Application {

    private final int ROWS = 8;
    private final int COLS = 6;

    private TableView<ObservableList<Integer>> tableView;
    private ObservableList<ObservableList<Integer>> data;
    private Label resultLabel;

    @Override
    public void start(Stage primaryStage) {
        tableView = new TableView<>();
        data = FXCollections.observableArrayList();

        for (int col = 0; col < COLS; col++) {
            final int colIndex = col;
            TableColumn<ObservableList<Integer>, String> column = new TableColumn<>("Col " + (col + 1));
            column.setCellValueFactory(cellData ->
                    new javafx.beans.property.SimpleStringProperty(cellData.getValue().get(colIndex).toString()));
            tableView.getColumns().add(column);
        }

        Button randomBtn = new Button("Заполнить случайными числами");
        randomBtn.setOnAction(e -> fillRandom());

        Button taskBtn = new Button("Выполнить задание");
        taskBtn.setOnAction(e -> doTask());

        resultLabel = new Label("Результат: ");

        HBox buttons = new HBox(10, randomBtn, taskBtn, resultLabel);
        buttons.setAlignment(Pos.CENTER);

        BorderPane root = new BorderPane();
        root.setCenter(tableView);
        root.setBottom(buttons);

        Scene scene = new Scene(root, 600, 400);
        primaryStage.setTitle("Работа с таблицами");
        primaryStage.setScene(scene);
        primaryStage.show();

        fillRandom();
    }

    private void fillRandom() {
        data.clear();
        Random rand = new Random();
        for (int i = 0; i < ROWS; i++) {
            ObservableList<Integer> row = FXCollections.observableArrayList();
            for (int j = 0; j < COLS; j++) {
                row.add(rand.nextInt(201) - 100);
            }
            data.add(row);
        }
        tableView.setItems(data);
    }

    private void doTask() {
        if (data.isEmpty()) return;

        int zeroCount = 0;
        for (int j = 0; j < COLS; j++) {
            if (data.get(1).get(j) == 0) {
                zeroCount++;
            }
        }

        int maxVal = data.get(0).get(1);
        int maxRowIndex = 0;
        for (int i = 1; i < ROWS; i++) {
            if (data.get(i).get(1) > maxVal) {
                maxVal = data.get(i).get(1);
                maxRowIndex = i;
            }
        }

        data.get(maxRowIndex).set(1, zeroCount);
        tableView.refresh();

        resultLabel.setText("Результат: нулей во 2-й строке = " + zeroCount +
                ", заменили max во 2-м столбце (" + maxVal + ")");
    }

    public static void main(String[] args) {
        launch(args);
    }
}
