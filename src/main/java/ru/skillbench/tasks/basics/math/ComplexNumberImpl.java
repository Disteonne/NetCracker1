package ru.skillbench.tasks.basics.math;

import java.util.Arrays;

public class ComplexNumberImpl implements ComplexNumber {
    private double re;
    private double im;
    private boolean[] complexArray = {false, false};

    public ComplexNumberImpl(double re, double im) {
        set(re, im);
        complexArray[0] = true;
        complexArray[1] = true;
    }

    public ComplexNumberImpl() {
        this.re = 0.0;
        this.im = 0.0;
    }


    public ComplexNumberImpl(String str) {
        set(str);
    }

    @Override
    public double getRe() {
        return re;
    }

    @Override
    public double getIm() {
        return im;
    }

    @Override
    public boolean isReal() {

        if ((complexArray[0] == true) && (complexArray[1] == false)) {
            return true;
        } else
            return false;
    }

    @Override
    public void set(double re, double im) {
        this.re = re;
        this.im = im;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof ComplexNumber))
            return false;
        ComplexNumber complexNumber = (ComplexNumber) obj;
        return this.getRe() == complexNumber.getRe() && this.getIm() == complexNumber.getIm();
    }

    @Override
    public void set(String value) throws NumberFormatException {
        //комплексное число,содержащее i
        String im = "";
        String re = "";
        byte index = 0;
        if (value.contains("i") && value.endsWith("i")) {
            char[] str = value.toCharArray();
            for (int i = str.length - 2; i >= 0; i--) {
                if (str[i] == '+' || str[i] == '-') {
                    index = (byte) i;
                    break;
                }
            }
            im = value.substring(index, value.length() - 1);
            re = value.substring(0, value.length() - (im.length() + 1));
            if (im.equals("+") || im.equals("-") || im.equals("")) {
                im += "1";
            }
            if (re.length() == 0) {
                //чисто мнимое число
                //try {
                    this.im = Double.parseDouble(im);
                    this.re = 0.0;//по умолчанию задаем 0
                    complexArray[1] = true;
                //} catch (NumberFormatException e) {

                //}
            } else {
                //re = value.substring(0, im.length());
                //try {
                    this.re = Double.parseDouble(re);
                    this.im = Double.parseDouble(im);
                    complexArray[0] = true;
                    complexArray[1] = true;

                //} catch (NumberFormatException e) {

                //}
            }
        } else {
            //try {
                this.re = Double.parseDouble(value);
                this.im = 0.0;//по умолчанию задаем 0
                complexArray[0] = true;
            //} catch (NumberFormatException e) {

            //}
        }
    }

    @Override
    public ComplexNumber copy(){
        ComplexNumber complexNumber = new ComplexNumberImpl();
        complexNumber.set(this.re, this.im);
        return complexNumber;
    }

    @Override
    public ComplexNumber clone() throws CloneNotSupportedException {
        ComplexNumber complexNumber = new ComplexNumberImpl();
        complexNumber.set(this.re, this.im);
        return complexNumber;
    }

    @Override
    public int compareTo(ComplexNumber other) {
        Double absOne = Math.pow(Math.abs(Math.pow(re, 2) + Math.pow(im, 2)), 2);
        Double absTwo = Math.pow(Math.abs(Math.pow(other.getRe(), 2) + Math.pow(other.getIm(), 2)), 2);
        return absOne.compareTo(absTwo);
    }

    @Override
    public void sort(ComplexNumber[] array) {
        Arrays.sort(array, ComplexNumber::compareTo);
    }

    @Override
    public ComplexNumber negate() {
        double Re = this.re;
        double Im = this.im;
        this.re = -Re;
        this.im = -Im;
        return this;
    }

    @Override
    public ComplexNumber add(ComplexNumber arg2) {
        double Re = this.re;
        double Im = this.im;
        this.re = Re + arg2.getRe();
        this.im = Im + arg2.getIm();
        return this;
    }

    @Override
    public ComplexNumber multiply(ComplexNumber arg2) {
        double Re = this.re;
        double Im = this.im;
        this.re = Re * arg2.getRe() - Im * arg2.getIm();
        this.im = Im * arg2.getRe() + Re * arg2.getIm();
        return this;
    }

    @Override
    public String toString() {
        String result = "";
        if (re == 0.0) {
            result = result + im + "i";
        } else if (im == 0.0) {
            result = result + re;
        } else {
            if (im > 0) {
                result = "" + re + "+" + im + "i";
            } else result = "" + re + "" + im + "i";
        }
        return result;
    }
}
