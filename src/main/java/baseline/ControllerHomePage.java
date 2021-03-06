/*
 *  UCF COP3330 Fall 2021 Application Assignment 2 Solution
 *  Copyright 2021 Hunter Davis
 */

package baseline;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.util.Collections;

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
    private TextField searchBox;

    @FXML
    public void handleSave() throws IOException {
        // open Save fxml file
        FXMLLoader loader = new FXMLLoader(InventoryManagementApplication.class.getClassLoader().getResource("baseline/Save.fxml"));
        Parent root = loader.load();

        // create new ControllerSave
        ControllerSave controllerSaveAs = loader.getController();

        // set item list to save there to itemList
        controllerSaveAs.setItemList(itemList);

        Stage stage = new Stage(StageStyle.DECORATED);
        stage.setTitle("Save As");
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    public void handleLoad() throws IOException {
        // open load fxml file
        FXMLLoader loader = new FXMLLoader(InventoryManagementApplication.class.getClassLoader().getResource("baseline/Load.fxml"));
        Parent root = loader.load();
        clearList();

        // create new ControllerLoad
        ControllerLoad controllerLoad = loader.getController();

        // set item list to load there to itemList
        controllerLoad.setItemList(itemList);

        Stage stage = new Stage(StageStyle.DECORATED);
        stage.setTitle("Load");
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    public void handleHelp() throws IOException {
        // Open help fxml file
        FXMLLoader loader = new FXMLLoader(InventoryManagementApplication.class.getClassLoader().getResource("baseline/Help.fxml"));
        Parent root = loader.load();

        // Set up scene and display
        Stage stage = new Stage(StageStyle.DECORATED);
        stage.setTitle("Help");
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    public void handleAdd() throws IOException {
        // create new Item
        Item newItem = new Item(itemList);

        // open AddEdit.fxml
        FXMLLoader loader = new FXMLLoader(InventoryManagementApplication.class.getClassLoader().getResource("baseline/AddEdit.fxml"));
        Parent root = loader.load();

        // create new ControllerEdit and use that new Item
        ControllerEdit editController = loader.getController();
        editController.setItem(newItem);

        // Open AddEdit page, but if closed: call restore() in Item and add it
        Stage stage = new Stage(StageStyle.DECORATED);
        stage.setTitle("Add");
        stage.setOnHidden(event -> {
            if (editController.getIsValid()) {
                itemList.add(0, newItem);
                tableView.getSelectionModel().selectFirst();
            }
        });
        Scene scene = new Scene(root);

        stage.setScene(scene);
        stage.show();
    }

    @FXML
    public void handleEdit() throws IOException {
        // get selected item from tableView
        Item selectedItem = (Item) tableView.getFocusModel().getFocusedItem();
        if (selectedItem == null)
            return;

        itemList.remove(selectedItem);
        Item backup = selectedItem.clone();

        // open AddEdit.fxml
        FXMLLoader loader = new FXMLLoader(InventoryManagementApplication.class.getClassLoader().getResource("baseline/AddEdit.fxml"));
        Parent root = loader.load();
        // create new ControllerEdit and use selected Item
        ControllerEdit editController = loader.getController();
        editController.setItem(selectedItem);

        // Open Edit page, but if closed: call restore() in Item and add it
        Stage stage = new Stage(StageStyle.DECORATED);
        stage.setTitle("Edit");
        stage.setOnHidden(event -> {
            if (!editController.getIsValid())
                selectedItem.restore(backup);

            itemList.add(0, selectedItem);
            tableView.getFocusModel().focus(0);
        });
        Scene scene = new Scene(root);

        stage.setScene(scene);
        stage.show();
    }

    @FXML
    public void handleRemove() {
        // get selected item
        Item selectedItem = (Item) tableView.getFocusModel().getFocusedItem();

        // remove selected item from itemList
        itemList.remove(selectedItem);
    }

    private void clearList() {
        // initialize itemList and filteredList as new lists
        itemList = FXCollections.observableArrayList();
        filteredList = new FilteredList<>(itemList);

        // set tableView to display new filteredList
        tableView.setItems(filteredList);
    }

    @FXML
    public void handleClear() {
        clearList();
    }

    private void initializeList() {
        clearList();

        // set cell factories for each tableColumn
        tableName.setCellValueFactory(new PropertyValueFactory<>("name"));
        tableSerialNumber.setCellValueFactory(new PropertyValueFactory<>("serialNumber"));
        tableValue.setCellValueFactory(new PropertyValueFactory<>("value"));
    }

    @FXML
    public void initialize() {
        initializeList();
    }

    @FXML
    public void handleSearchName() {
        // take input from searchBox
        String input = searchBox.getText();

        // change filteredList to only display those containing that substring
        if(input == null) {
            filteredList.setPredicate(item -> true);
        } else {
            filteredList.setPredicate(item -> item.getNameString().contains(input));
        }
    }

    @FXML
    public void handleSearchSerialNumber() {
        // take input from searchBox
        String input = searchBox.getText();

        // change filteredList to only display those containing that substring
        if(input == null) {
            filteredList.setPredicate(item -> true);
        } else {
            filteredList.setPredicate(item -> item.getSerialNumberString().contains(input));
        }
    }

    @FXML
    public void handleSortByName() {
        // Check item list isn't null
        if(itemList.isEmpty()) {
            return;
        }

        // set the sort to name
        itemList.get(0).setCompareTo(0);

        // use collections sort on name for itemList
        Collections.sort(itemList);
    }

    @FXML
    public void handleSortBySerialNumber() {
        // Check item list isn't null
        if(itemList.isEmpty()) {
            return;
        }

        // set the sort to serial numbers
        itemList.get(0).setCompareTo(1);

        // use collections sort on serialNumber for itemList
        Collections.sort(itemList);

    }

    @FXML
    public void handleSortByValue() {
        // Check item list isn't null
        if(itemList.isEmpty()) {
            return;
        }

        // set the sort to values
        itemList.get(0).setCompareTo(2);

        // use collections sort on value for itemList
        Collections.sort(itemList);
    }
}
