package tests;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Test12 {
    public static void main(String[] args) {
        //[([A-Za-z]{1,})[^ ][-]?[A-Za-z]{1,}]
        Pattern p1=Pattern.compile("[A-Za-z]*$");
        Matcher m= p1.matcher("Grandma's Boysenberry Spread");
        while (m.find()){
            System.out.println(m.group());
        }
    }
}
