package me.juniper.wave.object;

import me.juniper.wave.graphic.Renderer;
import me.juniper.wave.object.base.Enemy;
import me.juniper.wave.object.base.GameObject;
import me.juniper.wave.util.Color;
import me.juniper.wave.util.Dimension;

public class BounceEnemy extends Enemy {

    public BounceEnemy(Dimension dimension, Color color, float aspectRatio) {
        super(dimension, color, aspectRatio);

        dimension.clampPosition(0.0f, 0.0f, 1.0f, 1.0f, dimension.aspectRatio(aspectRatio));

        dx = 0.25f;
        dy = 0.25f;

        if (aspectRatio > 1.0f)
            dx /= aspectRatio;

        if (aspectRatio != 0.0f && aspectRatio <= 1.0f)
            dy *= aspectRatio;
    }

    @Override
    public void update(float dt) {
        dimension.setX(dimension.getX() + dx * dt);
        dimension.setY(dimension.getY() + dy * dt);

        if (dimension.doesMeetConstraintY())
            dy = -dy;

        if (dimension.doesMeetConstraintX())
            dx = -dx;
    }

    @Override
    public void render(Renderer renderer) {
        renderer.drawRectangle(dimension.aspectRatio(aspectRatio), color);
    }

    @Override
    protected void collide(short direction) {
        if (direction == GameObject.DIRECTION_BOTTOM || direction == GameObject.DIRECTION_TOP)
            dy = -dy;

        if (direction == GameObject.DIRECTION_LEFT || direction == GameObject.DIRECTION_RIGHT)
            dx = -dx;
    }

}
