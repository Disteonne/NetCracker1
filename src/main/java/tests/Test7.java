package tests;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Test7 {
    public static void main(String[] args) {
        //String pattern="((([A-Z]{1})[a-z]){2,}[.]?)\\s((([A-Z]{1})[a-z]){2,}[.]?)\\s(((([A-Z]{1})[a-z]){2,}[.]?)\\s)?";
        String pattern11="[A-Z]{1}[a-z]{2,}[.]?\\s";
        String name1="VVivak Oleg. Sergeevich. ";
        String pattern="[A-Z]{1}([a-z]{1,})[.]?\\s([A-Z]{1}([a-z]{1,})[.]?\\s){1,}";
        Pattern pattern1=Pattern.compile(pattern);
        String result="";
        //Pattern pattern1=Pattern.compile(pattern);
        Pattern pattern12=Pattern.compile(pattern11);
        Matcher matcher1=pattern1.matcher(name1);
        Matcher matcher11=pattern12.matcher(name1);
        while (matcher1.find()){
            result=matcher1.group();
            System.out.println(result);
            break;
        }
    }
}
