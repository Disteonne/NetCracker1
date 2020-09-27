package tests;

import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class Test4 {
    public static void main(String[] args) {

        DateTimeFormatter dateTimeFormatter=DateTimeFormatter.ofPattern("dd-MM-yyyy");
        LocalDate date1=LocalDate.parse("06-06-2020",dateTimeFormatter);
        System.out.println(LocalDate.now().toString());
        String[] str=LocalDate.now().toString().split("-");
        String newData=str[2]+"-"+str[1]+"-"+str[0];
        LocalDate date2=LocalDate.parse(newData,dateTimeFormatter);
        System.out.println(date1);
        Period period=Period.between(date1,date2);
        System.out.println(period);

        //Period periodFromString = Period.parse("P2019Y2M25D");
        //System.out.println("Period from String: " + periodFromString);
    }
}
