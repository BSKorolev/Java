package taskM;

import javafx.scene.image.Image;
import taskM.datamodel.TaskData;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("MainMenu.fxml"));
        primaryStage.setTitle("Task Manager");
        primaryStage.setScene(new Scene(root, 900, 500));
        primaryStage.show();
        Image icon = new Image(getClass().getResourceAsStream("icon.png"));
        primaryStage.getIcons().add(icon);
    }
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void stop() throws Exception {
        try {
            TaskData.getInstance().storeTodoItems();

        } catch(IOException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void init() throws Exception {
        try {
            TaskData.getInstance().loadTodoItems();

        } catch(IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
