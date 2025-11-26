package me.juniper.wave.object.management;

import me.juniper.wave.object.BounceEnemy;
import me.juniper.wave.object.FollowEnemy;
import me.juniper.wave.object.PlayerObject;
import me.juniper.wave.object.base.Enemy;
import me.juniper.wave.object.base.GameObject;
import me.juniper.wave.util.Color;
import me.juniper.wave.util.Dimension;

public class ObjectSpawner {

    private ObjectHandler objectHandler;
    private float aspectRatio;

    private PlayerObject playerObject;

    public ObjectSpawner(ObjectHandler objectHandler, float aspectRatio) {
        this.objectHandler = objectHandler;
        this.aspectRatio = aspectRatio;
    }

    public void startGame() {
        playerObject = new PlayerObject(new Dimension(0.5f - 0.02f, 0.5f - 0.02f, 0.04f, 0.04f),
                new Color(255, 255, 255), aspectRatio, (short) 3);
        objectHandler.addObject(playerObject);

        objectHandler.addObject(
                new BounceEnemy(new Dimension(0f, 0f, 0.04f, 0.04f), new Color(255, 0, 0),
                        aspectRatio));

        Enemy followEnemy = followEnemy();
        followEnemy.setTargetObject(playerObject);
        objectHandler.addObject(followEnemy);
    }

    public void update(float dt) {

    }

    private Enemy followEnemy() {
        Enemy enemy = new FollowEnemy(new Dimension(
                0f, 0f,
                0.04f, 0.04f),
                new Color(0, 255, 0),
                aspectRatio, () -> respawnFollowEnemy());
        enemy.setTargetObject(playerObject);
        return enemy;
    }

    private void spawn(GameObject gameObject) {
        objectHandler.addObject(gameObject);
    }

    private void respawnFollowEnemy() {
        if (!objectHandler.hasObjectType(FollowEnemy.class))
            spawn(followEnemy());
    }

}
