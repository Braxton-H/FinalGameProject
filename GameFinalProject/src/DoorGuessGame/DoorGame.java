package DoorGuessGame;

import java.util.Random;

public class DoorGame {
    private int correctDoor;
    private int doorsCount;  //Number of doors in current round
    private int doorsOpened; //Number of doors opened in this round
    private int totalDoorsOpened; //Number of doors opened in the whole game

    public DoorGame(int doorsCount) {
        this.doorsCount = doorsCount;
        this.doorsOpened = 0;
        this.totalDoorsOpened = 0;
        generateCorrectDoor();
    }

    //Randomly pick the correct door
    public void generateCorrectDoor() {
        Random random = new Random();
        correctDoor = random.nextInt(doorsCount); 
    }

    public int getCorrectDoor() {
        return correctDoor;
    }

    public int getDoorsCount() {
        return doorsCount;
    }

    public int getTotalDoorsOpened() {
        return totalDoorsOpened;
    }

    //Check too see if its the right door
    public void checkDoorSelection(int selectedDoor) throws CustomExceptions.WrongDoorException {
        doorsOpened++;  //If a door was opened incrament the doors
        totalDoorsOpened++; //Incrament the total doors opened in the whole game
        if (selectedDoor != correctDoor) {
            throw new CustomExceptions.WrongDoorException("Wrong Door! Try again.");
        }
    }

    //Incrament the doors open for the round
    public void incrementRound() {
        if (doorsCount < 10) {
            doorsCount++; //Add another door to the next round
        }
        doorsOpened = 0; //Reset doors opened for next round
        generateCorrectDoor();  //Make a new correct door
    }
}
