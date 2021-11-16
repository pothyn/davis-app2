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

    public Item clone() {
        return new Item(name.getValue(), serialNumber.getValue(), value.getValue());
    }

    public void restore(Item i) {
        name.setValue(i.getNameString());
        serialNumber.setValue(i.getSerialNumberString());
        value.setValue(i.getValueString());
    }

    public String getValueString() {
        return value.get();
    }

    public SimpleStringProperty valueProperty() {
        return value;
    }

    public String getSerialNumberString() {
        return serialNumber.get();
    }

    public SimpleStringProperty serialNumberProperty() {
        return serialNumber;
    }

    public String getNameString() {
        return name.get();
    }

    public SimpleStringProperty nameProperty() {
        return name;
    }
}
