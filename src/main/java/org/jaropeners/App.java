/* * * * * * * * * * * *
 * Devin McDermott     *
 * Amanda Camelio      *
 * Joaquin Rojas Chang *
 * Justin Meek         *
 * * * * * * * * * * * */

package org.jaropeners;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;

/**
 * JavaFX App
 */

public class App extends Application {

    private static Scene scene;

    @Override
    public void start(Stage stage) throws IOException {
        scene = new Scene(loadFXML("primary"), 900, 600);
        stage.setScene(scene);
        stage.getIcons().add(new Image("https://image.flaticon.com/icons/png/512/2229/2229536.png"));
        stage.setTitle("Jar Openers File Search");
        stage.show();



    }

    static void setRoot(String fxml) throws IOException {
        scene.setRoot(loadFXML(fxml));
    }

    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
        return fxmlLoader.load();

    }

    public static void main(String[] args) throws IOException {


        launch();
    }
}
