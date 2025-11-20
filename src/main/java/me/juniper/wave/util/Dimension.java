package me.juniper.wave.util;

public class Dimension {

    private float x, y, width, height;

    private float minX = -1f, minY = -1f, maxX = -1f, maxY = -1f;

    public Dimension(float x, float y, float width, float height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    private Dimension(float x, float y, float width, float height, float minX, float minY, float maxX, float maxY) {
        this(x, y, width, height);

        this.minX = minX;
        this.minY = minY;
        this.maxX = maxX;
        this.maxY = maxY;
    }

    public boolean intersects(Dimension other) {
        return getX() < other.getX() + other.getWidth() &&
                getX() + getWidth() > other.getX() &&
                getY() < other.getY() + other.getHeight() &&
                getY() + getHeight() > other.getY();
    }

    public boolean doesMeetConstraintX() {
        return (minX >= 0 && x <= minX) || (maxX >= 0 && x >= maxX);
    }

    public boolean doesMeetConstraintY() {
        return (minY >= 0 && y <= minY) || (maxY >= 0 && y >= maxY);
    }

    public void clampPosition(float minX, float minY, float maxX, float maxY) {
        this.minX = Math.abs(minX);
        this.minY = Math.abs(minY);
        this.maxX = Math.abs(maxX - width);
        this.maxY = Math.abs(maxY - height);
    }

    public Dimension resize(float dimensionGrowFactor) {
        if (dimensionGrowFactor < 0)
            dimensionGrowFactor = Math.abs(dimensionGrowFactor);

        if (dimensionGrowFactor > 0)
            return new Dimension(x - (dimensionGrowFactor / 2), y - (dimensionGrowFactor / 2),
                    width + dimensionGrowFactor, height + dimensionGrowFactor, minX, minY, maxX, maxY);

        return this;
    }

    public Dimension setAspectRatio(float aspectRatio) {
        if (aspectRatio == 0.0f)
            return this;

        if (aspectRatio > 1.0f) {
            this.width /= aspectRatio;
            return this;
        }

        this.height *= aspectRatio;
        return this;
    }

    public Dimension copy() {
        return new Dimension(x, y, width, height, minX, minY, maxX, maxY);
    }

    public float getX() {
        if (minX < 0 && maxX < 0)
            return x;

        if (minX < 0 && maxX >= 0)
            return x > maxX ? maxX : x;

        if (maxX < 0 && minX >= 0)
            return x < minX ? minX : x;

        if (x < minX)
            return minX;
        if (x > maxX)
            return maxX;

        return x;
    }

    public float getRawX() {
        return x;
    }

    public float getY() {
        if (minY < 0 && maxY < 0)
            return y;

        if (minY < 0 && maxY >= 0)
            return y > maxY ? maxY : y;

        if (maxY < 0 && minY >= 0)
            return y < minY ? minY : y;

        if (y < minY)
            return minY;
        if (y > maxY)
            return maxY;

        return y;
    }

    public float getRawY() {
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

    public float getMinX() {
        return minX;
    }

    public float getMinY() {
        return minY;
    }

    public float getMaxX() {
        return maxX;
    }

    public float getMaxY() {
        return maxY;
    }

}
