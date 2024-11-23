package DoorGuessGame;

public class Player {
    private String name;

    //Constructor to set the player's name
    public Player(String name) throws CustomExceptions.InvalidNameException {
        if (!isValidName(name)) {
            throw new CustomExceptions.InvalidNameException("Invalid name. Please enter a name with only letters.");
        }
        this.name = name;
    }

    //Getter for name
    public String getName() {
        return name;
    }

    //Method to validate the name (only letters allowed)
    public static boolean isValidName(String name) {
        return name.matches("[a-zA-Z]+");
    }
}
