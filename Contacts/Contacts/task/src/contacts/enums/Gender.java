package contacts.enums;

public enum Gender {
    MALE, FEMALE;

    public static Gender getGenderByString(String gender) {
        gender = gender.toUpperCase();
        if (gender.equals("M") || gender.equals("MALE")) {
                return MALE;
        }else if(gender.equals("F") || gender.equals("FEMALE")){
                return FEMALE;
        } else{
            return null;
        }
    }
}
