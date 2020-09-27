package tests;

import javax.swing.text.DateFormatter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class Test2 {
    public static void main(String[] args) {
        DateFormat dateFormat=new SimpleDateFormat("MM-DD-YYYY");
        DateFormatter dateFormatter=new DateFormatter(dateFormat);
        Calendar calendar=new GregorianCalendar();
    }
}
