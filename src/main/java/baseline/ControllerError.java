/*
 *  UCF COP3330 Fall 2021 Application Assignment 2 Solution
 *  Copyright 2021 Hunter Davis
 */

package baseline;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class ControllerError {
    @FXML
    private Label errorText;

    public void setErrorMessage(int flag) {
        // Flag is simple to understand, but it will show a screen depending on what error you fall into.
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
