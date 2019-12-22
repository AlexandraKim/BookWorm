package Utils;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateFormat {
    DateFormat() {}

    public static String convert(Date date){
        String pattern = "yyyy-MM-dd";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
        if (date == null){
            return "";
        }
        return simpleDateFormat.format(date);
    }

    public static String convert(Date date, boolean dateTime){
        String pattern = "yyyy-MM-dd hh:mm";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
        if (date == null){
            return "";
        }
        return simpleDateFormat.format(date);
    }
}
