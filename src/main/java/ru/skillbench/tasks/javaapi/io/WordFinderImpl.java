package ru.skillbench.tasks.javaapi.io;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class WordFinderImpl implements WordFinder {
    private String text = "";
    ArrayList<String> listInputStream = new ArrayList<>();
    ArrayList<String> listFile = new ArrayList<>();
    ArrayList<String> listResource=new ArrayList<>();
    Stream<String> stream=null;
    boolean flag1=false;
    boolean flag2=false;
    boolean flag3=false;
    boolean flag4=false;
    boolean se=false;

    /**
     * @return Текущий текст для поиска или <code>null</code>,
     * если ни один из set-методов не был выполнен успешно.
     */
    @Override
    public String getText() {
        if(text=="")
            return null;
        else
            return text;
    }

    /**
     * Принимает готовый текст для поиска (а не читает текст из файла/потока).
     *
     * @param text Текст для поиска
     * @throws IllegalArgumentException если <code>src == null</code>
     */
    @Override
    public void setText(String text) {
        if (text == null) {
            flag1=true;
            throw new IllegalArgumentException();
        }
        this.text = text;
    }

    /**
     * Считывает текст из указанного потока ввода. Кодировка не важна.
     *
     * @param is Поток ввода
     * @throws IOException              в случае ошибок при чтении из потока
     * @throws IllegalArgumentException если <code>is == null</code>
     */
    @Override
    public void setInputStream(InputStream is) throws IOException {
        if (is == null) {
            flag2=true;
            throw new IllegalArgumentException();
        }
        BufferedReader  bufferedReader=new BufferedReader(new InputStreamReader(is));
        String str="";
        while ((str = bufferedReader.readLine()) != null) {
            listInputStream.add(str);
            text=text + " " + bufferedReader.readLine()+" ";
        }
        bufferedReader.close();
        is.close();
    }
    /**
     * Считывает текст из указанного файла. Кодировка не важна.
     *
     * @param filePath Путь к файлу с текстом
     * @throws IOException              в случае ошибок при чтении файла
     * @throws IllegalArgumentException если <code>filePath == null</code>
     *
     */
    @Override
    public void setFilePath(String filePath) throws IOException {
        if(filePath==null){
            flag3=true;
            throw new IllegalArgumentException();
        }
        File file=new File(filePath);
        BufferedReader bufferedReader=new BufferedReader(new FileReader(file));
        String add="";
        while ((add= bufferedReader.readLine())!=null){
            listFile.add(add);
            text=text + " " + add +" ";
        }
        bufferedReader.close();
    }

    /**
     * Считывает текст из указанного файла через {@link Class#getResourceAsStream(String)}.<br/>
     * Это позволяет указывать краткое имя файла и читать его даже в том случае, если он лежит
     * в jar-нике вместе с классами и если прямая работа с файлами запрещена настройками безопасности.<br/>
     * Кодировка не важна.
     *
     * @param resourceName Короткое имя ресурса (файла), который должен лежать в том же пакете, что и класс
     * @throws IOException              в случае ошибок при чтении
     * @throws IllegalArgumentException если <code>resourceName == null</code>
     */
    @Override
    public void setResource(String resourceName) throws IOException {
        if(resourceName==null){
            flag4=true;
            throw new IllegalArgumentException();
        }
        InputStream inputStream=WordFinderImpl.class.getResourceAsStream(resourceName);
        BufferedReader bufferedReader=new BufferedReader(new InputStreamReader(inputStream));
        String str="";
        while ((str=bufferedReader.readLine())!=null){
            listResource.add(str);
            text=text + " " + str +" ";
        }
        bufferedReader.close();
    }

    /**
     * Ищет в тексте все слова, начинающиеся с указанной последовательности символов,
     * без учета их регистра ('А' и 'а' считаются одним и тем же символом). <br/>
     * Результат возвращает в виде {@link Stream}, порядок элементов в котором не важен.<br/>
     * Если <code>begin</code> - пустая строка или <code>null</code>,
     * то результат содержит все слова, найденные в тексте.<br/>
     * Все возвращенные слова должны быть приведены к нижнему регистру.
     *
     * @param begin первые символы искомых слов
     * @return слова, начинающиеся с указанной последовательности символов
     * @throws IllegalStateException если нет текста для поиска
     *                               (если ни один setter-метод не выполнялся или если он последний раз выполнился с ошибкой)
     */
    @Override
    public Stream<String> findWordsStartWith(String begin) {
        if(getText()==null || flag1 || flag2 ||flag3 ||flag4 ){
            throw new IllegalStateException();
        }
        se=true;
        ArrayList<String> list=new ArrayList<>();
        ArrayList<String> temp=new ArrayList<>();

        String[] str=getText().split("[\\s\\n\\r\\t]+");
        for (int i = 0; i < str.length; i++) {
            if(str[i]!=null) {
                temp.add(str[i].toLowerCase());
            }else {
                temp.add(str[i]);
            }
        }
        if(begin!=null && begin!="") {
            String s = begin.toLowerCase();
            for (int i = 0; i < temp.size(); i++) {
                if (temp.get(i).startsWith(s)) {
                    list.add(temp.get(i));
                }
            }
            this.stream=list.stream();
            return list.stream();
        }
        else {
            this.stream=temp.stream();
            return temp.stream();
        }

    }

    /**
     * Превращает слова, найденные в {@link #findWordsStartWith(String)},
     * в текст с разделителями "пробел",
     * предварительно приведя их к нижнему регистру
     * и отсортировав по алфавиту.<br/>
     * И записывает этот текст в <code>os</code>.
     *
     * @param os Поток вывода.  Кодировка не важна.
     * @throws IOException           в случае ошибок при записи в поток
     * @throws IllegalStateException если поиск слов не выполнялся
     *                               (если {@link #findWordsStartWith(String)} не выполнялся после setter-метода
     *                               или если setter-метод последний раз выполнился с ошибкой)
     */
    @Override
    public void writeWords(OutputStream os) throws IOException {
        if(!se || getText()==null || flag1 || flag2 ||flag3 ||flag4 ){
            throw new IllegalStateException();
        }

        ArrayList<String> list= (ArrayList<String>) stream.collect(Collectors.toList());
        Collections.sort(list);
        String outText="";
        for (int i = 0; i < list.size(); i++) {
            outText+=list.get(i).toLowerCase()+" ";
        }
        outText=outText.substring(0,outText.length()-1);
        BufferedWriter bufferedWriter=new BufferedWriter(new OutputStreamWriter(os));
        bufferedWriter.write(outText);
        bufferedWriter.close();
        //while (bufferedWriter.)
    }
}
