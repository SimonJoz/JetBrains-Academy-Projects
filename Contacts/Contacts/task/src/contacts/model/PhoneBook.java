package contacts.model;

import java.util.ArrayList;
import java.util.List;

public class PhoneBook {
    private final List<Record> records;

    public PhoneBook() {
        this.records = new ArrayList<>();
    }

    public boolean addRecord(Record record) {
        return records.add(record);
    }

    public boolean removeRecord(int index) {
        // index 0 is listed as 1.
        index = index - 1;
        try {
            return records.remove(index) != null;
        } catch (IndexOutOfBoundsException e) {
            return false;
        }
    }

    public boolean isEmpty() {
        return records.isEmpty();
    }

    public int getSize() {
        return records.size();
    }

    public Record getRecord(int index) {
        // index 0 is listed as 1.
        index = index - 1;
        try {
            return records.get(index);
        } catch (IndexOutOfBoundsException ex) {
            return null;
        }
    }

    public List<Record> getRecords() {
        return records;
    }
}
