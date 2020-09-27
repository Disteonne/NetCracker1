package tests;

public class Test1 {
    public static void main(String[] args) {
        String str="TEL:TYPE=HOME";
        String[] str1=str.split("=");
        for (String x: str1
             ) {
System.out.println(x);
        }

    }
}
