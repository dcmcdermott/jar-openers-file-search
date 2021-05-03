/* * * * * * * * * * * *
 * Devin McDermott     *
 * Amanda Camelio      *
 * Joaquin Rojas Chang *
 * Justin Meek         *
 * * * * * * * * * * * */

package org.jaropeners;

import javafx.fxml.FXML;
import javafx.scene.control.Button;

import java.io.IOException;

// Controller class for about.fxml (about page)
public class AboutController {

    public Button btnGoBack;

    @FXML
    private void switchToPrimary() throws IOException {
        App.setRoot("primary");
    }
}
