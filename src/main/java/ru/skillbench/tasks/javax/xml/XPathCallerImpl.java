package ru.skillbench.tasks.javax.xml;

import org.w3c.dom.NodeList;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
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
        String typeParam = "";
        if (docType.equals("emp")) {
            typeParam = "/content/emp/employee";
        } else {
            typeParam = "//employee";
        }
        XPathFactory xPathFactory = XPathFactory.newInstance();
        XPath xPath = xPathFactory.newXPath();
        Element[] elements = null;
        try {
            XPathExpression exception = xPath.compile(typeParam + "[@deptno= '" + deptno + "']");
            Object result = exception.evaluate(src, XPathConstants.NODESET);
            NodeList nodeList = (NodeList) result;
            elements = new Element[nodeList.getLength()];
            for (int i = 0; i < nodeList.getLength(); i++) {
                elements[i] = (Element) nodeList.item(i);
            }
        } catch (XPathException ex) {
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
        String typeParam = "";
        if (docType.equals("emp")) {
            typeParam = "/content/emp/employee";
        } else {
            typeParam = "//employee";
        }
        XPathFactory xPathFactory = XPathFactory.newInstance();
        XPath xPath = xPathFactory.newXPath();
        String resultReturn = "";
        int salary = 0;
        int max = 0;
        try {
            XPathExpression exception = xPath.compile(typeParam);
            Object result = exception.evaluate(src, XPathConstants.NODESET);
            NodeList nodeList = (NodeList) result;
            XPath xPaths = XPathFactory.newInstance().newXPath();
            int maxInd = 0;
            for (int i = 0; i < nodeList.getLength(); i++) {
                salary = ((Number) xPaths.evaluate("./sal",
                        nodeList.item(i), XPathConstants.NUMBER)).intValue();
                if (salary > max) {
                    max = salary;
                    maxInd = i;
                }
            }
            resultReturn = (String) xPaths.evaluate("./ename",
                    nodeList.item(maxInd), XPathConstants.STRING);

        } catch (Exception ex) {

        }
        return resultReturn;
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
        String typeParam = "";
        if (docType.equals("emp")) {
            typeParam = "/content/emp/employee";
        } else {
            typeParam = "//employee";
        }
        XPathFactory factory = XPathFactory.newInstance();
        XPath xPath = factory.newXPath();
        String res = "";
        int salary = 0;
        int max = 0;
        try {
            XPathExpression exception = xPath.compile(typeParam+"[@deptno='" + deptno + "']");
            Object result = exception.evaluate(src, XPathConstants.NODESET);
            NodeList nodeList = (NodeList) result;
            XPath xPaths = XPathFactory.newInstance().newXPath();
            int maxInd = 0;
            for (int i = 0; i < nodeList.getLength(); i++) {
                salary = ((Number) xPaths.evaluate("./sal",
                        nodeList.item(i), XPathConstants.NUMBER)).intValue()
                        + ((Number) xPaths.evaluate("./sal",
                        nodeList.item(i), XPathConstants.NUMBER)).intValue();
                if (salary > max) {
                    max = salary;
                    maxInd = i;
                }
            }
            res = (String) xPaths.evaluate("./ename",
                    nodeList.item(maxInd), XPathConstants.STRING);
        /*
        try {
            XPathExpression expression = xPath.compile(typeParam + "[@depnto='" + deptno + "']");
            Object resultObj = expression.evaluate(src, XPathConstants.NODESET);
            NodeList nodeList = (NodeList) resultObj;
            int max = 0;
            int maxSalary = 0;
            int index = 0;
            for (int i = 0; i < nodeList.getLength(); i++) {
                XPath xPath1 = XPathFactory.newInstance().newXPath();
                maxSalary = ((Number) xPath1.evaluate("./sal",
                        nodeList.item(i), XPathConstants.NUMBER)).intValue()
                        + ((Number) xPath1.evaluate("./sal",
                        nodeList.item(i), XPathConstants.NUMBER)).intValue();
                if (max < maxSalary) {
                    max = maxSalary;
                    index = i;
                }
            }
            XPath xPath2 = XPathFactory.newInstance().newXPath();
            res = ((String) xPath2.evaluate("./ename", nodeList.item(index), XPathConstants.STRING));

         */
        } catch (XPathExpressionException e) {
            e.printStackTrace();
        }
        return res;

    }

    /**
     * Выбрать всех топовых менеджеров (менеджер топовый, если над ним нет менеджера)
     *
     * @param src     XML документ для поиска
     * @param docType "emp" - для файла типа emp.xml; "emp-hier" - для файла типа emp-hier.xml
     */
    @Override
    public Element[] getTopManagement(Document src, String docType) {
        String typeParam = "";
        if (docType.equals("emp")) {
            typeParam = "/content/emp/employee[not(@mgr)]";
        } else {
            typeParam = "//employee";
        }
        XPathFactory xPathFactory=XPathFactory.newInstance();
        XPath xPath=xPathFactory.newXPath();
        Element[] elements=null;
        try {
            XPathExpression exception=xPath.compile(typeParam);
            Object res=exception.evaluate(src,XPathConstants.NODESET);
            NodeList nodeList=(NodeList) res;
            elements=new Element[nodeList.getLength()];
            for (int i = 0; i < nodeList.getLength(); i++) {
                elements[i]=(Element) nodeList.item(i);
            }
        } catch (XPathExpressionException e) {
            e.printStackTrace();
        }
        return elements;
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
        String typeParam = "";
        if (docType.equals("emp")) {
            typeParam = "/content/emp/employee[not(@empno = (/content/emp/employee/@mgr))]";
        } else {
            typeParam = "//employee[not(./employee)]";
        }
        XPathFactory xPathFactory=XPathFactory.newInstance();
        XPath xPath=xPathFactory.newXPath();
        Element[] elements=null;
        try {
            XPathExpression exception=xPath.compile(typeParam);
            Object res=exception.evaluate(src,XPathConstants.NODESET);
            NodeList nodeList=(NodeList) res;
            elements=new Element[nodeList.getLength()];
            for (int i = 0; i < nodeList.getLength(); i++) {
                elements[i]=(Element) nodeList.item(i);
            }
        } catch (XPathExpressionException e) {
            e.printStackTrace();
        }
        return elements;
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
        String typeParam = "";
        if (docType.equals("emp")) {
            typeParam = "/content/emp/employee[" +
                    "not(@empno='" + empno + "') " +
                    "and @mgr = (/content/emp/employee[@empno='" + empno + "']/@mgr)" +
                    "]";
        } else {
            typeParam = "//employee[@empno='" + empno + "']" +
                    "/../" +
                    "employee[not(@empno='" + empno + "')]";
        }
        XPathFactory xPathFactory=XPathFactory.newInstance();
        XPath xPath=xPathFactory.newXPath();
        Element[] elements=null;
        try {
            XPathExpression exception=xPath.compile(typeParam);
            Object res=exception.evaluate(src,XPathConstants.NODESET);
            NodeList nodeList=(NodeList) res;
            elements=new Element[nodeList.getLength()];
            for (int i = 0; i < nodeList.getLength(); i++) {
                elements[i]=(Element) nodeList.item(i);
            }
        } catch (XPathExpressionException e) {
            e.printStackTrace();
        }
        return elements;
    }
}
