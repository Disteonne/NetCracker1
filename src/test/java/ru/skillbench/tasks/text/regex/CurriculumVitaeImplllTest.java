package ru.skillbench.tasks.text.regex;

import junit.framework.TestCase;
import org.junit.Test;

import java.util.NoSuchElementException;

public class CurriculumVitaeImplllTest extends TestCase {
    private String[] wrongFullName={"Иванов Игорь Александрович ","M alex ","O 999 44 44",
            "Olegov PE Ivanovich800 270 22 44 ext 4","OLeGov Petr Ivanovich 890 555 55 55 ext 8",
            "Oleg. ext. 777"};
    private String[] goodFullName={"Max Alex Petr (800)5556641","Max. Alex Ivanov ","Mc Hovanski. ext. 990",
            "Mc Hw. ","Diana Razmikovna Dyatel 920 111 69 97"};

    @Test
    public void testSetText() {
        CurriculumVitae curriculumVitae = new CurriculumVitaeImplll();
        curriculumVitae.setText("Иванов Игорь Александрович 800 270 22 44");
        assertEquals("Иванов Игорь Александрович 800 270 22 44", curriculumVitae.getText());
    }

    @Test
    public void testGetText() {
        CurriculumVitae curriculumVitae = new CurriculumVitaeImplll();
        curriculumVitae.setText("Максимов Александр Петрович 800 555 66 41");
        assertEquals("Максимов Александр Петрович 800 555 66 41", curriculumVitae.getText());
    }

    @Test
    public void testGetPhones1() {
        CurriculumVitae curriculumVitae = new CurriculumVitaeImplll();
        curriculumVitae.setText("Максимов Александр Петрович 800 555-66-41");
        //String str = curriculumVitae.getPhones().get(0).getNumber();
        assertEquals("800 555-66-41", curriculumVitae.getPhones().get(0).getNumber());
    }

    @Test
    public void testGetPhones2() {
        CurriculumVitae curriculumVitae = new CurriculumVitaeImplll();
        curriculumVitae.setText("Максимов Александр Петрович (800) 555-66-41 ext. 6");
        //String str=curriculumVitae.getPhones().get(0).getNumber();
        assertEquals("(800) 555-66-41 ext. 6", curriculumVitae.getPhones().get(0).getNumber());
        assertEquals(800, curriculumVitae.getPhones().get(0).getAreaCode());
        assertEquals(6, curriculumVitae.getPhones().get(0).getExtension());
    }

    @Test
    public void testGetPhones3() {
        CurriculumVitae curriculumVitae = new CurriculumVitaeImplll();
        curriculumVitae.setText("Максимов Александр Петрович (800) 555 6641 ext. 6");
        //String str=curriculumVitae.getPhones().get(0).getNumber();
        assertEquals("(800) 555 6641 ext. 6", curriculumVitae.getPhones().get(0).getNumber());
        assertEquals(800, curriculumVitae.getPhones().get(0).getAreaCode());
        assertEquals(6, curriculumVitae.getPhones().get(0).getExtension());
    }

    @Test
    public void testGetPhones4() {
        CurriculumVitae curriculumVitae = new CurriculumVitaeImplll();
        curriculumVitae.setText("Максимов Александр Петрович (800) 555 6641 ext. 6 930 888 3322 ext 888");
        assertEquals("930 888 3322 ext 888", curriculumVitae.getPhones().get(1).getNumber());
        //assertEquals(930, curriculumVitae.getPhones().get(1).getAreaCode());
        //assertEquals(888, curriculumVitae.getPhones().get(1).getExtension());
    }

    @Test
    public void testGetPhones5() {
        CurriculumVitae curriculumVitae = new CurriculumVitaeImplll();
        curriculumVitae.setText("Максимов Александр Петрович (800)555 6641");
        assertEquals("555 6641", curriculumVitae.getPhones().get(0).getNumber());
        assertEquals(800, curriculumVitae.getPhones().get(0).getAreaCode());
        //assertEquals(6, curriculumVitae.getPhones().get(0).getExtension());
    }

