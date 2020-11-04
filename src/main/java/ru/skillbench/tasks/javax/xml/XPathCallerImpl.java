package ru.skillbench.tasks.javax.xml;

import org.w3c.dom.NodeList;

import javax.lang.model.element.Element;
import javax.swing.text.Document;
import javax.xml.xpath.*;

public class XPathCallerImpl implements XPathCaller {
    /**
     * Для заданного отдела выбрать всех сотрудников.
     *
     * @param src     XML документ для поиска
     * @param deptno  Номер отдела deptno
     * @param docType "emp" - для файла типа emp.xml; "emp-hier" - для файла типа emp-hier.xml
     */
    @Override
    public Element[] getEmployees(Document src, String deptno, String docType) {
        XPathFactory xPathFactory=XPathFactory.newInstance();
        XPath xPath=xPathFactory.newXPath();
        Element[] elements=null;
        try {
            XPathExpression exception = xPath.compile("employee[@deptno=" + deptno + "]");
            Object result=exception.evaluate(src, XPathConstants.NODESET);
            NodeList nodeList=(NodeList) result;
            elements=new Element[nodeList.getLength()];
            for (int i = 0; i < nodeList.getLength(); i++) {
                elements[i]=(Element) nodeList.item(i);
            }
        }catch (XPathException ex){
        }
        return elements;
    }

    /**
     * Выбрать имя самого высокооплачиваемого сотрудника.
     *
     * @param src     XML документ для поиска
     * @param docType "emp" - для файла типа emp.xml; "emp-hier" - для файла типа emp-hier.xml
     */
    @Override
    public String getHighestPayed(Document src, String docType) {
        return null;
    }

    /**
     * Выбрать имя самого высокооплачиваемого сотрудника (любого, если таких несколько).
     *
     * @param src     XML документ для поиска
     * @param deptno  Номер отдела deptno
     * @param docType "emp" - для файла типа emp.xml; "emp-hier" - для файла типа emp-hier.xml
     */
    @Override
    public String getHighestPayed(Document src, String deptno, String docType) {
        return null;
    }

    /**
     * Выбрать всех топовых менеджеров (менеджер топовый, если над ним нет менеджера)
     *
     * @param src     XML документ для поиска
     * @param docType "emp" - для файла типа emp.xml; "emp-hier" - для файла типа emp-hier.xml
     */
    @Override
    public Element[] getTopManagement(Document src, String docType) {
        return new Element[0];
    }

    /**
     * Выбрать всех сотрудников, не являющихся менеджерами.
     * Считать, что сотрудник не является менеджером, если у него нет подчиненных.
     *
     * @param src     XML документ для поиска
     * @param docType "emp" - для файла типа emp.xml; "emp-hier" - для файла типа emp-hier.xml
     */
    @Override
    public Element[] getOrdinaryEmployees(Document src, String docType) {
        return new Element[0];
    }

    /**
     * Для заданного сотрудника(empno) найти всех коллег, которые в подчинении у того же менеджера.
     *
     * @param src     XML документ для поиска
     * @param empno   Номер сотрудника empno
     * @param docType "emp" - для файла типа emp.xml; "emp-hier" - для файла типа emp-hier.xml
     */
    @Override
    public Element[] getCoworkers(Document src, String empno, String docType) {
        return new Element[0];
    }
}
