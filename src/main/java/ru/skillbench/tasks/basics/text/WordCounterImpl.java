package ru.skillbench.tasks.basics.text;

import java.io.PrintStream;
import java.lang.management.MemoryType;
import java.lang.reflect.Array;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class WordCounterImpl implements WordCounter {
    private String text="";

    @Override
    public void setText(String text) {
        this.text=text;
    }

    @Override
    public String getText() {
        if(text=="" || text==null){
            return null;
        }
        else
            return text;
    }

    @Override
    public Map<String, Long> getWordCounts() {
        Map<String,Long> counterWords=new HashMap<>();
        String lowerString=text.toLowerCase();
        if(getText()==null){
            throw new IllegalStateException();
        }else {
            //Hello,i love ma mom and my mom is nice girl <maybe>.
            String new_text=text.replaceAll("[-,.()^:]"," ");
            Pattern pattern=Pattern.compile("[\\s+]?[\\n+]?[\\t+]?[a-z][\\s+]?[\\n+]?[\\t+]?");
            Matcher matcher=pattern.matcher(new_text.toLowerCase());
            ArrayList<String>list=new ArrayList<>();
            while (matcher.find()){
                list.add(matcher.group());
            }
            return counterWords;
        }
    }

    @Override
    public List<Map.Entry<String, Long>> getWordCountsSorted() {
        return null;
    }

    @Override
    public <K extends Comparable<K>, V extends Comparable<V>> List<Map.Entry<K, V>> sort(Map<K, V> map, Comparator<Map.Entry<K, V>> comparator) {
        return null;
    }

    @Override
    public <K, V> void print(List<Map.Entry<K, V>> entries, PrintStream ps) {

    }
}
