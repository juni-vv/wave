package me.juniper.wave.ui;

import me.juniper.wave.graphic.Renderer;
import me.juniper.wave.ui.management.UIElement;
import me.juniper.wave.util.Color;

public class Button extends UIElement {

    public Button(float x, float y, float width, float height) {
        super(x, y, width, height);
        hoverGrowFactor = 0.1f;
    }

    @Override
    public void render(Renderer renderer) {
        if (isMouseOver()) {
            hoverGrowActive = true;
            renderer.drawSquare(x - (hoverGrowFactor / 2), y - (hoverGrowFactor / 2), width + hoverGrowFactor,
                    height + hoverGrowFactor, new Color((short) 0xFFF));
        } else {
            hoverGrowActive = false;
            renderer.drawSquare(x, y, width, height, new Color((short) 0xFFF));
        }
    }

}
