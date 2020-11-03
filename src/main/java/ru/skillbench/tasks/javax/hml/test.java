package ru.skillbench.tasks.javax.hml;

import org.w3c.dom.*;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import javax.xml.parsers.*;
import javax.xml.transform.*;
import javax.xml.transform.dom.*;
import javax.xml.transform.stream.*;
import java.io.*;


public class test {
    public static void main(String[] args) throws ParserConfigurationException, TransformerException, SAXException {
/*      DocumentBuilderFactory dbf=DocumentBuilderFactory.newInstance();
        DocumentBuilder db =dbf.newDocumentBuilder();
        Document doc=db.newDocument();

        Element root = doc.createElement("root");
        doc.appendChild(root);

        root.setTextContent("op");
        //Element item1 = doc.createElement("item");
        //root.appendChild(item1);

        File file = new File("test.xml");

        NodeList nodeList=doc.getChildNodes();
        String info="";
        for (int i = 0; i < nodeList.getLength(); i++) {
            info+=nodeList.item(i).getTextContent();
        }
        System.out.println(doc.getDocumentElement().getTagName()+" "+info);

        Transformer transformer = TransformerFactory.newInstance().newTransformer();
        transformer.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "yes");
        transformer.transform(new DOMSource(doc), new StreamResult(file));
 */
        DefaultHandler handler=new DefaultHandler();
        SAXParserFactory saxParserFactory=SAXParserFactory.newInstance();
        SAXParser saxParser= null;
        try {
            saxParser = saxParserFactory.newSAXParser();
            saxParser.parse(new File("test.xml"),handler);
        } catch (SAXException e) {
            throw new SAXException();
        }catch (IOException ex){
            ex.printStackTrace();
        }
    }
}
