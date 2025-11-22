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

    public static final short DIRECTION_RIGHT = 0;
    public static final short DIRECTION_BOTTOM = 1;
    public static final short DIRECTION_LEFT = 2;
    public static final short DIRECTION_TOP = 3;

    public GameObject(Dimension dimension, Color color, float aspectRatio) {
        this.dimension = dimension;
        this.color = color;
        this.aspectRatio = aspectRatio;
    }

    public Optional<ObjectTrail> objectTrail() {
        if (!(this instanceof ObjectTrail))
            return Optional.of(new ObjectTrail(dimension.copy(), color.copy(), aspectRatio));

        return Optional.empty();
    }

    public abstract void update(float dt);

    public abstract void render(Renderer renderer);

    public void calculateCollision(GameObject gameObject) {
        Dimension a = this.dimension;
        Dimension b = gameObject.dimension;

        float ax = a.getRawX();
        float ay = a.getRawY();
        float aw = ax + a.getWidth();
        float ah = ay + a.getHeight();

        float bx = b.getRawX();
        float by = b.getRawY();
        float bw = bx + b.getWidth();
        float bh = by + b.getHeight();

        float overlapX = Math.min(aw, bw) - Math.max(ax, bx);
        float overlapY = Math.min(ah, bh) - Math.max(ay, by);

        if (overlapX <= 0 || overlapY <= 0)
            return;

        short direction;

        if (overlapX < overlapY) {
            float push = overlapX / 2f;

            if (ax < bx) {
                a.setX(a.getRawX() - push);
                b.setX(b.getRawX() + push);

                direction = 0;
            } else {
                a.setX(a.getRawX() + push);
                b.setX(b.getRawX() - push);

                direction = 2;
            }

        } else {
            float push = overlapY / 2f;

            if (ay < by) {
                a.setY(a.getRawY() - push);
                b.setY(b.getRawY() + push);

                direction = 1;
            } else {
                a.setY(a.getRawY() + push);
                b.setY(b.getRawY() - push);

                direction = 3;
            }
        }

        onCollide(direction);

        if (this instanceof Enemy enemy)
            enemy.onCollideWith(gameObject);

        if (gameObject instanceof Enemy enemy)
            enemy.onCollideWith(this);
    }

    protected abstract void onCollide(short direction);

    public Dimension getDimension() {
        return dimension;
    }

    public Color getColor() {
        return color;
    }

}
