package contacts.controller;

import contacts.enums.Options;
import contacts.enums.RecordType;
import contacts.io.DataReader;
import contacts.model.Person;
import contacts.model.PhoneBook;
import contacts.model.Record;
import contacts.model.RecordFactory;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Map;

import static contacts.enums.Options.*;


public class MainControl {
    private final DataReader reader = DataReader.getInstance();
    private final PhoneBook phoneBook = new PhoneBook();
    private final Map<Options, Runnable> optionsMap = Map.of(
            ADD, this::add, REMOVE, this::remove, EDIT, this::edit,
            COUNT, this::count, INFO, this::info, EXIT, this::exit, WRONG, this::wrongOpt);

    public void mainLoop() {
        Options action;
        do {
            printOptions();
            action = getOption();
            Runnable runnable = optionsMap.get(action);
            runnable.run();
            System.out.println();
        } while (!action.equals(Options.EXIT));
    }

    private void wrongOpt() {
        System.out.println("No such option !");
    }

    private void printOptions() {
        System.out.printf("Enter action (%s): ", Options.getOptions());
    }

    private void add() {
        RecordType recordType = reader.readRecordType();
        Record record = RecordFactory.createRecord(recordType);
        if (phoneBook.addRecord(record)) {
            System.out.println("The record added.");
        }
    }

    private void remove() {
        if (phoneBook.isEmpty()) {
            System.out.println("No records to remove!");
            return;
        }
        int index = selectRecord();
        if (index == -1) return;
        String msg = phoneBook.removeRecord(index) ?
                "The record removed!" : "No such record !";
        System.out.println(msg);
    }

    private void edit() {
        if (phoneBook.isEmpty()) {
            System.out.println("No records to edit!");
            return;
        }
        int index = selectRecord();
        if (index == -1) return;
        reader.editRecord(getRecord(index));
    }

    private Record getRecord(int recordIndex) {
        Record record = phoneBook.getRecord(recordIndex);
        if(record == null){
            System.out.println("No such record !");
        }
        return record;
    }

    private void info() {
        if (phoneBook.isEmpty()) {
            System.out.println("No records to list!");
            return;
        }
        int index = selectRecord();
        if (index == -1) return;
        Record record = getRecord(index);
        if (record != null) {
            System.out.println(record);
        }
    }

    private int selectRecord() {
        listRecords();
        System.out.println("Select a record: ");
        return getInt();
    }

    private void listRecords() {
        List<Record> records = phoneBook.getRecords();
        records.forEach(record -> {
            int index = records.indexOf(record) + 1;  // starts from 0
            if (record instanceof Person) {
                System.out.printf("%d. %s\n", index, ((Person) record).fullName());
            } else {
                System.out.printf("%d. %s\n", index, record.getName());
            }
        });
    }

    private void count() {
        System.out.printf("The Phone Book has %d records.\n", phoneBook.getSize());
    }

    private void exit() {
        reader.scClose();
        System.out.println("Bye!");
    }

    private Options getOption() {
        return Options.getOption(reader.readString());
    }

    private int getInt() {
        int input = -1;
        try {
            input = reader.readInt();
        } catch (InputMismatchException ex) {
            System.out.println("Input must be number ! Try again.");
        }
        return input;
    }

}
