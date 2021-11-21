package baseline;

import java.util.ArrayList;
import java.util.List;

// Class is used only for JSON parsing

public class ItemArrayList {
    ArrayList<ItemBasic> itemBasicArrayList;

    public ItemArrayList(List<Item> items) {
        itemBasicArrayList = new ArrayList<>();

        List<Item> showing = items;

        // sets and adds itemBasic values for each ItemBasic
        for(int i = 0; i < showing.size(); i++) {
            showing.get(i).setItemBasic();
            add(showing.get(i).getItemBasic());
        }
    }

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
