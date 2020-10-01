package tests;

import java.util.ArrayList;

public class Test10 {
    public static void main(String[] args) {
        String bigStr="fchsarevhg diana@yandex.ru fghbf";
        String str="diana@yandex.ru";
        String pattern="[^.@ ]";
        //System.out.println(str.replaceAll(pattern,"X"));
        System.out.println(bigStr.replaceAll(str,(str.replaceAll(pattern,"X"))));


        ArrayList<String> stringList=new ArrayList<>();
        System.out.println(stringList.size());
    }
}
