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
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

// Controller class for primary.fxml (search page)
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

    // search result list
    @FXML
    public ListView<String> tvSearchResults;

    // indexed files list
    ObservableList<IndexedFile> oblist = FXCollections.observableArrayList();

    // initialize on startup
    @Override
    public void initialize(URL location, ResourceBundle resources) {

        // get indexed files from db and store in oblist
        getIndex();

        // select 'Any' search by default
        radAny.setSelected(true);

        // display number of currently indexed files
        number_indexed.setText(oblist.size() + " files indexed");
    }

    // When the search button is clicked, get the search-type selection
    // from the toggle group and call the corresponding search method
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

    // If 'Any' search selected
    @FXML
    private void searchAny() {

        // clear the results listview
        tvSearchResults.getItems().clear();

        // get user's search terms from text field
        String s = tfSearch.getText();
        String[] search_values = s.toLowerCase().split("[^\\w']+");

        // for each search term
        for (String value: search_values) {

            // for each indexed file
            oblist.forEach(o -> {

                // get all words from filepath
                String fp = o.getFilepath();
                String[] fp_words = fp.toLowerCase().split("[^\\w']+");

                // if the filepath contains any of the user's search terms,
                // add the filepath to the results listview
                if(Arrays.asList(fp_words).contains(value)) {
                    tvSearchResults.getItems().add(fp);
                }
            });
        }
    }

    // If 'All' search selected
    @FXML
    private void searchAll() {

        // clear the results listview
        tvSearchResults.getItems().clear();

        // get user's search terms from text field
        String s = tfSearch.getText();
        String[] search_values = s.toLowerCase().split("[^\\w']+");

        // for each indexed file
        oblist.forEach(o -> {

            // get all words from filepath
            String fp = o.getFilepath();
            String[] fp_words = fp.toLowerCase().split("[^\\w']+");

            // if the filepath contains all of the user's search terms,
            // add the filepath to the results listview
            if(Arrays.asList(fp_words).containsAll(Arrays.asList(search_values))) {
                tvSearchResults.getItems().add(fp);
            }
        });
    }

    // If 'Exact' search selected
    @FXML
    private void searchExact() {

        // clear the results listview
        tvSearchResults.getItems().clear();

        // get user's exact search term
        String s = tfSearch.getText();

        // for each indexed file
        oblist.forEach(o -> {

            // get the filepath
            String fp = o.getFilepath();

            // if the filepath exactly matches the user's search term,
            // add the filepath to the results listview
            if(s.equals(fp)) {
                tvSearchResults.getItems().add(fp);
            }
        });
    }

    // get indexed files from db and store them in oblist
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

    // navigation button methods
    @FXML
    private void switchToSecondary() throws IOException {
        App.setRoot("secondary");
    }
    @FXML
    public void switchToAbout() throws IOException {
        App.setRoot("about");
    }
}
