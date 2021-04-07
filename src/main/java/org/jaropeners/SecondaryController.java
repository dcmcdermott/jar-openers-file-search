/* Devin McDermott     *
 * Amanda Camelio      *
 * Joaquin Rojas Chang *
 * Justin Meek         *
 * * * * * * * * * * * */

package org.jaropeners;

import java.io.IOException;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;

public class SecondaryController {

    @FXML
    private void switchToPrimary() throws IOException {
        App.setRoot("primary");
    }
    @FXML
    private TableColumn<?, ?> tvIndexedFiles;

    @FXML
    private Button btnAddFile;

    @FXML
    private Button btnRemoveFile;

    @FXML
    private Button btnRemoveOutOfDate;

    @FXML
    private Button btnBackToFileSearch;


}