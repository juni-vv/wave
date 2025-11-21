package me.juniper.wave.object;

import me.juniper.wave.graphic.Renderer;
import me.juniper.wave.object.base.Enemy;
import me.juniper.wave.object.base.GameObject;
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

        if (diffX != 0 && diffY != 0) {
            float diff = (float) Math.sqrt((float) Math.abs(diffX * diffX) + (float) Math.abs(diffY * diffY));
            diffX /= diff;
            diffY /= diff;

            diffX *= 0.15;
            diffY *= 0.15;
        }

        dx = -diffX;
        dy = -diffY;

        dimension.setX(dimension.getX() + dx * dt);
        dimension.setY(dimension.getY() + dy * dt);

    }

    @Override
    public void render(Renderer renderer) {

    }

    @Override
    protected void collide(short direction) {

    }

    @Override
    protected void collide(GameObject gameObject) {
        if (gameObject instanceof PlayerObject)
            shouldDie = true;
    }

}
