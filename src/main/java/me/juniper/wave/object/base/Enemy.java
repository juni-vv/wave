package me.juniper.wave.object.base;

import me.juniper.wave.util.Color;
import me.juniper.wave.util.Dimension;

public abstract class Enemy extends GameObject {

    public Enemy(Dimension dimension, Color color, float aspectRatio) {
        super(dimension, color, aspectRatio);
    }

}
