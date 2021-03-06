package ru.skillbench.tasks.javaapi.collections;

import java.util.*;

public class StringFilterImpl implements StringFilter {
    private Set<String> set = new LinkedHashSet<>();
    private ArrayList<String> arrayList = new ArrayList<>();
    private Filter filter = new Filter() {
        @Override
        public boolean validator(String s) {
            if (s == null || s == "") {
                return true;
            } else
                return false;
        }
    };
    private boolean isEmpty = true;

    public StringFilterImpl() {
        super();
    }

    @Override
    public void add(String s) {
        boolean isTrue=false;
        //set.add(s.toLowerCase());
        if (isEmpty == true) {
            arrayList.add(s);
        } else {
            for (int i = 0; i < arrayList.size(); i++) {
                if (arrayList.get(i) == s) {
                    isTrue=true;
                }
            }
            if(isTrue!=true){
                arrayList.add(s);
            }
        }
        isEmpty = false;
    }

    @Override
    public boolean remove(String s) {
        //return set.remove(s.toLowerCase());
        if (s == null) {
            return arrayList.remove(null);
        } else {
            return arrayList.remove(s.toLowerCase());
        }
    }

    @Override
    public void removeAll() {
        arrayList.removeAll(arrayList);
        /*
        for (String s : set
        ) {
            remove(s);
        }
         */
    }

    @Override
    public Collection<String> getCollection() {
        return arrayList;
    }

    @Override
    public Iterator<String> getStringsContaining(String chars) {
        ArrayList<String> list = new ArrayList<>();
        list.addAll(arrayList);
        ArrayList<String> resultList = new ArrayList<>();

        if (filter.validator(chars) == true) {
            return arrayList.iterator();
        } else {
            return new Iterator<String>() {
                int counter = 0;
                int countHasNext = 0;

                @Override
                public boolean hasNext() {
                    boolean isNext = false;
                    if (countHasNext == 0) {
                        for (int i = 0; i < list.size(); i++) {
                            if(list.get(i)!=null) {
                                if (list.get(i).contains(chars.toLowerCase())) {
                                    isNext = true;
                                    resultList.add(list.get(i));
                                    countHasNext = resultList.size();
                                }
                            }
                        }
                    } else if (counter < countHasNext) {
                        isNext = true;
                    } else
                        isNext = false;
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
        list.addAll(arrayList);
        ArrayList<String> resultList = new ArrayList<>();

        if (filter.validator(begin) == true) {
            return arrayList.iterator();
        } else {
            return new Iterator<String>() {
                int counter = 0;
                int countHasNext = 0;

                @Override
                public boolean hasNext() {
                    boolean isNext = false;
                    if (countHasNext == 0) {
                        for (int i = 0; i < list.size(); i++) {
                            if(list.get(i)!=null) {
                                if (list.get(i).startsWith(begin.toLowerCase())) {
                                    isNext = true;
                                    resultList.add(list.get(i));
                                    countHasNext = resultList.size();
                                }
                            }
                        }
                    } else if (counter < countHasNext) {
                        isNext = true;
                    } else
                        isNext = false;
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
        list.addAll(arrayList);
        ArrayList<String> resultList = new ArrayList<>();

        if (filter.validator(format) == true) {
            return arrayList.iterator();
        } else {
            return new Iterator<String>() {
                int counter = 0;
                int countHasNext = 0;

                @Override
                public boolean hasNext() {
                    boolean isNext = false;
                    if (countHasNext == 0) {
                        for (int i = 0; i < list.size(); i++) {
                            if(list.get(i)!=null) {
                                String str = list.get(i);
                                str = str.replaceAll("[0-9]", "#");
                                if (str.equals(format.toLowerCase())) {
                                    isNext = true;
                                    resultList.add(list.get(i));
                                    countHasNext = resultList.size();
                                }
                            }
                        }
                    } else if (counter < countHasNext) {
                        isNext = true;
                    } else
                        isNext = false;
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
        list.addAll(arrayList);
        ArrayList<String> resultList = new ArrayList<>();

        if (filter.validator(pattern) == true) {
            return arrayList.iterator();
        } else {
            return new Iterator<String>() {
                int counter = 0;
                int countHasNext = 0;

                @Override
                public boolean hasNext() {
                    boolean isNext = false;
                    String patternString = pattern.replaceAll("[*]", "[a-z]{0,}");
                    if (countHasNext == 0) {

                        for (int i = 0; i < list.size(); i++) {
                            if (list.get(i) != null) {
                                String str = list.get(i);
                                if (str.matches(patternString)) {
                                    isNext = true;
                                    resultList.add(list.get(i));
                                    countHasNext = resultList.size();
                                }
                            }
                        }
                    } else if (counter < countHasNext) {
                        isNext = true;
                    } else
                        isNext = false;
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

}

