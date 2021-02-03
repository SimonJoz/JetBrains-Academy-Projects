package flashcards.app;

import flashcards.app.controller.MainControl;

public class Main {
    public static void main(String[] args) {
        MainControl mainControl = new MainControl();
        mainControl.mainLoop(args);
    }
}

