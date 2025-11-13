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

        if (isMouseOver()) {
            System.out.println("mouse over");
            renderer.drawSquare(x - 0.1f, y - 0.1f, width + 0.2f, height + 0.2f, new Color((short) 0xFFF));
        } else {
            System.out.println("button x: " + x + ", y: " + y);
            renderer.drawSquare(x, y, width, height, new Color((short) 0xFFF));
        }
    }

}
