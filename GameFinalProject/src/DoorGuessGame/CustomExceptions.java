package DoorGuessGame;

public class CustomExceptions {

    //Custom exception for invalid player name
    public static class InvalidNameException extends Exception {
        public InvalidNameException(String message) {
            super(message);
        }
    }

    //Custom exception for selecting the wrong door
    public static class WrongDoorException extends Exception {
        public WrongDoorException(String message) {
            super(message);
        }
    }

    //Custom exception for any generic game errors
    public static class GameException extends Exception {
        public GameException(String message) {
            super(message);
        }
    }
}
