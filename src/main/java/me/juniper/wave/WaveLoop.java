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

        while (isRunning && !window.shouldClose()) {
            waveInstance.update();
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
