package ru.skillbench.tasks.basics.text;


import java.io.PrintStream;
import java.util.*;

public class WordCounterImpl implements WordCounter {
    private Map<String, Long> map = new HashMap<>();
    private String text = "";

    @Override
    public void setText(String text) {
        this.text = text;
    }

    @Override
    public String getText() {
        if (text == "" || text == null) {
            return null;
        } else
            return text;
    }

    @Override
    public Map<String, Long> getWordCounts() {
        if (getText() == null) {
            throw new IllegalStateException();
        } else {
            ArrayList<String> list = new ArrayList<>();
            String s = text.toLowerCase();
            String[] spl = s.split("[\\s]+|[\\n]+|[\\t]+");
            for (int i = 0; i < spl.length; i++) {
                int l = spl[i].length();
                if (!spl[i].equals("") && spl[i].startsWith("<") == false && spl[i].endsWith(">") == false) {
                    list.add(spl[i].replaceAll("[.,():^&?;!\\-\"]", ""));
                }
            }

            Long item;
            for (String swd : list
            ) {
                item = map.get(swd);
                if (item == null) {
                    map.put(swd, 1L);
                } else
                    map.put(swd, item + 1);
            }
            return map;
        }
    }

    @Override
    public List<Map.Entry<String, Long>> getWordCountsSorted() {
        Map<String,Long> map_one=new HashMap<>();
        map_one.putAll(map);
        List<Map.Entry<String, Long>> list=new ArrayList<>(map_one.entrySet());
        CompOneCount compOneCount=new CompOneCount();
        CompTwoString compTwoString=new CompTwoString();
        Collections.sort(list,compOneCount.thenComparing(compTwoString));
        return list;
    }

    class  CompOneCount implements Comparator<Map.Entry<String,Long>>{
        @Override
        public int compare(Map.Entry<String, Long> o1, Map.Entry<String, Long> o2) {
            return o1.getValue().compareTo(o2.getValue());
        }
    }
    class CompTwoString implements Comparator<Map.Entry<String,Long>>{

        @Override
        public int compare(Map.Entry<String, Long> o1, Map.Entry<String, Long> o2) {
            return o1.getKey().compareTo(o2.getKey());
        }
    }

    @Override
    public <K extends Comparable<K>, V extends Comparable<V>> List<Map.Entry<K, V>> sort(Map<K, V> map, Comparator<Map.Entry<K, V>> comparator) {
        Map<K,V> map_two=new HashMap<>();
        map_two.putAll(map);
        List<Map.Entry<K,V>> lis=new ArrayList<>(map_two.entrySet());
        Collections.sort(lis,comparator);
        return lis;
    }

    @Override
    public <K, V> void print(List<Map.Entry<K, V>> entries, PrintStream ps) {
        List<Map.Entry<K, V>> list=entries;
        for (int i = 0; i < list.size(); i++) {
            ps.println(list.get(i).getKey()+" "+list.get(i).getValue());
        }
    }
}
