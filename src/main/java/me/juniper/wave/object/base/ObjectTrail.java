package me.juniper.wave.object.base;

import org.lwjgl.opengl.GL11;

import me.juniper.wave.graphic.Color;
import me.juniper.wave.graphic.Renderer;

public class ObjectTrail extends GameObject {

    private float trailDuration = 0.5f;

    public ObjectTrail(float x, float y, float width, float height, Color color) {
        super(x, y, width, height, color);
    }

    @Override
    public void update(float dt, int sWidth, int sHeight) {
        if (trailDuration > 0) {
            color.setAlpha(color.getAlpha() - 0.05f);
            trailDuration -= 3f * dt;
        }
    }

    @Override
    public void render(Renderer renderer) {
        GL11.glEnable(GL11.GL_BLEND);
        GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);

        renderer.drawSquare(x, y, width, height, color);

        GL11.glDisable(GL11.GL_BLEND);
    }

    public void setTrailDuration(float trailDuration) {
        this.trailDuration = trailDuration;
    }

    public boolean shouldDie() {
        return trailDuration <= 0;
    }

}
