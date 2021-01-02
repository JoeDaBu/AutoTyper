//package Model;
//
//import java.awt.event.KeyEvent;
//import java.awt.event.KeyListener;
//
//public class IsKeyPressed2 implements KeyListener {
//    public static Boolean stopPressed = false;
//
//    public IsKeyPressed2() {
//
//    }
//
//    @Override
//    public synchronized void keyTyped(KeyEvent e) {
//        if (e.getKeyCode() == KeyEvent.VK_BACK_SPACE) {
//            AutoType2.stopPressed = true;
//            System.out.println("working");
//        }
//    }
//
//    @Override
//    public synchronized void keyPressed(KeyEvent e) {
//        if (e.getKeyCode() == KeyEvent.VK_BACK_SPACE) {
//            AutoType2.stopPressed = true;
//            System.out.println("working");
//        }
//
//    }
//
//    @Override
//    public synchronized void keyReleased(KeyEvent e) {
//        if (e.getKeyCode() == KeyEvent.VK_BACK_SPACE) {
//            AutoType2.stopPressed = true;
//            System.out.println("working");
//        }
//    }
//
//}
