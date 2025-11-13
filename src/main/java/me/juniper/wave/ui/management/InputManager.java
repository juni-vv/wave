package me.juniper.wave.ui.management;

import org.lwjgl.glfw.GLFW;
import org.lwjgl.glfw.GLFWCursorPosCallback;

import me.juniper.wave.graphic.Window;

public class InputManager {

    private final Window window;
    private double mouseX, mouseY;

    public InputManager(Window window) {
        this.window = window;

        GLFW.glfwSetCursorPosCallback(window.getWindowId(), new GLFWCursorPosCallback() {
            @Override
            public void invoke(long windowId, double xpos, double ypos) {
                if (!(window.getWindowId() == windowId))
                    return;

                mouseX = xpos;
                mouseY = ypos;
            }
        });
    }

    public boolean isKeyDown(int key) {
        return GLFW.glfwGetKey(window.getWindowId(), key) == 1;
    }

    public boolean isMouseButtonDown(int button) {
        return GLFW.glfwGetMouseButton(window.getWindowId(), button) == 1;
    }

    public float getMouseX() {
        return (float) (mouseX / window.getWidth());
    }

    public float getMouseY() {
        return (float) (mouseY / window.getHeight());
    }

    public void dispose() {
        GLFW.glfwSetCursorPosCallback(window.getWindowId(), null).free();
    }

}
