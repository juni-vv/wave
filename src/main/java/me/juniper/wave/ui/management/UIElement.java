package me.juniper.wave.ui.management;

import me.juniper.wave.graphic.Renderer;

public abstract class UIElement {

    protected float x, y, width, height;
    protected InputManager inputManager;

    public UIElement(float x, float y, float width, float height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    protected void setInputManager(InputManager inputManager) {
        this.inputManager = inputManager;
    }

    public abstract void render(Renderer renderer);

    public boolean isMouseOver() {
        System.out.println("X: " + inputManager.getMouseX() + ", Y:" + inputManager.getMouseY());

        return inputManager.getMouseX() >= x && inputManager.getMouseX() <= x + width && inputManager.getMouseY() >= y
                && inputManager.getMouseY() <= y + height;
    }

}
