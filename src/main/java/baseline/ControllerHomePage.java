package baseline;

import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

public class ControllerHomePage {
    private ObservableList<Item> itemList;
    private FilteredList<Item> filteredList;

    @FXML
    private TableView tableView;

    @FXML
    private TableColumn<Item, String> tableName;

    @FXML
    private TableColumn<Item, String> tableSerialNumber;

    @FXML
    private TableColumn<Item, String> tableValue;

    @FXML
    public void handleSave() {
        // open saveAs fxml file
        // create new ControllerSaveAs
        // set item list to save there to itemList
    }

    @FXML
    public void handleLoad() {
        // open load fxml file
        // create new ControllerLoad
        // set item list to load there to itemList
    }

    @FXML
    public void handleHelp() {
        // open help fxml file
    }

    @FXML
    public void handleAdd() {
        // create new Item
        // open AddEdit.fxml
        // create new ControllerEdit and use that new Item
    }

    @FXML
    public void handleEdit() {
        // open AddEdit.fxml
        // create new ControllerEdit and use selected Item
    }

    @FXML
    public void handleRemove() {
        // get selected item
        // remove selected item from itemList
    }

    private void clearList() {
        // initialize itemList and filteredList as new lists
        // set tableView to display new filteredList
    }

    @FXML
    public void handleClear() {
        clearList();
    }

    private void initializeList() {
        clearList();

        // set cell factories for each tableColumn
    }

    @FXML
    public void initialize() {
        initializeList();
    }

    @FXML
    public void handleSearch() {
        // take input from searchBox
        // change filteredList to only display those containing that substring
        // set tableView
    }

    @FXML
    public void handleSortByName() {
        // use collections sort on name for filteredList
        // set it for tableView
    }

    @FXML
    public void handleSortBySerialNumber() {
        // use collections sort on serialNumber for filteredList
        // set tableView
    }

    @FXML
    public void handleSortByValue() {
        // use collections sort on value for filteredList
        // set tableView
    }
}
