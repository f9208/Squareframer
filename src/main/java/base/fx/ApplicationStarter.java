package base.fx;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.net.URL;

public class ApplicationStarter extends Application {
    public static void main(String[] args) {
        Application.launch();
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("Square Frame");
        FXMLLoader loader = new FXMLLoader();
        URL xmlUrl = getClass().getResource("/mainScene.fxml");
        loader.setLocation(xmlUrl);
        loader.getController();
        Parent root = loader.load();

        primaryStage.setScene(new Scene(root));
        primaryStage.setHeight(300);
        primaryStage.setWidth(400);
        primaryStage.show();
    }
}
