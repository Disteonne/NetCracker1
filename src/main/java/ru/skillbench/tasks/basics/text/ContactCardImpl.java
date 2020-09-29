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
    private Calendar calendarOne;

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
        //String parseStr = scanner.nextLine();
        ArrayList<String> list = new ArrayList<>();
        while (scanner.hasNext()) {
            list.add(scanner.nextLine());
        }

        for (String s : list
        ) {
            System.out.println(s);
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
                    continue;
                }
                if (list.get(i).startsWith("ORG")) {
                    contactCard.ORG = list.get(i);
                    countORG++;
                    continue;
                }
                if (list.get(i).startsWith("BEGIN")) {
                    contactCard.BEGIN = list.get(i);
                    countBEGIN++;
                    continue;
                }
                if (list.get(i).startsWith("END")) {
                    contactCard.END = list.get(i);
                    countEND++;
                    continue;
                }
                if (list.get(i).startsWith("BDAY")) {
                    contactCard.BDAY = list.get(i);
                    //Calendar calendar;
                    //DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("DD-MM-YYYY", Locale.ENGLISH);
                    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy", Locale.ENGLISH);
                    String[] str = contactCard.BDAY.split(":");
                    try {
                        Date date = simpleDateFormat.parse(str[1]);
                        calendarOne = Calendar.getInstance();
                        calendarOne.setTime(date);
                        //calendarOne=calendar;
                    } catch (ParseException ex) {
                        throw new InputMismatchException();
                    }
                    continue;
                }
                if (list.get(i).startsWith("GENDER")) {
                    contactCard.GENDER = list.get(i);
                    continue;
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
        if (countBEGIN == 0 || countEND == 0 || countFN == 0 || countORG == 0) {
            throw new NoSuchElementException();
        }
        //Проверка GENDER
        if (contactCard.GENDER != null) {
            String[] gender = contactCard.GENDER.split(":");
            if (gender[1].equals("M") == false) {
                if (gender[1].equals("F") == false)
                    throw new InputMismatchException();
            }

        }


        //Проверка типов
        if (contactCard.TEL[0] == null && contactCard.TEL[1] == null && contactCard.TEL[2] == null &&
                contactCard.TEL[3] == null && contactCard.TEL[4] == null) {
            throw new NoSuchElementException();
        } else {
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

            if (countTypeWORK > 0 || countTypeHOME > 0) {
                throw new InputMismatchException();
            }
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

        if (this.GENDER != null) {
            String[] genderToStringArray = this.GENDER.split(":");
            if (genderToStringArray[1].equals("M")) {
                return false;
            } else
                return true;
        } else
            return false;
        /*
        String[] genderToStringArray=new String[2];
        if (genderToStringArray[1].equals("M") || this.GENDER == null) {
            if(GENDER==null) {
                return false;
            }
            else
                genderToStringArray=this.GENDER.split(":");

        } else if(genderToStringArray[1].equals("F"))
            return true;
        else
            throw new InputMismatchException();
         */
    }

    @Override
    public Calendar getBirthday() throws InputMismatchException {
        Calendar calendar;
        if (this.BDAY == null) {
            throw new NoSuchElementException();
        } else {
            return calendarOne;
        }
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
                }
            }
        }
        return number;
    }
}