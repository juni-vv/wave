package me.juniper.wave.ui.management;

import me.juniper.wave.graphic.Renderer;

public abstract class UIElement {

    protected float x, y, width, height;
    protected InputManager inputManager;

    protected float hoverGrowFactor;
    protected boolean hoverGrowActive = false;

    public UIElement(float x, float y, float width, float height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.hoverGrowFactor = 0.0f;
    }

    protected void setInputManager(InputManager inputManager) {
        this.inputManager = inputManager;
    }

    public abstract void render(Renderer renderer);

    public boolean isMouseOver() {
        double mx = inputManager.getMouseX();
        double my = inputManager.getMouseY();

        if (hoverGrowActive) {
            double gx = hoverGrowFactor / 2.0;

            boolean insideX = mx >= (x - gx) && mx <= (x + width + gx);
            boolean insideY = my >= (y - gx) && my <= (y + height + gx);

            if (insideX && insideY)
                return true;
        }

        return mx >= x && mx <= x + width && my >= y && my <= y + height;
    }

}
