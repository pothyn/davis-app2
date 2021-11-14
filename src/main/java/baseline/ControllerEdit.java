package baseline;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class ControllerEdit {
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
            // open error prompt
            // new ControllerError
            // controllerError.setText("name")
            // return

        // else if serialNumberField is not between 2 and 256 chars (length)
            // open error prompt
            // new ControllerError
            // controllerError.setText("serial number")
            // return

        // else if valueField is not between 2 and 256 chars (length)
            // open error prompt
            // new ControllerError
            // controllerError.setText("value")
            // return

        // set isValid to true
        // close window
    }

    private void setItem(Item item) {
        // set item equal to class'
        // set up the values
    }

    private boolean getIsValid() {
        return isValid;
    }
}
