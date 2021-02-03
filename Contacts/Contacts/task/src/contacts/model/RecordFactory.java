package contacts.model;

import contacts.enums.RecordType;
import contacts.io.DataReader;

public class RecordFactory {


    public static Record createRecord(RecordType type) {
        DataReader reader = DataReader.getInstance();
        switch (type) {
            case PERSON:
                return reader.readPerson();
            case ORGANIZATION:
                return reader.readOrganization();
        }
        return null;
    }


}
