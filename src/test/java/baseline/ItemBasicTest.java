package baseline;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ItemBasicTest {
    ItemBasic itemBasic;

    @BeforeEach
    void init() {
        itemBasic = new ItemBasic();
    }

    @Test
    void testGettersAndSetters() {
        itemBasic.setName("Light");
        itemBasic.setSerialNumber("L-XXX-XXX-XXX");
        itemBasic.setValue("$300.00");

        assertEquals("Light", itemBasic.getName());
        assertEquals("L-XXX-XXX-XXX", itemBasic.getSerialNumber());
        assertEquals("$300.00", itemBasic.getValue());
    }
}