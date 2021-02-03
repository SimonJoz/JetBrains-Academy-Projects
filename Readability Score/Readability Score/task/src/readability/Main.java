package readability;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static java.lang.String.format;

public class Main {

    public static final String SPACE = " ";

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String text;
        if (args.length > 0) {
            text = importText(format("%s", args[0]));
        } else {
            System.out.println("Enter path to text file: ");
            text = importText(scanner.nextLine());
        }
        if (!text.equals("")) {
            float sentences = text.split("[.!?]").length;
            float chars = text.replaceAll("\\s", "").length();
            float words = removePunctuation(text).split("\\s+").length;
            float syllables = getSyllables(text);
            float polysyllables = getPolysyllables(text);
            float L = chars / words * 100;
            float S = sentences / words * 100;
            float scoreCL = 0.0588F * L - 0.296F * S - 15.8F;
            float scoreSMOG = 1.043F * (float) Math.sqrt(polysyllables * (30 / sentences)) + 3.1291F;
            float scoreARI = 4.71F * chars / words + 0.5F * words / sentences - 21.43F;
            float scoreFK = 0.39F * words / sentences + 11.8F * syllables / words - 15.59F;

            System.out.printf("Words: %d\nSentences: %d\nCharacters: %d\nSyllables: %d\nPolysyllables: %d\n",
                    (int) words, (int) sentences, (int) chars, (int) syllables, (int) polysyllables);

            String choice = "";
            while (!isInputOk(choice)) {
                System.out.println("Enter the score you want to calculate (ARI, FK, SMOG, CL, ALL):");
                choice = scanner.nextLine().toUpperCase();
                switch (choice) {
                    case "ARI" -> System.out.printf("Automated Readability Index: %s\n", scoreAndAge(scoreARI));
                    case "FK" -> System.out.printf("Flesch–Kincaid readability tests: %s\n", scoreAndAge(scoreFK));
                    case "SMOG" -> System.out.printf("Simple Measure of Gobbledygook: %s\n", scoreAndAge(scoreSMOG));
                    case "CL" -> System.out.printf("Coleman–Liau index: %s\n", scoreAndAge(scoreCL));
                    case "ALL" -> {
                        System.out.printf("Automated Readability Index: %s\n", scoreAndAge(scoreARI));
                        System.out.printf("Flesch–Kincaid readability tests: %s\n", scoreAndAge(scoreFK));
                        System.out.printf("Simple Measure of Gobbledygook: %s\n", scoreAndAge(scoreSMOG));
                        System.out.printf("Coleman–Liau index: %s\n", scoreAndAge(scoreCL));
                        float averageAge = (getAge(scoreARI) + getAge(scoreFK) + getAge(scoreSMOG) + getAge(scoreCL)) / 4;
                        System.out.printf("This text should be understood in average by %.2f year olds.", averageAge);
                    }
                    default -> System.err.println("UNKNOWN OPTION TRY AGAIN.");
                }
            }
        }
    }

    private static boolean isInputOk(String choice) {
        return choice.matches("ARI|FK|SMOG|CL|ALL");
    }

    private static String scoreAndAge(float score) {
        return format("%.2f (about %d year olds).", score, Math.round(getAge(score)));
    }

    private static String removePunctuation(String text) {
        return text.replaceAll("[.,?!]", "");
    }

    private static String importText(String filePath) {
        StringBuilder sb = new StringBuilder();
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            br.lines().forEach(line -> sb.append(line).append(SPACE));
        } catch (IOException e) {
            System.out.println("File not found !");
        }
        return sb.toString().trim();
    }

    private static long getPolysyllables(String text) {
        return Arrays.stream(removePunctuation(text).split(SPACE))
                .filter(word -> countVowels(word) > 2)
                .count();
    }

    private static int getSyllables(String text) {
        return Arrays.stream(removePunctuation(text).split(SPACE))
                .mapToInt(Main::countVowels)
                .sum();
    }

    private static int countVowels(String word) {
        final String regex = "(?i)[aeiouy][^aeiouy\\s]|[aiouy]$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(word);
        int count = (int) matcher.results().count();
        return Math.max(count, 1);
    }

    private static float getAge(float score) {
        return switch (Math.round(score)) {
            case 1 -> 6;
            case 2 -> 7;
            case 3 -> 9;
            case 4 -> 10;
            case 5 -> 11;
            case 6 -> 12;
            case 7 -> 13;
            case 8 -> 14;
            case 9 -> 15;
            case 10 -> 16;
            case 11 -> 17;
            case 12 -> 18;
            case 13, 14 -> 24;
            default -> 0;
        };
    }
}
