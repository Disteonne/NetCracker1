package tests;

import ru.skillbench.tasks.text.regex.CurriculumVitae;

import java.lang.ref.SoftReference;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Test6 {
    public static void main(String[] args) {

        String s = "800 270-25-28 ext. 8888 ext 9";
        Pattern pattern=Pattern.compile("\\s*ext\\.?\\s*([0-9]+)");
        Matcher matcher=pattern.matcher(s);
        while (matcher.find()) {
            System.out.println(matcher.group());
        }


        /*
        String s = "(800) 270-25-28 ext. 8888 ext 9";
        String s1=s.substring(0,9);
        System.out.println(s1);
        System.out.println(s);
        Pattern pattern=Pattern.compile("\\(?([1-9][0-9]{2})\\)?[-. ]*");
        Matcher matcher=pattern.matcher(s);
        while (matcher.find()) {
            System.out.println(matcher.group());
        }

         */

        /*
        String s1="495 926-93-47";
        Pattern pattern1=Pattern.compile("\\(?([1-9][0-9]{2})\\)?");
        Pattern pattern2=Pattern.compile("(\\(?([1-9][0-9]{2})\\)?[-. ]*)?");
        Matcher matcher1=pattern2.matcher(s);
        while (matcher1.find()) {
            System.out.println(matcher.group());
        }

         */
        /*
        Pattern pattern1NumberRegion = Pattern.compile("(\\(?([1-9][0-9]{2})\\)?[-. ]*)?");
        Pattern pattern1Ext = Pattern.compile("\\s*ext\\.?\\s*([0-9]+)");
        Matcher matcher1 = pattern1NumberRegion.matcher(s);// код региона
        Matcher matcher2 = pattern1Ext.matcher(s);//доп номер
        if (matcher1.find() == false && matcher2.find() == true) {
            System.out.println(s.substring(0, matcher2.group().length()));
            System.out.println("dop");
            System.out.println(Integer.parseInt(matcher2.group()));
            //phone = new CurriculumVitae.Phone(s.substring(0, matcher2.group().length()), -1, Integer.parseInt(matcher2.group()));
        } else if (matcher1.find() == true && matcher2.find() == false) {
            System.out.println(s.substring(matcher1.group().length(), s.length()));
            System.out.println("kod");
            System.out.println(Integer.parseInt(matcher1.group()));
            //phone = new CurriculumVitae.Phone(s.substring(matcher1.group().length(), s.length()), Integer.parseInt(matcher1.group()), -1);
        } else if (matcher1.find() == true && matcher2.find() == true) {
            System.out.println(s.substring(matcher1.group().length(), s.length() - matcher2.group().length()));
            System.out.println("kod");
            System.out.println(Integer.parseInt(matcher1.group()));
            System.out.println("dop");
            System.out.println( Integer.parseInt(matcher2.group()));
            //phone = new CurriculumVitae.Phone(s.substring(matcher1.group().length(), s.length() - matcher2.group().length()),
                   // Integer.parseInt(matcher1.group()), Integer.parseInt(matcher2.group()));
        } else {
           System.out.println(s);
        }
         */
       // System.out.println(s.indexOf("kek"));
    }
}
