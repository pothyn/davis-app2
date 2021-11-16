package baseline;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

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
        Stage stage = (Stage) fileTextBox.getScene().getWindow();

        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open Resource File");

        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Text doc(*.txt)", "*.txt"));
        File f = fileChooser.showOpenDialog(stage);
        if (f != null) {
            file = f;
            fileTextBox.textProperty().setValue(f.getPath());
        }
    }

    @FXML
    public void loadFile() throws FileNotFoundException {
        getData();

        // close stage
        Stage stage = (Stage) fileTextBox.getScene().getWindow();
        stage.close();
    }

    public void getData() throws FileNotFoundException {
        // Determine whether file is JSON, html or txt

        // if txt, parseDataCSV()
        if(file.toPath().toString().endsWith(".txt")) {
            // go line by line through the txt
            System.out.println("LOG: Load File .txt");
            parseDataCSV();
        }
        // else if html, parseDataHTML()
            // use entire file and format it inside parseDataHTML()
        // else parseDataJSON()
            // use entire file and deformat inside parseDataJSON()
    }

    public void parseDataCSV() throws FileNotFoundException {

        Scanner in = new Scanner(file);

        // loop through each line
        while(in.hasNext()) {
            addLineCSV(in.nextLine());
        }
    }

    public void addLineCSV(String line) {
        Item newItem = new Item();
        int round = 0;
        int beginIndex = 0;

        // for each value in list, separated by a tab, assign it to name, then serialNumber, then value
        for(int i=0; i < line.length(); i++) {
            if(line.charAt(i) == '\t') {
                if(round == 0)
                    newItem.nameProperty().setValue(line.substring(beginIndex,i));
                else if(round == 1) {
                    newItem.serialNumberProperty().setValue(line.substring(beginIndex,i));
                }
                else if(round == 2)
                    newItem.valueProperty().setValue(line.substring(beginIndex,i));

                round++;
                beginIndex = i+1;
            }
        }
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
