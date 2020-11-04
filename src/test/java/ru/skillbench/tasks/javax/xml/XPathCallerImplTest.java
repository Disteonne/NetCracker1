package ru.skillbench.tasks.javax.xml;

import junit.framework.TestCase;
import org.w3c.dom.Document;
import org.w3c.dom.Element;


import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import java.io.File;


public class XPathCallerImplTest extends TestCase {
    public Document doc(String fileName) {
        Document document=null;
        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder documentBuilder = factory.newDocumentBuilder();
            document = documentBuilder.parse(new File(fileName));
        }catch (Exception ex){
            ex.printStackTrace();
        }
        return document;
    }
    public void testGetEmployees() {
        XPathCallerImpl caller=new XPathCallerImpl();
        Element[] elements= (Element[]) caller.getEmployees((javax.swing.text.Document) doc("C:\\Users\\huawei\\Desktop\\Netcracker\\emp-hier.xml"),"20","emp-hier");
        assertEquals("",elements[0].getTagName());
    }
}