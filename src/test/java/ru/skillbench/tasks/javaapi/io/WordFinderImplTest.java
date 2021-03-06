package ru.skillbench.tasks.javaapi.io;

import junit.framework.TestCase;
import org.junit.Test;

import java.io.*;
import java.util.ArrayList;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class WordFinderImplTest extends TestCase {

    @Test
    public void testGetText() {
        WordFinderImpl wordFinder=new WordFinderImpl();
        wordFinder.setText("mama arbuz Azbuka kekchick");
        assertEquals("mama arbuz Azbuka kekchick",wordFinder.getText());
    }

    @Test
    public void testFindWordsStartWith() {
        WordFinderImpl wordFinder=new WordFinderImpl();
        wordFinder.setText("nally follow for for [ bid foundation free ] freedom from");
        Stream stream= wordFinder.findWordsStartWith("f");
        ArrayList<String> list= (ArrayList<String>) stream.collect(Collectors.toList());
        String res="";
        for (int i = 0; i < list.size(); i++) {
            res+=list.get(i)+" ";
        }
        assertEquals("",res);
    }
    @Test
    public void testFindWordsStartWithTwo() {
        WordFinderImpl wordFinder=new WordFinderImpl();
        wordFinder.setText("rights\n" +
                "these, not\n" +
                "covered, you\n" +
                "have, program\n" +
                "means, law\n" +
                "that, program\n" +
                "is, so\n" +
                "that, it\n" +
                "in, it\n" +
                "if, forbid\n" +
                "anyone, this\n" +
                "general, original\n" +
                "authors, software\n" +
                "\n" +
                ", the\n" +
                "program, distributed\n" +
                "under, that\n" +
                "you, free\n" +
                "software\tto, it\n" +
                "some, you\n" +
                "distribute, reputations.\n" +
                "\n" +
                ", follow\n" +
                "\n" +
                "\t\t, software\n" +
                "patents, for\n" +
                "this, it\n" +
                "either, any\n" +
                "patent, public\n" +
                "license, software\n" +
                "foundation, program\n" +
                "whether, below\n" +
                "refers, too\n" +
                "\n" +
                ", another\n" +
                "language, free\n" +
                "software, by\n" +
                "the, of\n" +
                "running, to\n" +
                "your, copy\n" +
                "distribute, their\n" +
                "rights\n" +
                "\n" +
                ", all\n" +
                "\n" +
                ", we\n" +
                "want, and\n" +
                ", whether\n" +
                "gratis, and\n" +
                "modification, free\n" +
                "program, contains\n" +
                "a, to\n" +
                "using, the\n" +
                "source, you\n" +
                "\n" +
                "activities, in\n" +
                "the, certain\n" +
                "that, it\n" +
                "\n" +
                ", instead\tyou, things\n" +
                "\n" +
                ", your\n" +
                "freedom, license\n" +
                ", not\n" +
                "price, modification");
        Stream stream= wordFinder.findWordsStartWith("");
        ArrayList<String> list= (ArrayList<String>) stream.collect(Collectors.toList());
        assertEquals(0,list.size());
    }
    @Test
    public void testFindWordsStartWithThree()  throws IOException {
        WordFinderImpl wordFinder=new WordFinderImpl();
        wordFinder.setText("hebrjkb ahrenh Gfhdc Based b[e been below by dskjfv asjf lpt HGV tgl GVJA");
        Stream stream= wordFinder.findWordsStartWith("b");
        File file=new File("kek");
        wordFinder.writeWords(new FileOutputStream(file));
        //assertEquals("",re);
    }

    public void testWriteWords() {
    }
}