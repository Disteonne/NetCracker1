package ru.skillbench.tasks.javaapi.reflect;

import java.util.ArrayList;

public class Test {
    public static void main(String[] args) {
        ArrayList<?>list=new ArrayList<>();
        list.add(null);
        System.out.println(list.size());
    }
}
