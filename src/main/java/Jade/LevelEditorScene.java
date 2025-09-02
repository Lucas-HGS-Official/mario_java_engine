package Jade;

import java.awt.event.KeyEvent;

public class LevelEditorScene extends Scene{

    private boolean isChangingScene = false;
    private float timeToChangeScene = 2.f;

    public LevelEditorScene() {
        System.out.println("Inside level editor scene");
    }

    @Override
    public void update(float dt) {
        if(!isChangingScene && KeyListener.isKeyPressed(KeyEvent.VK_SPACE)) {
            isChangingScene = true;
        }

        if(isChangingScene && timeToChangeScene > 0) {
            timeToChangeScene -= dt;

            Window.get().r -= dt*5.f;
            Window.get().g -= dt*5.f;
            Window.get().b -= dt*5.f;
        } else if(isChangingScene) {
            Window.changeScene(1);
        }
    }
}
