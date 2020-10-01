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
    private List<Phone> phoneList = new ArrayList<>();
    private List<String> numberPhones = new ArrayList<>();
    private String textResume = null;
    private String fullName = "";
    private List<String> hideStr;
    private List<String> unHideStr;

    public CurriculumVitaeImpl() {
        super();
    }

    @Override
    public void setText(String text) {
        this.textResume = text;
        hideStr = new ArrayList<>();
        unHideStr = new ArrayList<>();
    }

    @Override
    public String getText() {
        if (textResume != null)
            return textResume;
        else
            throw new IllegalStateException();
    }

    @Override
    public List<Phone> getPhones() {
        Pattern pattern = Pattern.compile(PHONE_PATTERN);
        Pattern pattern1 = Pattern.compile("\\s*ext\\.?\\s*([0-9]+)");
        Matcher matcher = pattern.matcher(getText());
        Phone phone = null;
        boolean isStr = false;
        String str = "";
        String[] splitForExt = {"", ""};//второй эл- --- доп номер

        while (matcher.find()) {
            numberPhones.add(matcher.group());
        }
        for (String s : numberPhones
        ) {
            Matcher matcher1 = pattern1.matcher(s);
            while (matcher1.find()) {
                str = matcher1.group();
                splitForExt = matcher1.group().split("\\s*ext\\.?\\s*");
                isStr = true;
            }
            //если в строке 7 цифр
            if (s.substring(0, s.length() - str.length()).codePoints().filter(Character::isDigit).count() == 7) {
                if (isStr == false)
                    phone = new Phone(s, -1, -1);
                else
                    phone = new Phone(s, -1, Integer.parseInt(splitForExt[1]));
            } else {
                Pattern codeNumber = Pattern.compile("^(\\(?([1-9][0-9]{2})\\)?[-. ]*)");
                Matcher matcherCodeNumber = codeNumber.matcher(s.substring(0, s.length() - str.length()));
                boolean isCodeTrue = false;
                String brackets = "";
                int code = 0;
                while (matcherCodeNumber.find()) {
                    isCodeTrue = true;
                    brackets = matcherCodeNumber.group();
                    break;
                }
                Pattern codeOnly = Pattern.compile("([1-9][0-9]{2})");
                Matcher matcherCodeOnly = codeOnly.matcher(brackets);
                while (matcherCodeOnly.find()) {
                    code = Integer.parseInt(matcherCodeOnly.group());
                    break;
                }
                if (isCodeTrue == true && isStr == false) {
                    phone = new Phone(s, code, -1);
                }
                if (isCodeTrue == true && isStr == true) {
                    phone = new Phone(s, code, Integer.parseInt(splitForExt[1]));
                }
            }
            phoneList.add(phone);
        }
        return phoneList;
    }

    @Override
    public String getFullName() {
        String temp = "";
        if (textResume == null) {
            throw new IllegalStateException();
        } else {
            String pattern = "^((([A-Z]([a-z]+))[.]?) ([A-Z]([a-z]+)[.]?) ([A-Z]([a-z]+)[.]?)?)";
            Pattern patternFIO = Pattern.compile(pattern);
            Matcher matcher = patternFIO.matcher(getText());
            ArrayList<String> resultFullName = new ArrayList<>();
            boolean isFind = false;
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
            if ((isFind==false && resultFullName.size() < 2) || (isFind != true)) {
                throw new NoSuchElementException();
            } else {
                for (String s : resultFullName
                ) {
                    temp += s + " ";
                }
            }
        }
        fullName = temp.substring(0, temp.length() - 1);
        return fullName;
    }

    @Override
    public String getFirstName() {
        if (textResume != null) {
            if (getFullName().equals("")) {
                throw new NoSuchElementException();
            } else {
                String[] fullName = getFullName().split(" ");
                this.name = fullName[0];
                return name;
            }
        } else
            throw new IllegalStateException();
    }

    @Override
    public String getMiddleName() {
        if (getText() == null) {
            throw new IllegalStateException();
        } else if (getFullName().equals("")) {
            throw new NoSuchElementException();
        } else {
            String[] fullName = getFullName().split(" ");
            if (fullName.length == 2) {
                return null;
            } else {
                midName = fullName[1];
                return midName;
            }
        }
    }

    @Override
    public String getLastName() {
        if (getText() != null) {
            if (getFullName().equals("")) {
                throw new NoSuchElementException();
            } else {
                String[] fullNames = getFullName().split(" ");
                lastName = fullNames[fullNames.length - 1];
                return lastName;
            }
        } else
            throw new IllegalStateException();
    }

    @Override
    public void updateLastName(String newLastName) {
        if (getText() == null) {
            throw new IllegalStateException();
        } else if (getFullName().equals("")) {
            throw new NoSuchElementException();
        } else {
            String str[] = getText().split(getLastName());
            textResume = str[0] + newLastName;
        }
    }

    @Override
    public void updatePhone(Phone oldPhone, Phone newPhone) {
        String oldPhoneStr = oldPhone.getNumber();
        String newPhoneStr = newPhone.getNumber();
        if (getText() != null) {
            if (getText().contains(oldPhoneStr)) {
                textResume = getText().replaceAll(oldPhoneStr, newPhoneStr);
            } else
                throw new IllegalArgumentException();
        } else
            throw new IllegalStateException();
    }

    @Override
    public void hide(String piece) {
        //fullName=getFullName();
        boolean isTrue = false;
        if (textResume != null) {
            if (textResume.contains(piece)) {
                //String result=piece.replaceAll("[^.@ ]","X");
                textResume = textResume.replace(piece, piece.replaceAll("[^.@ ]", "X"));
                hideStr.add(piece.replaceAll("[^.@ ]", "X"));
                unHideStr.add(piece);
            } else
                throw new IllegalArgumentException();

        } else
            throw new IllegalStateException();
    }

    @Override
    public void hidePhone(String phone) {
        boolean isTrue = false;
        //fullName=getFullName();
        if (textResume != null) {
            if (textResume.contains(phone)) {
                textResume = textResume.replace(phone, phone.replaceAll("\\d", "X"));
                hideStr.add(phone.replaceAll("\\d", "X"));
                unHideStr.add(phone);
            } else
                throw new IllegalArgumentException();
        } else
            throw new IllegalStateException();
    }

    @Override
    public int unhideAll() {
        int count = 0;
        if (textResume == null)
            throw new IllegalStateException();
        else {
            if (hideStr.size() != 0 && unHideStr.size() != 0 && hideStr.size() == unHideStr.size()) {
                for (int i = 0; i < hideStr.size(); i++) {
                    for (int j = 0; j < unHideStr.size(); j++) {
                        if (hideStr.get(i).equals(unHideStr.get(j).replaceAll("[^.@( )-]", "X"))) {
                            textResume = textResume.replaceAll(hideStr.get(i), unHideStr.get(j));
                            count++;
                            break;
                        }
                    }
                }
            }
        }
        return count;
    }
}
