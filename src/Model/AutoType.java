//package Model;
//
//import java.awt.*;
//import java.awt.event.KeyEvent;
//import java.util.HashMap;
//import java.util.Map;
//import java.util.Timer;
//
//public class AutoType {
//
//    private static AutoType instance;
//
//    public static Robot robot;
//    public static Map<String, Integer> toType;
//    public static Boolean stopPressed;
//    public static Thread thread;
//    public static Timer timer;
//
//    public AutoType() {
//        if (toType == null || thread == null || robot == null) {
//            stopPressed = false;
//
//            String str = "-np";
//            String str2 = "pls beg";
//
//            try {
//                robot = new Robot();
//            } catch (AWTException e) {
//                System.out.println("failed to create robot");
//            }
//
////            InputMap inputMap = new InputMap();
////            ActionMap actionMap = new ActionMap();
////
////            inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_N, 0), "stop");
////            actionMap.put("stop", new EscapeAction());
//
//            toType = new HashMap<>();
//            addToType(str, 1000);
//
//            thread = new Thread(AutoType::runBot);
//            thread.start();
//        }
//    }
//
//    public static synchronized AutoType getInstance() {
//        if (null == instance) {
//            instance = new AutoType();
//        }
//        return instance;
//    }
//
//    public static void setStopPressed(Boolean stopPressed) {
//        AutoType.stopPressed = stopPressed;
//    }
//
//    private static void runBot() {
//        try {
//            Thread.sleep(3000);
//            while (!stopPressed) {
//
//                if (stopPressed) {
//                    break;
//                }
//                for (String s : toType.keySet()) {
//                    for (char c : s.toCharArray()) {
//                        int keyCode = KeyEvent.getExtendedKeyCodeForChar(c);
//                        if (KeyEvent.CHAR_UNDEFINED == keyCode) {
//                            throw new RuntimeException(
//                                    "Key code not found for character '" + c + "'");
//                        }
//                        robotPressKey(robot, keyCode);
//                    }
//                    robotPressKey(robot, KeyEvent.VK_ENTER);
////                    Thread.sleep(toType.get(s));
//                    robot.delay(toType.get(s));
//                }
//            }
//        } catch (final InterruptedException e) {
//            stopPressed = true;
//            return;
//        }
//    }
//
//    private static void addToType(String str, Integer integer) {
//        toType.put(str, integer);
//    }
//
//    private static boolean removeFromType(String str) {
//        boolean x = false;
//        for (String s : toType.keySet()) {
//            if (s.equals(str)) {
//                x = true;
//            }
//        }
//        toType.remove(str);
//        return x;
//    }
//
//    private static void robotPressKey(Robot robot, int vk) {
//        robot.keyPress(vk);
//        robot.keyRelease(vk);
//    }
//
//    private static void robotPressKey(Robot robot, int vk, int vk2) {
//        robot.keyPress(vk);
//        robot.keyPress(vk2);
//        robot.keyRelease(vk);
//        robot.keyRelease(vk2);
//    }
//
//
//
//}
