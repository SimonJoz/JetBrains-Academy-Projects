package com.company;

public class ConverterType {
    private static final DataReader reader = new DataReader();

    public void getUserReq() {
        System.out.println("Which converter would you like to use ?\nPlease pic from the list below.");
        System.out.println("[ \"Weight\" | \"Length\" ]\nEnter converter type: ");

        String input = reader.readText();

        switch (input.toLowerCase()) {
            case "weight" -> Weights.weightConverter(reader);
            case "length" -> Length.lengthConverter(reader);
            default -> System.out.println("Command not recognized.. ");
        }
    }
}
