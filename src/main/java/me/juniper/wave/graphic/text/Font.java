package me.juniper.wave.graphic.text;

import java.io.InputStream;
import java.nio.ByteBuffer;

import org.lwjgl.BufferUtils;
import org.lwjgl.stb.STBTTFontinfo;
import org.lwjgl.stb.STBTruetype;

public class Font {

    private STBTTFontinfo fontInfo;
    private ByteBuffer fontBuffer;

    public Font(String path) throws Exception {
        try (InputStream is = Font.class.getResourceAsStream(path)) {
            if (is == null)
                throw new RuntimeException("Font not found: " + path);

            byte[] bytes = is.readAllBytes();

            fontBuffer = BufferUtils.createByteBuffer(bytes.length);
            fontBuffer.put(bytes).flip();
        }

        fontInfo = STBTTFontinfo.create();

        if (!STBTruetype.stbtt_InitFont(fontInfo, fontBuffer))
            throw new RuntimeException("Failed to initialize font.");
    }

    public STBTTFontinfo getFontInfo() {
        return fontInfo;
    }

    public ByteBuffer getFontBuffer() {
        return fontBuffer;
    }

}
