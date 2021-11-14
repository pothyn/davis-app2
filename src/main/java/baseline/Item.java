package baseline;

import javafx.beans.property.SimpleStringProperty;

public class Item {
    private SimpleStringProperty name;
    private SimpleStringProperty serialNumber;
    private SimpleStringProperty value;

    // Initialize without values
    public Item() {
        this.name = new SimpleStringProperty();
        this.serialNumber = new SimpleStringProperty();
        this.value = new SimpleStringProperty();
    }

    // Initialize with values (mostly used for editing)
    public Item(String name, String serialNumber, String value) {
        this.name = new SimpleStringProperty(name);
        this.serialNumber = new SimpleStringProperty(serialNumber);
        this.value = new SimpleStringProperty(value);
    }

    private String getValueString() {
        return value.get();
    }

    private SimpleStringProperty getValueStringProperty() {
        return value;
    }

    private String getSerialNumberString() {
        return serialNumber.get();
    }

    private SimpleStringProperty getSerialNumberStringProperty() {
        return serialNumber;
    }

    private String getNameString() {
        return name.get();
    }

    private SimpleStringProperty getNameStringProperty() {
        return name;
    }
}
