package Model;
import javax.swing.*;
import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.util.Map;

public class Main2 extends JFrame {

    public static AutoType2 instance;
    public static Robot robot;
    public static Map<String, Integer> toType;
    public static Thread thread;
    public static String string;

    private static Boolean inFocus = false;
    private static JPanel panel;

    /**
     * Aye, I do not recommend storing passwords as plain text if it is for
     * something important but here is this code anyway because i'm bored.
     *
     * Also should not that using a AutoHotKey or any other macro program is way
     * easier but im using Java because learning amiright
     */
    public static void main(String[] args) {

        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                Main2 frame = new Main2("-np");
                frame.setTitle("Copier");
                frame.setResizable(false);
                frame.setSize(1000, 1000);
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.setVisible(true);
                frame.setState(Frame.ICONIFIED);

                Thread t = new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            Thread.sleep(1000);
                        } catch (InterruptedException ex) {
                        }
                        // try catch for forcing Escape key press
                        try {
                            while(!inFocus) {
                                if (panel.isFocusOwner()) {
                                    inFocus = true;
                                    break;
                                }
                                for (char c : string.toCharArray()) {
                                    int keyCode = KeyEvent.getExtendedKeyCodeForChar(c);
                                    if (KeyEvent.CHAR_UNDEFINED == keyCode) {
                                        throw new RuntimeException(
                                                "Key code not found for character '" + c + "'");
                                    }
                                    robotPressKey(robot, keyCode);
                                }
                                robotPressKey(robot, KeyEvent.VK_ENTER);
                                Thread.sleep(1000);
                            }
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }

                    }
                });
                t.start();
            }
        });
    }

    public Main2(String s) {
        this.string = s;
        try {
            robot = new Robot();
        } catch (AWTException e) {
            System.out.println("failed to create robot");
        }

        panel = new JPanel(new BorderLayout());
        InputMap inputMap = panel.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW);
        ActionMap actionMap = panel.getActionMap();

        for (char c : string.toCharArray()) {
            int keyCode = KeyEvent.getExtendedKeyCodeForChar(c);
            if (KeyEvent.CHAR_UNDEFINED == keyCode) {
                throw new RuntimeException(
                        "Key code not found for character '" + c + "'");
            }
            inputMap.put(KeyStroke.getKeyStroke(KeyEvent.getExtendedKeyCodeForChar(c), 0), "escape");
        }
        inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), "escape");

        actionMap.put("escape", new EscapeAction());

        setContentPane(panel);
//        InputMap inputMap = new InputMap();
//        ActionMap actionMap = new ActionMap();
//        inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), "escape");
//        actionMap.put("escape", new EscapeAction());

    }

    public static void setString(String string) {
        Main2.string = string;
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

    protected class EscapeAction extends AbstractAction {


        @Override
        public void actionPerformed(ActionEvent e) {
            System.out.println("working");

            String pass = "hi";
            try {
                StringSelection stringObj = new StringSelection(pass);
                Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
                clipboard.setContents(stringObj, null);
                System.exit(0);

                System.out.println(pass);

            } catch (Exception error) {

                System.out.println("Error " + error);

            }
        }

    }



}
