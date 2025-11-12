package me.juniper.wave.ui.management;

import me.juniper.wave.graphic.Renderer;

public abstract class UIElement {

    protected float x, y, width, height;

    public UIElement(float x, float y, float width, float height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    public abstract void render(Renderer renderer);

}
