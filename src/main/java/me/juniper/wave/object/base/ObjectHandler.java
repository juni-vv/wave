package me.juniper.wave.object.base;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import me.juniper.wave.graphic.Renderer;
import me.juniper.wave.object.PlayerObject;
import me.juniper.wave.window.InputManager;

public class ObjectHandler {

    private final List<GameObject> addQueue = new ArrayList<GameObject>();
    private final List<GameObject> gameObjects = new ArrayList<GameObject>();
    private final List<GameObject> removeQueue = new ArrayList<GameObject>();

    private InputManager inputManager;

    public ObjectHandler(InputManager inputManager) {
        this.inputManager = inputManager;
    }

    public void addObject(GameObject gameObject) {
        gameObjects.add(gameObject);
    }

    public void removeObject(GameObject gameObject) {
        gameObjects.remove(gameObject);
    }

    public void update(float dt, int sWidth, int sHeight) {
        for (GameObject gameObject : gameObjects) {
            gameObject.update(dt, sWidth, sHeight);
            gameObject.objectTrail().ifPresent(addQueue::add);

            if (gameObject instanceof PlayerObject playerObject)
                playerObject.handleInput(inputManager);

            if (gameObject instanceof ObjectTrail trail)
                if (trail.shouldDie())
                    removeQueue.add(gameObject);
        }

        gameObjects.removeAll(removeQueue);
        gameObjects.addAll(addQueue);
        addQueue.clear();
        removeQueue.clear();
    }

    public void render(Renderer renderer) {
        for (GameObject gameObject : gameObjects)
            gameObject.render(renderer);
    }

    public Stream<GameObject> getObjects() {
        return gameObjects.stream();
    }

}
