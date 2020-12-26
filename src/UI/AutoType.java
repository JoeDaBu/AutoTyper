package UI;

import java.awt.*;
import java.awt.event.KeyEvent;

public class AutoType {

    public static void main(String[] args) {
        String str = "pls meme" +
                "";

        Robot robot = null;
        try {
            robot = new Robot();
        } catch (AWTException e) {
            System.out.println("failed to create robot");
        }

        robot.delay(3000);
        while (true) {
            for (char c : str.toCharArray()) {
                int keyCode = KeyEvent.getExtendedKeyCodeForChar(c);
                if (KeyEvent.CHAR_UNDEFINED == keyCode) {
                    throw new RuntimeException(
                            "Key code not found for character '" + c + "'");
                }
                robotPressKey(robot, keyCode);
            }
            robotPressKey(robot, KeyEvent.VK_ENTER);
            robot.delay(3000);
        }
    }

    private static void robotPressKey(Robot robot, int vk) {
        robot.keyPress(vk);
        robot.delay(10);
        robot.keyRelease(vk);
    }

    private static void robotPressKey(Robot robot, int vk, int vk2) {
        robot.keyPress(vk);
        robot.keyPress(vk2);
        robot.keyRelease(vk);
        robot.keyRelease(vk2);
    }
}
