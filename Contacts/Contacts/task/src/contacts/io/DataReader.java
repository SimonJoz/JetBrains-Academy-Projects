package contacts.io;

import contacts.enums.Gender;
import contacts.enums.RecordType;
import contacts.exception.WrongInputException;
import contacts.model.Organization;
import contacts.model.Person;
import contacts.model.Record;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

public class DataReader {
    private final Scanner scanner;
    private static final DataReader instance = new DataReader();

    private DataReader() {
        scanner = new Scanner(System.in);
    }

    public static DataReader getInstance() {
        return instance;
    }

    public RecordType readRecordType() {
        RecordType type = null;
        boolean isInputOk = false;
        while (!isInputOk) {
            try {
                type = RecordType.getTypeByString(readString("Enter the type (person, organization): "));
                isInputOk = true;
            } catch (WrongInputException e) {
                System.out.println("Wrong input. Try again!");
            }
        }
        return type;
    }

    public Person readPerson() {
        String name = readString("Enter the name: ");
        String surname = readString("Enter the surname: ");
        LocalDate date = readBirthDate();
        Gender gender = readGender();
        String phone = readString("Enter the number: ");
        return new Person(name, surname, phone, date, gender);
    }

    public Organization readOrganization() {
        String name = readString("Enter the organization name: ");
        String address = readString("Enter the address: ");
        String phone = readString("Enter the number: ");
        return new Organization(name, address, phone);
    }

    public LocalDate readBirthDate() {
        LocalDate date = null;
        try {
            date = LocalDate.parse(readString("Enter the birth date: "));
        } catch (DateTimeParseException ex) {
            System.out.println("Bad date !");
        }
        return date;
    }

    public Gender readGender() {
        String genderStr = readString("Enter the gender (M, F): ");
        Gender gender = Gender.getGenderByString(genderStr);
        if (gender == null) {
            System.out.println("Bad gender !");
        }
        return gender;
    }

    public void editRecord(Record record) {
        if (record != null) {
            String field;
            if (record instanceof Person) {
                field = readString("Select a field (name, surname, birth, gender, number): ");
            } else {
                field = readString("Select a field (name, address, number): ");
            }
            record.edit(field.toLowerCase());
            record.setEditTime(LocalDateTime.now());
            System.out.println("The record updated!");
        }
    }

    public int readInt() {
        try {
            return scanner.nextInt();
        } finally {
            scanner.nextLine();
        }
    }

    public String readString(String msg) {
        System.out.print(msg);
        return scanner.nextLine().trim();
    }

    public String readString() {
        return scanner.nextLine();
    }

    public void scClose() {
        scanner.close();
    }
}
