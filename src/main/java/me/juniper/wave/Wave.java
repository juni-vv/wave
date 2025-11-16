package me.juniper.wave;

import org.lwjgl.opengl.GL11;

import me.juniper.wave.graphic.Renderer;
import me.juniper.wave.graphic.Window;
import me.juniper.wave.object.BounceEnemy;
import me.juniper.wave.object.PlayerObject;
import me.juniper.wave.object.base.ObjectHandler;
import me.juniper.wave.ui.management.InputManager;
import me.juniper.wave.ui.management.UIManager;
import me.juniper.wave.util.Color;
import me.juniper.wave.util.Dimension;

public class Wave {

    private final int WIDTH = 1280;
    private final int HEIGHT = 720;

    private Window window;
    private InputManager inputManager;
    private Renderer renderer;

    private WaveLoop waveLoop;

    private UIManager uiManager;
    private ObjectHandler objectHandler;

    public Wave() {
        window = new Window(WIDTH, HEIGHT, "Wave"); // TODO: get values from env file
        inputManager = new InputManager(window);
        renderer = new Renderer();

        uiManager = new UIManager(inputManager);
        objectHandler = new ObjectHandler(inputManager);

        objectHandler.addObject(
                new PlayerObject(new Dimension(0f, 0f, 0.04f, 0.04f), new Color(255, 255, 255),
                        (float) WIDTH / (float) HEIGHT));

        objectHandler.addObject(
                new BounceEnemy(new Dimension(0f, 0f, 0.04f, 0.04f), new Color(255, 0, 0),
                        (float) WIDTH / (float) HEIGHT));

        /* TEMPORARY */
        // Dimension buttonDimension = new Dimension(0.4f, 0.4f, 0.2f, 0.1f);
        // Button testButton = new Button(buttonDimension, "Test");
        // uiManager.addElement(testButton, 1);
        /* * */

        waveLoop = new WaveLoop(window, this);
        waveLoop.run();

        window.dispose();
    }

    protected void update(float dt) {
        objectHandler.update(dt);
    }

    protected void render() {
        renderer.clear(new Color(0x000));

        GL11.glLoadIdentity();

        objectHandler.render(renderer);
        uiManager.render(renderer);
    }

}
