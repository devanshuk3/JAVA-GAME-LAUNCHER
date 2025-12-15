// App.java - Place this in the same pacman/src/pacman folder
import javax.swing.*;

public class App {
    public static void main(String[] args) {
        // Create the game window
        JFrame frame = new JFrame("PacMan");
        PacMan pacmanGame = new PacMan();
        
        frame.add(pacmanGame);
        frame.pack();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        
        // Ensure the game has focus for keyboard input
        pacmanGame.requestFocusInWindow();
    }
}