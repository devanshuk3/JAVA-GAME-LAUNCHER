import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class GameLauncher extends JFrame {
    private static final String PARENT_FOLDER = System.getProperty("user.home") + "\\OneDrive\\Desktop\\ITR PROJECT";
    private static final String THUMBS_FOLDER = PARENT_FOLDER + "\\thumbs";
    private JPanel gamesPanel;
    private List<Game> games;

    public GameLauncher() {
        setTitle("Game Launcher - Steam Style");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1200, 800);
        setLocationRelativeTo(null);

        File folder = new File(PARENT_FOLDER);
        if (!folder.exists()) {
            JOptionPane.showMessageDialog(this,
                    "ITR PROJECT folder not found at: " + PARENT_FOLDER + "\n\nPlease make sure the folder exists.",
                    "Folder Not Found",
                    JOptionPane.ERROR_MESSAGE);
            System.exit(1);
        }

        File thumbsFolder = new File(THUMBS_FOLDER);
        if (!thumbsFolder.exists()) {
            System.out.println("Thumbs folder not found: " + THUMBS_FOLDER);
        } else {
            System.out.println("Thumbs folder found: " + THUMBS_FOLDER);
        }

        initializeGames();
        createUI();
    }

    private void initializeGames() {
        games = new ArrayList<>();

        addGameIfExists("Flappy Bird", "flappybird\\src\\flappy_bird", "flappybird.png");
        addGameIfExists("PacMan", "pacman\\src\\pacman", "pacman.jpg");
        addGameIfExists("Snake", "snake\\src\\snake", "snake.png");
        addGameIfExists("Space Invaders", "space_invaders\\src\\space_invaders", "spaceinvaders.png");
        addGameIfExists("Tic Tac Toe", "tictactoe\\src\\tictactoe", "tictactoe.jpg");
        addGameIfExists("Whac-a-Mole", "whac-a-mole\\src\\whac_a_mole", "whackamole.png");

        System.out.println("Found " + games.size() + " games");
    }

    
    private void addGameIfExists(String gameName, String path, String thumbnailName) {
        File gameFolder = new File(PARENT_FOLDER, path);
        if (gameFolder.exists() && gameFolder.isDirectory()) {
            if (hasJavaFiles(gameFolder)) {
                games.add(new Game(gameName, path, thumbnailName));
                System.out.println("✓ Added: " + gameName + " at " + path);
            } else {
                System.out.println("✗ No Java files in: " + gameName + " at " + path);
            }
        } else {
            System.out.println("✗ Folder not found: " + gameName + " at " + path);
        }
    }

    private boolean hasJavaFiles(File folder) {
        File[] javaFiles = folder.listFiles((dir, name) -> name.toLowerCase().endsWith(".java"));
        return javaFiles != null && javaFiles.length > 0;
    }

    private void createUI() {
        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBackground(new Color(28, 33, 39));
        mainPanel.setBorder(new EmptyBorder(20, 20, 20, 20));

        JLabel headerLabel = new JLabel("GAME LAUNCHER - " + games.size() + " Games Available", SwingConstants.CENTER);
        headerLabel.setFont(new Font("Arial", Font.BOLD, 28));
        headerLabel.setForeground(Color.WHITE);
        headerLabel.setBorder(new EmptyBorder(0, 0, 30, 0));

        int columns = Math.min(4, games.size());
        gamesPanel = new JPanel(new GridLayout(0, columns, 20, 20));
        gamesPanel.setBackground(new Color(28, 33, 39));
        gamesPanel.setBorder(new EmptyBorder(20, 0, 20, 0));

        for (Game game : games) {
            gamesPanel.add(createGameCard(game));
        }

        JScrollPane scrollPane = new JScrollPane(gamesPanel);
        scrollPane.setBackground(new Color(28, 33, 39));
        scrollPane.getViewport().setBackground(new Color(28, 33, 39));
        scrollPane.setBorder(BorderFactory.createEmptyBorder());
        scrollPane.getVerticalScrollBar().setUnitIncrement(16);

        mainPanel.add(headerLabel, BorderLayout.NORTH);
        mainPanel.add(scrollPane, BorderLayout.CENTER);

        add(mainPanel);
    }

    private JPanel createGameCard(Game game) {
        JPanel card = new JPanel();
        card.setLayout(new BorderLayout());
        card.setBackground(new Color(42, 49, 57));
        card.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(62, 69, 77), 2),
                BorderFactory.createEmptyBorder(10, 10, 10, 10)));
        card.setPreferredSize(new Dimension(250, 300));

        JPanel thumbnailPanel = new JPanel(new BorderLayout());
        thumbnailPanel.setBackground(new Color(42, 49, 57));
        thumbnailPanel.setPreferredSize(new Dimension(230, 180));

        ImageIcon thumbnail = loadThumbnail(game);
        JLabel thumbnailLabel;

        if (thumbnail != null) {
            thumbnailLabel = new JLabel(thumbnail);
            thumbnailLabel.setHorizontalAlignment(SwingConstants.CENTER);
        } else {
            thumbnailLabel = new JLabel("<html><center>" + game.getName() + "</center></html>", SwingConstants.CENTER);
            thumbnailLabel.setForeground(Color.LIGHT_GRAY);
            thumbnailLabel.setFont(new Font("Arial", Font.BOLD, 16));
            thumbnailLabel.setOpaque(true);
            thumbnailLabel.setBackground(new Color(35, 41, 48));
            thumbnailLabel.setBorder(BorderFactory.createLineBorder(new Color(62, 69, 77), 1));
        }

        thumbnailPanel.add(thumbnailLabel, BorderLayout.CENTER);

        JLa wbel nameLabel = new JLabel(game.getName(), SwingConstants.CENTER);
        nameLabel.setFont(new Font("Arial", Font.BOLD, 16));
        nameLabel.setForeground(Color.WHITE);
        nameLabel.setBorder(new EmptyBorder(10, 5, 5, 5));

        JButton playButton = new JButton("PLAY");
        playButton.setBackground(new Color(94, 129, 172));
        playButton.setForeground(Color.WHITE);
        playButton.setFont(new Font("Arial", Font.BOLD, 14));
        playButton.setFocusPainted(false);
        playButton.setBorder(BorderFactory.createEmptyBorder(8, 20, 8, 20));

        playButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                playButton.setBackground(new Color(114, 149, 192));
                card.setBorder(BorderFactory.createCompoundBorder(
                        BorderFactory.createLineBorder(new Color(94, 129, 172), 2),
                        BorderFactory.createEmptyBorder(10, 10, 10, 10)));
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                playButton.setBackground(new Color(94, 129, 172));
                card.setBorder(BorderFactory.createCompoundBorder(
                        BorderFactory.createLineBorder(new Color(62, 69, 77), 2),
                        BorderFactory.createEmptyBorder(10, 10, 10, 10)));
            }
        });

        playButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                launchGame(game);
            }
        });

        JPanel buttonPanel = new JPanel();
        buttonPanel.setBackground(new Color(42, 49, 57));
        buttonPanel.add(playButton);

        card.add(thumbnailPanel, BorderLayout.CENTER);
        card.add(nameLabel, BorderLayout.NORTH);
        card.add(buttonPanel, BorderLayout.SOUTH);

        return card;
    }

    private ImageIcon loadThumbnail(Game game) {
        String[] possiblePaths = {
                THUMBS_FOLDER + "\\" + game.getThumbnailName(),
                PARENT_FOLDER + "\\" + game.getFolderPath() + "\\" + game.getThumbnailName(),
                PARENT_FOLDER + "\\" + game.getFolderPath() + "\\thumbnail.png",
                PARENT_FOLDER + "\\" + game.getFolderPath() + "\\thumbnail.jpg",
                PARENT_FOLDER + "\\" + game.getFolderPath() + "\\screenshot.png",
                PARENT_FOLDER + "\\" + game.getFolderPath() + "\\screenshot.jpg",
                PARENT_FOLDER + "\\" + game.getFolderPath() + "\\icon.png",
                PARENT_FOLDER + "\\" + game.getFolderPath() + "\\icon.jpg"
        };

        for (String path : possiblePaths) {
            File imageFile = new File(path);
            if (imageFile.exists()) {
                try {
                    System.out.println("Loading thumbnail from: " + path);
                    ImageIcon originalIcon = new ImageIcon(path);
                    Image originalImage = originalIcon.getImage();
                    Image scaledImage = originalImage.getScaledInstance(230, 180, Image.SCALE_SMOOTH);
                    return new ImageIcon(scaledImage);
                } catch (Exception e) {
                    System.out.println("Error loading thumbnail: " + path);
                }
            }
        }

        System.out.println("No thumbnail found for: " + game.getName());
        return null;
    }

    private void launchGame(Game game) {
        String gamePath = PARENT_FOLDER + "\\" + game.getFolderPath();
        File gameFolder = new File(gamePath);

        if (!gameFolder.exists()) {
            JOptionPane.showMessageDialog(this,
                    "Game directory not found:\n" + gamePath,
                    "Directory Not Found",
                    JOptionPane.ERROR_MESSAGE);
            return;
        }

        try {
            String mainClassName = findMainClass(gameFolder);

            boolean success = compileAndRunGame(gameFolder, mainClassName, game.getName());

            if (!success) {
                showManualInstructions(game, gamePath, mainClassName);
            }

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this,
                    "Error launching game: " + ex.getMessage() + "\n\nPlease try manual compilation.",
                    "Launch Error",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    private String findMainClass(File gameFolder) {
        String[] possibleMainClasses = { "App", "Main", "Game", "Launcher", "Start" };

        File[] classFiles = gameFolder.listFiles((dir, name) -> name.toLowerCase().endsWith(".class"));
        if (classFiles != null) {
            for (File classFile : classFiles) {
                String className = classFile.getName().replace(".class", "");
                for (String possibleClass : possibleMainClasses) {  
                    if (className.equalsIgnoreCase(possibleClass)) {
                        return className;
                    }
                }
            }
        }

        File[] javaFiles = gameFolder.listFiles((dir, name) -> name.toLowerCase().endsWith(".java"));
        if (javaFiles != null) {
            for (File javaFile : javaFiles) {
                String className = javaFile.getName().replace(".java", "");
                for (String possibleClass : possibleMainClasses) {
                    if (className.equalsIgnoreCase(possibleClass)) {
                        return className;
                    }
                }
            }
        }

        return "App";
    }

    private boolean compileAndRunGame(File gameFolder, String mainClassName, String gameName) {
        try {
            JOptionPane.showMessageDialog(this,
                    "Compiling " + gameName + "...\nPlease wait...",
                    "Compiling",
                    JOptionPane.INFORMATION_MESSAGE);

            ProcessBuilder compilePb = new ProcessBuilder("javac", "*.java");
            compilePb.directory(gameFolder);
            compilePb.redirectErrorStream(true);

            Process compileProcess = compilePb.start();
            int compileResult = compileProcess.waitFor();

            if (compileResult != 0) {
                return false;
            }

            ProcessBuilder runPb = new ProcessBuilder("java", mainClassName);
            runPb.directory(gameFolder);
            runPb.redirectErrorStream(true);

            Process runProcess = runPb.start();

            Thread.sleep(3000);

            if (runProcess.isAlive()) {
                JOptionPane.showMessageDialog(this,
                        gameName + " is now running!\n\nThe game window should appear shortly.",
                        "Game Started",
                        JOptionPane.INFORMATION_MESSAGE);
                return true;
            } else {
                return false;
            }

        } catch (Exception e) {
            System.err.println("Error compiling/running game: " + e.getMessage());
            return false;
        }
    }

    private void showManualInstructions(Game game, String gamePath, String mainClassName) {
        String message = "Game: " + game.getName() + "\n" +
                "Path: " + gamePath + "\n" +
                "Main Class: " + mainClassName + "\n\n" +
                "To run manually:\n" +
                "1. Open Command Prompt\n" +
                "2. Navigate to: cd \"" + gamePath + "\"\n" +
                "3. Compile: javac *.java\n" +
                "4. Run: java " + mainClassName + "\n\n" +
                "Make sure Java Development Kit (JDK) is installed.";

        JOptionPane.showMessageDialog(this,
                message,
                "Manual Instructions - " + game.getName(),
                JOptionPane.INFORMATION_MESSAGE);
    }

    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }

        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new GameLauncher().setVisible(true);
            }
        });
    }
}