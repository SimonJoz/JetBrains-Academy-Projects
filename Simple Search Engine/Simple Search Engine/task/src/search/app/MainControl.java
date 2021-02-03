package search.app;

import search.dataBase.PeopleData;
import search.dataReader.DataReader;
import search.dataReader.file.FileManager;

import java.util.Arrays;
import java.util.InputMismatchException;

public class MainControl {
    private final DataReader reader = new DataReader();
    private final PeopleData peopleData = new PeopleData();
    private final SearchControl searchControl = new SearchControl(reader, peopleData);
    private final FileManager fileManager = new FileManager();

    public void mainLoop(String[] args) {
        if (args.length > 1) {
            importAndAddToDB(args[1]);
        } else {
            readPeopleAndAddToDB();
        }
        Options choice;
        do {
            Options.printOptions();
            choice = getOption();
            switch (choice) {
                case SEARCH:
                    search();
                    break;
                case PRINT:
                    printPeople();
                    break;
                case EXIT:
                    exit();
                    break;
                case NO_SUCH_OPTION:
                    System.out.println(Options.NO_SUCH_OPTION.getDescription());
                    break;
            }

        } while (!choice.equals(Options.EXIT));
    }

    private void importAndAddToDB(String arg) {
        peopleData.getPeopleList().addAll(fileManager.importData(arg));
        peopleData.mapWordsWithIndex();
    }

    private void printPeople() {
        System.out.println("\n=== List of people ===");
        peopleData.printPeopleList();
    }

    private void search() {
      searchControl.chooseSearchOption();
    }

    private void exit() {
        reader.scClose();
        System.out.println("Bye!");
    }

    private void readPeopleAndAddToDB() {
        boolean isInputOk = false;
        while (!isInputOk) {
            try {
                reader.readPeople(peopleData.getPeopleList());
                peopleData.mapWordsWithIndex();
                isInputOk = true;
            } catch (InputMismatchException e) {
                System.out.println("Wrong input type ! Number expected.");
            }
        }
    }

    private Options getOption() {
        Options option = null;
        boolean isInputOk = false;
        while (!isInputOk) {
            try {
                option = Options.getOptionById(reader.readInt());
                isInputOk = true;
            } catch (InputMismatchException e) {
                System.out.println("Unexpected input. To choose an option type number !");
            }
        }
        return option;
    }

    private enum Options {
        SEARCH(1, "Find a person."),
        PRINT(2, "Print all people."),
        EXIT(0, "Exit."),
        NO_SUCH_OPTION(3, "Incorrect option! Try again.");


        private int id;
        private String description;

        Options(int id, String description) {
            this.id = id;
            this.description = description;
        }

        public static Options getOptionById(int id) {
            return Arrays.stream(Options.values())
                    .filter(v -> v.getId() == id)
                    .findFirst()
                    .orElse(Options.NO_SUCH_OPTION);
        }

        public static void printOptions() {
            System.out.println("\n=== Menu ===");
            Arrays.stream(values())
                    .filter(v -> !v.equals(NO_SUCH_OPTION))
                    .forEach(v -> System.out.printf
                            ("%d. %s\n", v.getId(), v.getDescription()));
        }

        public int getId() {
            return id;
        }

        public String getDescription() {
            return description;
        }
    }
}
