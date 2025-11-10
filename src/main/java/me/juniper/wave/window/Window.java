package me.juniper.wave.window;

import org.lwjgl.glfw.Callbacks;
import org.lwjgl.glfw.GLFW;
import org.lwjgl.glfw.GLFWErrorCallback;
import org.lwjgl.opengl.GL;
import org.lwjgl.opengl.GLCapabilities;

public class Window {

    private long windowId;
    private GLFWErrorCallback errorCallback;
    private GLCapabilities glCapabilities;

    private final int width;
    private final int height;
    private final String title;

    public Window(int width, int height, String title) {
        this.width = width;
        this.height = height;
        this.title = title;

        init();
    }

    private void init() {
        errorCallback = GLFWErrorCallback.createPrint(System.err).set();

        if (!GLFW.glfwInit())
            throw new IllegalStateException("Unable to initialize GLFW!");

        GLFW.glfwDefaultWindowHints();
        GLFW.glfwWindowHint(GLFW.GLFW_VISIBLE, 0);
        GLFW.glfwWindowHint(GLFW.GLFW_RESIZABLE, 0);

        windowId = GLFW.glfwCreateWindow(width, height, title, 0L, 0L);
        if (windowId == 0L)
            throw new RuntimeException("Failed to create GLFW window!");

        GLFW.glfwMakeContextCurrent(windowId);
        GLFW.glfwSwapInterval(1);
        GLFW.glfwShowWindow(windowId);

        glCapabilities = GL.createCapabilities();
    }

    public void dispose() {
        Callbacks.glfwFreeCallbacks(windowId);
        GLFW.glfwDestroyWindow(windowId);
        GLFW.glfwTerminate();
        GLFW.glfwSetErrorCallback(null).free();

        if (errorCallback != null)
            errorCallback.free();
    }

    public long getWindowId() {
        return windowId;
    }

    public boolean shouldClose() {
        return GLFW.glfwWindowShouldClose(windowId);
    }

    public void swapBuffers() {
        GLFW.glfwSwapBuffers(windowId);
    }

    public void pollEvents() {
        GLFW.glfwPollEvents();
    }

    public GLCapabilities getGlCapabilities() {
        return glCapabilities;
    }
}
