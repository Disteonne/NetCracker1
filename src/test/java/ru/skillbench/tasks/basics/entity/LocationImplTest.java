package ru.skillbench.tasks.basics.entity;

import junit.framework.TestCase;

public class LocationImplTest extends TestCase {

    public void testTestGetName() {
        Location l1=new LocationImpl();
        l1.setName("Moscow");
        assertEquals("Moscow",l1.getName());
    }

    public void testGetType() {
        Location l1=new LocationImpl();
        l1.setType(Location.Type.APARTMENT);
        assertEquals(Location.Type.APARTMENT,l1.getType());
    }
    public void testGetParentName() {
        Location l1=new LocationImpl();
        Location l2=new LocationImpl();
        l2.setName("ул. Русская");
        l1.setParent(l2);
        assertEquals("ул. Русская",l1.getParentName());
    }

    public void testGetTopLocation() {
        Location l1=new LocationImpl();
        Location l2=new LocationImpl();
        Location l3=new LocationImpl();
        l1.setName("Москва");
        l1.setType(Location.Type.CITY);
        l2.setName("Россия");
        l1.setType(Location.Type.COUNTRY);
        l3.setName("ул. Ямская");
        l3.setType(Location.Type.STREET);
        l3.setParent(l1);
        l1.setParent(l2);
        assertEquals("Россия",l1.getTopLocation().getName());
    }

    public void testIsCorrect1() {
        Location l1=new LocationImpl();
        Location l2=new LocationImpl();
        Location l3=new LocationImpl();
        l1.setName("Москва");
        l1.setType(Location.Type.CITY);
        l2.setName("Россия");
        l2.setType(Location.Type.COUNTRY);
        l3.setName("ул. Ямская");
        l3.setType(Location.Type.STREET);
        l3.setParent(l1);
        l1.setParent(l2);
        assertEquals(true,l3.isCorrect());
    }

    public void testIsCorrect2(){
        Location l1=new LocationImpl();
        Location l3=new LocationImpl();
        l1.setName("Москва");
        l1.setType(Location.Type.CITY);
        l3.setName("ул. Ямская");
        l3.setType(Location.Type.STREET);
        l1.setParent(l3);
        assertEquals(false,l1.isCorrect());
    }

    public void testGetAddress1() {
        Location l1=new LocationImpl(); //"10 к.1";
        Location l2=new LocationImpl(); //"ул. Академика";
        Location l3=new LocationImpl(); //" г. Долгопрудный";
        Location l4=new LocationImpl(); //"Московская обл.";
        Location l5=new LocationImpl(); //"Moscow";
        Location l6=new LocationImpl(); //Russia
        l1.setName("10 к.1");
        l1.setType(Location.Type.BUILDING);
        l2.setName("ул. Академика");
        l2.setType(Location.Type.STREET);
        l3.setName("г. Долгопрудный");
        l3.setType(Location.Type.CITY);
        l4.setName("Московская обл.");
        l4.setType(Location.Type.REGION);
        l5.setName("Москва");
        l5.setType(Location.Type.CITY);
        l6.setName("Россия");
        l6.setType(Location.Type.COUNTRY);

        l1.setParent(l2);
        l2.setParent(l3);
        l3.setParent(l4);
        l4.setParent(l5);
        l5.setParent(l6);

        assertEquals("д. 10 к.1, ул. Академика, г. Долгопрудный, Московская обл., г. Москва, Россия", l1.getAddress());
    }

    public void testGetAddress2() {
        Location l1=new LocationImpl();
        Location l2=new LocationImpl();
        Location l3=new LocationImpl();
        Location l4=new LocationImpl();
        Location l6=new LocationImpl();
        l1.setName("15");
        l1.setType(Location.Type.BUILDING);
        l2.setName("Ошарская");
        l2.setType(Location.Type.STREET);
        l3.setName("Нижний Новгород");
        l3.setType(Location.Type.CITY);
        l4.setName("Нижегородская обл.");
        l4.setType(Location.Type.REGION);
        l6.setName("Россия");
        l6.setType(Location.Type.COUNTRY);

        l1.setParent(l2);
        l2.setParent(l3);
        l3.setParent(l4);
        l4.setParent(l6);
        assertEquals("д. 15, ул. Ошарская, г. Нижний Новгород, Нижегородская обл., Россия", l1.getAddress());
    }

    public void testGetAddress3() {
        Location l1=new LocationImpl(); //"10 к.1";
        l1.setName("10 к.1");
        l1.setType(Location.Type.BUILDING);
        assertEquals("д. 10 к.1", l1.getAddress());
    }
    public void testGetAddress4() {
        Location l1=new LocationImpl(); //"10 к.1";
        l1.setName("Нагорная");
        l1.setType(Location.Type.STREET);
        assertEquals("ул. Нагорная", l1.getAddress());
    }
}