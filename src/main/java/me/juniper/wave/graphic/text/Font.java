package me.juniper.wave.graphic.text;

import java.nio.ByteBuffer;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.lwjgl.stb.STBTTFontinfo;
import org.lwjgl.stb.STBTruetype;

public class Font {

    private STBTTFontinfo fontInfo;
    private ByteBuffer fontBuffer;

    public Font(String path) throws Exception {
        fontBuffer = ByteBuffer.wrap(Files.readAllBytes(Paths.get(path)));
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
