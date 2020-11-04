package ru.skillbench.tasks.javax.xml;

import javax.lang.model.element.Element;
import javax.swing.text.Document;

public interface XPathCaller {
    /**
     * Для заданного отдела выбрать всех сотрудников.
     * @param src XML документ для поиска
     * @param deptno Номер отдела deptno
     * @param docType "emp" - для файла типа emp.xml; "emp-hier" - для файла типа emp-hier.xml
     */
    Element[] getEmployees (Document src, String deptno, String docType);

    /**
     * Выбрать имя самого высокооплачиваемого сотрудника.
     * @param src XML документ для поиска
     * @param docType "emp" - для файла типа emp.xml; "emp-hier" - для файла типа emp-hier.xml
     */
    String getHighestPayed(Document src, String docType);

    /**
     * Выбрать имя самого высокооплачиваемого сотрудника (любого, если таких несколько).
     * @param src XML документ для поиска
     * @param deptno Номер отдела deptno
     * @param docType "emp" - для файла типа emp.xml; "emp-hier" - для файла типа emp-hier.xml
     */
    String getHighestPayed(Document src, String deptno, String docType);
    /**
     * Выбрать всех топовых менеджеров (менеджер топовый, если над ним нет менеджера)
     * @param src XML документ для поиска
     * @param docType "emp" - для файла типа emp.xml; "emp-hier" - для файла типа emp-hier.xml
     */
    Element[] getTopManagement(Document src, String docType);
    /**
     * Выбрать всех сотрудников, не являющихся менеджерами.
     * Считать, что сотрудник не является менеджером, если у него нет подчиненных.
     * @param src XML документ для поиска
     * @param docType "emp" - для файла типа emp.xml; "emp-hier" - для файла типа emp-hier.xml
     */
    Element[] getOrdinaryEmployees(Document src, String docType);

    /**
     * Для заданного сотрудника(empno) найти всех коллег, которые в подчинении у того же менеджера.
     * @param src XML документ для поиска
     * @param empno Номер сотрудника empno
     * @param docType "emp" - для файла типа emp.xml; "emp-hier" - для файла типа emp-hier.xml
     */
    Element[] getCoworkers(Document src, String empno, String docType);
}
