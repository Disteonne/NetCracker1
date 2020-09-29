package ru.skillbench.tasks.text.regex;

import junit.framework.TestCase;
import org.junit.Test;

public class CurriculumVitaeImplTest extends TestCase {

    @Test
    public void testSetText() {
        CurriculumVitae curriculumVitae=new CurriculumVitaeImpl();
        curriculumVitae.setText("Иванов Игорь Александрович 800 270 22 44");
        assertEquals("Иванов Игорь Александрович 800 270 22 44",curriculumVitae.getText());
    }
    @Test
    public void testGetText() {
        CurriculumVitae curriculumVitae=new CurriculumVitaeImpl();
        curriculumVitae.setText("Максимов Александр Петрович 800 555 66 41");
        assertEquals("Максимов Александр Петрович 800 555 66 41",curriculumVitae.getText());
    }
    @Test
    public void testGetPhones1() {
        CurriculumVitae curriculumVitae=new CurriculumVitaeImpl();
        curriculumVitae.setText("Максимов Александр Петрович 800 555-66-41");
        String str=curriculumVitae.getPhones().get(0).getNumber();
        assertEquals("555-66-41",curriculumVitae.getPhones().get(0).getNumber());
    }
    @Test
    public void testGetPhones2() {
        CurriculumVitae curriculumVitae=new CurriculumVitaeImpl();
        curriculumVitae.setText("Максимов Александр Петрович (800) 555-66-41 ext. 6");
        //String str=curriculumVitae.getPhones().get(0).getNumber();
        assertEquals("555-66-41",curriculumVitae.getPhones().get(0).getNumber());
        assertEquals(800,curriculumVitae.getPhones().get(0).getAreaCode());
        assertEquals(6,curriculumVitae.getPhones().get(0).getExtension());
    }
    @Test
    public void testGetPhones3() {
        CurriculumVitae curriculumVitae=new CurriculumVitaeImpl();
        curriculumVitae.setText("Максимов Александр Петрович (800) 555 6641 ext. 6");
        //String str=curriculumVitae.getPhones().get(0).getNumber();
        assertEquals("555 6641",curriculumVitae.getPhones().get(0).getNumber());
        assertEquals(800,curriculumVitae.getPhones().get(0).getAreaCode());
        assertEquals(6,curriculumVitae.getPhones().get(0).getExtension());
    }

}