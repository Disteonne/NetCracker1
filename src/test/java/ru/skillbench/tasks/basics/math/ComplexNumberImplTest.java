package ru.skillbench.tasks.basics.math;

import junit.framework.TestCase;
import org.junit.Test;
import java.lang.*;

public class ComplexNumberImplTest extends TestCase {

    @Test
    public void testGetRe() {
        ComplexNumber c1=new ComplexNumberImpl("5i");
        assertEquals(0.0,c1.getRe());
    }
    @Test
    public void testGetIm() {
        ComplexNumber c1=new ComplexNumberImpl("5i");
        assertEquals(5.0,c1.getIm());
    }

    @Test
    public void testIsReal() {
        ComplexNumber c1=new ComplexNumberImpl("+0");
        boolean b=c1.isReal();
        assertEquals(true,c1.isReal());
    }
    @Test
    public void testIsReal2() {
        ComplexNumber c1=new ComplexNumberImpl("i");
        boolean b=c1.isReal();
        assertEquals(false,c1.isReal());
    }
    @Test
    public void testIsReal3() {
        ComplexNumber c1=new ComplexNumberImpl("0.0");
        boolean b=c1.isReal();
        assertEquals(true,c1.isReal());
    }
    @Test
    public void testSet1() {
        ComplexNumber c1=new ComplexNumberImpl("-1+4i");
        double re= c1.getRe();
        double im=c1.getIm();
        assertEquals(-1.0,c1.getRe());
        assertEquals(4.0,c1.getIm());
    }
    @Test
    public void testSet2() {
        ComplexNumber c1=new ComplexNumberImpl("-1+0.0i");
        double re= c1.getRe();
        double im=c1.getIm();
        assertEquals(-1.0,c1.getRe());
        assertEquals(0.0,c1.getIm());
    }
    @Test
    public void testSet3(){
        ComplexNumber c1=new ComplexNumberImpl("0.0i");
        double re= c1.getRe();
        double im=c1.getIm();
        assertEquals(0.0,c1.getRe());
        assertEquals(0.0,c1.getIm());
    }
    @Test
    public void testSet4(){
        ComplexNumber c1=new ComplexNumberImpl("-5+2i");
        double re= c1.getRe();
        double im=c1.getIm();
        assertEquals(-5.0,c1.getRe());
        assertEquals(2.0,c1.getIm());
    }
    @Test
    public void testSet5(){
        ComplexNumber c1=new ComplexNumberImpl("1+i");
        double re= c1.getRe();
        double im=c1.getIm();
        assertEquals(1.0,c1.getRe());
        assertEquals(1.0,c1.getIm());
    }
    @Test
    public void testSet6(){
        ComplexNumber c1=new ComplexNumberImpl("+4-i");
        double re= c1.getRe();
        double im=c1.getIm();
        assertEquals(4.0,c1.getRe());
        assertEquals(-1.0,c1.getIm());
    }
    @Test
    public void testSet7(){
        ComplexNumber c1=new ComplexNumberImpl("i");
        double re= c1.getRe();
        double im=c1.getIm();
        assertEquals(0.0,c1.getRe());
        assertEquals(1.0,c1.getIm());
    }
    @Test
    public void testSet8(){
        ComplexNumber c1=new ComplexNumberImpl("-3i");
        double re= c1.getRe();
        double im=c1.getIm();
        assertEquals(0.0,c1.getRe());
        assertEquals(-3.0,c1.getIm());
    }
    @Test
    public void testSet9(){
        ComplexNumber c1=new ComplexNumberImpl("3");
        double re= c1.getRe();
        double im=c1.getIm();
        assertEquals(3.0,c1.getRe());
        assertEquals(0.0,c1.getIm());
    }
    @Test(expected = NumberFormatException.class)
    public void testSet10(){
        ComplexNumber c1=new ComplexNumberImpl("1+2*i");
    }
    @Test(expected = NumberFormatException.class)
    public void testSet11(){
        ComplexNumber c1=new ComplexNumberImpl();
        c1.set("2+2");
    }
    @Test(expected = NumberFormatException.class)
    public void testSet12(){
        ComplexNumber c1=new ComplexNumberImpl("j");
    }
    // "+4-i", "i", "-3i", "3". Incorrect examples: "1+2*i", "2+2", "j"
    @Test
    public void testAdd() {
        ComplexNumber c1=new ComplexNumberImpl(3,-5);
        ComplexNumber c2=new ComplexNumberImpl(-3,5);
        c1.add(c2);
        assertEquals(0.0,c1.getRe());
        assertEquals(0.0,c1.getIm());
    }


    public void testNegative() {
        ComplexNumber c1=new ComplexNumberImpl(3,-5);
        c1.negate();
        assertEquals(-3.0,c1.getRe());
        assertEquals(5.0,c1.getIm());
    }

    public void testMultiply() {
        ComplexNumber c1=new ComplexNumberImpl(3,-5);
        ComplexNumber c2=new ComplexNumberImpl(0,0);
        c1.multiply(c2);
        assertEquals(0.0,c1.getRe());
        assertEquals(0.0,c1.getIm());
    }
}