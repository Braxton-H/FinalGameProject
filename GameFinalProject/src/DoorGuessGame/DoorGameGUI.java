package DoorGuessGame;

import javax.swing.*;
import java.awt.*;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class DoorGameGUI extends JFrame {
    private Player player;
    private DoorGame doorGame;
    private NameEntryPanel nameEntryPanel;
    private DoorPanel doorPanel;
    private MessagePanel messagePanel;

    public DoorGameGUI() {
        //Set up the window frame
        setTitle("Door Game");
        setSize(500, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        //Initialize components for game
        nameEntryPanel = new NameEntryPanel(this);
        doorPanel = new DoorPanel(this);
        messagePanel = new MessagePanel();

        //Add components to the frame
        add(nameEntryPanel, BorderLayout.NORTH);
        add(doorPanel, BorderLayout.CENTER);
        add(messagePanel, BorderLayout.SOUTH);
    }
    
    private void saveHighScore(String playerName, int doorsOpened) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("C:\\Users\\braxt\\eclipse-workspace\\GameFinalProject\\HighScores.txt", true))) {
            writer.write(playerName + " - Doors Opened: " + doorsOpened);
            writer.newLine();
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "Error saving high score: " + e.getMessage());
        }
    }
    
    private void displayHighScores() {
        StringBuilder highScores = new StringBuilder("High Scores:\n");
        try (BufferedReader reader = new BufferedReader(new FileReader("C:\\Users\\braxt\\eclipse-workspace\\GameFinalProject\\HighScores.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                highScores.append(line).append("\n");
            }
        } catch (IOException e) {
            highScores.append("Error reading high scores: ").append(e.getMessage());
        }

        JOptionPane.showMessageDialog(this, highScores.toString());
    }

    //Enable game after name entered
    public void switchToGameMode() {
        doorGame = new DoorGame(5); //Start with 5 doors
        messagePanel.setMessage("Welcome, " + player.getName() + "! Choose a door.");
        nameEntryPanel.setVisible(false);
        doorPanel.setVisible(true);
        doorPanel.enableDoors(doorGame.getDoorsCount());
    }

    // Handle door selection
    public void handleDoorSelection(int selectedDoor) {
        if (doorGame == null) {
            messagePanel.setMessage("Please enter your name first before starting.");
            return;
        }
        
        try {
            doorGame.checkDoorSelection(selectedDoor);
            messagePanel.setMessage("You picked the correct door, Next Round!");
            int doorsOpened = doorGame.getTotalDoorsOpened();
            if (doorGame.getDoorsCount() == 10) {
                endGame(doorsOpened);
            } else {
                doorGame.incrementRound();
                doorPanel.resetDoors(doorGame.getDoorsCount());
                messagePanel.setMessage("Round " + (doorGame.getDoorsCount()) + ": Choose a door.");
            }
        } catch (CustomExceptions.WrongDoorException ex) {
            messagePanel.setMessage(ex.getMessage());
            doorPanel.disableDoor(selectedDoor);
        }
    }

    //Set the player after name is entered
    public void setPlayer(Player player) {
        this.player = player;
    }

    //End the game after round 10 and show the total doors opened
    private void endGame(int doorsOpened) {
        String message = "Game Over! You opened a total of " + doorsOpened + " doors. Would you like to restart?";
        int option = JOptionPane.showOptionDialog(this, message, "Game Over",
                JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, null, null);

        //Save the high score when the game ends
        saveHighScore(player.getName(), doorsOpened);

        if (option == JOptionPane.YES_OPTION) {
            restartGame();
        } else {
            System.exit(0);
        }
    }

    //Restart the game to initial start
    private void restartGame() {
        nameEntryPanel.setVisible(true);
        doorPanel.setVisible(false);
        messagePanel.setMessage("Please enter your name to begin.");
    }

    public static void main(String[] args) {
        //Run the GUI in the Event-Dispatching thread
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                DoorGameGUI game = new DoorGameGUI();
                game.setVisible(true);
            }
        });
    }
}
