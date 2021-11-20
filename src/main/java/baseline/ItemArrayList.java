package baseline;

import java.util.ArrayList;
import java.util.List;

// Class is used only for JSON parsing

public class ItemArrayList {
    ArrayList<ItemBasic> itemArrayList;

    public ItemArrayList(List<Item> items) {
        itemArrayList = new ArrayList<>();

        List<Item> showing = items;

        // toString for item values
        for(int i = 0; i < showing.size(); i++) {
            System.out.println(showing.get(i));
        }

        // adds and displays itemBasic values
        for(int i = 0; i < showing.size(); i++) {
            showing.get(i).setItemBasic();
            System.out.println(showing.get(i).getItemBasic());
            add(showing.get(i).getItemBasic());
        }
    }

    public void add(ItemBasic itemBasic) {
        itemArrayList.add(itemBasic);
    }

    public ItemBasic get(int i) {
        return itemArrayList.get(i);
    }

    public List<ItemBasic> get() {
        return itemArrayList;
    }

    public int size() {
        return itemArrayList.size();
    }
}
