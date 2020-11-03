
package ru.skillbench.tasks.javax.xml;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import javax.xml.parsers.*;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

import org.w3c.dom.Text;
import org.xml.sax.Attributes;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class SimpleXMLImpl implements  SimpleXML {

    public String createXML(String tagName, String textNode) {
        String stringResult = null;
        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder documentBuilder = factory.newDocumentBuilder();
            Document document = documentBuilder.newDocument();
            Element root = document.createElement(tagName);
            Text text=document.createTextNode(textNode);
            root.appendChild(text);
            document.appendChild(root);
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            transformer.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "yes");
            StringWriter writer = new StringWriter();
            StreamResult result = new StreamResult(writer); //output
            transformer.transform(new DOMSource(document), result);
            stringResult = writer.toString();
            writer.close();
        } catch (ParserConfigurationException e){

        }catch(TransformerException e ){

        }catch (IOException e){

        }
        return stringResult;
    }

    @Override
    public String parseRootElement(InputStream xmlStream) throws SAXException {
        InputSource source=new InputSource(xmlStream);
        SAXParserFactory factory=SAXParserFactory.newInstance();
        SAXParser saxParser= null;
        String res=null;

        try {
            saxParser = factory.newSAXParser();
            NewHandler handler = new NewHandler();
            saxParser.parse(source, handler);
            res = handler.getRoot();
        }catch (ParserConfigurationException e){

        }catch (IOException e){

        }
        return res;
    }
    private class NewHandler extends DefaultHandler{
        private List<String> list=new ArrayList<>();

        @Override
        public void startElement (String uri, String localName,
                                  String qName, Attributes attributes)
                throws SAXException {
            list.add(qName);
        }

        public String getRoot() {
            return !list.isEmpty() ? list.get(0) : null;
        }
    }
    public static void main(String[] args) {
        SimpleXMLImpl simpleXML=new SimpleXMLImpl();
        String str= simpleXML.createXML("root","and");
        System.out.println(str);
    }
}

