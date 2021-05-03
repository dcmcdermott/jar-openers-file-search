/* * * * * * * * * * * *
 * Devin McDermott     *
 * Amanda Camelio      *
 * Joaquin Rojas Chang *
 * Justin Meek         *
 * * * * * * * * * * * */

package org.jaropeners;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.Locale;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

public class PrimaryController implements Initializable {

    public TextField tfSearch;
    public Button btnSearch;
    public Label number_indexed;
    public RadioButton radAny;
    public RadioButton radAll;
    public RadioButton radExact;
    public Button btnIndexMaintenance;
    public Button btnAbout;
    public ToggleGroup search_type;

    @FXML
    public ListView<String> tvSearchResults;

    ObservableList<IndexedFile> oblist = FXCollections.observableArrayList();

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        getIndex();
        number_indexed.setText(oblist.size() + " files indexed");
    }

    @FXML
    public void search() {
        if (search_type.getSelectedToggle().equals(radAny)) {
            searchAny();
        }
        else if (search_type.getSelectedToggle().equals(radAll)) {
            searchAll();
        }
        else if (search_type.getSelectedToggle().equals(radExact)) {
            searchExact();
        }
    }

    @FXML
    private void searchAny() {

        tvSearchResults.getItems().clear();

        String s = tfSearch.getText();

        String[] search_values = s.toLowerCase().split("[^\\w']+");

        for (String value: search_values) {

            oblist.forEach(o -> {

                String fp = o.getFilepath();

                String[] fp_words = fp.toLowerCase().split("[^\\w']+");

                if(Arrays.asList(fp_words).contains(value)) {

                    tvSearchResults.getItems().add(fp);
                }
            });
        }
    }

    @FXML
    private void searchAll() {

        tvSearchResults.getItems().clear();

        String s = tfSearch.getText();

        String[] search_values = s.toLowerCase().split("[^\\w']+");

            oblist.forEach(o -> {

                String fp = o.getFilepath();

                String[] fp_words = fp.toLowerCase().split("[^\\w']+");

                if(Arrays.asList(fp_words).containsAll(Arrays.asList(search_values))) {

                    tvSearchResults.getItems().add(fp);
                }
            });

    }

    @FXML
    private void searchExact() {

        tvSearchResults.getItems().clear();

        String s = tfSearch.getText();

            oblist.forEach(o -> {

                String fp = o.getFilepath();

                if(s.equals(fp)) {

                    tvSearchResults.getItems().add(fp);
                }
            });
    }

    private void getIndex() {
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
    }

    @FXML
    private void switchToSecondary() throws IOException {
        App.setRoot("secondary");
    }

    @FXML
    public void switchToAbout() throws IOException {
        App.setRoot("about");
    }
}
