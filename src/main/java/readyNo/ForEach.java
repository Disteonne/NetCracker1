package readyNo;

public class ForEach {
    //Перегрузка конструктора
    public ForEach(String st) {
    }
    public ForEach(int i){

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
    }
}
