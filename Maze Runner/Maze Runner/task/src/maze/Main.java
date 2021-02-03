package maze;

import java.util.Random;
import java.util.Scanner;

public class Main {
    public static final int ONE = 1;
    public static final int ZERO = 0;
    public static final String PASS = "  ";
    public static final String WALL = "\u2588\u2588";


    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Please, enter the size of a maze");
        int rowNumb = sc.nextInt();
        int colNumb = sc.nextInt();
        int[][] maze = new int[rowNumb][colNumb];

        final int LAST_COL = colNumb - ONE;
        final int LAST_ROW = rowNumb - ONE;

        Random random = new Random();
        int entry = random.nextInt((LAST_ROW - ONE)) + ONE;
        int exit = random.nextInt((LAST_ROW - ONE)) + ONE;

        createFrame(maze);

        maze[entry][ZERO] = ZERO;
        maze[exit][LAST_COL] = ZERO;
        maze[entry][ZERO + ONE] = ZERO;
        maze[exit][LAST_COL - ONE] = ZERO;

        printMaze(maze);


    }

    private static void createFrame(int[][] maze) {
        final int rowLen = maze.length;
        final int colLen = maze[ZERO].length;
        for (int row = ZERO; row < rowLen; row++) {
            for (int col = ZERO; col < colLen; col++) {
                if (row == rowLen - ONE || row == ZERO
                        || col == colLen - ONE || col == ZERO) {
                    maze[row][col] = ONE;
                }
            }
        }
    }

    private static void printMaze(int[][] numbs) {
        for (int[] numb : numbs) {
            for (int col : numb) {
                if (col == ONE) {
                    System.out.print(WALL);
                } else {
                    System.out.print(PASS);
                }
            }
            System.out.println();
        }
    }
}
