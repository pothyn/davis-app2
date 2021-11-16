package baseline;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class ControllerSave {
    private ObservableList<Item> itemList;
    private File file;

    @FXML
    TextField fileLocationTextBox;

    @FXML
    public void filePick() {
        Stage stage = (Stage) fileLocationTextBox.getScene().getWindow();

        // new fileChooser
        FileChooser choose = new FileChooser();

        // add .txt file extension
        choose.getExtensionFilters().add(new FileChooser.ExtensionFilter("Text doc(*.txt)", "*.txt"));
        // add .html file extension
        choose.getExtensionFilters().add(new FileChooser.ExtensionFilter("HTML File(*.html)", "*.html"));
        // add .json file extension
        choose.getExtensionFilters().add(new FileChooser.ExtensionFilter("JSON File(*.json)", "*.json"));

        // show save dialog
        File f = choose.showSaveDialog(stage);
        if (f != null) {
            file = f;
            fileLocationTextBox.textProperty().setValue(f.getPath());
        }
    }

    @FXML
    public void saveFile() {
        clearText();

        // check if file is .txt, .json, .html and write depending on that
        if(file.toPath().toString().endsWith(".txt")) {
            System.out.println(".txt file detected!");
            saveCSV();
        }
        else if(file.toPath().toString().endsWith(".html")) {
            System.out.println(".html file detected!");
        }
        else {
            System.out.println(".json file detected!");
        }
    }

    public void setItemList(ObservableList<Item> itemList) {
        this.itemList = itemList;
    }

    public void saveCSV() {
        // for entire itemList
            // write (in file), name + "\t" + serialNumber + "\t" + value "\n"

        clearText();
        for(int i = 0; i < itemList.size(); i++) {
            String line = itemList.get(i).getNameString() + "\t"
                    + itemList.get(i).getSerialNumberString() + "\t"
                    + itemList.get(i).getValueString() + "\t\n";

            FileWriter fileWriter;
            try {
                fileWriter = new FileWriter(file,true);
                BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
                bufferedWriter.write(line);
                bufferedWriter.flush();
                bufferedWriter.close();

            } catch (IOException e) {
                System.out.println("Add line failed!!" +e);
            }
        }

        Stage stage = (Stage) fileLocationTextBox.getScene().getWindow();
        stage.close();
    }

    public void saveJSON() {
        // use gson to convert from json to values
    }

    public void saveHTML() {
        // create html webp
    }

    private void clearText() {
        // erase everything from a file
        FileWriter fileWriter;
        try {
            fileWriter = new FileWriter(file,false);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            bufferedWriter.write("");
            bufferedWriter.flush();
            bufferedWriter.close();

        } catch (IOException e) {
            System.out.println("Add line failed!!" +e);
        }
    }
}