    @Test
    public void testGetPhones6() {
        CurriculumVitae curriculumVitae = new CurriculumVitaeImplll();
        curriculumVitae.setText("Максимов Александр Петрович (800)5556641");
        assertEquals("5556641", curriculumVitae.getPhones().get(0).getNumber());
        assertEquals(800, curriculumVitae.getPhones().get(0).getAreaCode());
        //assertEquals(6, curriculumVitae.getPhones().get(0).getExtension());
    }
    @Test
    public void testGetPhones7() {
        CurriculumVitae curriculumVitae = new CurriculumVitaeImplll();
        curriculumVitae.setText("Максимов Александр Петрович (609) 234-5678");
        String result=curriculumVitae.getPhones().get(0).toString();
        assertEquals("(609) 234-5678{NO EXT}", curriculumVitae.getPhones().get(0).toString());
        //assertEquals(6, curriculumVitae.getPhones().get(0).getExtension());
    }
    @Test
    public void testGetPhones8() {
        CurriculumVitae curriculumVitae = new CurriculumVitaeImplll();
        curriculumVitae.setText("Alex Miller (609)      234-5678");
        //String result=curriculumVitae.getPhones().get(0).toString();
        assertEquals(609, curriculumVitae.getPhones().get(0).getAreaCode());
        //assertEquals(6, curriculumVitae.getPhones().get(0).getExtension());
    }


    @Test
    public void testGetFullName1() {
        CurriculumVitae curriculumVitae = new CurriculumVitaeImplll();
        curriculumVitae.setText("Max Alex Petr (800)5556641");
        //String result=curriculumVitae.getFullName();
        assertEquals("Max Alex Petr", curriculumVitae.getFullName());
    }
    @Test
    public void testGetFullName2(){
        CurriculumVitae curriculumVitae = new CurriculumVitaeImplll();
        //curriculumVitae.setText(goodFullName[0]);
        //assertEquals("Max Alex Petr ",curriculumVitae.getFullName());

        //curriculumVitae.setText(goodFullName[1]);
        //assertEquals("Max. Alex Ivanov ",curriculumVitae.getFullName());

        //curriculumVitae.setText(goodFullName[2]);
        //assertEquals("Mc Hovanski. ",curriculumVitae.getFullName());
        curriculumVitae.setText(goodFullName[3]);
        assertEquals("Mc Hw.",curriculumVitae.getFullName());
    }
    @Test(expected = NoSuchElementException.class)
    public void testGetFullName3(){

        CurriculumVitae curriculumVitae = new CurriculumVitaeImplll();

        //curriculumVitae.setText(wrongFullName[0]);

        //curriculumVitae.setText(wrongFullName[1]);

        curriculumVitae.setText(wrongFullName[2]);

        //curriculumVitae.setText(wrongFullName[3]);
        //assertEquals(" ",curriculumVitae.getFullName());

        //curriculumVitae.setText(wrongFullName[4]);
        //assertEquals(" ",curriculumVitae.getFullName());

        //curriculumVitae.setText(wrongFullName[5]);
        //assertEquals(" ",curriculumVitae.getFullName());
    }
    @Test(expected = IllegalStateException.class)
    public void testGetFullName4(){
        CurriculumVitae curriculumVitae=new CurriculumVitaeImplll();
        curriculumVitae.getFullName();
    }
    @Test
    public void testGetFullName5(){
        CurriculumVitae curriculumVitae=new CurriculumVitaeImplll();
        curriculumVitae.setText("Junior Talnted Student Frist Campus Center Genome Integrity Human Disease\\n\" +\n" +
                "                \" Phylogenetic Reassignment Princeton University Spring Residential College Princeton University \" +\n" +
                "                \"September Career Peer Princeton University September Microsoft Office");
        String str=curriculumVitae.getFullName();
        assertEquals("",curriculumVitae.getFullName());
    }

