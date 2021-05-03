/* * * * * * * * * * * *
 * Devin McDermott     *
 * Amanda Camelio      *
 * Joaquin Rojas Chang *
 * Justin Meek         *
 * * * * * * * * * * * */

package org.jaropeners;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.attribute.FileTime;
import java.sql.*;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.FileChooser;

// Controller class for secondary.fxml (index maintenance page)
public class SecondaryController implements Initializable {

    public Button btnAddFile;
    public Button btnRemoveFile;
    public Button btnBackToFileSearch;

    // indexed files tableview
    @FXML
    public TableView<IndexedFile> tvIndexedFiles;
    @FXML
    public TableColumn<IndexedFile, String> col_filepath;
    @FXML
    public TableColumn<IndexedFile, String> col_last_modified;

    final static ObservableList<IndexedFile> oblist = FXCollections.observableArrayList();

    // initialize in startup
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        updateTable();
    }

    // When add file button is clicked
    @FXML
    public void addFile() throws IOException {

        // display file chooser to user
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Add File to Index");

        // get filepath and last_modified time from chosen file
        File selectedFile = fileChooser.showOpenDialog(null);
        FileTime lm = Files.getLastModifiedTime(selectedFile.toPath());

        // add the chosen file to db
        try (Connection con = DBDriver.getConnection()) {

            // sql query
            String query = " insert into indexed_files (filepath, last_modified)"
                    + " values (?, ?)";

            // create sql prepared statement
            PreparedStatement preparedStmt = con.prepareStatement(query);
            preparedStmt.setString (1, selectedFile.toString());
            preparedStmt.setString (2, lm.toString());

            // execute the prepared statement
            preparedStmt.execute();
        }
        catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        // update the tableview
        updateTable();
    }

    // when remove selected file button is clicked
    @FXML
    private void removeFile() {

        // get filepath from currently selected row
        String selected_file_path = tvIndexedFiles.getSelectionModel().getSelectedItem().filepath;

        // delete selected file from db
        try (Connection con = DBDriver.getConnection()) {

            // sql query
            String query = "delete from indexed_files where filepath = ?";

            // create sql prepared statement
            PreparedStatement preparedStmt1 = con.prepareStatement(query);
            preparedStmt1.setString(1, selected_file_path);

            // execute the prepared statement
            preparedStmt1.execute();
        }
        catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        // update the tableview
        updateTable();
    }

    // update indexed files tableview
    public void updateTable() {

        // clear the tableview
        tvIndexedFiles.getItems().clear();

        // get all indexed files from db and store them as objects in oblist
        try (Connection con = DBDriver.getConnection()) {
            ResultSet rs = con.createStatement().executeQuery("select * from indexed_files");
            while (rs.next()) {
                oblist.add(new IndexedFile(
                        rs.getString("filepath"),
                        rs.getString("last_modified")));
            }
        }
        catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        // set cell value factories for table columns
        col_filepath.setCellValueFactory(new PropertyValueFactory<>("filepath"));
        col_last_modified.setCellValueFactory(new PropertyValueFactory<>("last_modified"));

        // apply oblist to indexed_files table
        tvIndexedFiles.setItems(oblist);
    }

    // navigation button methods
    @FXML
    private void switchToPrimary() throws IOException {
        tvIndexedFiles.getItems().clear();
        App.setRoot("primary");
    }

}