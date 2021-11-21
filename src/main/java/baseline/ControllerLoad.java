/*
 *  UCF COP3330 Fall 2021 Application Assignment 2 Solution
 *  Copyright 2021 Hunter Davis
 */

package baseline;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.jsoup.Jsoup;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

public class ControllerLoad {
    private ObservableList<Item> itemList;
    private File file;

    @FXML
    TextField fileTextBox;

    @FXML
    public void filePick() {
        Stage stage = (Stage) fileTextBox.getScene().getWindow();

        // create fileChooser
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open Resource File");

        // add .txt extension
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Text doc(*.txt)", "*.txt"));
        // add .html extension
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("HTML File(*.html)", "*.html"));
        // add .json extension
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("JSON File(*.json)", "*.json"));

        // show open file prompt
        File f = fileChooser.showOpenDialog(stage);
        if (f != null) {
            file = f;
            fileTextBox.textProperty().setValue(f.getPath());
        }
    }

    @FXML
    public void loadFile() throws IOException {
        getDataWithFileType();

        // close stage
        Stage stage = (Stage) fileTextBox.getScene().getWindow();
        stage.close();
    }

    public void getDataWithFileType() throws IOException {
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

    public void loadHTML() throws IOException {
        itemList.remove(0, itemList.size());
        Document doc = Jsoup.parse(file, "UTF-8");

        // Get locations of <tr> in the table, and those will be tableRows
        Elements tableRows = doc.select("tr");
        for (Element tableRow : tableRows) {
            // Locations of <td> will be tableColumns
            Elements tableColumns = tableRow.select("td");
            // Don't count headers!
            if (tableColumns.isEmpty())
                continue;

            // Set the name to the values inside the tags
            String name = tableColumns.get(0).html();
            String serialNumber = tableColumns.get(1).html();
            String value = tableColumns.get(2).html();
            itemList.add(new Item(name, serialNumber, value, itemList));
        }
    }

    public void loadJSON() throws FileNotFoundException {
        Scanner in = new Scanner(file);
        StringBuilder jsonText = new StringBuilder();

        GsonBuilder builder = new GsonBuilder();
        Gson gson = builder.create();

        // Add everything to one single string
        while(in.hasNext()) {
            jsonText.append(in.nextLine());
        }

        // Use that one single string and plug it into Gson with the ItemArrayList class
        ItemArrayList itemArrayList = gson.fromJson(jsonText.toString(), ItemArrayList.class);

        // We then run through ItemArrayList's ArrayList to get each value.
        for(int i = 0; i < itemArrayList.size(); i++) {
            itemList.add(new Item(itemArrayList.get(i).getName(),itemArrayList.get(i).getSerialNumber(),
                    itemArrayList.get(i).getValue(), itemList));
        }
    }

    public void setItemList(ObservableList<Item> itemList) {
        // Necessary for knowing parent list (i.e. adding items through here)
        this.itemList = itemList;
    }
}
