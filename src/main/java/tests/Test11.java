package tests;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Test11 {
    public static void main(String[] args) {
        Pattern codeNumber=Pattern.compile("\\(?([1-9][0-9]{2})\\)?[-. ]?");
        Matcher matcherCodeNumber=codeNumber.matcher("(690) 555");
        while (matcherCodeNumber.find()){
            System.out.println(matcherCodeNumber.group());
            break;
        }
    }
}
