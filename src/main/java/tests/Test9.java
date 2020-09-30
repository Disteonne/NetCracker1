package tests;

public class Test9 {
    public static void main(String[] args) {
        String str="Diana Razmikovna Dyatel";
        String[] s=str.split("Dyatel");
        for (String st:s
             ) {
            System.out.println("k: "+"\t"+st+"k");
        }
    }
}
