package ru.skillbench.tasks.basics.entity;


import java.util.ArrayList;

public class LocationImpl implements Location {
    private String location;
    private Type type;
    private Location parent;
    private String res = "";
    private static ArrayList<String> arrayList = new ArrayList<>();

    public LocationImpl() {
        super();
    }

    @Override
    public String getName() {
        return location;
    }

    @Override
    public void setName(String name) {
        this.location = name;
    }

    @Override
    public Type getType() {
        return type;
    }

    @Override
    public void setType(Type type) {
        this.type = type;
    }

    @Override
    public void setParent(Location parent) {
        this.parent = parent;
    }

    @Override
    public String getParentName() {
        return parent != null ? parent.getName() : "--";
    }

    @Override
    public Location getTopLocation() {
        return parent == null ? this : parent.getTopLocation();
    }

    @Override
    public boolean isCorrect() {
        return type.ordinal() > parent.getType().ordinal() ? true : false;
    }

    /**
     * СТРОЧКИ =>                                   ОПИСАНИЕ
     * 64-71   => Находим индекс первого пробела и меняем значение b1 в зависимости от того,найдется ли '.' ДО первого пробела.
     * 73-74   => Если точка нашлась ДО первого пробела или же в конце строки,то загоняем в result исходное назв.локации,если нет,то...
     * 75-100  => ...в зависимости от типа будем добавлять ПРЕФИКС к результирубщей строке result.(с использ.switch-case)
     * 101-110 => После if/else добавляем наш результат в arrayList(static).Если же мы дошли до конца "ветки",то
     * достаем все элементы из списка в одну строку и обрезаем на два эл-та (", "),если же нет,то рекурсивно проходим дальше.
     */
    @Override
    public String getAddress() {
        String result ="";
        byte index = (byte) location.indexOf(" ");
        boolean b1 = false;
        char[] locationToChar = location.toCharArray();
        for (int i = 0; i < index; i++) {
            if (locationToChar[i] == '.')
                b1 = true;
        }
        if (b1 == true || location.endsWith(".")) {
            result = location + ", ";
        } else {
            switch (type) {
                case APARTMENT:
                    result = Type.APARTMENT.getNameForAddress() +location + ", ";
                    break;
                case CITY:
                    result = Type.CITY.getNameForAddress() +  location + ", ";
                    break;
                case REGION:
                    result =Type.REGION.getNameForAddress() +  location + ", ";
                    break;
                case STREET:
                    result = Type.STREET.getNameForAddress() + location + ", ";
                    break;
                case COUNTRY:
                    result = Type.COUNTRY.getNameForAddress() + location + ", ";
                    break;
                case DISTRICT:
                    result =Type.DISTRICT.getNameForAddress() + location + ", ";
                    break;
                case BUILDING:
                    result = Type.BUILDING.getNameForAddress() +  location + ", ";
                    break;
            }
        }
        arrayList.add(result);
        if (parent == null) {
            for (String s : arrayList
            ) {
                res += s;
            }
            return res.substring(0, res.length() - 2);
        } else
           return parent.getAddress();
    }

    @Override
    public String toString() {
        return location + " " + "(" + type + ")";
    }
}
