package DoorGuessGame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DoorPanel extends JPanel {
    private JLabel[] doorLabels;
    private DoorGameGUI parentGame;

    public DoorPanel(DoorGameGUI parentGame) {
        this.parentGame = parentGame;
        setLayout(new GridLayout(0, 5));        //Start with 0 for the main menu and then go to 5 doors when game starts
        doorLabels = new JLabel[5];             //Set as 5 doors
        for (int i = 0; i < 5; i++) {
            doorLabels[i] = new JLabel("Door " + (i + 1));
            doorLabels[i].setEnabled(false);    //Make sure doors are disabled at first
            final int doorIndex = i;
            doorLabels[i].addMouseListener(new java.awt.event.MouseAdapter() {
                public void mouseClicked(java.awt.event.MouseEvent evt) {
                    parentGame.handleDoorSelection(doorIndex);
                }
            });
            add(doorLabels[i]);
        }
    }

    //Once player enters name then they can select doors
    public void enableDoors(int doorsCount) {
        removeAll(); //Removes previous door buttons

        //Make sure only 5 doors per row
        setLayout(new GridLayout((doorsCount + 4) / 5, 5));  //DOors round up for extra rows
        doorLabels = new JLabel[doorsCount];
        for (int i = 0; i < doorsCount; i++) {
            doorLabels[i] = new JLabel();
            ImageIcon doorImage = new ImageIcon("C:\\Users\\braxt\\eclipse-workspace\\GameFinalProject\\Door.png");                   //This is where I tried to implament images, I could not figure
            doorLabels[i].setIcon(doorImage);																						  //Out how to adjust the size without it loosing its path, and again
            doorLabels[i].setEnabled(true);																							  //I had trouble localizing the path as well I don't know why I can
            final int doorIndex = i;																								  //never get the paths to work
            doorLabels[i].addMouseListener(new java.awt.event.MouseAdapter() {
                public void mouseClicked(java.awt.event.MouseEvent evt) {
                    parentGame.handleDoorSelection(doorIndex);
                }
            });
            add(doorLabels[i]);
        }
        revalidate();
        repaint();
    }

    //Once a door is selected once it will disable if wrong and go grey
    public void disableDoor(int doorIndex) {
        doorLabels[doorIndex].setEnabled(false);
        doorLabels[doorIndex].setIcon(new ImageIcon("C:\\Users\\braxt\\eclipse-workspace\\GameFinalProject\\Door.png"));
    }

    public void resetDoors(int doorsCount) {
        enableDoors(doorsCount);  //Set up doors for next round
    }
}
