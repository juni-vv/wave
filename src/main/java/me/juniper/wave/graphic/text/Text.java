package me.juniper.wave.graphic.text;

import me.juniper.wave.util.Color;
import me.juniper.wave.util.Dimension;

public class Text {

    private final String text;
    private final FontTexture texture;
    private final Color color;
    private final Dimension dimension;

    public Text(String text, FontTexture texture, Color color, Dimension dimension) {
        this.text = text;
        this.texture = texture;
        this.color = color;
        this.dimension = dimension;
    }

    public String get() {
        return text;
    }

    public FontTexture getFontTexture() {
        return texture;
    }

    public Color getColor() {
        return color;
    }

    public Dimension getDimension() {
        return dimension;
    }

}
