package me.juniper.wave.ui.management;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import me.juniper.wave.graphic.Renderer;

public class UIManager {

    private final Map<Integer, List<UIElement>> layers = new HashMap<>();

    private InputManager inputManager;

    public UIManager(InputManager inputManager) {
        this.inputManager = inputManager;
    }

    public void addElement(UIElement element, int layer) {
        element.setInputManager(inputManager);
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

    public void render(Renderer renderer) {
        layers.keySet().stream().sorted().forEach(layerIndex -> {
            List<UIElement> layerList = layers.get(layerIndex);
            for (UIElement element : layerList)
                element.render(renderer);
        });
    }

    public void clear() {
        layers.clear();
    }

}
