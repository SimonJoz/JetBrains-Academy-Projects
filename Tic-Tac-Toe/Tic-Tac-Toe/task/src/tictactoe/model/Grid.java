package tictactoe.model;


public class Grid {
    private static final String EMPTY = " ";
    private final String[][] grid;

    public Grid() {
        grid = new String[][]{
                {EMPTY, EMPTY, EMPTY},
                {EMPTY, EMPTY, EMPTY},
                {EMPTY, EMPTY, EMPTY}
        };
    }

    public boolean isCellOccupied(int row, int column) {
        return !grid[row][column].equals(EMPTY);
    }

    public int getColumn(String coordinates) {
        int column = Integer.parseInt(coordinates.substring(0, 1));
        return correctColumnNo(column);
    }

    public int getRow(String coordinates) {
        int row = Integer.parseInt(coordinates.substring(2));
        return correctRowNo(row);
    }

    public String[][] getGrid() {
        return grid;
    }

    public void printGrid() {
        System.out.println("---------");
        for (String[] row : grid) {
            System.out.print("| ");
            for (String value : row) {
                System.out.print(value + EMPTY);
            }
            System.out.println("|");
        }
        System.out.println("---------");
    }

    ////////////////////////////////////////////////////
    // coordinates start with 1 and can be 1, 2 or 3. //
    //    first number -- column from left to right   //
    //       second -- row from bottom up             //
    //           ----------------------               //
    //           |(1, 3) (2, 3) (3, 3)|               //
    //           |(1, 2) (2, 2) (3, 2)|               //
    //           |(1, 1) (2, 1) (3, 1)|               //
    //           ----------------------               //
    ////////////////////////////////////////////////////

    private int correctRowNo(int row) {
        if (row == 1) row = 2;
        else if (row == 2) row = 1;
        else if (row == 3) row = 0;
        return row;
    }

    private int correctColumnNo(int column) {
        return column - 1;
    }
}
