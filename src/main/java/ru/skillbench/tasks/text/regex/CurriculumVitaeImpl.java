package ru.skillbench.tasks.text.regex;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CurriculumVitaeImpl implements CurriculumVitae {
    private String lastName;             //Фамилия
    private String name;                //Имя
    private String midName;             //Отчество
    private Phone numberOfTelephone;    //Номер телефона
    private String number;
    private List<Phone> phoneList = new ArrayList<>();
    private List<String> numberPhones = new ArrayList<>();
    private String textResume;
    private String fullName="";

    public CurriculumVitaeImpl() {
        super();
    }

    @Override
    public void setText(String text) {
        this.textResume = text;
    }

    @Override
    public String getText() {
        return textResume;
    }

    /**
     * @return List<Phone></Phone>
     * СТРОЧКИ:
     * 39-46   ----> Паттерн номера,паттерн "...ext...".
     * String brackets-для записи кода региона между скобками;
     * isStr=false,если "...ext..." отстутствует (нет доп номеров);
     * String str- для записи регулярного выражения для доп номера;
     * String[] splitForExt - для сплита по "...ext...".0й аргумент-все выражение до ext (""),1й-доп номер.
     * 65-67   ----> Пока есть подстроки,удовл.рег выражению для НОМЕРА-добавляем в список номеров (String).
     * 69-122  ----> Проходим по списку String номеров и  проверяем на след условия:
     * (72-77):   Находим подстроку,соотв.рег выражению для доп номера.Записываем в str.Т.к.вошли в цикл,то доп номер есть,след
     * isStr=true. Сплитим рег.выражением для ext,учитывая пробелы,чтобы получить строку,в которой сод.число Ext.
     * (80-84):   Разрезаем исходную строку на длину части,содержащ.доп номер(если его нет,то str.l=0)(УЧИТЫВАЯ пробелы).И если в ост.строке
     * кол-во цифр 7 (т.е.чисто номер),то проверяем есть ли доп.номер(isStr),если да,то учитываем,если нет,то -1.
     * (80-84):   Если уже разрезанная строка (от доп номера) имеет кол-во цифр !=7 (лучше взять стандартно 10),то
     * (86-91):  Находим индексы скобок и записываем число между ними в brackets,прогоняя по циклу.Если скобки не будут
     * найдены,то index=-1,а в цикл для записи мы НЕ ВОЙДЕМ автоматически.
     * (92-104): Если в записи все-таки есть ОБЕ скобки,то 1)Если после закрыв.скобки есть пробел,то (а)Если есть доп номер
     * создаем объект,где номер от indexTwo+2 и вычета доп номера (с пробелами включит). (б) если нет доп.номера
     * то в объекте в его поле: -1. 2)Если нет пробела,то (а) есть доп номер,но главный номер от indexTwo+1 и выч.
     * длины доп номера. (б) нет доп номера: -1.
     * (104-114): Если нет скобок,то 1)Есть пробел после кода региона а)есть доп номер б)нет доп номера. 2)Нет пробела после
     * кода рег а)есть доп б)нет доп. Ост-добавляем в список и возвращаем его.
     */
    @Override
    public List<Phone> getPhones() {
        Pattern pattern = Pattern.compile(PHONE_PATTERN);
        Pattern pattern1 = Pattern.compile("\\s*ext\\.?\\s*([0-9]+)");
        Matcher matcher = pattern.matcher(getText());
        String scobki = "";
        Phone phone = null;
        boolean isStr = false;
        String str = "";
        String[] splitForExt = {"", ""};//второй эл- --- доп номер
        //"(916)125-4171", "495 926-93-47 ext.1846", "800 250 0890"
        while (matcher.find()) {
            numberPhones.add(matcher.group());
        }
        for (String s : numberPhones
        ) {
            Matcher matcher1 = pattern1.matcher(s);
            while (matcher1.find() == true) {
                //isOneExt++;
                str = matcher1.group();
                splitForExt = matcher1.group().split("\\s*ext\\.?\\s*");
                isStr = true;
                //если isOneExt>1 то мб эксепшн?
            }
            //если в строке 7 цифр
            if (s.substring(0, s.length() - str.length()).codePoints().filter(Character::isDigit).count() == 7) {
                if (isStr == false)
                    phone = new Phone(s);
                else
                    phone = new Phone(s.substring(0, s.length() - str.length()), -1, Integer.parseInt(splitForExt[1]));
            } else {
                byte indexOne = (byte) s.substring(0, s.length() - str.length()).indexOf("(");
                byte indexTwo = (byte) s.substring(0, s.length() - str.length()).indexOf(")");
                char[] strToChar = s.substring(0, s.length() - str.length()).toCharArray();
                for (int i = indexOne + 1; i < indexTwo; i++) {
                    scobki += strToChar[i];
                }
                if (s.substring(0, s.length() - str.length()).contains("(") && s.substring(0, s.length() - str.length()).contains(")")) {
                    if (strToChar[indexTwo + 1] == ' ') {
                        if (isStr == true) {
                            phone = new Phone(s.substring(indexTwo + 2, s.length() - str.length()), Integer.parseInt(scobki), Integer.parseInt(splitForExt[1]));
                        } else
                            phone = new Phone(s.substring(indexTwo + 2, s.length() - str.length()), Integer.parseInt(scobki), -1);
                    } else {
                        if (isStr == true) {
                            phone = new Phone(s.substring(indexTwo + 1, s.length() - str.length()), Integer.parseInt(scobki), Integer.parseInt(splitForExt[1]));
                        } else
                            phone = new Phone(s.substring(indexTwo + 1, s.length() - str.length()), Integer.parseInt(scobki), -1);
                    }
                } else {
                    if (strToChar[3] == ' ') {
                        if (isStr == true) {
                            phone = new Phone(s.substring(4, s.length() - str.length()), Integer.parseInt(s.substring(0, 3)), Integer.parseInt(splitForExt[1]));
                        } else
                            phone = new Phone(s.substring(4, s.length() - str.length()), Integer.parseInt(s.substring(0, 3)), -1);
                    } else {
                        if (isStr == true) {
                            phone = new Phone(s.substring(3, s.length() - str.length()), Integer.parseInt(s.substring(0, 3)), Integer.parseInt(splitForExt[1]));
                        } else
                            phone = new Phone(s.substring(3, s.length() - str.length()), Integer.parseInt(s.substring(0, 3)), -1);
                    }
                }
            }
            phoneList.add(phone);
        }
        return phoneList;
    }

    @Override
    public String getFullName() {
        if (textResume==null){
            throw new IllegalStateException();
        }
        else {
            String pattern = "(([A-Z]{1}([a-z]{1,}))[.]?)\\s([A-Z]{1}([a-z]{1,})[.]?\\s){1,}";
            Pattern patternFIO = Pattern.compile(pattern);
            Matcher matcher = patternFIO.matcher(getText());
            ArrayList<String> resultFullName = new ArrayList<>();
            boolean isFind = false;
            byte intLength = 0;
            while (matcher.find()) {
                String[] result = matcher.group().split(" ");
                //Засунули все в список
                for (String s : result
                ) {
                    resultFullName.add(s);
                }
                isFind = true;
            }
            if ((isFind != true && resultFullName.size() < 2) || (isFind != true)) {
                throw new NoSuchElementException();
            } else {
                for (String s : resultFullName
                ) {
                    fullName += s + " ";
                }
            }
        }
        return fullName;
    }

    @Override
    public String getFirstName() {
        if(textResume==null){
            throw new IllegalStateException();
        }
        else if(fullName==""){
            throw new NoSuchElementException();
        }
        else {
            String[] fullName = getFullName().split(" ");
            this.name=fullName[0];
            return name;
        }
    }

    @Override
    public String getMiddleName() {
        if(textResume==null){
            throw new IllegalStateException();
        }
        else if(fullName==""){
            throw new NoSuchElementException();
        }
        else{
            String[] fullName=getFullName().split(" ");
            if(fullName.length==2){
                return null;
            }else {
                midName=fullName[1];
                return midName;
            }
        }
    }

    @Override
    public String getLastName() {
        if(textResume==null){
            throw new IllegalStateException();
        }
        else if(fullName==""){
            throw new NoSuchElementException();
        }else {
            String[] fullNames=fullName.split(" ");
            lastName=fullNames[fullNames.length-1];
            return lastName;
        }
    }

    @Override
    public void updateLastName(String newLastName) {
        if(textResume==null){
            throw new IllegalStateException();
        }
        else if(fullName=="") {
            throw new NoSuchElementException();
        }else {
            String str[]=textResume.split(getLastName());
            textResume=str[0]+newLastName;
        }

    }

    @Override
    public void updatePhone(Phone oldPhone, Phone newPhone) {
        boolean isTrue=false;
        if(textResume!=null){
            if(oldPhone.getExtension()==-1 && oldPhone.getAreaCode()==-1) {
                if (textResume.contains(oldPhone.getNumber())) {
                    textResume.replaceAll(oldPhone.getNumber(), newPhone.getNumber());
                } else {
                    throw new IllegalArgumentException();
                }
            }
            else if(oldPhone.getExtension()==-1)
        }else
            throw new IllegalStateException();
        /*
        boolean isTrue = false;
        byte index=0;
        if (textResume != null) {
            phoneList=getPhones();
            for (Phone p : phoneList
            ) {
                if (p.getNumber().equals(oldPhone.getNumber()) && p.getAreaCode()== oldPhone.getAreaCode() &&
                        p.getExtension() == oldPhone.getExtension())
                    isTrue = true;
                index++;
            }
            if (isTrue == false)
                throw new IllegalArgumentException();
            else {
                phoneList.remove(oldPhone);
                phoneList.add(index-1,newPhone);
            }
        }
        else
            throw new IllegalStateException();

         */
    }

    @Override
    public void hide(String piece) {
        //fullName=getFullName();
        boolean isTrue=false;
        if(textResume!=null){
            if(textResume.contains(piece)){
                //String result=piece.replaceAll("[^.@ ]","X");
                textResume=textResume.replace(piece,piece.replaceAll("[^.@ ]","X"));
            }
            else
                throw new IllegalArgumentException();

        }else
            throw new IllegalStateException();
    }

    @Override
    public void hidePhone(String phone) {
    boolean isTrue=false;
    //fullName=getFullName();
    if(textResume!=null){
        if(textResume.contains(phone)){
            textResume=textResume.replace(phone,phone.replaceAll("\\d","X"));
        }
        else
            throw new IllegalArgumentException();
    }else
        throw new IllegalStateException();
    }

    @Override
    public int unhideAll() {
        return 0;
    }
}
