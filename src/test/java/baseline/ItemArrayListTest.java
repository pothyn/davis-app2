package baseline;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ItemArrayListTest {
    ItemArrayList itemArrayList;
    ObservableList itemList;

    @BeforeEach
    void init() {
        itemList = FXCollections.observableArrayList();

        Item item1 = new Item("Pencil","A-XXX-XXX-X01","$1.00", itemList);
        Item item2 = new Item("Computer","A-XXX-XXX-X00","$300.00", itemList);

        itemList.add(item1);
        itemList.add(item2);

        itemArrayList = new ItemArrayList(itemList);
    }

    @Test
    void testAddAndGet() {
        Item item3 = new Item("Apple","B-XER-ORE-934","$4.00", itemList);

        item3.setItemBasic();
        itemArrayList.add(item3.getItemBasic());

        assertEquals("Apple", itemArrayList.get(2).getName());
    }

    @Test
    void testSize() {
        assertEquals(2, itemArrayList.size());
    }
}