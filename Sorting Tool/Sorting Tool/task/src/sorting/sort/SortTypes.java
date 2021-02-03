package sorting.sort;

public enum SortTypes {
    BY_COUNT("byCount"),
    NATURAL("natural");

    private final String type;

    SortTypes(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }

    public static SortTypes getTypeByString(String type){
        String result = BY_COUNT.type.equals(type) ?
                type.substring(0,2) + "_" + type.substring(2) : type;
        return SortTypes.valueOf(result.toUpperCase());
    }
}
