package me.juniper.wave.ui;

import me.juniper.wave.graphic.Renderer;
import me.juniper.wave.ui.management.UIElement;
import me.juniper.wave.util.Color;

public class Button extends UIElement {

    public Button(float x, float y, float width, float height) {
        super(x, y, width, height);
    }

    @Override
    public void render(Renderer renderer) {
        renderer.drawSquare(x, y, width, height, new Color(0xFFF));
    }

}
