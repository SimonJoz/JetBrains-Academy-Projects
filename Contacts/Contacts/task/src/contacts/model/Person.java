package contacts.model;

import contacts.enums.Gender;
import contacts.io.DataReader;

import java.time.LocalDate;

import static java.lang.String.format;

public class Person extends Record {

    private String surname;
    private LocalDate birthDate;
    private Gender gender;


    public Person(String name, String surname, String phoneNumber, LocalDate birthDate, Gender gender) {
        super(name, phoneNumber);
        this.surname = surname;
        this.birthDate = birthDate;
        this.gender = gender;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public String fullName() {
        return format("%s %s", name, surname);
    }

    @Override
    public void edit(String field) {
        DataReader reader = DataReader.getInstance();
        switch (field) {
            case "name":
                setName(reader.readString("Enter name: "));
                break;
            case "surname":
                setSurname(reader.readString("Enter surname: "));
                break;
            case "number":
                setPhoneNumber(reader.readString("Enter number: "));
                break;
            case "birth":
                setBirthDate(reader.readBirthDate());
                break;
            case "gender":
                setGender(reader.readGender());
                break;
            default:
                System.out.println("No such field..");
        }
    }

    @Override
    public String toString() {
        String date = birthDate == null ? NO_DATA : String.valueOf(birthDate);
        String gender = this.gender == null ? NO_DATA : String.valueOf(this.gender);
        String edit = editTime == null ? NO_DATA : String.valueOf(editTime);
        String number = hasNumber() ? phoneNumber : NO_NUMBER;
        return format("Name: %s\nSurname: %s\nBirth date: %s\nGender: %s\nNumber: %s\nTime created: %s\nTime last edit: %s",
                name, surname, date, gender, number, createdTime, edit);
    }
}
