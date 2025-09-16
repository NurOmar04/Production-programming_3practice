import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

import java.util.HashMap;
import java.util.Map;

public class Task10 extends Application {

    private ListView<String> notesListView;
    private TextArea noteEditor;
    private ObservableList<String> notes;
    private Map<String, String> notesContent;
    private String currentNote = null;

    @Override
    public void start(Stage primaryStage) {
        notes = FXCollections.observableArrayList();
        notesContent = new HashMap<>();

        notesListView = new ListView<>(notes);
        noteEditor = new TextArea();

        Button addButton = new Button("Добавить");
        Button deleteButton = new Button("Удалить");
        Button renameButton = new Button("Переименовать");

        notesListView.getSelectionModel().selectedItemProperty().addListener((obs, oldVal, newVal) -> {
            currentNote = newVal;
            if (newVal != null) {
                noteEditor.setText(notesContent.getOrDefault(newVal, ""));
            } else {
                noteEditor.clear();
            }
        });

        noteEditor.textProperty().addListener((obs, oldText, newText) -> {
            if (currentNote != null) {
                notesContent.put(currentNote, newText);
            }
        });

        addButton.setOnAction(e -> {
            TextInputDialog dialog = new TextInputDialog("Новая заметка");
            dialog.setTitle("Добавление заметки");
            dialog.setHeaderText("Введите имя новой заметки:");
            dialog.setContentText("Имя:");

            dialog.showAndWait().ifPresent(name -> {
                if (!name.trim().isEmpty() && !notes.contains(name)) {
                    notes.add(name);
                    notesContent.put(name, "");
                    notesListView.getSelectionModel().select(name);
                } else {
                    Alert alert = new Alert(Alert.AlertType.WARNING,
                            "Заметка с таким именем уже существует или имя пустое!", ButtonType.OK);
                    alert.showAndWait();
                }
            });
        });

        deleteButton.setOnAction(e -> {
            String selected = notesListView.getSelectionModel().getSelectedItem();
            if (selected != null) {
                notesContent.remove(selected);
                int index = notesListView.getSelectionModel().getSelectedIndex();
                notes.remove(selected);

                if (!notes.isEmpty()) {
                    if (index >= notes.size()) {
                        index = notes.size() - 1;
                    }
                    notesListView.getSelectionModel().select(index);
                } else {
                    noteEditor.clear();
                    currentNote = null;
                }
            }
        });

        renameButton.setOnAction(e -> {
            String selected = notesListView.getSelectionModel().getSelectedItem();
            if (selected != null) {
                TextInputDialog dialog = new TextInputDialog(selected);
                dialog.setTitle("Переименование заметки");
                dialog.setHeaderText("Введите новое имя для заметки:");
                dialog.setContentText("Имя:");

                dialog.showAndWait().ifPresent(newName -> {
                    if (!newName.trim().isEmpty() && !notes.contains(newName)) {
                        String content = notesContent.remove(selected);
                        notesContent.put(newName, content);

                        int index = notes.indexOf(selected);
                        notes.set(index, newName);

                        notesListView.getSelectionModel().select(newName);
                    } else {
                        Alert alert = new Alert(Alert.AlertType.WARNING,
                                "Заметка с таким именем уже существует или имя пустое!", ButtonType.OK);
                        alert.showAndWait();
                    }
                });
            }
        });

        HBox buttons = new HBox(10, addButton, deleteButton, renameButton);

        BorderPane root = new BorderPane();
        root.setLeft(notesListView);
        root.setCenter(noteEditor);
        root.setBottom(buttons);

        notesListView.setPrefWidth(200);

        Scene scene = new Scene(root, 600, 400);
        primaryStage.setTitle("Заметки");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
