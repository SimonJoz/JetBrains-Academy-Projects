package contacts.model;

import java.time.LocalDateTime;

public abstract class Record {
    public static final String NUMBER_VALIDATION_REGEX =
            "\\+?([\\d]{1,2}\\s*)?(\\([\\w]{2,}\\)[\\s-]*([\\w]{2,4}[\\s-]*)*" +
            "|[0-9]{2,3}[\\s-]*\\([0-9]{2,3}\\)[\\s-]*([\\w]{2,4}[\\s-]*)*|([\\w]{2,4}[\\s-]*)+)|\\d";
    public static final String NO_NUMBER = "[no number]";
    public static final String NO_DATA = "[no data]";

    protected String name;
    protected String phoneNumber;
    protected LocalDateTime createdTime;
    protected LocalDateTime editTime;


    public Record(String name, String phoneNumber) {
        this.name = name;
        setPhoneNumber(phoneNumber);
        setCreatedTime(LocalDateTime.now());
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPhoneNumber(String phoneNumber) {
        if (!validatePhone(phoneNumber)) {
            System.out.println("Wrong number format!");
            this.phoneNumber = "";
            return;
        }
        this.phoneNumber = phoneNumber;
    }

    private boolean validatePhone(String number) {
        return number.matches(NUMBER_VALIDATION_REGEX);
    }

    public abstract void edit(String field);

    public void setCreatedTime(LocalDateTime createdTime) {
        this.createdTime = createdTime;
    }

    public void setEditTime(LocalDateTime editTime) {
        this.editTime = editTime;
    }

    protected boolean hasNumber() {
        return phoneNumber != null && !"".equals(phoneNumber);
    }

    public String getName() {
        return name;
    }
}
