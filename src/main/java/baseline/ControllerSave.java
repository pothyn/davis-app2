package baseline;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

import java.io.File;

public class ControllerSave {
    private ObservableList<Item> itemList;
    private File file;

    @FXML
    TextField fileLocationTextBox;

    @FXML
    public void filePick() {
        // new fileChooser
        // add .txt file extension
        // add .html file extension
        // add .json file extension

        // show save dialog
    }

    @FXML
    public void saveFile() {
        clearText();

        // check if file is .txt, .json, .html and write depending on that
    }

    public void setItemList(ObservableList<Item> itemList) {
        this.itemList = itemList;
    }

    public void saveCSV() {
        // for entire itemList
            // write (in file), name + "\t" + serialNumber + "\t" + value "\n"
    }

    public void saveJSON() {
        // use gson to convert from json to values
    }

    public void saveHTML() {
        // create html webp
    }

    private void clearText() {
        // erase everything from a file
    }
}
