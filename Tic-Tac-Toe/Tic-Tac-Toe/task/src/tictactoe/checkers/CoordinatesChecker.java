package tictactoe.checkers;

import tictactoe.io.Reader;

public class CoordinatesChecker {
    private final Reader reader;

    public CoordinatesChecker(Reader reader) {
        this.reader = reader;
    }

    public String getCoordinates() {
        String coordinates = "";
        boolean inputOk = false;
        while (!inputOk) {
            coordinates = reader.readCoordinates();
            inputOk = isInputOk(coordinates);
        }
        return coordinates;
    }

    private boolean isInputOk(String coordinates) {
        if (isNumberButOutOfScope(coordinates)) {
            System.err.println("Coordinates should be from 1 to 3!\n(Correct format: \"X X\") ");
            return false;
        } else if (isLetter(coordinates)) {
            System.err.println("Enter numbers please !");
            return false;
        }
        return true;
    }

    private boolean isLetter(String coordinates) {
        return !coordinates.matches("(\\s*\\d+\\s*)+");
    }

    private boolean isNumberButOutOfScope(String coordinates) {
        return !coordinates.matches("([1-3]\\s+[1-3])")
                && !isLetter(coordinates);
    }


}
