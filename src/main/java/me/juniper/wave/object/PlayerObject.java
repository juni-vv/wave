package me.juniper.wave.object;

import org.lwjgl.glfw.GLFW;

import me.juniper.wave.graphic.Renderer;
import me.juniper.wave.object.base.GameObject;
import me.juniper.wave.ui.management.InputManager;
import me.juniper.wave.util.Color;
import me.juniper.wave.util.Dimension;

public class PlayerObject extends GameObject {

    private short health;
    private float speed = 0.25f;

    public PlayerObject(Dimension dimension, Color color, float aspectRatio, short health) {
        super(dimension, color, aspectRatio);

        dimension.setAspectRatio(aspectRatio);
        dimension.clampPosition(0.0f, 0.0f, 1.0f, 1.0f);

        this.health = health <= 0 ? 3 : health;
    }

    public void handleInput(InputManager inputManager) {
        dx = 0;
        dy = 0;

        if (inputManager.isKeyDown(GLFW.GLFW_KEY_W))
            dy = -1;
        if (inputManager.isKeyDown(GLFW.GLFW_KEY_A))
            dx = -1;
        if (inputManager.isKeyDown(GLFW.GLFW_KEY_S))
            dy = 1;
        if (inputManager.isKeyDown(GLFW.GLFW_KEY_D))
            dx = 1;

        if (dx != 0 && dy != 0) {
            float diff = (float) Math.sqrt(Math.abs(dx * dx) + Math.abs(dy * dy));
            dx /= diff;
            dy /= diff;
        }

        dx *= speed;
        dy *= speed;

        if (aspectRatio > 1.0f)
            dx /= aspectRatio;

        if (aspectRatio != 0.0f && aspectRatio <= 1.0f)
            dy *= aspectRatio;
    }

    @Override
    public void update(float dt) {
        dimension.setX(dimension.getX() + dx * dt);
        dimension.setY(dimension.getY() + dy * dt);
    }

    @Override
    public void render(Renderer renderer) {
        renderer.drawRectangle(dimension, color);
    }

    @Override
    protected void onCollide(short direction) {
        health--;
    }

    @Override
    public boolean shouldDie() {
        return health <= 0;
    }

    public void setHealth(short health) {
        assert health >= 0;
        this.health = health < 0 ? 0 : health;
    }

    public short getHealth() {
        return health;
    }
}
