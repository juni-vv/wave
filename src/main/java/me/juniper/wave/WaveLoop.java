package me.juniper.wave;

import me.juniper.wave.window.Window;

public class WaveLoop {

    private final Window window;
    private final Wave waveInstance;
    private boolean isRunning;

    public WaveLoop(Window window, Wave waveInstance) {
        this.window = window;
        this.waveInstance = waveInstance;
    }

    public void run() {
        isRunning = true;

        long lastTime = System.nanoTime();

        while (isRunning && !window.shouldClose()) {
            long now = System.nanoTime();
            float delta = (now - lastTime) / 1_000_000_000f;
            lastTime = now;

            waveInstance.update(delta);
            waveInstance.render();

            window.swapBuffers();
            window.pollEvents();
        }

        window.dispose();
    }

    public void stop() {
        isRunning = false;
    }

    public boolean isRunning() {
        return isRunning;
    }

}
