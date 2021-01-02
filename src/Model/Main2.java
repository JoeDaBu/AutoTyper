package Model;
import javax.swing.*;
import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.util.Map;

public class Main2 extends JFrame implements ActionListener {

    public static AutoType2 instance;
    public static Robot robot;
    public static Map<String, Integer> toType;
    public static Thread thread;
    public static String string;
    public static Integer delay;

    private static JButton start;
    private static JButton stop;
    private static Boolean inFocus = false;
    private static JPanel panel;

//creates the JFrame and initiates Main2
    public static void main(String[] args) {
                Main2 frame = new Main2("-np", 1000);
                frame.setTitle("Copier");
                frame.setResizable(true);
                frame.setExtendedState(JFrame.MAXIMIZED_BOTH);

                start.setFont(new Font("Arial", Font.PLAIN, JFrame.MAXIMIZED_HORIZ*200));
                stop.setFont(new Font("Arial", Font.PLAIN, JFrame.MAXIMIZED_HORIZ*200));
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.setVisible(true);
                frame.setLayout(new GridLayout(2,1));
    }

    //the robot string typer, sent to another thread
    public void start() {
            thread = new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        Thread.sleep(2000);
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
                            Thread.sleep(delay);
                        }
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                }
            });
            thread.start();
        }

        //sets up the display and stop keys and the button
    public Main2(String s, Integer delay) {
        this.delay = delay;
        this.string = s;
        try {
            robot = new Robot();
        } catch (AWTException e) {
            System.out.println("failed to create robot");
        }
        start = new JButton();
        start.addActionListener(this);
        start.setText("Start");
        start.setBounds(100,100,100,100);
        start.setFocusable(true);
        start.setBackground(new Color(0, 255, 37));
        start.setVisible(true);

        stop = new JButton();
        stop.addActionListener(this);
        stop.setText("Stop");
        stop.setBounds(100,100,100,100);
        stop.setFocusable(true);
        stop.setBackground(new Color(255, 0, 0));
        stop.setVisible(true);

        panel = new JPanel(new GridLayout(2,1));
        InputMap inputMap = panel.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW);
        ActionMap actionMap = panel.getActionMap();
        panel.add(start);
        panel.add(stop);
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

    //robot key typer shortcut
    private static void robotPressKey(Robot robot, int vk) {
        robot.keyPress(vk);
        robot.keyRelease(vk);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == start) {
            start();
        } else if (e.getSource() == stop) {
            thread.interrupt();
        }
    }

    //stops the typer
    protected class EscapeAction extends AbstractAction {


        @Override
        public void actionPerformed(ActionEvent e) {
            System.out.println("working");

            String pass = "hi";
            try {
                StringSelection stringObj = new StringSelection(pass);
                Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
                clipboard.setContents(stringObj, null);
                thread.interrupt();

                System.out.println(pass);

            } catch (Exception error) {

                System.out.println("Error " + error);

            }
        }

    }



}
