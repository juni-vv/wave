package me.juniper.wave.ui.management;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UIManager {

    private final Map<Integer, List<UIElement>> layers = new HashMap<>();

    public void addElement(UIElement element, int layer) {
        layers.computeIfAbsent(layer, l -> new ArrayList<>()).add(element);
    }

    public void removeElement(UIElement element, int layer) {
        List<UIElement> layerList = layers.get(layer);
        if (layerList != null) {
            layerList.remove(element);

            if (layerList.isEmpty())
                layers.remove(layer);
        }
    }

    public void removeElement(UIElement element) {
        layers.values().forEach(list -> list.remove(element));
        layers.entrySet().removeIf(entry -> entry.getValue().isEmpty());
    }

    public void clear() {
        layers.clear();
    }

}
