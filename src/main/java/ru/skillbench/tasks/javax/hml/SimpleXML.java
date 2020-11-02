
package ru.skillbench.tasks.javax.hml;

import jdk.internal.org.xml.sax.*;

import javax.xml.transform.Transformer;
import java.io.InputStream;

public interface SimpleXML {

    public String createXML(String tagName, String textNode);

    public String parseRootElement(InputStream xmlStream) throws SAXException;
}

