package contacts.model;

import contacts.io.DataReader;

import static java.lang.String.format;

public class Organization extends Record {
    private String address;

    public Organization(String name, String address, String phoneNumber) {
        super(name, phoneNumber);
        this.address = address;
    }

    public void setAddress(String address) {
        this.address = address;
    }


    @Override
    public String toString() {
        String edit = editTime == null ? NO_DATA : String.valueOf(editTime);
        String number = hasNumber() ? phoneNumber : NO_NUMBER;
        return format("Organization name: %s\nAddress: %s\nNumber: %s\nTime created: %s\nTime last edit: %s",
                name, address, number, createdTime, edit);
    }

    @Override
    public void edit(String field) {
        DataReader reader = DataReader.getInstance();
        switch (field) {
            case "name":
                setName(reader.readString("Enter name: "));
                break;
            case "address":
                setAddress(reader.readString("Enter address: "));
                break;
            case "number":
                setPhoneNumber(reader.readString("Enter number: "));
                break;
            default:
                System.out.println("No such field..");
        }
    }
}
