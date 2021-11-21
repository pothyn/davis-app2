package baseline;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ItemTest {

    ObservableList<Item> itemList;
    Item item1;
    Item item2;

    @BeforeEach
    void init() {
        itemList = FXCollections.observableArrayList();
        item1 = new Item("name1", "A-XXX-XXX-X01", "$4.70", itemList);
        item2 = new Item("name2", "A-XXX-XXX-X02", "$4.68", itemList);

    }

    @Test
    void testCompareToName() {
        item1.setCompareTo(0);
        assertTrue(item1.compareTo(item2) < 0);
    }

    @Test
    void testCompareToSerialNumber() {
        item1.setCompareTo(1);
        assertTrue(item1.compareTo(item2) < 0);
    }

    @Test
    void testCompareToValue() {
        item1.setCompareTo(2);
        assertTrue(item1.compareTo(item2) > 0);
    }

    @Test
    void testClone() {
        Item item3 = item1.clone();
        assertEquals(item1.getNameString(), item3.getNameString());
        assertEquals(item1.getSerialNumberString(), item3.getSerialNumberString());
        assertEquals(item1.getValueString(), item3.getValueString());
    }

    @Test
    void testSetItemBasic() {
        ItemBasic itemBasic1 = new ItemBasic();
        itemBasic1.setName("name1");
        itemBasic1.setSerialNumber("A-XXX-XXX-X01");
        itemBasic1.setValue("$4.70");

        item1.setItemBasic();
        ItemBasic itemBasicFromItem = item1.getItemBasic();

        assertEquals(itemBasic1.getName(), itemBasicFromItem.getName());
        assertEquals(itemBasic1.getSerialNumber(), itemBasicFromItem.getSerialNumber());
        assertEquals(itemBasic1.getValue(), itemBasicFromItem.getValue());
    }
}