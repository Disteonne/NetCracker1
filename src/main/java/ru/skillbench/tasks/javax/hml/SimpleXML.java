
package ru.skillbench.tasks.javax.hml;

import java.io.InputStream;

import javax.xml.transform.Transformer;

import org.xml.sax.SAXException;
import java.io.InputStream;

public interface SimpleXML {

    public String createXML(String tagName, String textNode);

    public String parseRootElement(InputStream xmlStream) throws SAXException;
}

