package Model;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.HashMap;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

public class AutoType2 {

    public static AutoType2 instance;
    public static Robot robot;
    public static Map<String, Integer> toType;
    public static Thread thread;
    public static Timer timer;
    public static String string;

    public AutoType2(String str) {
        if (toType == null || thread == null || robot == null) {


            string = "works";

            try {
                robot = new Robot();
            } catch (AWTException e) {
                System.out.println("failed to create robot");
            }

            toType = new HashMap<>();
            addToType(str, 1000);

            thread = new Thread(AutoType2::runBot);
            thread.start();
        }
    }

    private static void runBot() {
        timer = new Timer("test");
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                for (char c : string.toCharArray()) {
                    int keyCode = KeyEvent.getExtendedKeyCodeForChar(c);
                    if (KeyEvent.CHAR_UNDEFINED == keyCode) {
                        throw new RuntimeException(
                                "Key code not found for character '" + c + "'");
                    }
                    robotPressKey(robot, keyCode);
                }
                robotPressKey(robot, KeyEvent.VK_ENTER);
//                    Thread.sleep(toType.get(s));

            }
        };

        timer.schedule(task,3000,1000);
    }

    private static void addToType(String str, Integer integer) {
        toType.put(str, integer);
    }

    private static boolean removeFromType(String str) {
        boolean x = false;
        for (String s : toType.keySet()) {
            if (s.equals(str)) {
                x = true;
            }
        }
        toType.remove(str);
        return x;
    }

    private static void robotPressKey(Robot robot, int vk) {
        robot.keyPress(vk);
        robot.keyRelease(vk);
    }

    public static synchronized AutoType2 getInstance(String string) {
        if (null == instance) {
            instance = new AutoType2(string);
        }
        return instance;
    }

}
