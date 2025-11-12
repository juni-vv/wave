package me.juniper.wave.object.base;

import org.lwjgl.opengl.GL11;

import me.juniper.wave.graphic.Color;
import me.juniper.wave.graphic.Renderer;

public class ObjectTrail extends GameObject {

    private float trailDurationInitial;
    private float trailDurationRemaining;
    private float alphaInitial;

    public ObjectTrail(float x, float y, float width, float height, Color color) {
        super(x, y, width, height, color);

        this.trailDurationInitial = 0.5f;
        this.trailDurationRemaining = 0.5f;

        this.alphaInitial = color.getAlpha();
    }

    private void updateAlpha() {
        color.setAlpha(alphaInitial * (trailDurationRemaining / trailDurationInitial));
    }

    @Override
    public void update(float dt, int sWidth, int sHeight) {
        if (trailDurationRemaining > 0) {
            trailDurationRemaining -= 3f * dt;

            updateAlpha();
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
        this.trailDurationInitial = trailDuration;
        this.trailDurationRemaining = trailDuration;
    }

    public boolean shouldDie() {
        return trailDurationRemaining <= 0;
    }

}
