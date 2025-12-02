package me.juniper.wave.ui;

import me.juniper.wave.graphic.Renderer;
import me.juniper.wave.graphic.Window;
import me.juniper.wave.ui.management.UIElement;
import me.juniper.wave.util.Color;
import me.juniper.wave.util.Dimension;

public class Button extends UIElement {

    private Color[] colors;
    private float aspectRatio = 1.0f;

    public Button(Window window, Dimension dimension, Color[] colors) {
        super(window, dimension);
        hoverGrowFactor = 0.1f;

        this.colors = colors;
        this.aspectRatio = (float) window.getWidth() / (float) window.getHeight();
    }

    @Override
    public void render(Renderer renderer) {
        if (isMouseOver()) {
            hoverGrowActive = true;

            float thickness = 0.001f;
            float dx = thickness / aspectRatio;
            float dy = thickness;

            Dimension resizedBorder = dimension.resize(hoverGrowFactor);
            Dimension resizedInside = new Dimension(resizedBorder.getX() + dx, resizedBorder.getY() + dy,
                    resizedBorder.getWidth() - dx * 2f, resizedBorder.getHeight() - dy * 2f);

            // Border (0), Button (1)
            renderer.drawRectangle(resizedBorder, getColor(0));
            renderer.drawRectangle(resizedInside, getColor(1));
        } else {
            hoverGrowActive = false;

            float thickness = 0.001f;
            float dx = thickness / aspectRatio;
            float dy = thickness;

            Dimension inside = new Dimension(dimension.getX() + dx, dimension.getY() + dy,
                    dimension.getWidth() - dx * 2f, dimension.getHeight() - dy * 2f);

            // Border (0), Button (1)
            renderer.drawRectangle(dimension, getColor(0));
            renderer.drawRectangle(inside, getColor(1));
        }
    }

    private Color getColor(int index) {
        if (colors == null || colors.length == 0)
            return new Color((short) 0xfff);

        if (index > colors.length)
            index = colors.length;

        return colors[index];
    }

}
