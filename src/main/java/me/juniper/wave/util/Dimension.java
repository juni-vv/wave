package me.juniper.wave.util;

public class Dimension {

    private float x, y, width, height;

    public Dimension(float x, float y, float width, float height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    public Dimension getDimension(float dimensionGrowFactor) {
        if (dimensionGrowFactor < 0)
            dimensionGrowFactor = Math.abs(dimensionGrowFactor);

        if (dimensionGrowFactor > 0)
            return new Dimension(x - (dimensionGrowFactor / 2), y - (dimensionGrowFactor / 2),
                    width + dimensionGrowFactor, height + dimensionGrowFactor);

        return this;
    }

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }

    public float getWidth() {
        return width;
    }

    public float getHeight() {
        return height;
    }

}
