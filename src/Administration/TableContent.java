package Administration;

public class TableContent {
    private static String _value = "l";

    TableContent(){}

    public static void setValue(String value) {
        _value = value;
    }

    public static String getValue() {
        return _value;
    }
}
