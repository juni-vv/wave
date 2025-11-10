package me.juniper.wave.object.base;

import me.juniper.wave.graphic.Color;
import me.juniper.wave.graphic.Renderer;

public abstract class GameObject {

    protected float x, y, width, height;
    protected float dx, dy;
    protected Color color;

    public GameObject(float x, float y, float width, float height, Color color) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.color = color;
    }

    public abstract void update(float dt, int sWidth, int sHeight);

    public abstract void render(Renderer renderer);

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }

    public float getWidth() {
        return width;
    }

    public float getHeight() {
        return height;
    }

    public Color getColor() {
        return color;
    }

}
