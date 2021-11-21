/*
 *  UCF COP3330 Fall 2021 Application Assignment 2 Solution
 *  Copyright 2021 Hunter Davis
 */

package baseline;

import java.util.ArrayList;
import java.util.List;

// Class is used only for JSON parsing

public class ItemArrayList {
    ArrayList<ItemBasic> itemBasicArrayList;

    public ItemArrayList(List<Item> items) {
        itemBasicArrayList = new ArrayList<>();

        // sets and adds itemBasic values for each ItemBasic
        for (Item item : items) {
            item.setItemBasic();
            add(item.getItemBasic());
        }
    }

    // ItemArrayList uses same functionality as an ArrayList when being called
    public void add(ItemBasic itemBasic) {
        itemBasicArrayList.add(itemBasic);
    }

    public ItemBasic get(int i) {
        return itemBasicArrayList.get(i);
    }

    public int size() {
        return itemBasicArrayList.size();
    }
}
