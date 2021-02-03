package tictactoe.controller;

import tictactoe.checkers.CoordinatesChecker;
import tictactoe.checkers.StatusChecker;
import tictactoe.io.Reader;
import tictactoe.model.Grid;
import tictactoe.model.Players;

public class GridControl {
    private static final int GAME_CYCLES = 9;
    private static String currentPlayer = Players.PLAYER_O;

    private final Reader reader = new Reader();
    private final CoordinatesChecker coordinatesChecker = new CoordinatesChecker(reader);
    private final StatusChecker statChecker = new StatusChecker();
    private final Grid grid = new Grid();

    private final String[][] gameBord = grid.getGrid();
    private int row;
    private int column;
    private boolean cellOccupied;
    private String coordinates;
    private String moves;

    public void mainLoop() {
        printGrid();
        int gameCycles = GAME_CYCLES;
        while (gameCycles > 0) {
            coordinates = getCoordinates();
            row = getRow();
            column = getColumn();
            cellOccupied = isCellOccupied();
            ifCellOccupiedLoop();
            setPlayerOnCell();
            changePlayer();
            printGrid();
            updateMovesLog();
            if (oWin()) break;
            if (xWin()) break;
            gameCycles--;
        }
        ifDrawMsg();
    }

    private void ifCellOccupiedLoop() {
        if (cellOccupied) {
            while (cellOccupied) {
                System.err.println("This cell is occupied! Choose another one!");
                coordinates = getCoordinates();
                row = getRow();
                column = getColumn();
                cellOccupied = isCellOccupied();
        ;    }
        }
    }

    private boolean oWin() {
        if (statChecker.oWin(moves)) {
            System.out.println("O wins !");
            return true;
        }
        return false;
    }

    private boolean xWin() {
        if (statChecker.xWin(moves)) {
            System.out.println("X wins !");
            return true;
        }
        return false;
    }

    private void ifDrawMsg() {
        if (statChecker.isDraw(moves)) System.out.println("Draw !");
    }

    private void updateMovesLog() {
        // current moves to string -- e.g. "X XO  XO".
        moves = getMovesString(gameBord);
    }

    private void printGrid() {
        grid.printGrid();
    }

    private void changePlayer() {
        currentPlayer = Players.changePlayer(currentPlayer);
    }

    private void setPlayerOnCell() {
        gameBord[row][column] = currentPlayer;
    }

    private int getRow() {
        return grid.getRow(coordinates);
    }

    private boolean isCellOccupied() {
        return grid.isCellOccupied(row, column);
    }

    private int getColumn() {
        return grid.getColumn(coordinates);
    }

    private String getCoordinates() {
        return coordinatesChecker.getCoordinates();
    }

    private String getMovesString(String[][] gameBord) {
        return statChecker.getMovesString(gameBord);
    }
}
