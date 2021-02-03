package sorting.io;

public enum DataTypes {
    LONG("long"),
    WORD("word"),
    LINE("line");

    private final String type;

    DataTypes(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }

    public static DataTypes getTypeByString(String type){
        return DataTypes.valueOf(type.toUpperCase());
    }
}
