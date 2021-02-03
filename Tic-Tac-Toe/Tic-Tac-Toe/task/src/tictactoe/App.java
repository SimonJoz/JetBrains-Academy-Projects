package tictactoe;


import tictactoe.controller.GridControl;

public class App {
    public static void main(String[] args) {
        GridControl controller = new GridControl();
        controller.mainLoop();
    }
}
