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
import java.nio.file.Path;
import java.nio.file.attribute.FileTime;
import java.sql.*;
import java.util.Arrays;
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
import javafx.stage.Stage;

public class SecondaryController implements Initializable {


    @FXML
    public TableView<IndexedFile> tvIndexedFiles;
    @FXML
    public TableColumn<IndexedFile, String> col_filepath;
    @FXML
    public TableColumn<IndexedFile, String> col_last_modified;

    private static ObservableList<IndexedFile> oblist = FXCollections.observableArrayList();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        updateTable();
    }

    @FXML
    public void addFile() throws IOException {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open Resource File");
        File selectedFile = fileChooser.showOpenDialog(null);
        FileTime lm = Files.getLastModifiedTime(selectedFile.toPath());

        try (Connection con = DBDriver.getConnection()) {
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
        updateTable();
    }

    @FXML
    private void removeFile() throws Exception {

        // get data from currently selected row and current time
        String selected_file_path = tvIndexedFiles.getSelectionModel().getSelectedItem().filepath;

        // remove the given med from today's med list
        //tvPrescriptions.getItems().removeAll(tvPrescriptions.getSelectionModel().getSelectedItem());

        try (Connection con = DBDriver.getConnection()) {

            // Q1 update given today to true for selected med
            // sql statement
            String query = "delete from indexed_files where filepath = ?";

            // create sql prepared statement
            PreparedStatement preparedStmt1 = con.prepareStatement(query);
            preparedStmt1.setString(1, selected_file_path);

            // execute the prepared statement
            preparedStmt1.execute();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        // refresh given meds table
        updateTable();
    }

    public void updateTable() {

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

    @FXML
    private void switchToPrimary() throws IOException {
        tvIndexedFiles.getItems().clear();
        App.setRoot("primary");
    }

}