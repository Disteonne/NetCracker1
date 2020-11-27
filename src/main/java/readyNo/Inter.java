package readyNo;

import java.lang.reflect.Array;
import java.util.*;

public interface Inter {
    default void meht(){
        System.out.println("lol");
    }

    static void kek(){

    }
}
class Testion implements Inter{

}
abstract class Cat{
    static void m(){
        int i=5;
    }
}
class Testic extends Cat{
    Cat cat=new Testic();

    public static void main(String[] args) {
        Set set=new HashSet<>();
        set.add("kek");
        ArrayList<Integer> i=new ArrayList<>();
        i.add(5);
        i.add(10);
        System.out.println( Collections.max(i));

    }
}