package ru.skillbench.tasks.javaapi.collections;

import junit.framework.TestCase;
import org.junit.Test;

public class StringFilterImplTest extends TestCase {

    public void testAdd() {
    }

    public void testRemove() {
    }

    public void testRemoveAll() {
    }

    public void testGetCollection() {
    }
    @Test
    public void testGetStringsContaining() {
        StringFilter stringFilter=new StringFilterImpl();
        stringFilter.add("Mama");
        stringFilter.add("papa");
        stringFilter.add("lalala papa hehe mama");
        assertEquals("h",stringFilter.getStringsContaining("hehe").next());
    }
}