package baseline;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

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
    private void handleSubmit() {
        // if nameField is not between 2 and 256 chars (length)
        if(nameField.getText().length() < 2 || nameField.getText().length() > 256 ) {
            // open error prompt
            // new ControllerError
            // controllerError.setText("name")
            // return
            System.out.println();
        }

        // else if serialNumberField is not in format A-XXX-XXX-XXX
            // open error prompt
            // new ControllerError
            // controllerError.setText("serial number")
            // return

        // else if valueField cannot be parseDoubled
            // open error prompt
            // new ControllerError
            // controllerError.setText("value")
            // return

        else {
            isValid = true;
            Stage stage = (Stage) submitButton.getScene().getWindow();
            stage.close();
        }
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
