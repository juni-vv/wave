package me.juniper.wave;

import org.lwjgl.glfw.GLFW;
import org.lwjgl.opengl.GL11;

import me.juniper.wave.window.Window;

public class Wave {

    private Window window;
    private WaveLoop waveLoop;

    public Wave() {
        window = new Window(1280, 720, "Wave"); // TODO: get values from env file

        waveLoop = new WaveLoop(window, this);
        waveLoop.run();

        window.dispose();
    }

    protected void update() {
        if (window.isKeyPressed(GLFW.GLFW_KEY_ESCAPE)) {
            waveLoop.stop();
        }
    }

    protected void render() {
        GL11.glClear(GL11.GL_COLOR_BUFFER_BIT | GL11.GL_DEPTH_BUFFER_BIT);
        GL11.glClearColor(0.5f, 0.5f, 0.5f, 1.0f);
    }

}
