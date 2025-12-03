package me.juniper.wave.graphic;

import org.lwjgl.opengl.GL11;
import org.lwjgl.stb.STBTTBakedChar;

import me.juniper.wave.graphic.text.Text;
import me.juniper.wave.util.Color;
import me.juniper.wave.util.Dimension;

public class Renderer {

    private final int windowWidth, windowHeight;

    public Renderer(int windowWidth, int windowHeight) {
        this.windowWidth = windowWidth;
        this.windowHeight = windowHeight;

        setupProjection();
    }

    private void setupProjection() {
        GL11.glMatrixMode(GL11.GL_PROJECTION);
        GL11.glLoadIdentity();
        GL11.glOrtho(0, 1, 1, 0, -1, 1);
        GL11.glMatrixMode(GL11.GL_MODELVIEW);
        GL11.glLoadIdentity();
    }

    public void clear(Color color) {
        GL11.glClearColor(color.getRed(), color.getGreen(), color.getBlue(), color.getAlpha());
        GL11.glClear(GL11.GL_COLOR_BUFFER_BIT);
    }

    public void drawRectangle(Dimension dimension, Color color) {
        drawRectangle(dimension.getX(), dimension.getY(), dimension.getWidth(), dimension.getHeight(), color);
    }

    public void drawRectangle(float x, float y, float width, float height, Color color) {
        GL11.glColor4f(color.getRed(), color.getGreen(), color.getBlue(), color.getAlpha());
        GL11.glBegin(GL11.GL_QUADS);
        GL11.glVertex2f(x, y);
        GL11.glVertex2f(x + width, y);
        GL11.glVertex2f(x + width, y + height);
        GL11.glVertex2f(x, y + height);
        GL11.glEnd();
    }

    public void drawText(Text text) {
        GL11.glEnable(GL11.GL_TEXTURE_2D);
        GL11.glEnable(GL11.GL_BLEND);

        GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
        GL11.glBindTexture(GL11.GL_TEXTURE_2D, text.getFontTexture().getTextureID());

        GL11.glColor3f(text.getColor().getRed(), text.getColor().getGreen(), text.getColor().getBlue());

        STBTTBakedChar.Buffer cData = text.getFontTexture().getCharData();
        float x = text.getDimension().getX() * windowWidth;
        float y = text.getDimension().getY() * windowHeight;

        GL11.glBegin(GL11.GL_QUADS);

        for (int i = 0; i < text.get().length(); i++) {
            char c = text.get().charAt(i);
            if (c < 32 || c >= 128)
                continue;

            STBTTBakedChar bc = cData.get(c - 32);

            float x0 = x + bc.xoff() / windowWidth;
            float y0 = y - bc.yoff() / windowHeight;
            float x1 = x0 + (bc.x1() - bc.x0()) / windowWidth;
            float y1 = y0 + (bc.y1() - bc.y0()) / windowHeight;

            float s0 = bc.x0() / 512.0f;
            float t0 = bc.y0() / 512.0f;
            float s1 = bc.x1() / 512.0f;
            float t1 = bc.y1() / 512.0f;

            GL11.glTexCoord2f(s0, t0);
            GL11.glVertex2f(x0, y0);

            GL11.glTexCoord2f(s1, t0);
            GL11.glVertex2f(x1, y0);

            GL11.glTexCoord2f(s1, t1);
            GL11.glVertex2f(x1, y1);

            GL11.glTexCoord2f(s0, t1);
            GL11.glVertex2f(x0, y1);

            x += bc.xadvance() / windowWidth;
        }

        GL11.glEnd();
        GL11.glDisable(GL11.GL_BLEND);
        GL11.glDisable(GL11.GL_TEXTURE_2D);
    }

}
