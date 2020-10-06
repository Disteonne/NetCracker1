package tests;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Test14 {
    public static void main(String[] args) {
        /*
        ArrayList<String> list=new ArrayList<>();
        list.add("s");
        String str="Every day i am hungry!";
        System.out.println((str.startsWith("Ev")));
        String str1="(900)200-1111";
        Pattern pattern=Pattern.compile("\\([0-9]{3}\\)[0-9]{3}[-][0-9]{4}");
        Matcher matcher= pattern.matcher(str1);
        String[] spl=str1.split("\\([0-9]{3}\\)[0-9]{3}[-][0-9]{4}");
        System.out.println(spl.length);
        for (int i = 0; i < spl.length; i++) {
            System.out.println(spl[i]);
        }
    }
         */
        String str="distr*";
        String strRep=str.replace("[*]","\\s");
    }
}
