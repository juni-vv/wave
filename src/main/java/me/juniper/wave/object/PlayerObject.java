package me.juniper.wave.object;

import org.lwjgl.glfw.GLFW;

import me.juniper.wave.graphic.Renderer;
import me.juniper.wave.object.base.GameObject;
import me.juniper.wave.ui.management.InputManager;
import me.juniper.wave.util.Color;
import me.juniper.wave.util.Dimension;

public class PlayerObject extends GameObject {

    private float speed = 0.25f;

    public PlayerObject(Dimension dimension, Color color, float aspectRatio) {
        super(dimension, color, aspectRatio);
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

    }

    @Override
    public void update(float dt, int sWidth, int sHeight) {
        if (dimension.getX() < 0 || dimension.getX() + dimension.getWidth() > sWidth)
            dx = 0;
        if (dimension.getY() < 0 || dimension.getY() + dimension.getHeight() > sHeight)
            dy = 0;

        dimension.setX(dimension.getX() + dx * dt);
        dimension.setY(dimension.getY() + dy * dt);
    }

    @Override
    public void render(Renderer renderer) {
        renderer.drawRectangle(dimension.aspectRatio(aspectRatio), color);
    }

}
