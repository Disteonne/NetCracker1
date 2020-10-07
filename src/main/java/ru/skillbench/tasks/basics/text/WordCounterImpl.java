package ru.skillbench.tasks.basics.text;
import java.io.PrintStream;
import java.text.Collator;
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
        Map<String,Long> map_one=getWordCounts();

        map_one.putAll(map);
        List<Map.Entry<String, Long>> list=new ArrayList<>(map_one.entrySet());
        CompOneCount compOneCount=new CompOneCount();
        CompTwoString compTwoString=new CompTwoString();
        Collections.sort(list,compOneCount.thenComparing(compTwoString));
        return list;
        /*
        Comparator<Map.Entry<String,Long>> comparator=new CompTwoString(){
            @Override
            public int compare(Map.Entry<String, Long> o1, Map.Entry<String, Long> o2) {
                if(o1.getValue()<o2.getValue()) return 1;
                if(o1.getValue()>o2.getValue()) return -1;
                if(o1.getValue()==o2.getValue()){
                    Collator collator=Collator.getInstance();
                    return collator.compare(o1.getKey(),o2.getKey());
                }
                return 0;
            }
        };
        return sort(map_one,comparator);
         */
    }

    static class  CompOneCount implements Comparator<Map.Entry<String,Long>>{
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
         /*
        List<Map.Entry<K, V>> words_s = new LinkedList<>();
        words_s.addAll(map.entrySet());
        boolean sorted = false;
        List<Map.Entry<K,V>> tmp = new LinkedList<>();
        while (!sorted)
        {
            sorted = true;
            for (int i = 0; i < words_s.size() - 1; i++) {
                int result_compare = comparator.compare(words_s.get(i),words_s.get(i+1));
                if (result_compare > 0) {
                    tmp.add(words_s.get(i));
                    words_s.set(i,words_s.get(i+1));
                    words_s.set(i+1,tmp.get(0));
                    tmp.clear();
                    sorted = false;
                }
            }
        }
        return words_s;

          */
    }

    @Override
    public <K, V> void print(List<Map.Entry<K, V>> entries, PrintStream ps) {
        List<Map.Entry<K, V>> list=entries;
        for (int i = 0; i < list.size(); i++) {
            ps.println(list.get(i).getKey()+" "+list.get(i).getValue());
        }
    }

}
