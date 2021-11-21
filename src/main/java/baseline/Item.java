package baseline;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.ObservableList;

public class Item implements Comparable<Item> {
    private SimpleStringProperty name;
    private SimpleStringProperty serialNumber;
    private SimpleStringProperty value;
    private ItemBasic itemBasic;
    private ObservableList<Item> parentList;

    private static int sortFlag;

    // Initialize without values
    public Item(ObservableList<Item> parentList) {
        this.name = new SimpleStringProperty();
        this.serialNumber = new SimpleStringProperty();
        this.value = new SimpleStringProperty();
        this.parentList = parentList;
    }

    // Initialize with values (mostly used for editing)
    public Item(String name, String serialNumber, String value, ObservableList<Item> parentList) {
        this.name = new SimpleStringProperty(name);
        this.serialNumber = new SimpleStringProperty(serialNumber);
        this.value = new SimpleStringProperty(value);
        this.parentList = parentList;
    }

    public void setCompareTo(int sortFlag) {
        if(sortFlag >= 0 && sortFlag < 3)
            this.sortFlag = sortFlag;
    }

    public int compareTo(Item i) {
        if(sortFlag == 0)
            return this.getNameString().compareToIgnoreCase(i.getNameString());
        else if(sortFlag == 1)
            return this.getSerialNumberString().compareToIgnoreCase(i.getSerialNumberString());
        else
            return this.getValueString().compareTo(i.getValueString());
    }

    public Item clone() {
        return new Item(name.getValue(), serialNumber.getValue(), value.getValue(), parentList);
    }

    public void restore(Item i) {
        name.setValue(i.getNameString());
        serialNumber.setValue(i.getSerialNumberString());
        value.setValue(i.getValueString());
    }

    public void setItemBasic() {
        itemBasic = new ItemBasic();
        itemBasic.setName(getNameString());
        itemBasic.setValue(getValueString());
        itemBasic.setSerialNumber(getSerialNumberString());
    }

    public ItemBasic getItemBasic() {
        return itemBasic;
    }

    public String toString() {
        return "name:"+getNameString()+", serialNumber:"+getSerialNumberString()+", value:"+getValueString();
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

    public ObservableList<Item> getParentList() {
        return parentList;
    }
}
