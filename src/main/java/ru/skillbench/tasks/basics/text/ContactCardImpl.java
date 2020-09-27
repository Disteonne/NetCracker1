package ru.skillbench.tasks.basics.text;
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
        //Для проверки:
        //String parseStr = "FN: Forrest Gump\\r\\nORG:Bubba Gump Shrimp Co.\\r\\nGENDER:M\\r\\nTEL;TYPE=HOME:4951234567\\r\\nTEL;TYPE=HOME:4951234567\\r\\nBEGINnBEGIN: kek\\r\\nEND: kek1";
        ContactCardImpl contactCard = new ContactCardImpl();
        //String str[]=parseStr.split("\r\n"); <---not working
        String result = parseStr.replace("\\r\\n", "&");
        String str[] = result.split("&");
        byte countFN = 0;
        byte countORG = 0;
        byte countBEGIN = 0;
        byte countEND = 0;
        int countAddTel = 0;
        for (int i = 0; i < str.length; i++) {
            if (str[i].indexOf(':') == -1) {
                throw new InputMismatchException();
            } else {
                if (str[i].startsWith("FN")) {
                    contactCard.FN = str[i];
                    countFN++;
                }
                if (str[i].startsWith("ORG")) {
                    contactCard.ORG = str[i];
                    countORG++;
                }
                if (str[i].startsWith("BEGIN")) {
                    contactCard.BEGIN = str[i];
                    countBEGIN++;
                }
                if (str[i].startsWith("END")) {
                    contactCard.END = str[i];
                    countEND++;
                }
                if (str[i].startsWith("BDAY")) {
                    contactCard.BDAY = str[i];
                }
                if (str[i].startsWith("GENDER")) {
                    contactCard.GENDER = str[i];
                }
                if (str[i].startsWith("TEL")) {
                    char[] strTOchar = str[i].toCharArray();
                    if (strTOchar[3] != ';') {
                        throw new InputMismatchException();
                    } else {
                        String[] tempStr = str[i].split(":");
                        char[] phoneNumbers = tempStr[1].toCharArray();
                        if (phoneNumbers.length!=10) {
                            throw new InputMismatchException();
                        } else {
                            contactCard.TEL[countAddTel] = str[i];
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
        Scanner scanner=new Scanner(System.in);
        ContactCard newContactCard=getInstance(scanner);
        return newContactCard;
    }

    @Override
    public String getFullName() {
        return this.FN;
    }

    @Override
    public String getOrganization() {
        return this.ORG;
    }

    @Override
    public boolean isWoman() {
        String[] genderToStringArray=this.GENDER.split(":");
        if(genderToStringArray[1]=="M" ||this.GENDER==null){
            return false;
        }
        else
            return true;
    }

    @Override
    public Calendar getBirthday() throws InputMismatchException {

        Calendar calendar=Calendar.getInstance();
        DateTimeFormatter dateFormat=DateTimeFormatter.ofPattern("DD-MM-YYYY", Locale.ENGLISH);
        try {
           // dateFormat.;
           // calendar.setTime(dateFormat.get);
            calendar.setTime(dateFormat.parse(BDAY));
        }catch (ParseException ex){
            throw new InputMismatchException();
        }
        if(this.BDAY==null){
            throw new NoSuchElementException();
        }
        return null;
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public Period getAge() {
        return null;
    }

    @Override
    public int getAgeYears() {
        return 0;
    }

    @Override
    public String getPhone(String type) {
        String number="";
        for (String s: this.TEL
             ) {
            if(s!=""){
                String[] strSplit=s.split("=");
                if(strSplit[1].startsWith(type)){
                    String[] strSplitOne=strSplit[1].split(":");
                    char[] strToChar=strSplitOne[1].toCharArray();
                    for (int i = 0; i < 3; i++) {
                        number+="("+strToChar[i];
                    }
                    number+=") ";
                    for (int i = 3; i <6 ; i++) {
                        number+=strToChar[i];
                    }
                    number+="-";
                    for (int i = 6; i <strToChar.length ; i++) {
                        number+=strToChar[i];
                    }
                }
                else
                    throw new NoSuchElementException();
            }

        }
        return number;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        //ContactCardImpl contactCard = getInstance(scanner);

    }
}
