package flashcards.app.IO;


import flashcards.app.model.Definition;

import java.util.Scanner;

import static flashcards.app.model.Log.addToLog;

public class DataReader {

    /*
     *
     *   Most of methods could be replaced by single method e.g. readInput(text).
     *   Split only for clarity and better readability of controller classes.
     *
     */

    private final Scanner sc = new Scanner(System.in);

    public String readString() {
        String input = sc.nextLine();
        addToLog(input);
        return input;
    }

    public int readNoOfQuestions() {
        String output = "How many times to ask?";
        System.out.println(output);
        int number = sc.nextInt();
        sc.nextLine();
        addToLog(output);
        addToLog(String.valueOf(number));
        return number;
    }

    public void scClose() {
        String output = "Bye Bye!";
        System.out.println(output);
        addToLog(output);
        sc.close();
    }

    public Definition readCardDefinition() {
        String output = "The definition of the card:";
        System.out.println(output);
        String description = sc.nextLine();
        addToLog(output);
        addToLog(description);
        return new Definition(description, Definition.INITIAL_COUNT);
    }

    public String readCardName() {
        String output = "The card: ";
        System.out.println(output);
        String input = sc.nextLine();
        addToLog(output);
        addToLog(input);
        return input;
    }

    public String readFileName() {
        String output = "File name:";
        System.out.println(output);
        String input = sc.nextLine();
        addToLog(output);
        addToLog(input);
        return input;
    }

    public String readOptionChoice() {
        String output = "Input the action (add, remove, import, export, ask, exit, log, hardest card, reset stats):";
        System.out.println(output);
        String input = sc.nextLine();
        addToLog(output);
        addToLog(input);
        return input;
    }
}
