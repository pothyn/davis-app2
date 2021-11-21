/*
 *  UCF COP3330 Fall 2021 Application Assignment 2 Solution
 *  Copyright 2021 Hunter Davis
 */

package baseline;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class InventoryManagementApplication extends javafx.application.Application {
    private Stage window;

    @Override
    public void start(Stage stage) throws IOException {
        window = stage;
        goToHomePage();
    }

    public static void main(String[] args) {
        launch(args);
    }

    public void goToHomePage() throws IOException {
        // Open Load.fxml
        FXMLLoader loader = new FXMLLoader(InventoryManagementApplication.class.getClassLoader().getResource("baseline/HomePage.fxml"));
        Parent root = loader.load();

        Scene scene = new Scene(root);

        window.setTitle("Stockable");
        window.setScene(scene);
        window.show();
    }
}
