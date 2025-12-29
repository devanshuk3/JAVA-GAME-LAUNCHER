# JAVA-GAME-LAUNCHER

A simple **Java-based Game Launcher** — a desktop application to launch, organize, and manage your Java games from one place.  
This project is written entirely in Java and is a great starter project for learning **GUI programming**, **file handling**, and **process execution** in Java.

## 🚀 About

This launcher lets you:

- Add games (executable JARs or other Java game formats)
- Start games from a central UI
- Organize your game list
- Save and load configurations

> No frameworks like Swing/JavaFX are assumed — feel free to integrate whichever UI toolkit you'd like!

## 🧱 Features (Planned / TODO)

✔️ Add games with custom icons  
✔️ Launch games from a GUI  
🟧 Remove games from the list  
🟧 Save launcher configuration to disk  
🟧 Support platform-independent launcher packaging

*(Mark ✔ for implemented features and add more as needed.)*

## 📁 Project Structure

JAVA-GAME-LAUNCHER/
├── src/
│ └── main/java/…
├── resources/
├── .gitignore
├── build.gradle / pom.xml (if applicable)
└── README.md


## 📌 Requirements

To build and run this project:

- **Java JDK 8+**
- Optional: **Gradle or Maven** (if build scripts are added)
- A Java-compatible IDE (IntelliJ IDEA, Eclipse, VS Code, etc.)

## 🛠️ How to Build & Run

### Using Command Line
   ```bash
   git clone https://github.com/devanshuk3/JAVA-GAME-LAUNCHER.git
   cd JAVA-GAME-LAUNCHER
   javac -d bin src/*.java
   java -cp bin Main


