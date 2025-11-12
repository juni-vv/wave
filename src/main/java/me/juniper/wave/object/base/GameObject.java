package me.juniper.wave.object.base;

import java.util.Optional;

import me.juniper.wave.graphic.Renderer;
import me.juniper.wave.util.Color;

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

    public Optional<GameObject> objectTrail() {
        if (!(this instanceof ObjectTrail)) {
            return Optional.of(new ObjectTrail(x, y, width, height, color.copy()));
        }

        return Optional.empty();
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
