package me.juniper.wave.ui;

import me.juniper.wave.graphic.Renderer;
import me.juniper.wave.ui.management.UIElement;
import me.juniper.wave.util.Color;
import me.juniper.wave.util.Dimension;

public class Button extends UIElement {

    public Button(Dimension dimension) {
        super(dimension);
        hoverGrowFactor = 0.1f;
    }

    @Override
    public void render(Renderer renderer) {
        if (isMouseOver()) {
            hoverGrowActive = true;
            renderer.drawSquare(dimension.getDimension(hoverGrowFactor), new Color((short) 0xFFF));
        } else {
            hoverGrowActive = false;
            renderer.drawSquare(dimension, new Color((short) 0xFFF));
        }
    }

}
