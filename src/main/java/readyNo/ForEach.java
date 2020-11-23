package readyNo;

public class ForEach {
    public static int statOne=5;
    public int notStatTwo=7;
    //Перегрузка конструктора
    static {
        statOne=10;
    }
    public ForEach(String st) {
    }
    public ForEach(int i){

    }
    /*
    *       Перегрузка методов
     */
    public void overOne(String s,int i){

    }
    public String overOne(String s) throws Exception{
        //неважно какой тип возвр значения и есть ли исключения
        //самое важное-сигнатура
        return s;
    }

    /*
    *       Использование статических констант в методах
     */
    public static void funcStaticOne(){
        statOne=10;
        System.out.println(statOne);
        //notStatTwo-error,только статик
    }
    public void funcStaticTwo(){
        statOne=15;//not error-в не стат методах можно исп стат конст
        System.out.println(statOne);
        System.out.println(notStatTwo);
    }

    public static void main(String[] args) {
        int[] array= {1,2,3,4,5};
        //Только чтение
        //Изменение в for-each невозможно
        for (int x:array
             ) {
            x=-1;
        }
        for (int i = 0; i < array.length; i++) {
            System.out.println(array[i]);
        }

    }
}
class test{
    public static void main(String[] args) {
       ForEach forEach=new ForEach("kek");
       ForEach forEach1=new ForEach(5);
       System.out.println("------------------------");
       ForEach.funcStaticOne();
        System.out.println("-    -     -     -    -");
       forEach.funcStaticTwo();
        System.out.println("-    -     -     -    -");
        System.out.println(ForEach.statOne); //если в процессе изменили stat переменную.то она изменилась
        //для всего класса

    }
}
