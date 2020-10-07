package tests;

import java.util.ArrayList;

public class Test16 {
    public static void main(String[] args) {
        ArrayList<Integer> i=new ArrayList<>();
        i.add(5);
        i.add(8);
        i.add(10);
        i.remove(8);
        for (int j = 0; j < i.size(); j++) {
            System.out.println(i.get(j));
        }
    }
}
