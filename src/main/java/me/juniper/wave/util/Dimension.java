package me.juniper.wave.util;

public class Dimension {

    private float x, y, width, height;

    public Dimension(float x, float y, float width, float height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    public Dimension resize(float dimensionGrowFactor) {
        if (dimensionGrowFactor < 0)
            dimensionGrowFactor = Math.abs(dimensionGrowFactor);

        if (dimensionGrowFactor > 0)
            return new Dimension(x - (dimensionGrowFactor / 2), y - (dimensionGrowFactor / 2),
                    width + dimensionGrowFactor, height + dimensionGrowFactor);

        return this;
    }

    public Dimension aspectRatio(float aspectRatio) {
        if (aspectRatio == 0.0f)
            return this;

        if (aspectRatio > 1.0f)
            return new Dimension(x, y, width / aspectRatio, height);

        return new Dimension(x, y, width, height * aspectRatio);
    }

    public Dimension copy() {
        return new Dimension(x, y, width, height);
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

    public void setX(float x) {
        this.x = x;
    }

    public void setY(float y) {
        this.y = y;
    }

    public void setWidth(float width) {
        this.width = width;
    }

    public void setHeight(float height) {
        this.height = height;
    }

}
