package me.juniper.wave.object;

import org.lwjgl.glfw.GLFW;

import me.juniper.wave.graphic.Color;
import me.juniper.wave.graphic.Renderer;
import me.juniper.wave.object.base.GameObject;
import me.juniper.wave.window.InputManager;

public class PlayerObject extends GameObject {

    private float speed = 0.5f;

    public PlayerObject(float x, float y, float width, float height, Color color) {
        super(x, y, width, height, color);
    }

    public void handleInput(InputManager inputManager) {
        dx = 0;
        dy = 0;

        if (inputManager.isKeyDown(GLFW.GLFW_KEY_W))
            dy = -speed;
        if (inputManager.isKeyDown(GLFW.GLFW_KEY_A))
            dx = -speed;
        if (inputManager.isKeyDown(GLFW.GLFW_KEY_S))
            dy = speed;
        if (inputManager.isKeyDown(GLFW.GLFW_KEY_D))
            dx = speed;
    }

    @Override
    public void update(float dt, int sWidth, int sHeight) {
        x += dx * dt;
        y += dy * dt;

        if (x < 0 || x + width > sWidth)
            dx = -dx;
        if (y < 0 || y + height > sHeight)
            dy = -dy;
    }

    @Override
    public void render(Renderer renderer) {
        renderer.drawSquare(x, y, width, height, color);
    }

}
