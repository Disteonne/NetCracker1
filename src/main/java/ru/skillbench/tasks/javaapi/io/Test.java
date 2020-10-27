package ru.skillbench.tasks.javaapi.io;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Test {
    public static void main(String[] args) {
        /*
        ArrayList<Integer> list=new ArrayList<>();
        list.add(7);
        list.add(-1);
        list.add(3);
        list.add(-12);
        Stream stream=list.stream();

        ArrayList<Integer> l= (ArrayList<Integer>) stream.collect(Collectors.toList());
        for (Integer i:l
             ) {
            System.out.println(i);
        }


        ArrayList<String> str=new ArrayList<>();
        str.add("kek");
        str.add("Mari");
        str.add("apple");
        str.add("Zom");


        Collections.sort(str);

        for (int i = 0; i < str.size(); i++) {
            System.out.println(str.get(i));
        }
      String strin="";
        for (int i = 0; i < str.size(); i++) {
            strin+=str.get(i).toLowerCase()+" ";
        }
        strin=strin.substring(0,strin.length()-1);
    System.out.println(strin+"kek");

        ArrayList<String> l=new ArrayList<>();
        l.add(null);
        System.out.println(l.get(0).toLowerCase());

         */
        String str="kekek";
        String[] k=str.split("[\\s\\t\\r\\n]+");
        for (int i = 0; i < k.length; i++) {
            System.out.println(k[i]);
        }
    }
}
