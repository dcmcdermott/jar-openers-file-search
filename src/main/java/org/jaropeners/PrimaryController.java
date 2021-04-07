/* Devin McDermott     *
 * Amanda Camelio      *
 * Joaquin Rojas Chang *
 * Justin Meek         *
 * * * * * * * * * * * */

package org.jaropeners;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class PrimaryController {



    @FXML
    private void switchToSecondary() throws IOException {
        App.setRoot("secondary");
    }


    public void switchToAbout() throws IOException {
        App.setRoot("tertiary");

    }


    public void switchToPrimary() throws IOException {
        App.setRoot("primary");
    }
}
