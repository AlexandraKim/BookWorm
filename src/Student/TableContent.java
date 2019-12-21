package Student;
// This class is needed to switch between the tabs
// Whenever a button in the menu is clicked the value this class should also be updated
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
