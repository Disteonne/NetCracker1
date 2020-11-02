package ru.skillbench.tasks.javaapi.io;
import java.io.*;
import java.util.*;
import java.util.stream.Stream;

public class WordFinderImpll  implements  WordFinder{
    private String text=null;
    private List<String> foundedWords = new ArrayList<>();

    @Override
    public String getText() {
        return text;
    }

    @Override
    public void setText(String input) {
        if (input==null)throw new IllegalArgumentException();
        else text=input;
    }

    @Override
    public void setInputStream(InputStream is) throws IOException {
        if(is == null)throw new IllegalArgumentException();
        try(BufferedInputStream bis = new BufferedInputStream(is); ByteArrayOutputStream buf = new ByteArrayOutputStream())
        {
            int result = bis.read();
            while(result != -1) {
                buf.write((byte)result);
                result = bis.read();
            }
            setText(buf.toString());
        }
    }

    @Override
    public void setFilePath(String filePath) throws IOException {
        if(filePath == null)throw new IllegalArgumentException();
        try(BufferedInputStream bis = new BufferedInputStream(new FileInputStream(new File(filePath))); ByteArrayOutputStream buf = new ByteArrayOutputStream())
        {
            int result = bis.read();
            while(result != -1) {
                buf.write((byte)result);
                result = bis.read();
            }
            setText(buf.toString());
        }
    }

    @Override
    public void setResource(String resourceName) throws IOException {
        if(resourceName == null)throw new IllegalArgumentException();
        try(InputStream bis = new BufferedInputStream(WordFinderImpl.class.getResourceAsStream(resourceName));
            ByteArrayOutputStream buf = new ByteArrayOutputStream())
        {
            int result = bis.read();
            while(result != -1) {
                buf.write((byte)result);
                result = bis.read();
            }
            setText(buf.toString());
        }
    }

    @Override
    public Stream<String> findWordsStartWith(String begin) {
        if(text==null) throw new IllegalStateException();
        List<String> allWords = new ArrayList<>();
        Collections.addAll(allWords, text.split("[\\s\\n\\t\\r]"));
        for (String word:allWords)
        {
            if (word!=null)
            {
                if (!word.equals(""))
                {
                    word = word.toLowerCase();
                    if (begin==null || begin.equals(""))
                    {
                        foundedWords.add(word.toLowerCase());
                    }
                    else
                    {
                        if (word.startsWith(begin.toLowerCase()))foundedWords.add(word);
                    }
                }
            }
        }
        if (foundedWords==null || foundedWords.isEmpty())return Stream.<String>builder().build();
        else return foundedWords.stream();
    }

    @Override
    public void writeWords(OutputStream os) throws IOException {
        if (foundedWords.isEmpty()) throw new IllegalStateException();
        Collections.sort(foundedWords);
        String outputString = String.join(" ", foundedWords);
        PrintWriter pw = new PrintWriter(os);
        pw.write(outputString);
    }
}
