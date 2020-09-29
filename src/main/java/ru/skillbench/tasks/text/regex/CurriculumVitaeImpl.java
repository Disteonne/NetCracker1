package ru.skillbench.tasks.text.regex;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CurriculumVitaeImpl implements CurriculumVitae {
    private String surname;             //Фамилия
    private String name;                //Имя
    private String midName;             //Отчество
    private Phone numberOfTelephone;    //Номер телефона
    private String number;
    private List<Phone> phoneList = new ArrayList<>();
    private List<String> numberPhones = new ArrayList<>();
    private String textResume;

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

    @Override
    public List<Phone> getPhones() {
        Pattern pattern = Pattern.compile(PHONE_PATTERN);
        Pattern pattern1 = Pattern.compile("\\s*ext\\.?\\s*([0-9]+)");
        Matcher matcher = pattern.matcher(getText());
        String scobki = "";
        String numberEXT = "";
        byte isOneExt = 0;
        byte count = 0;
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
                if (s.substring(0, s.length() - str.length()).contains("(") && s.substring(0, s.length() - str.length()).contains(")")) {
                    byte indexOne = (byte) s.substring(0, s.length() - str.length()).indexOf("(");
                    byte indexTwo = (byte) s.substring(0, s.length() - str.length()).indexOf(")");
                    char[] strToChar = s.substring(0, s.length() - str.length()).toCharArray();
                    for (int i = indexOne + 1; i < indexTwo; i++) {
                        scobki += strToChar[i];
                    }
                    if (isStr == true) {
                        phone = new Phone(s.substring(indexTwo + 2, s.length() - str.length()), Integer.parseInt(scobki), Integer.parseInt(splitForExt[1]));
                    } else
                        phone = new Phone(s.substring(indexTwo + 2, s.length() - str.length()), Integer.parseInt(scobki), -1);
                } else {

                    if (isStr == true) {
                        phone = new Phone(s.substring(3, s.length() - str.length()), Integer.parseInt(scobki), Integer.parseInt(splitForExt[1]));
                    } else
                        phone = new Phone(s.substring(4, s.length() - str.length()), Integer.parseInt(s.substring(0,3)), -1);
                }
            }
        }
        phoneList.add(phone);

            /*
            if((s.indexOf("ext")!=-1 ||s.indexOf("ext.")!=-1) &&){

            }
        }

             */
        /*
        Pattern pattern1NumberRegion = Pattern.compile("(\\(?([1-9][0-9]{2})\\)?[-. ]*)?");
        Pattern pattern1Ext = Pattern.compile("\\s*ext\\.?\\s*([0-9]+)");
        Matcher matcher = pattern.matcher(getText());
        Phone phone = null;
        while (matcher.find()) {
            numberPhones.add(matcher.group());
        }
            for (String s : numberPhones
            ) {
                Matcher matcher1 = pattern1NumberRegion.matcher(s);// код региона
                Matcher matcher2 = pattern1Ext.matcher(s);//доп номер
                if (matcher1.find() == false && matcher2.find() == true) {
                    phone = new Phone(s.substring(0, matcher2.group().length()), -1, Integer.parseInt(matcher2.group()));
                } else if (matcher1.find() == true && matcher2.find() == false) {
                    phone = new Phone(s.substring(matcher1.group().length(), s.length()), Integer.parseInt(matcher1.group()), -1);
                } else if (matcher1.find() == true && matcher2.find() == true) {
                    phone = new Phone(s.substring(matcher1.group().length(), s.length() - matcher2.group().length()),
                            Integer.parseInt(matcher1.group()), Integer.parseInt(matcher2.group()));
                } else {
                    phone = new Phone(s);
                }
                phoneList.add(phone);
            }
         */

        return phoneList;
    }

    @Override
    public String getFullName() {
        return null;
    }

    @Override
    public String getFirstName() {
        return null;
    }

    @Override
    public String getMiddleName() {
        return null;
    }

    @Override
    public String getLastName() {
        return null;
    }

    @Override
    public void updateLastName(String newLastName) {

    }

    @Override
    public void updatePhone(Phone oldPhone, Phone newPhone) {

    }

    @Override
    public void hide(String piece) {

    }

    @Override
    public void hidePhone(String phone) {

    }

    @Override
    public int unhideAll() {
        return 0;
    }
}
