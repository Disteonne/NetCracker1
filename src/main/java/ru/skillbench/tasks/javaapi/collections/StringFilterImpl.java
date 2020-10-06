package ru.skillbench.tasks.javaapi.collections;

import java.lang.reflect.Array;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringFilterImpl implements StringFilter {
    private Set<String> set = new LinkedHashSet<>();
    private Filter filter = new Filter() {
        @Override
        public boolean validator(String s) {
            if (s == null || s == "") {
                return true;
            } else
                return false;
        }
    };

    public StringFilterImpl() {
        super();
    }

    @Override
    public void add(String s) {
        set.add(s.toLowerCase());
    }

    @Override
    public boolean remove(String s) {
        return set.remove(s.toLowerCase());
    }

    @Override
    public void removeAll() {
        for (String s : set
        ) {
            remove(s);
        }
    }

    @Override
    public Collection<String> getCollection() {
        return set;
    }

    @Override
    public Iterator<String> getStringsContaining(String chars) {
        ArrayList<String> list = new ArrayList<>();
        list.addAll(set);
        ArrayList<String> resultList=new ArrayList<>();

        if (filter.validator(chars) == true) {
            return set.iterator();
        } else {
            return new Iterator<String>() {
                int counter=0;
                int countHasNext=0;
                @Override
                public boolean hasNext() {
                    boolean isNext=false;
                    if(countHasNext==0) {
                        for (int i = 0; i < list.size(); i++) {
                            if (list.get(i).contains(chars.toLowerCase())) {
                                isNext = true;
                                resultList.add(list.get(i));
                                countHasNext=resultList.size();
                            }
                        }
                    }else if(counter<countHasNext){
                        isNext=true;
                    }
                    else
                        isNext=false;
                    return isNext;
                }

                @Override
                public String next() {
                        return resultList.get(counter++);
                    }
            };
        }
    }


    @Override
    public Iterator<String> getStringsStartingWith(String begin) {
        ArrayList<String> list = new ArrayList<>();
        list.addAll(set);
        ArrayList<String> resultList=new ArrayList<>();

        if (filter.validator(begin) == true) {
            return set.iterator();
        } else {
            return new Iterator<String>() {
                int counter=0;
                int countHasNext=0;
                @Override
                public boolean hasNext() {
                    boolean isNext=false;
                    if(countHasNext==0) {
                        for (int i = 0; i < list.size(); i++) {
                            if (list.get(i).startsWith(begin.toLowerCase())) {
                                isNext = true;
                                resultList.add(list.get(i));
                                countHasNext=resultList.size();
                            }
                        }
                    }else if(counter<countHasNext){
                        isNext=true;
                    }
                    else
                        isNext=false;
                    return isNext;
                }

                @Override
                public String next() {
                    return resultList.get(counter++);
                }
            };
        }
    }

    @Override
    public Iterator<String> getStringsByNumberFormat(String format) {
        ArrayList<String> list = new ArrayList<>();
        list.addAll(set);
        ArrayList<String> resultList=new ArrayList<>();

        if (filter.validator(format) == true) {
            return set.iterator();
        } else {
            return new Iterator<String>() {
                int counter=0;
                int countHasNext=0;
                @Override
                public boolean hasNext() {
                    boolean isNext=false;
                    if(countHasNext==0) {
                        for (int i = 0; i < list.size(); i++) {
                            String str = list.get(i);
                            str = str.replaceAll("[0-9]", "#");
                            if (str.equals(format.toLowerCase())) {
                                isNext = true;
                                resultList.add(list.get(i));
                                countHasNext=resultList.size();
                            }
                        }
                    }else if(counter<countHasNext){
                        isNext=true;
                    }
                    else
                        isNext=false;
                    return isNext;
                }

                @Override
                public String next() {
                    return resultList.get(counter++);
                }
            };
        }
    }

    @Override
    public Iterator<String> getStringsByPattern(String pattern) {
        ArrayList<String> list = new ArrayList<>();
        list.addAll(set);
        ArrayList<String> resultList=new ArrayList<>();

        if (filter.validator(pattern) == true) {
            return set.iterator();
        } else {
            return new Iterator<String>() {
                int counter=0;
                int countHasNext=0;
                @Override
                public boolean hasNext() {
                    boolean isNext=false;
                    String patternString=pattern.replaceAll("[*]","[a-z]{0,}");
                    if(countHasNext==0) {

                        for (int i = 0; i < list.size(); i++) {
                            String str = list.get(i);
                            if (str.matches(patternString)) {
                                isNext = true;
                                resultList.add(list.get(i));
                                countHasNext=resultList.size();
                            }
                        }
                    }else if(counter<countHasNext){
                        isNext=true;
                    }
                    else
                        isNext=false;
                    return isNext;
                }

                @Override
                public String next() {
                    return resultList.get(counter++);
                }
            };
        }
    }

    interface Filter {
        public boolean validator(String s);
    }

    public static void main(String[] args) {
        StringFilter stringFilter = new StringFilterImpl();
        stringFilter.add("mama");
        stringFilter.add("1 999");
        stringFilter.add("9 388");
        stringFilter.add("Every day i am hungry!");
        stringFilter.add("I miss my mom");
        stringFilter.add("My mom is very beautiful woman ");
        stringFilter.add("hihi sis");
        stringFilter.add("i love my mom");
        stringFilter.add("(900)300-1111");
        stringFilter.add("mom dont like cat");
        stringFilter.add("john is jopa");
        stringFilter.add("what are u doing, mom?");
        stringFilter.add("mam 1 555");
        stringFilter.add("1 999 cat");
        stringFilter.add("9");
        stringFilter.add("-9.32");
        stringFilter.add("-7.432");
        stringFilter.add("mam -9.00");
        stringFilter.add("almati");
        stringFilter.add("a2m");
        stringFilter.add("abudabimani");
        /*
        Iterator iterator = stringFilter.getStringsContaining("e");

        while (iterator.hasNext()) {
           // String str= (String) iterator.next();
            System.out.println(iterator.next());

        }

         */


        /*
        Iterator iterator1=stringFilter.getStringsStartingWith("mo");
        while (iterator1.hasNext()){
            String res= (String) iterator1.next();
            System.out.println(res);
        }

         */

        /*
        Iterator iterator2 = stringFilter.getStringsByNumberFormat("#");
        //ArrayList<String> s=new ArrayList<>();
        while (iterator2.hasNext()){
           System.out.println(iterator2.next());
        }
         */


        /*
        Iterator iterator3 = stringFilter.getStringsByNumberFormat("(###)###-####");
        while (iterator3.hasNext()) {
            System.out.print(iterator3.next());
        }
         */

        Iterator iterator4=stringFilter.getStringsByPattern("*m");
        while (iterator4.hasNext()){
            //String s=(String) iterator4.next();
            System.out.println(iterator4.next());
        }
    }
}
