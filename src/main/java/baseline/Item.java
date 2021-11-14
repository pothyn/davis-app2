package baseline;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

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

    // THERES A LOT MORE OF THESE FUNCTIONS, BUT ITS RESTING TIME

    private SimpleStringProperty nameStringProperty() {
        return name;
    }
}
