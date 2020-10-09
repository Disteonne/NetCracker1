package tests2;

import java.util.ArrayList;
import java.util.List;

public  class Test1 {
    String res="";
    static int count=0;
    public static void main(String[] args) {
        ArrayList<String> list=new ArrayList<>();
        list.add("kek");
        list.add("swap");
        list.add("sorry");
        //System.out.println(list.iterator().next());
        System.out.println(Res.sumStr(list));
    }
    static class Res {
        private static String res;

        public static String sumStr(List<String> list) {
            count++;
            if(count==3) {
                return res + " kek";
            }else {
               return res += list.iterator() + " "+sumStr(list);
            }
        }
    }
}
