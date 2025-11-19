package me.juniper.wave.graphic.text;

import java.nio.ByteBuffer;

import org.lwjgl.BufferUtils;
import org.lwjgl.opengl.GL11;
import org.lwjgl.stb.STBTTBakedChar;
import org.lwjgl.stb.STBTruetype;

public class FontTexture {

    private int textureID;
    private STBTTBakedChar.Buffer charData;

    private static final int BITMAP_W = 512;
    private static final int BITMAP_H = 512;

    public FontTexture(Font font, float fontSize) {
        charData = STBTTBakedChar.malloc(96);
        ByteBuffer bitmap = BufferUtils.createByteBuffer(BITMAP_W * BITMAP_H);

        STBTruetype.stbtt_BakeFontBitmap(font.getFontBuffer(), fontSize, bitmap, BITMAP_W, BITMAP_H, 32, charData);

        textureID = GL11.glGenTextures();
        GL11.glBindTexture(GL11.GL_TEXTURE_2D, textureID);
        GL11.glTexImage2D(GL11.GL_TEXTURE_2D, 0, GL11.GL_ALPHA, BITMAP_W, BITMAP_H, 0, GL11.GL_ALPHA,
                GL11.GL_UNSIGNED_BYTE, bitmap);
        GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MIN_FILTER, GL11.GL_LINEAR);
    }

    public int getTextureID() {
        return textureID;
    }

    public STBTTBakedChar.Buffer getCharData() {
        return charData;
    }

}
