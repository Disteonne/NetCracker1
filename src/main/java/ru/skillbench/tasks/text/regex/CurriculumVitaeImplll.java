package ru.skillbench.tasks.text.regex;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CurriculumVitaeImplll implements CurriculumVitae {
    private String textResume = "";
    boolean checkText = false;
    private String fullName = "";
    private List<String> hideStr = new ArrayList<>();
    private List<String> unHideStr = new ArrayList<>();


    @Override
    public void setText(String text) {
        this.textResume = text;
        checkText = true;
    }


    @Override
    public String getText() {
        if (textResume == null || !checkText) {
            throw new IllegalStateException();
        } else
            return textResume;
    }
    // - - - - - - - - - - - - -
    @Override
    public List<Phone> getPhones() {
        String resume_text_2 = getText();
        String regionCode = "";
        String extension;
        List<Phone> phones = new ArrayList<>();
        Pattern pattern = Pattern.compile(PHONE_PATTERN);
        Pattern pattern2;
        Matcher matcher2;
        Matcher matcher = pattern.matcher(resume_text_2);
        while (matcher.find()) {
            String phone_str = matcher.group();
            String phone_str_rem = phone_str.replaceAll("[-()\\s]", "");
            if (phone_str_rem.length() > 7)// region code
            {
                pattern2 = Pattern.compile("^(\\(?[1-9][0-9]{2}\\)?)");
                matcher2 = pattern2.matcher(phone_str);
                while (matcher2.find()) {
                    regionCode = matcher2.group();
                    regionCode = regionCode.replaceAll("[()]", "");
                }
            } else regionCode = "-1";
            if (phone_str.contains("ext")) {
                extension = phone_str.substring(phone_str.indexOf("ext") + 3);
                extension = extension.replaceAll("[.\\s]", "");
            } else extension = "-1";
            Phone founded = new Phone(phone_str, Integer.parseInt(regionCode), Integer.parseInt(extension));
            phones.add(founded);
        }
        return phones;
    }


    /**
     * @return String fullName
     * Получаем текст резюме.Ипользуем регулярные выражения для поиска 2 слов (минимум),состоящих минимум из 2 букв,где первая из
     * обязательно заглавная.(Только англ.)
     * Создаем булев.переменную,которая в последствии ответит на вопрос "было ли найдено данное рег.выражение в тексте резюме".
     * Изначально isFind=false.
     * Если рег.выражение найдено,то сплитим пробелами и добавляем в список.
     * Проверка на ограничение кол-ва слов убрана,поскольку регулярное выражение построено т.о.,который позволяет
     * сразу же находить от 2 до 3 слов.
     * Если isFind!=true,то выбрасываем эксепшн,а если же данное рег.выражение было найдено,то собираем fullName и возвращаем.
     */
    @Override
    public String getFullName() {
        String temp = "";
        String textResumeTwo = getText();
        //^([A-Z][a-z]{1,}[.]?\s[A-Z][a-z]{1,}[.]?\s([A-Z][a-z]{1,}[.]?)?)
        //^([A-Z][a-z]{1,}[.]?\s){2,3}
        //(([A-Z]([a-z]+))[.]?) (([A-Z]([a-z]+))[.]?) ([A-Z]([a-z]+)[.]?){0,1}
        String pattern = "^(((([A-Z]([a-z]+))[.]?)\\s([A-Z]([a-z]+)[.]?)){1}[\\s]?([A-Z]([a-z]+)[.]?[\\s]?)?)";
        Pattern patternFIO = Pattern.compile(pattern);
        Matcher matcher = patternFIO.matcher(textResumeTwo);
        ArrayList<String> resultFullName = new ArrayList<>();
        boolean isFind = false;
        String name="";
        while (matcher.find()) {
            String[] result = matcher.group().split(" ");
            //Засунули все в список
            for (String s : result
            ) {
                resultFullName.add(s);
            }
            isFind = true;
            break;
        }
        if (!isFind) {
            throw new NoSuchElementException();
        } else {
            //name=resultFullName.get(0);
            for (String s : resultFullName
            ) {
                temp += s + " ";
            }
        }
        fullName = temp.substring(0, temp.length() - 1);
        return fullName;
    }


    @Override
    public String getFirstName() {
        String firstName = getFullName();
        String[] fullName = firstName.split(" ");
        firstName = fullName[0];
        return firstName;
    }
    @Override
    public String getMiddleName() {
        String middleName = getFullName();
        String[] fullName = middleName.split(" ");
        return fullName.length == 2 ? null : fullName[1];
    }

    @Override
    public String getLastName() {
        String lastName = getFullName();
        String[] fullNames = lastName.split(" ");
        lastName = fullNames[fullNames.length - 1];
        return lastName;
    }

    @Override
    public void updateLastName(String newLastName) {
        if (newLastName.equals(""))
            return;
        String textRes = getText();
        String currFullName = getFullName();
        String newName = getFirstName() + " " + getMiddleName() + " " + newLastName;
        textRes = textRes.replaceFirst(currFullName, newName);
        setText(textRes);
    }

    @Override
    public void updatePhone(Phone oldPhone, Phone newPhone) {
        if (oldPhone.getNumber().equals("") || newPhone.getNumber().equals("")) return;
        String oldPhoneStr = oldPhone.getNumber();
        String newPhoneStr = newPhone.getNumber();
        String textRes = getText();
        if (getText().contains(oldPhoneStr)) {
            textRes = textRes.replaceAll(oldPhoneStr, newPhoneStr);
        } else
            throw new IllegalArgumentException();
        setText(textRes);
    }

    @Override
    public void hide(String piece) {
        String textRes = getText();
        if (textRes.contains(piece)) {
            //String result=piece.replaceAll("[^.@ ]","X");
            textRes = textRes.replace(piece, piece.replaceAll("[^.@ ]", "X"));
            setText(textRes);
            hideStr.add(piece.replaceAll("[^.@ ]", "X"));
            unHideStr.add(piece);
        } else
            throw new IllegalArgumentException();
    }

    @Override
    public void hidePhone(String phone) {
        String textRes = getText();
        if (textRes.contains(phone)) {
            textRes = textRes.replace(phone, phone.replaceAll("\\d", "X"));
            setText(textRes);
            hideStr.add(phone.replaceAll("\\d", "X"));
            unHideStr.add(phone);
        } else
            throw new IllegalArgumentException();
    }

    @Override
    public int unhideAll() {
        String textRes = getText();
        int count = 0;
        if (hideStr.size() != 0 && unHideStr.size() != 0 && hideStr.size() == unHideStr.size()) {
            for (int i = 0; i < hideStr.size(); i++) {
                for (int j = 0; j < unHideStr.size(); j++) {
                    if (hideStr.get(i).equals(unHideStr.get(j).replaceAll("[^.@( )-]", "X"))) {
                        String res=textRes.replace(hideStr.get(i), unHideStr.get(j));
                        String unh=unHideStr.get(j);
                        String h=hideStr.get(i);
                        textRes =res;
                        count++;
                        break;
                    }
                }
            }
        }
        setText(textRes);
        return count;
    }
}
