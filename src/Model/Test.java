package Model;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Test implements KeyListener {
    public static Thread thread;

    public Test() {
        thread = new Thread(Test::run);
        thread.start();
    }

    public static void run() {
        try {
            while (true) {
                Thread.sleep(1000);
                System.out.println("Working");
                thread.interrupt();
            }
        } catch (final InterruptedException e) {
            System.out.println("interrupted");
            return;
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {
        int keyCode = e.getKeyCode();
            System.out.println("working 2");
            thread.interrupt();
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int keyCode = e.getKeyCode();
            System.out.println("working 2");
            thread.interrupt();
    }

    @Override
    public void keyReleased(KeyEvent e) {
        int keyCode = e.getKeyCode();
            System.out.println("working 2");
            thread.interrupt();
    }
}
