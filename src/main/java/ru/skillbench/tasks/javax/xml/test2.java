package ru.skillbench.tasks.javax.xml;

import javax.xml.transform.stream.StreamResult;
import java.io.StringWriter;

public class test2 {
    public static void main(String[] args) {
        StringWriter stringWriter=new StringWriter(100);
        stringWriter.append("opa");
        System.out.println(stringWriter.toString());
        StreamResult result=new StreamResult(stringWriter);
        System.out.println(result.getWriter().toString());
    }
}
