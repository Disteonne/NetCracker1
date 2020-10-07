package tests;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Test15 {
    public static void main(String[] args) {
//[<>@#,. -+^&*A-z0-9:\/]{2,}
//[\s\n\t]?[a-z]{1,}[\s\n\t]?
        Pattern pattern=Pattern.compile("[a-z]{1,}");
        String text="  A \"Combined Work\" is a work produced by combining or linking an\n" +
                "Application with the Library.  The particular version of the Library\n" +
                "with which is the Combined Work was made is also called the \"Linked\n" +
                "Version\".";
        Map<String,Integer> map=new HashMap<>();
        String txt=text.toLowerCase();
        Matcher matcher=pattern.matcher(txt);
        int count;
        while (matcher.find()){
        String word=matcher.group();
            if(txt.contains(word)){
                boolean isContains=true;
                count=0;
                String strTemp=txt;
               while (isContains){
                   int len_strTemp=strTemp.length();
                   count++;
                   int index=strTemp.indexOf(word);
                   strTemp=strTemp.substring(index+word.length(),len_strTemp);
                   //int len_strTemp=strTemp.length();

                   if(!strTemp.contains(word))
                   //if(!txt.contains(word))
                       isContains=false;
               }
               map.put(word,count);
            }else {
                count = 1;
                map.put(word,count);
            }
            //System.out.println(count);

            //System.out.println(matcher.group());
        }

        for (Map.Entry<String,Integer> pair:map.entrySet()
             ) {
            System.out.println(pair.getValue()+" "+pair.getKey());
        }
    }
}
