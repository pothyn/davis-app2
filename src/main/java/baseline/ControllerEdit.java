package baseline;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;

public class ControllerEdit {
    private Item item;
    private boolean isValid;

    @FXML
    TextField nameField;

    @FXML
    TextField serialNumberField;

    @FXML
    TextField valueField;

    @FXML
    Button submitButton;

    @FXML
    private void handleSubmit() throws IOException {
        // if nameField is not between 2 and 256 chars (length)
        // nameField is wrong
        if(nameField.getText() == null || nameField.getText().length() < 2 || nameField.getText().length() > 256) {
            displayError(0);
        }

        // else if serialNumberField is not in format A-XXX-XXX-XXX
        // serialNumberField is wrong
        else if(!isSerialNumberValid() || !isSerialNumberUnique()) {
            displayError(1);
        }

        // else if valueField cannot be parseDoubled
        else if(!valueFieldParsable()) {
            displayError(2);
        }

        else {
            isValid = true;
            Stage stage = (Stage) submitButton.getScene().getWindow();
            stage.close();
        }
    }

    public void displayError(int flag) throws IOException {
        FXMLLoader loader = new FXMLLoader(InventoryManagementApplication.class.getClassLoader().getResource("baseline/Error.fxml"));
        Parent root = loader.load();
        ControllerError controllerError = loader.getController();
        // FLAG: 0 = name, 1 = serialNumber, 2 = value
        controllerError.setErrorMessage(flag);

        Stage stage = new Stage(StageStyle.DECORATED);
        stage.setTitle("Error");

        Scene scene = new Scene(root);

        stage.setScene(scene);
        stage.show();
    }

    public boolean isSerialNumberValid() {
        String serialNum = serialNumberField.getText();
        int forStacker = 2;

        // Check the string length
        if(serialNum == null || serialNum.length() != 13)
            return false;

        // Check the Initial Letter
        else if(!serialNum.substring(0,1).toLowerCase().matches("[a-z]")) {
            return false;
        }

        // Check the hyphens
        else if(serialNum.charAt(1) != ('-')
                || serialNum.charAt(5) != ('-')
                || serialNum.charAt(9) != ('-')
        ) {
            return false;
        }

        // Check the X's (Letters or Numbers)
        for(int i = 0; i < 3; i++) {
            for(int j = 0; j < 3; j++) {
                if(!Character.isDigit(serialNum.charAt(j + 3*i + forStacker))
                        && !Character.isLetter(serialNum.charAt(j + 3*i + forStacker))) {
                    return false;
                }
            }
            forStacker++;
        }

        return true;
    }

    public boolean isSerialNumberUnique() {
        // for loop through all other values in item list
        ObservableList<Item> parentList = item.getParentList();

        for (Item i : parentList) {
            if (i.getSerialNumberString().equalsIgnoreCase(item.getSerialNumberString()))
                return false;
        }
        return true;
    }

    private boolean valueFieldParsable() {
        // take value field
        String value = valueField.getText();

        // take off the initial dollar sign from string
        if(value.charAt(0) != '$') {
            return false;
        }

        value = value.substring(1);

        try {
            Double.parseDouble(value);
        } catch(NumberFormatException e) {
            System.out.println("number format exception");
            return false;
        }

        return true;
    }

    public void setItem(Item item) {
        // set item equal to class'
        this.item = item;
        nameField.textProperty().bindBidirectional(item.nameProperty());
        serialNumberField.textProperty().bindBidirectional(item.serialNumberProperty());
        valueField.textProperty().bindBidirectional(item.valueProperty());
    }

    public boolean getIsValid() {
        return isValid;
    }
}
