package ru.skillbench.tasks.javax.hml;

import org.w3c.dom.*;
import javax.xml.parsers.*;
import javax.xml.transform.*;
import javax.xml.transform.dom.*;
import javax.xml.transform.stream.*;
import java.io.File;


public class test {
    public static void main(String[] args) throws ParserConfigurationException, TransformerException {
        DocumentBuilderFactory dbf;
        DocumentBuilder db ;
        Document               doc;

        dbf = DocumentBuilderFactory.newInstance();
        db  = dbf.newDocumentBuilder();
        doc = db.newDocument();
        Element root = doc.createElement("root");
        //root.setAttribute("xmlns", "http://www.javacore.ru/schemas/");
        doc.appendChild(root);

        Element item1 = doc.createElement("item");
        root.appendChild(item1);

        File file = new File("test.xml");

        //doc.getTextContent();
        System.out.println(doc.getDocumentElement().getTagName()+" "+doc.getNodeValue());

        Transformer transformer = TransformerFactory.newInstance().newTransformer();
        transformer.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "yes");
        transformer.transform(new DOMSource(doc), new StreamResult(file));

    }
}
