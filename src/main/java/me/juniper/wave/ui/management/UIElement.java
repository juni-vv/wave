package me.juniper.wave.ui.management;

import me.juniper.wave.graphic.Renderer;
import me.juniper.wave.graphic.Window;
import me.juniper.wave.util.Dimension;

public abstract class UIElement {

    protected Dimension dimension;
    protected InputManager inputManager;

    protected Window window;

    protected float hoverGrowFactor;
    protected boolean hoverGrowActive = false;

    public UIElement(Window window, Dimension dimension) {
        this.dimension = dimension;
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

            boolean insideX = mx >= (dimension.getX() - gx) && mx <= (dimension.getX() + dimension.getWidth() + gx);
            boolean insideY = my >= (dimension.getY() - gx) && my <= (dimension.getY() + dimension.getHeight() + gx);

            if (insideX && insideY)
                return true;
        }

        return mx >= dimension.getX() && mx <= dimension.getX() + dimension.getWidth() && my >= dimension.getY()
                && my <= dimension.getY() + dimension.getHeight();
    }

}
