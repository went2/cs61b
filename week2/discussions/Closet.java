import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ArrayList;


public class Closet {
    Map<String, Clothing> items;
    Map<String, List<Clothing>> colorItems;

    public Closet(List<Clothing> clothings) {
        items = new HashMap<>();
        // 保存所有的 clothing 对象
        for(Clothing item : clothings) {
            String itemName = item.color + " " + item.type;
            items.put(itemName, item);
        }

        // 构建 colorItems 用于快速查找
        colorItems = new HashMap<>();
        for(Map.Entry<String, Clothing> item : items.entrySet()) {
            String color = item.getValue().color;
            if(colorItems.get(color) == null) {
                colorItems.put(color, new ArrayList<Clothing>());
            }
            colorItems.get(color).add(item.getValue());
        }
    }

    public List<Clothing> getItemsByColor(String color) {
        return colorItems.get(color);
    }

    public List<Clothing> getItemsByDay(Map<String, String> map, String curDay) {
        String color = map.get(curDay);
        return getItemsByColor(color);
    }

    public void enterEmoPhase(List<String> happyColors) {
        Map<String, List<Clothing>> map = new HashMap<>();
        for(String color : happyColors) {
            map.put(color, getItemsByColor(color));
        }
        for(Map.Entry<String, List<Clothing>> entry : map.entrySet()) {
            List<Clothing> clothes = entry.getValue();
            if(clothes != null) {
                for(Clothing cloth : clothes) {
                    cloth.color = "Black";
                }
            }
        }
    }
}
