package Model;

import java.awt.*;
import java.awt.event.KeyEvent;

public class IsKeyPressed {
    public static volatile boolean stopPressed = false;
    public static boolean isStopPressed() {
        synchronized (IsKeyPressed.class) {
            return stopPressed;
        }
    }
    public static void main(String[] args) {
        System.out.println("working");
        KeyboardFocusManager.getCurrentKeyboardFocusManager().addKeyEventDispatcher(new KeyEventDispatcher() {

            @Override
            public boolean dispatchKeyEvent(KeyEvent ke) {
                synchronized (IsKeyPressed.class) {
                    switch (ke.getID()) {
                        case KeyEvent.KEY_PRESSED:
                            System.out.println("working");
                            if (ke.getKeyCode() == KeyEvent.VK_BACK_SPACE) {
                                stopPressed = false;
                            }
                            break;

                        case KeyEvent.KEY_RELEASED:
                            System.out.println("working");
                            if (ke.getKeyCode() == KeyEvent.VK_BACK_SPACE) {
                                stopPressed = true;
                            }
                            break;
                    }
                    return false;
                }
            }
        });
    }
}

