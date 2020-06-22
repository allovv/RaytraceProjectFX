package CoreGraphicsRT;

import CoreGraphicsRT.Rotator.Rotator;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;

public class CatchAction {
    public static void keyboardCapture(Scene scene) {
        scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                switch (event.getCode()) {
                    case UP:
                        App.graphCore.moveCamera(GraphicsCore.Direction.forward);
                        App.reProcessing();
                        break;
                    case DOWN:
                        App.graphCore.moveCamera(GraphicsCore.Direction.back);
                        App.reProcessing();
                        break;
                    case LEFT:
                        App.graphCore.moveCamera(GraphicsCore.Direction.left);
                        App.reProcessing();
                        break;
                    case RIGHT:
                        App.graphCore.moveCamera(GraphicsCore.Direction.right);
                        App.reProcessing();
                        break;
                    case TAB:
                        App.graphCore.moveCamera(GraphicsCore.Direction.down);
                        App.reProcessing();
                        break;
                    case SPACE:
                        App.graphCore.moveCamera(GraphicsCore.Direction.up);
                        App.reProcessing();
                        break;
                    case W:
                        break;
                    case S:
                        break;
                    case A:
                        App.graphCore.getCamera().incCameraAngleX();
                        Rotator.updateRotatorXZ(App.graphCore.getCamera().getCameraAngleX());
                        App.reProcessing();
                        break;
                    case D:
                        App.graphCore.getCamera().decCameraAngleX();
                        Rotator.updateRotatorXZ(App.graphCore.getCamera().getCameraAngleX());
                        App.reProcessing();
                        break;
                }
            }
        });
    }
}
