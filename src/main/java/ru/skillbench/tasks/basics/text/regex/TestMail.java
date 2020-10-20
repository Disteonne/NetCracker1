package ru.skillbench.tasks.basics.text.regex;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TestMail {
    public static void main(String[] args) {
        //Pattern pattern=Pattern.compile("([A-Za-z0-9][A-Za-z0-9_\\-\\.]{0,20}[A-Za-z0-9]$)[@]([A-Za-z0-9][a-zA-Z0-9\\-\\._]*([A-Za-z0-9]$)?){2}");
        //String str="a...............b@c.d.e.f.com";
        Pattern pattern=Pattern.compile(".[\\s]*[\\t]*.");
        String str="jbvjkdb avjfb kjfd                  kfjvknsv jkfrn\n fjvjkfbvfd";
        Matcher matcher=pattern.matcher(str);
        while (matcher.find()){
            System.out.println(matcher.group());
        }
    }
}
