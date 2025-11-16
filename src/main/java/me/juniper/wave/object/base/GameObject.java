package me.juniper.wave.object.base;

import java.util.Optional;

import me.juniper.wave.graphic.Renderer;
import me.juniper.wave.util.Color;
import me.juniper.wave.util.Dimension;

public abstract class GameObject {

    protected Dimension dimension;
    protected float dx, dy;
    protected float aspectRatio;
    protected Color color;

    public GameObject(Dimension dimension, Color color, float aspectRatio) {
        this.dimension = dimension;
        this.color = color;
        this.aspectRatio = aspectRatio;
    }

    public Optional<GameObject> objectTrail() {
        if (!(this instanceof ObjectTrail))
            return Optional.of(new ObjectTrail(dimension.copy(), color.copy(), aspectRatio));

        return Optional.empty();
    }

    public abstract void update(float dt, int sWidth, int sHeight);

    public abstract void render(Renderer renderer);

    public Dimension getDimension() {
        return dimension;
    }

    public Color getColor() {
        return color;
    }

}
