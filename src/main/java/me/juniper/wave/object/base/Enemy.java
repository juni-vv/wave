package me.juniper.wave.object.base;

import me.juniper.wave.util.Color;
import me.juniper.wave.util.Dimension;

public abstract class Enemy extends GameObject {

    protected GameObject targetObject;
    protected boolean shouldDie = false;

    public Enemy(Dimension dimension, Color color, float aspectRatio) {
        super(dimension, color, aspectRatio);
    }

    public void setTargetObject(GameObject gameObject) {
        this.targetObject = gameObject;
    }

    public GameObject getTargetObject() {
        return targetObject;
    }

    protected abstract void collide(GameObject gameObject);

    public boolean shouldDie() {
        return shouldDie;
    }

}
