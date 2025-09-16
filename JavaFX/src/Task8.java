import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Task8 extends Application {
    public static class Student {
        private final int id;
        private final String name;
        private final int age;

        public Student(int id, String name, int age) {
            this.id = id;
            this.name = name;
            this.age = age;
        }

        public int getId() { return id; }
        public String getName() { return name; }
        public int getAge() { return age; }
    }

    private int nextId = 4;

    @Override
    public void start(Stage stage) {
        TableView<Student> table = new TableView<>();
        ObservableList<Student> data = FXCollections.observableArrayList(
                new Student(1, "Ажар", 20),
                new Student(2, "Рахим", 21),
                new Student(3, "Диас", 20)
        );

        TableColumn<Student, Integer> idCol = new TableColumn<>("ID");
        idCol.setCellValueFactory(new PropertyValueFactory<>("id"));

        TableColumn<Student, String> nameCol = new TableColumn<>("Имя");
        nameCol.setCellValueFactory(new PropertyValueFactory<>("name"));

        TableColumn<Student, Integer> ageCol = new TableColumn<>("Возраст");
        ageCol.setCellValueFactory(new PropertyValueFactory<>("age"));

        table.getColumns().addAll(idCol, nameCol, ageCol);
        table.setItems(data);

        TextField nameField = new TextField();
        nameField.setPromptText("Имя");

        TextField ageField = new TextField();
        ageField.setPromptText("Возраст");

        Button addButton = new Button("Добавить");
        addButton.setOnAction(e -> {
            try {
                String name = nameField.getText().trim();
                int age = Integer.parseInt(ageField.getText().trim());
                if (!name.isEmpty()) {
                    data.add(new Student(nextId++, name, age));
                    nameField.clear();
                    ageField.clear();
                }
            } catch (NumberFormatException ex) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Ошибка");
                alert.setHeaderText("Неверный ввод возраста");
                alert.setContentText("Введите число для возраста.");
                alert.showAndWait();
            }
        });

        Button deleteButton = new Button("Удалить выбранного студента");
        deleteButton.setOnAction(e -> {
            Student selected = table.getSelectionModel().getSelectedItem();
            if (selected != null) {
                data.remove(selected);
            }
        });

        HBox addBox = new HBox(10, nameField, ageField, addButton);
        VBox root = new VBox(10, table, addBox, deleteButton);
        root.setStyle("-fx-padding: 20;");

        Scene scene = new Scene(root, 500, 400);
        stage.setTitle("Задание 8. Таблица студентов");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}
