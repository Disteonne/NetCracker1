package readyNo;

public class Construct {
    //если тут констр по умолчанию.то ошибки не будет
    public Construct(int i) {
    }

    public Construct() {
    }
}
class ConstrExt extends Construct{
    public ConstrExt(int a) {
        super(a);//вызовет 1й констр
        //super(); вызовет 2й конструктор.но можно не писать.т.к сделает по умолчанию
    }

    public static void main(String[] args) {
        Construct construct=new ConstrExt(5);
    }
}
