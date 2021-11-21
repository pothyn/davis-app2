package baseline;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
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
            saveCSV();
        }
        else if(file.toPath().toString().endsWith(".html")) {
            saveHTML();
        }
        else {
            saveJSON();
        }
    }

    public void setItemList(ObservableList<Item> itemList) {
        this.itemList = itemList;
    }

    public void saveCSV() {
        clearText();

        // Writes all values to a single string and adds the tabs in together
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

        // Closes file
        Stage stage = (Stage) fileLocationTextBox.getScene().getWindow();
        stage.close();
    }

    public void saveJSON() {
        clearText();

        FileWriter fileWriter;
        ItemArrayList itemArrayList = new ItemArrayList(itemList);

        GsonBuilder builder = new GsonBuilder();
        builder.setPrettyPrinting();
        Gson gson = builder.create();
        String gsonString = gson.toJson(itemArrayList);

        try {
            fileWriter = new FileWriter(file,true);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            bufferedWriter.write(gsonString);
            bufferedWriter.flush();
            bufferedWriter.close();

        } catch (IOException e) {
            System.out.println("Add line failed!!" +e);
        }

        Stage stage = (Stage) fileLocationTextBox.getScene().getWindow();
        stage.close();
    }

    public void saveHTML() {
        clearText();

        FileWriter fileWriter;
        StringBuilder htmlString = new StringBuilder();

        // Adds necessary HTML syntax
        htmlString.append("<!DOCTYPE html><html><body><table><tr>\n");
        htmlString.append("<tr><th>Name</th><th>Serial Number</th><th>Value</th></tr>\n");

        // Adds data to a table
        for(int i = 0; i < itemList.size(); i++) {
            htmlString.append("<tr>");
            htmlString.append("<td>" + itemList.get(i).getNameString() + "</td>");
            htmlString.append("<td>" + itemList.get(i).getSerialNumberString() + "</td>");
            htmlString.append("<td>" + itemList.get(i).getValueString() + "</td>");
            htmlString.append("<tr>\n");
        }

        // More necessary HTML syntax
        htmlString.append("</table></body></html>");

        // Actually writes to the file
        try {
            fileWriter = new FileWriter(file,true);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            bufferedWriter.write(htmlString.toString());
            bufferedWriter.flush();
            bufferedWriter.close();

        } catch (IOException e) {
            System.out.println("Add line failed!!" +e);
        }

        // Closes the file
        Stage stage = (Stage) fileLocationTextBox.getScene().getWindow();
        stage.close();
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
