package me.juniper.wave.object;

import me.juniper.wave.graphic.Renderer;
import me.juniper.wave.object.base.Enemy;
import me.juniper.wave.util.Color;
import me.juniper.wave.util.Dimension;

public class FollowEnemy extends Enemy {

    public FollowEnemy(Dimension dimension, Color color, float aspectRatio) {
        super(dimension, color, aspectRatio);

        dimension.setAspectRatio(aspectRatio);
        dimension.clampPosition(0.0f, 0.0f, 1.0f, 1.0f);
    }

    @Override
    public void update(float dt) {
        if (targetObject == null)
            return;

        if (!(targetObject instanceof PlayerObject))
            return;

        float diffX = dimension.getX() - targetObject.getDimension().getX();
        float diffY = dimension.getY() - targetObject.getDimension().getY();
        float distance = (float) Math.sqrt((float) Math.abs(diffX * diffX - diffY * diffY));

        dx = ((-1 / distance) * diffX) / 10;
        dy = ((-1 / distance) * diffY) / 10;

        if (dx > 0.15f)
            dx = 0.15f;

        if (dx < -0.15f)
            dx = -0.15f;

        if (dy > 0.15f)
            dy = 0.15f;

        if (dy < -0.15f)
            dy = -0.15f;

        dimension.setX(dimension.getX() + dx * dt);
        dimension.setY(dimension.getY() + dy * dt);

        System.out.println(distance);
    }

    @Override
    public void render(Renderer renderer) {

    }

    @Override
    protected void collide(short direction) {

    }

}
