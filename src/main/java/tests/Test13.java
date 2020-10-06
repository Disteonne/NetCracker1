package tests;

import java.util.*;

public class Test13 {
    public static void main(String[] args) {
        Set set=new LinkedHashSet();
        set.add("Mama".toLowerCase());
        set.add("Mama");
        set.add(null);
        set.add(null);

        Iterator iterator= set.iterator();
        while (iterator.hasNext()){
            System.out.println((String) iterator.next());
        }
       boolean b=set.remove(null);
        System.out.println(b);
    }
}