    @Test
    public void testGetFirstName(){
        CurriculumVitae curriculumVitae=new CurriculumVitaeImplll();
        curriculumVitae.setText(goodFullName[1]);
        curriculumVitae.getFullName();
        assertEquals("Max.",curriculumVitae.getFirstName());
    }
    @Test
    public void testGetLastName(){
        CurriculumVitae curriculumVitae=new CurriculumVitaeImplll();
        curriculumVitae.setText(goodFullName[4]);
        curriculumVitae.getFullName();
        assertEquals("Razmikovna",curriculumVitae.getMiddleName());
    }
    @Test
    public void testMiddleName(){
        CurriculumVitae curriculumVitae=new CurriculumVitaeImplll();
        curriculumVitae.setText("John Mybh ");
        assertEquals("",curriculumVitae.getMiddleName());
    }
    @Test
    public void testUpdateLastName(){
        CurriculumVitae curriculumVitae=new CurriculumVitaeImplll();
        curriculumVitae.setText("Diana Razmikovna Dyatel ");
        curriculumVitae.getFullName();
        curriculumVitae.updateLastName("Barova");
        assertEquals("Diana Razmikovna Barova",curriculumVitae.getText());
    }
    @Test
    public void testUpdateLastName1(){
        CurriculumVitae curriculumVitae=new CurriculumVitaeImplll();
        curriculumVitae.setText("Diana Razmikovna Dyatel 800 999 11 22 ext.3");
        curriculumVitae.getPhones();
        curriculumVitae.getFullName();
        curriculumVitae.updateLastName("Zotina");
        String str= curriculumVitae.getText();;
        assertEquals("Diana Razmikovna Zotina 800 999 11 22 ext.3",curriculumVitae.getText());
    }
    @Test(expected = IllegalStateException.class)
    public void testUpdateLastName2(){
        CurriculumVitae curriculumVitae=new CurriculumVitaeImplll();
        curriculumVitae.updateLastName("Zotina");
        assertEquals("Diana Razmikovna Zotina",curriculumVitae.getText());
    }
    @Test(expected = NoSuchElementException.class)
    public void testUpdateLastName3(){
        CurriculumVitae curriculumVitae=new CurriculumVitaeImplll();
        curriculumVitae.setText("Diana R");
        curriculumVitae.getFullName();
        curriculumVitae.updateLastName("Zotina");
        assertEquals("",curriculumVitae.getText());
    }
    @Test
    //Для явного внесения объекта телефона в список
    public void testUpdatePhone1(){
        CurriculumVitae curriculumVitae=new CurriculumVitaeImplll();
        curriculumVitae.setText(goodFullName[0]);
        CurriculumVitae.Phone newPhone=new CurriculumVitae.Phone("111-55-66",-1,-1);
        CurriculumVitae.Phone oldPhone=new CurriculumVitae.Phone("5556641",800,-1);
        curriculumVitae.updatePhone(oldPhone,newPhone);
        //assertEquals("5556641",curriculumVitae.getPhones().get(0).getNumber());
        assertEquals("(800)111-55-66",curriculumVitae.getPhones().get(0).getNumber());
    }
    @Test
    //Для замены тлф в строке
    public void testUpdatePhone2(){
        CurriculumVitae curriculumVitae=new CurriculumVitaeImplll();
        curriculumVitae.setText("Alex Miller 800 5556641");
        CurriculumVitae.Phone newPhone=new CurriculumVitae.Phone("111-55-66",-1,-1);
        CurriculumVitae.Phone oldPhone=new CurriculumVitae.Phone("5556641",800,-1);
        curriculumVitae.updatePhone(oldPhone,newPhone);
        assertEquals("Alex Miller 800 111-55-66",curriculumVitae.getText());
    }
    @Test
    public void testHide(){
        CurriculumVitae curriculumVitae=new CurriculumVitaeImplll();
        curriculumVitae.setText("Diana. Dyatel ");
        curriculumVitae.hide("Diana.");
        assertEquals("XXXXX. Dyatel ",curriculumVitae.getText());
    }

    @Test
    public void testHidePhone(){
        CurriculumVitae curriculumVitae=new CurriculumVitaeImplll();
        curriculumVitae.setText("Diana. Dyatel (920) 111-69-97");
        curriculumVitae.hidePhone("(920) 111-69-97");
        assertEquals("Diana. Dyatel (XXX) XXX-XX-XX",curriculumVitae.getText());
    }
    @Test
    public void testUnHideAll(){
        CurriculumVitae curriculumVitae=new CurriculumVitaeImplll();
        curriculumVitae.setText("Diana. Dyatel (920) 111-69-97");
        curriculumVitae.hide("Diana.");
        curriculumVitae.hidePhone("(920) 111-69-97");
        //assertEquals("",curriculumVitae.getText());
        curriculumVitae.unhideAll();
        int count=curriculumVitae.unhideAll();
        assertEquals("Diana. Dyatel (920) 111-69-97",curriculumVitae.getText());
    }

    @Test
    public void testListPhoneSize(){
        CurriculumVitae curriculumVitae=new CurriculumVitaeImplll();
        curriculumVitae.setText("Diana. Dyatel (920) 111-69-97 (999) 222 3344 800 222 11 11");
        //assertEquals(-1,curriculumVitae.getPhones().get(2).getExtension());
        assertEquals(3,curriculumVitae.getPhones().size());
    }
    @Test
    public void testGetLastName4(){
        CurriculumVitae curriculumVitae=new CurriculumVitaeImplll();
        curriculumVitae.setText("Diana Dyatel ");
        String str=curriculumVitae.getLastName();
        assertEquals("Dyatel",curriculumVitae.getLastName());
    }

}
