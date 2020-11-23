package readyNo;

public class ForEach {
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
