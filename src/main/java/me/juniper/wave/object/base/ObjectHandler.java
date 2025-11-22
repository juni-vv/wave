package me.juniper.wave.object.base;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import me.juniper.wave.graphic.Renderer;
import me.juniper.wave.object.PlayerObject;
import me.juniper.wave.ui.management.InputManager;

public class ObjectHandler {

    private final List<GameObject> gameObjectAdd = new ArrayList<GameObject>();
    private final List<GameObject> gameObjects = new ArrayList<GameObject>();
    private final List<GameObject> gameObjectDel = new ArrayList<GameObject>();

    private final List<GameObject> objectTrailAdd = new ArrayList<GameObject>();
    private final List<GameObject> objectTrails = new ArrayList<GameObject>();
    private final List<GameObject> objectTrailDel = new ArrayList<GameObject>();

    private InputManager inputManager;

    public ObjectHandler(InputManager inputManager) {
        this.inputManager = inputManager;
    }

    public void addObject(GameObject gameObject) {
        gameObjectAdd.add(gameObject);
    }

    public void removeObject(GameObject gameObject) {
        gameObjectDel.remove(gameObject);
    }

    public void update(float dt) {
        for (GameObject gameObject : gameObjects) {
            gameObject.update(dt);
            gameObject.objectTrail().ifPresent(objectTrailAdd::add);

            if (gameObject instanceof PlayerObject playerObject)
                playerObject.handleInput(inputManager);

            if (gameObject instanceof Enemy enemy)
                if (enemy.shouldDie())
                    gameObjectDel.add(enemy);

            checkCollisions(gameObject);
        }

        for (GameObject gameObject : objectTrails) {
            if (gameObject instanceof ObjectTrail trail) {
                gameObject.update(dt);

                if (trail.shouldDie())
                    objectTrailDel.add(gameObject);
            }
        }

        gameObjects.removeAll(gameObjectDel);
        objectTrails.removeAll(objectTrailDel);

        gameObjects.addAll(gameObjectAdd);
        objectTrails.addAll(objectTrailAdd);

        gameObjectAdd.clear();
        objectTrailAdd.clear();

        gameObjectDel.clear();
        objectTrailDel.clear();
    }

    public void render(Renderer renderer) {
        for (GameObject gameObject : gameObjects)
            gameObject.render(renderer);
        for (GameObject gameObject : objectTrails)
            gameObject.render(renderer);
    }

    public void checkCollisions(GameObject gameObjectA) {
        for (GameObject gameObjectB : gameObjects) {
            if (gameObjectA == gameObjectB)
                continue;

            if (gameObjectA.getDimension().intersects(gameObjectB.getDimension())) {
                gameObjectA.calculateCollision(gameObjectB);
                gameObjectB.calculateCollision(gameObjectA);
            }
        }
    }

    public Stream<GameObject> getObjects() {
        return gameObjects.stream();
    }

    public Stream<ObjectTrail> getObjectTrails() {
        return objectTrails.stream().map(o -> (ObjectTrail) o);
    }

}
