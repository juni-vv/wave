package me.juniper.wave.object;

import org.joml.Vector2f;
import me.juniper.wave.graphic.Renderer;
import me.juniper.wave.object.base.Enemy;
import me.juniper.wave.object.base.GameObject;
import me.juniper.wave.util.Color;
import me.juniper.wave.util.Dimension;

public class FollowEnemy extends Enemy {

    private DeathCallback deathCallback;
    final private float speed = 0.15f;

    public FollowEnemy(Dimension dimension, Color color, float aspectRatio, DeathCallback deathCallback) {
        super(dimension, color, aspectRatio);

        this.deathCallback = deathCallback;

        dimension.setAspectRatio(aspectRatio);
        dimension.clampPosition(0.0f, 0.0f, 1.0f, 1.0f);
    }

    @Override
    public void update(float dt) {
        if (targetObject == null)
            return;

        if (!(targetObject instanceof PlayerObject))
            return;

        float diffX = targetObject.getDimension().getX() - dimension.getX();
        float diffY = targetObject.getDimension().getY() - dimension.getY();
        Vector2f direction = new Vector2f(diffX, diffY);
        direction.normalize();
        direction.mul(speed);

        dx = direction.x;
        dy = direction.y;

        dimension.setX(dimension.getX() + dx * dt);
        dimension.setY(dimension.getY() + dy * dt);

    }

    @Override
    public void render(Renderer renderer) {

    }

    @Override
    protected void onCollide(short direction) {

    }

    @Override
    protected void onCollideWith(GameObject gameObject) {
        if (gameObject instanceof PlayerObject) {
            shouldDie = true;
        }
    }

    public void onDeath() {
        deathCallback.onDeathCallback();
    }

    public interface DeathCallback {
        public void onDeathCallback();
    }

}
