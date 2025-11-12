package me.juniper.wave.graphic;

import org.lwjgl.opengl.GL11;

import me.juniper.wave.util.Color;

public class Renderer {

    private int screenWidth, screenHeight;

    public Renderer(int screenWidth, int screenHeight) {
        this.screenWidth = screenWidth;
        this.screenHeight = screenHeight;

        setupProjection();
    }

    private void setupProjection() {
        float aspectRatio = (float) screenWidth / screenHeight;

        GL11.glMatrixMode(GL11.GL_PROJECTION);
        GL11.glLoadIdentity();
        GL11.glOrtho(0, 1, 1 / aspectRatio, 0, -1, 1);
        GL11.glMatrixMode(GL11.GL_MODELVIEW);
        GL11.glLoadIdentity();
    }

    public void clear(Color color) {
        GL11.glClearColor(color.getRed(), color.getGreen(), color.getBlue(), color.getAlpha());
        GL11.glClear(GL11.GL_COLOR_BUFFER_BIT);
    }

    public void drawSquare(float x, float y, float width, float height, Color color) {
        GL11.glColor4f(color.getRed(), color.getGreen(), color.getBlue(), color.getAlpha());
        GL11.glBegin(GL11.GL_QUADS);
        GL11.glVertex2f(x, y);
        GL11.glVertex2f(x + width, y);
        GL11.glVertex2f(x + width, y + height);
        GL11.glVertex2f(x, y + height);
        GL11.glEnd();
    }

}
