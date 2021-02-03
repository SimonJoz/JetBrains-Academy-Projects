package contacts.enums;

import contacts.exception.WrongInputException;

public enum RecordType {
    PERSON, ORGANIZATION;

    public static RecordType getTypeByString(String type) throws WrongInputException {
        type = type.toUpperCase();
        if (type.equals("PERSON")) {
            return PERSON;
        } else if (type.equals("ORGANIZATION")) {
            return ORGANIZATION;
        } else {
            throw new WrongInputException();
        }
    }
}
