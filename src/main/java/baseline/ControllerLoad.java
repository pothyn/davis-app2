package baseline;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

import java.io.File;

public class ControllerLoad {
    private ObservableList<Item> itemList;
    private File file;

    @FXML
    TextField fileTextBox;

    @FXML
    public void filePick() {
        // create fileChooser

        // add .txt extension
        // add .html extension
        // add .json extension
        // show open file prompt
    }

    @FXML
    public void loadFile() {
        getData();

        // close stage
    }

    public void getData() {
        // Determine whether file is JSON, html or txt

        // if txt, parseDataCSV()
            // go line by line through the txt
        // else if html, parseDataHTML()
            // use entire file and format it inside parseDataHTML()
        // else parseDataJSON()
            // use entire file and deformat inside parseDataJSON()
    }

    public void parseDataCSV(String line) {
        Item newItem = new Item();

        // for each value in list, separated by a tab, assign it to name, then serialNumber, then value

        itemList.add(newItem);
    }

    public void parseDataHTML() {
        // format for html

        // add table format and add each value

        // close formatting
    }

    public void parseDataJSON() {
        // run through values for JSON and save them to itemList
    }

    public void setItemList(ObservableList<Item> itemList) {
        this.itemList = itemList;
    }
}
