package me.juniper.wave;

import org.lwjgl.opengl.GL11;

import me.juniper.wave.graphic.Color;
import me.juniper.wave.graphic.Renderer;
import me.juniper.wave.object.PlayerObject;
import me.juniper.wave.object.base.ObjectHandler;
import me.juniper.wave.window.InputManager;
import me.juniper.wave.window.Window;

public class Wave {

    private final int WIDTH = 1280;
    private final int HEIGHT = 720;

    private Window window;
    private InputManager inputManager;
    private Renderer renderer;

    private WaveLoop waveLoop;
    private ObjectHandler objectHandler;

    public Wave() {
        window = new Window(WIDTH, HEIGHT, "Wave"); // TODO: get values from env file
        inputManager = new InputManager(window);
        renderer = new Renderer(WIDTH, HEIGHT);

        objectHandler = new ObjectHandler(inputManager);

        objectHandler.addObject(new PlayerObject(0f, 0f, 0.04f, 0.04f, new Color(255, 255, 255)));

        waveLoop = new WaveLoop(window, this);
        waveLoop.run();

        window.dispose();
    }

    protected void update(float dt) {
        objectHandler.update(dt, WIDTH, HEIGHT);
    }

    protected void render() {
        renderer.clear(new Color(0x000));
        GL11.glLoadIdentity();
        objectHandler.render(renderer);
    }

}
