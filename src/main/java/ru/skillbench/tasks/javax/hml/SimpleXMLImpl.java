
package ru.skillbench.tasks.javax.hml;

import jdk.internal.org.xml.sax.SAXException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.InputStream;

public class SimpleXMLImpl implements  SimpleXML {


    @Override
    public String createXML(String tagName, String textNode) {
        DocumentBuilderFactory dbf;
        DocumentBuilder db = null;
        Document doc;

        dbf = DocumentBuilderFactory.newInstance();
        try {
            db  = dbf.newDocumentBuilder();
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        }
        doc = db.newDocument();
        Element root = doc.createElement(tagName);
        doc.appendChild(root);
        Element item1 = doc.createElement(textNode);
        root.appendChild(item1);

        return "<"+doc.getDocumentElement().getTagName()+">"+textNode+"</"+doc.getDocumentElement().getTagName()+">";
    }


    @Override
    public String parseRootElement(InputStream xmlStream) throws SAXException {
        return null;
    }
}

