package baseline;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class ControllerError {
    @FXML
    private Label errorText;

    private String text;

    @FXML
    public void initialize() {

    }

    public void setErrorMessage(int flag) {
        if(flag == 0)
            showNameError();
        else if(flag == 1)
            showSerialNumberError();
        else if(flag == 2)
            showValueError();
    }

    public void showNameError() {
        errorText.setText("Invalid Name.");
    }

    public void showSerialNumberError() {
        errorText.setText("Invalid Serial Number. (A-XXX-XXX-XXX)");
    }

    public void showValueError() {
        errorText.setText("Invalid Value. ($XX.XX)");
    }
}
