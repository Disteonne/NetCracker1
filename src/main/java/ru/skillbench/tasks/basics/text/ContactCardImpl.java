package ru.skillbench.tasks.basics.text;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class ContactCardImpl implements ContactCard {
    private String FN;
    private String ORG;
    private String GENDER;
    private String BDAY;
    private String[] TEL;
    private String BEGIN;
    private String END;

    /*
    public ContactCardImpl(String FN, String ORG, String GENDER, String BDAY, String[] TEL) {
        this.FN = FN;
        this.ORG = ORG;
        this.GENDER = GENDER;
        this.BDAY = BDAY;
        TEL = new String[5];
        for (int i = 0; i < TEL.length; i++) {
            TEL[i] = "";
        }
        }
     */

    public ContactCardImpl() {
        TEL = new String[5];
        for (int i = 0; i < TEL.length; i++) {
            TEL[i] = "";
        }

    }

    @Override
    public ContactCardImpl getInstance(Scanner scanner) {
        String parseStr = scanner.nextLine();
        ArrayList<String> list = new ArrayList<>();
        while (scanner.hasNext()) {
            list.add(scanner.nextLine());
        }
        //Для проверки:
        //String parseStr = "FN: Forrest Gump\\r\\nORG:Bubba Gump Shrimp Co.\\r\\nGENDER:M\\r\\nTEL;TYPE=HOME:4951234567\\r\\nTEL;TYPE=HOME:4951234567\\r\\nBEGINnBEGIN: kek\\r\\nEND: kek1";
        ContactCardImpl contactCard = new ContactCardImpl();
        //String str[]=parseStr.split("\r\n"); <---not working
        //String result = parseStr.replace("\\r\\n", "&");
        //String str[] = result.split("&");
        byte countFN = 0;
        byte countORG = 0;
        byte countBEGIN = 0;
        byte countEND = 0;
        int countAddTel = 0;
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).indexOf(':') == -1) {
                throw new InputMismatchException();
            } else {
                if (list.get(i).startsWith("FN")) {
                    contactCard.FN = list.get(i);
                    countFN++;
                }
                if (list.get(i).startsWith("ORG")) {
                    contactCard.ORG = list.get(i);
                    countORG++;
                }
                if (list.get(i).startsWith("BEGIN")) {
                    contactCard.BEGIN = list.get(i);
                    countBEGIN++;
                }
                if (list.get(i).startsWith("END")) {
                    contactCard.END = list.get(i);
                    countEND++;
                }
                if (list.get(i).startsWith("BDAY")) {
                    contactCard.BDAY = list.get(i);
                }
                if (list.get(i).startsWith("GENDER")) {
                    contactCard.GENDER = list.get(i);
                }
                if (list.get(i).startsWith("TEL")) {
                    char[] strTOchar = list.get(i).toCharArray();
                    if (strTOchar[3] != ';') {
                        throw new InputMismatchException();
                    } else {
                        String[] tempStr = list.get(i).split(":");
                        char[] phoneNumbers = tempStr[1].toCharArray();
                        for (int j = 0; j < phoneNumbers.length; j++) {
                            if (phoneNumbers[j] == ' ' || phoneNumbers[j] == '.' || phoneNumbers[j] == '+')
                                throw new InputMismatchException();
                        }
                        if (phoneNumbers.length < 10 || phoneNumbers.length > 10) {
                            throw new InputMismatchException();
                        } else {
                            contactCard.TEL[countAddTel] = list.get(i);
                            countAddTel++;
                        }
                    }
                }
            }
        }
        //Условие обязательых полей
        if (countBEGIN == 0 && countEND == 0 && countFN == 0 && countORG == 0) {
            throw new NoSuchElementException();
        }
        //Проверка типов
        byte countTypeWORK = -1;
        byte countTypeHOME = -1;
        for (int i = 0; i < contactCard.TEL.length; i++) {
            if (contactCard.TEL[i] != "") {
                String[] checkType = contactCard.TEL[i].split("=");
                if (checkType[1].startsWith("WORK")) {
                    countTypeWORK++;
                }
                if (checkType[1].startsWith("HOME")) {
                    countTypeHOME++;
                }
            }
        }
        if (countTypeHOME > 0 || countTypeHOME > 0) {
            throw new InputMismatchException();
        }
        return contactCard;
    }

    @Override
    public ContactCard getInstance(String data) {
        // Scanner scanner = new Scanner(data);
        // ContactCard newContactCard = getInstance(scanner);
        return getInstance(new Scanner(data));
    }

    @Override
    public String getFullName() {
        String[] strSplit = FN.split(":");
        return strSplit[1];
    }

    @Override
    public String getOrganization() {
        String[] strSplit = ORG.split(":");
        return strSplit[1];
    }

    @Override
    public boolean isWoman() {
        String[] genderToStringArray = this.GENDER.split(":");
        if (genderToStringArray[1].equals("M") || this.GENDER == null) {
            return false;
        } else if(genderToStringArray[1].equals("F"))
            return true;
        else
            throw new InputMismatchException();
    }

    @Override
    public Calendar getBirthday() throws InputMismatchException {
        Calendar calendar;
        if (this.BDAY == null) {
            throw new NoSuchElementException();
        } else {
            DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("DD-MM-YYYY", Locale.ENGLISH);
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("DD-MM-YYYY", Locale.ENGLISH);
            try {
                Date date = simpleDateFormat.parse(BDAY);
                calendar = Calendar.getInstance();
                calendar.setTime(date);
            } catch (ParseException ex) {
                throw new InputMismatchException();
            }
        }
        //Calendar calendar=Calendar.getInstance();
        //calendar.setTime(date);
        //LocaleData localeData= LocalDate.parse(
        /*
        try {
           // dateFormat.;
           // calendar.setTime(dateFormat.get);
            calendar.setTime(dateFormat.parse(BDAY));
        }catch (ParseException ex){
            throw new InputMismatchException();
        }
         */
        return calendar;
    }

    @Override
    public Period getAge() {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy", Locale.ENGLISH);
        String[] strSplit = BDAY.split(":");
        LocalDate date1 = LocalDate.parse(strSplit[1], dateTimeFormatter);
        String[] str = LocalDate.now().toString().split("-");
        String newData = str[2] + "-" + str[1] + "-" + str[0];
        LocalDate date2 = LocalDate.parse(newData, dateTimeFormatter);
        Period period = Period.between(date1, date2);
        return period;
    }

    @Override
    public int getAgeYears() {
        return getAge().getYears();
    }

    @Override
    public String getPhone(String type) {
        String number = "(";
        for (String s : this.TEL
        ) {
            if (s != "") {
                String[] strSplit = s.split("=");
                if (strSplit[1].startsWith(type)) {
                    String[] strSplitOne = strSplit[1].split(":");
                    char[] strToChar = strSplitOne[1].toCharArray();
                    for (int i = 0; i < 3; i++) {
                        number += strToChar[i];
                    }
                    number += ") ";
                    for (int i = 3; i < 6; i++) {
                        number += strToChar[i];
                    }
                    number += "-";
                    for (int i = 6; i < strToChar.length; i++) {
                        number += strToChar[i];
                    }
                } else
                    throw new NoSuchElementException();
            }

        }
        return number;
    }

    /*
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        //ContactCardImpl contactCard = getInstance(scanner);

    }
     */
}
