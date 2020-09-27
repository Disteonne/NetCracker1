package tests;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Scanner;

public class Test3 {
    public static void main(String[] args) {
        String str="BEGIN:VCARD\r\n\"+text+\"\r\nEND:VCARD";
        Scanner scanner=new Scanner(str);
        ArrayList<String> list=new ArrayList<>();
        //String str1=scanner.nextLine();
        scanner.useDelimiter("\r\n");
        while (scanner.hasNext()) {
            list.add(scanner.nextLine());
            //str1=scanner.nextLine();
        }
        for (int i = 0; i < list.size(); i++) {
            System.out.println(list.get(i));
        }
    }
}
