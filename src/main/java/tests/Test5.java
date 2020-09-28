package tests;

import java.util.Arrays;

public class Test5 {
    public static void main(String[] args) {
        String c1="+1+i";
        String[] sttSplit=c1.split("/+",2);
        char[] ch=c1.toCharArray();
        System.out.println(Arrays.binarySearch(ch,'+'));
        System.out.println(c1.substring(0,c1.length()-2));
        System.out.println(c1.substring(c1.length()).length());
        String c2="";
        System.out.println(c2.length());
    }
}
