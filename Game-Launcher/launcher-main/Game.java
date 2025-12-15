// Game.java
public class Game {
    private String name;
    private String folderPath;
    private String thumbnailName;
    
    public Game(String name, String folderPath, String thumbnailName) {
        this.name = name;
        this.folderPath = folderPath;
        this.thumbnailName = thumbnailName;
    }
    
    public String getName() { return name; }
    public String getFolderPath() { return folderPath; }
    public String getThumbnailName() { return thumbnailName; }
}