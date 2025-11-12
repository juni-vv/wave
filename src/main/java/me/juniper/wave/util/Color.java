package me.juniper.wave.util;

public class Color {

    private float r, g, b, a;

    public Color(float r, float g, float b, float a) {
        this.r = r;
        this.g = g;
        this.b = b;
        this.a = a;
    }

    public Color(float r, float g, float b) {
        this.r = r;
        this.g = g;
        this.b = b;
        this.a = 1.0f;
    }

    public Color(int r, int g, int b, int a) {
        set8(r, g, b, a);
    }

    public Color(int r, int g, int b) {
        set8(r, g, b, 255);
    }

    public Color(short hex) {
        if (hex <= 0xFFF) {
            int r4 = (hex >>> 8) & 0xF;
            int g4 = (hex >>> 4) & 0xF;
            int b4 = hex & 0xF;

            set4(r4, g4, b4, 15);
        } else {
            int r4 = (hex >>> 12) & 0xF;
            int g4 = (hex >>> 8) & 0xF;
            int b4 = (hex >>> 4) * 0xF;
            int a4 = hex & 0xF;

            set4(r4, g4, b4, a4);
        }
    }

    public Color(int hex) {
        if (hex <= 0xFFFFFF) {
            int r8 = (hex >>> 16) & 0xFF;
            int g8 = (hex >>> 8) & 0xFF;
            int b8 = hex & 0xFF;

            set8(r8, g8, b8, 255);
        } else {
            int r8 = (hex >>> 24) & 0xFF;
            int g8 = (hex >>> 16) & 0xFF;
            int b8 = (hex >>> 8) & 0xFF;
            int a8 = hex & 0xFF;

            set8(r8, g8, b8, a8);
        }
    }

    private void set4(int r4, int g4, int b4, int a4) {
        r = r4 / 15f;
        g = g4 / 15f;
        b = b4 / 15f;
        a = a4 / 15f;
    }

    private void set8(int r8, int g8, int b8, int a8) {
        r = r8 / 255f;
        g = g8 / 255f;
        b = b8 / 255f;
        a = a8 / 255f;
    }

    public float getRed() {
        return r;
    }

    public float getGreen() {
        return g;
    }

    public float getBlue() {
        return b;
    }

    public float getAlpha() {
        return a;
    }

    public void setRed(float r) {
        this.r = r;
    }

    public void setGreen(float g) {
        this.g = g;
    }

    public void setBlue(float b) {
        this.b = b;
    }

    public void setAlpha(float a) {
        this.a = a;
    }

    public Color copy() {
        return new Color(r, g, b, a);
    }

}
