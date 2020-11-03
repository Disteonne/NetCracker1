
package ru.skillbench.tasks.javax.hml;


import org.w3c.dom.Document;
import org.w3c.dom.Element;

import javax.xml.parsers.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

import org.w3c.dom.NodeList;
import org.xml.sax.Attributes;
import org.xml.sax.ContentHandler;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class SimpleXMLImpl implements  SimpleXML {


    @Override
    public String createXML(String tagName, String textNode) {
        DocumentBuilderFactory factory=DocumentBuilderFactory.newInstance();
        DocumentBuilder builder= null;
        try {
            builder = factory.newDocumentBuilder();
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        }
        Document document=builder.newDocument();

        Element element=document.createElement(tagName);
        element.setTextContent(textNode);

        String info="";
        NodeList nodeList=document.getChildNodes();
        for (int i = 0; i < nodeList.getLength(); i++) {
            info+=nodeList.item(i).getTextContent();
        }
        return "<"+document.getDocumentElement().getTagName()+">"+info+"</"+document.getDocumentElement().getTagName()+">";
    }


    @Override
    public String parseRootElement(InputStream xmlStream) throws SAXException {
        InputSource source=new InputSource(xmlStream);
        SAXParserFactory factory=SAXParserFactory.newInstance();
        SAXParser saxParser= null;

        try {
            saxParser = factory.newSAXParser();
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        }
        DefaultHandler handler=new DefaultHandler(){
           ArrayList<String> list=new ArrayList();
            @Override
            public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
                list.add(qName);
            }

            public String getRoot(){
                if(list.size()==0){
                    return null;
                }
                else {
                    return list.get(0);
                }
            }
        };


        String tmp="";
        try {
            saxParser.parse(source,handler);
        }catch (IOException e){

        }
        return handler;
    }
}

