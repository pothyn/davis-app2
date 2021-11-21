package baseline;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
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

        // add .txt extension
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Text doc(*.txt)", "*.txt"));
        // add .html extension
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("HTML File(*.html)", "*.html"));
        // add .json extension
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("JSON File(*.json)", "*.json"));

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
            loadCSV();
        }
        // else if html, parseDataHTML()
        else if(file.toPath().toString().endsWith(".html")) {
            // use entire file and format it inside parseDataHTML()
            loadHTML();
        }
        // else parseDataJSON()
        else
            loadJSON();
            // use entire file and deformat inside parseDataJSON()
    }

    public void loadCSV() throws FileNotFoundException {

        Scanner in = new Scanner(file);

        // loop through each line
        while(in.hasNext()) {
            addLineCSV(in.nextLine());
        }
    }

    public void addLineCSV(String line) {
        Item newItem = new Item(itemList);
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

    public void loadHTML() throws FileNotFoundException {
        Scanner in = new Scanner(file);
        String nextLine;

        in.nextLine();
        in.nextLine();
        while(in.hasNext()) {
            nextLine = in.nextLine();
            if(nextLine.equals("</table></body></html>"))
                break;
            else {
                addLineHTML(nextLine);
            }
        }
    }

    public void addLineHTML(String line) {
        Item newItem = new Item(itemList);
        int round = 0;
        int beginIndex = 0;

        line = line.substring(4);

        // for each value in list, separated by a tab, assign it to name, then serialNumber, then value
        for(int i=0; i < line.length()-5; i++) {
            if(line.substring(i,i+5).equals("</td>")) {
                if(round == 0)
                    newItem.nameProperty().setValue(line.substring(beginIndex+4,i));
                else if(round == 1) {
                    newItem.serialNumberProperty().setValue(line.substring(beginIndex+8,i));
                }
                else if(round == 2)
                    newItem.valueProperty().setValue(line.substring(beginIndex+8,i));

                round++;
                beginIndex = i+1;
            }
        }
        itemList.add(newItem);
    }

    public void loadJSON() throws FileNotFoundException {
        // run through values for JSON and save them to itemList
        Scanner in = new Scanner(file);
        StringBuilder jsonText = new StringBuilder();

        GsonBuilder builder = new GsonBuilder();
        Gson gson = builder.create();

        while(in.hasNext()) {
            jsonText.append(in.nextLine());
        }

        ItemArrayList itemArrayList = gson.fromJson(jsonText.toString(), ItemArrayList.class);

        for(int i = 0; i < itemArrayList.size(); i++) {
            itemList.add(new Item(itemArrayList.get(i).getName(),itemArrayList.get(i).getSerialNumber(),
                    itemArrayList.get(i).getValue(), itemList));
        }
    }

    public void setItemList(ObservableList<Item> itemList) {
        this.itemList = itemList;
    }
}
