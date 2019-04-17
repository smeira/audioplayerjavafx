package application;

import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;

public class Main extends Application {

    @Override

    public void start(Stage primaryStage) {

        try {
            primaryStage.setScene(new Scene(
                    (BorderPane) FXMLLoader.load(getClass().getResource("Screen.fxml"))));
            primaryStage.show();
            primaryStage.setTitle("Simple Audio Player");
        } catch (Exception e) {
            System.out.println(e.toString());
        }        

        
    }

    public static void main(String[] args) {
        launch(args);
    }

}
