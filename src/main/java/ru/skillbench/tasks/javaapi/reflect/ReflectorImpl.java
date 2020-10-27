package ru.skillbench.tasks.javaapi.reflect;

import java.lang.reflect.*;
import java.sql.Ref;
import java.util.*;
import java.util.function.*;
import java.util.stream.*;

public class ReflectorImpl implements Reflector {
    Class<?> clazz;


    /**
     * Constructs a new object.
     */
    public ReflectorImpl() {
        super();
    }

    /**
     * Задает класс, у которого нужно брать метаданные в других методах.
     *
     * @param clazz
     */
    @Override
    public void setClass(Class<?> clazz) {
        this.clazz = clazz;
    }

    /**
     * Метод возвращает имена всех public методов, принимающих параметры заданных типов, -
     * для класса, заданного методом {@link #setClass(Class)}, в т.ч. его суперклассов.<br/>
     * Если метод какого-либо суперкласса переопределен методом класса, и имеет то же возвращаемое значение,
     * метод суперкласса возвращаться не должен.
     *
     * @param paramTypes Типы параметров, которые входят в сигнатуру искомых методов
     * @return Набор имен методов
     * @throws NullPointerException если класс равен null
     */
    @Override
    public Stream<String> getMethodNames(Class<?>... paramTypes) {
        if (paramTypes == null) {
            throw new NullPointerException();
        } else {
            HashMap<String, String> map = new HashMap<>();
            Class<?> clazzZ = clazz;
            while (clazzZ != null) {
                Method[] methods = clazz.getMethods();
                for (int i = 0; i < methods.length; i++) {
                    Class<?>[] type = methods[i].getParameterTypes();
                    if (Arrays.equals(type, paramTypes)) {
                        map.put(methods[i].getName(), methods[i].getReturnType().toString());
                    }
                }
                clazzZ = clazzZ.getSuperclass();
            }
            return map.keySet().stream();
        }

    }


    /**
     * Метод возвращает набор всех не-static полей класса, заданного методом {@link #setClass(Class)},
     * в т.ч. полей его суперклассов.<br/>
     * Для выделения не-статических полей рекомендуется использовать метод {@link Stream#filter(Predicate)}
     * с lambda-выражением.<br/>
     * В отличие от {@link Class#getFields()}, возвращаемые поля могут иметь любые модификаторы доступа:
     * private, public, protected или default.
     *
     * @return Набор не-static полей для всей иерархии наследования данного класса.
     * @throws NullPointerException если класс равен null
     */
    @Override
    public Stream<Field> getAllDeclaredFields() {
        Class<?> classNew = clazz;
        ArrayList<Field> fieldsList = new ArrayList<>();
        while (classNew != null) {
            Field[] fields = classNew.getDeclaredFields();
            for (int i = 0; i < fields.length; i++) {
                int modifiers = fields[i].getModifiers();
                if (!Modifier.isStatic(modifiers)) {
                    fieldsList.add(fields[i]);
                }
            }
            classNew = classNew.getSuperclass();
        }

        return fieldsList.stream();
    }

    /**
     * Возвращает значение поля заданного объекта без расчета на то, что у поля есть getter.<br/>
     * Поле может иметь любой идентификатор доступа.<br/>
     * Поле объявлено в классе, который задан методом {@link #setClass(Class), а если он не задан, -
     * в классе <code>target.getClass()</code>.<br/>
     * Примечание: объект <code>target</code> может быть экземпляром подкласса, и тогда
     * в <code>target.getClass()</code> не объявлено поле с заданным именем.
     *
     * @param target    Объект, где хранится значение поля
     * @param fieldName Имя поля
     * @param target
     * @param fieldName
     * @return Значение поля
     * @throws NoSuchFieldException   если поля с указанным именем не существует
     * @throws IllegalAccessException если к полю нет доступа
     *                                (при правильно реализованном методе такого исключения возникать не должно)
     */
    @Override
    public Object getFieldValue(Object target, String fieldName) throws NoSuchFieldException, IllegalAccessException {
        Field[] fields = target.getClass().getDeclaredFields();
        Field[] fields1 = target.getClass().getFields();
        Object object=new Object();
        boolean isTrue = false;
        for (int i = 0; i < fields.length; i++) {
            if (fields[i].getName().equals(fieldName)) {
                isTrue = true;
                fields[i].setAccessible(true);
                object=fields[i].get(target);
            }
        }
        for (int i = 0; i < fields1.length; i++) {
            if (fields1[i].getName().equals(fieldName)) {
                isTrue = true;
                fields1[i].setAccessible(true);
                object=fields[i].get(target);
            }
        }
        if(isTrue!=true){
            throw new NoSuchFieldException();
        }
        return object;
    }

    /**
     * Метод создает экземпляр класса, заданного методом {@link #setClass(Class)},
     * с помощью public конструктора с параметром <code>constructorParameter</code>,
     * после чего вызвает его метод с <code>methodName</code>, в который передаются <code>methodParams</code>.<br/>
     * Метод может иметь любой модификатор доступа - необязательно public,
     * однако если метод не public, то объявлен именно в этом классе (а не в его суперклассе).
     *
     * @param constructorParam Передаваемый конструктору параметр или null, чтобы использовать
     *                         конструктор без параметров. Тип параметра конструктора равен <code>constructorParam.getClass()</code>
     * @param methodName       Название метода, который нужно вызвать.
     * @param methodParams     Массив параметров для вызова метода, ни один из которых не равен null;
     *                         предполагается, что сигнатура метода содержит типы элементов methodParams (а не их супертипы).
     * @return Результат, который возвращает метод. Может быть Void
     * @throws IllegalAccessException    если к конструктору или к методу нет доступа
     *                                   (при правильно реализованном {@link #getMethodResult(Object, String, Object...)} такого возникать не должно)
     * @throws InstantiationException    если класс не может быть инстанциирован (является абстрактным и т.п.)
     * @throws NoSuchMethodException     если в классе не существует подходящего конструктора или метода
     * @throws InvocationTargetException конструктор или метод при вызове выбросили проверяемое исключение
     * @throws RuntimeException          если конструктор или метод при вызове выбросили непроверяемое исключение;
     *                                   для этого в {@link #getMethodResult(Object, String, Object...)} необходимо обрабатывать InvocationTargetException
     */
    @Override
    public Object getMethodResult(Object constructorParam, String methodName, Object... methodParams) throws IllegalAccessException, InstantiationException, NoSuchMethodException, InvocationTargetException {
       try {
           Class classS = Class.forName(clazz.getName());
           //ReflectorImpl builder=(ReflectorImpl)classS.newInstance();
           Class aClass=classS.getClass();


           //Class<?> classS=clazz.getName();
           //Object classS=clazz.getClass().newInstance();
           Constructor constructor=classS.getConstructor((Class<?>) constructorParam);


          // constructor.setAccessible(true);
          // Object reflectorInstance =constructor.newInstance(constructorParam);
           Method methods=aClass.getDeclaredMethod(methodName);
           methods.setAccessible(true);

       }catch (ClassNotFoundException ex){

       }
       finally {
           return null;
       }
    }
}
