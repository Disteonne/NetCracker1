package readyNo;


import java.io.*;
import java.util.Scanner;

public class Io {
    public static void main(String[] args) {
        Scanner scanner=new Scanner(System.in);
        while (scanner.hasNext()){
            String s=scanner.nextLine().toString();
        }

        //АБСТРАКТНЫЕ
        InputStream inputStream=new InputStream() {
            @Override
            public int read() throws IOException {
                return 0;
            }
        };
        OutputStream outputStream=new OutputStream() {
            @Override
            public void write(int b) throws IOException {

            }
        };
        Reader reader=new Reader() {
            @Override
            public int read(char[] cbuf, int off, int len) throws IOException {
                return 0;
            }

            @Override
            public void close() throws IOException {

            }
        };
        Writer writer=new Writer() {
            @Override
            public void write(char[] cbuf, int off, int len) throws IOException {

            }

            @Override
            public void flush() throws IOException {

            }

            @Override
            public void close() throws IOException {

            }
        };
        //НЕ АБСТРАКТНЫЕ
        //InputStreamReader inputStreamReader=new InputStreamReader();
        //OutputStreamWriter outputStreamWriter=new OutputStreamWriter();
        //FileWriter fileWriter=new FileWriter();
        //ByteArrayInputStream byteArrayInputStream=new ByteArrayInputStream();
        //DataInputStream dataInputStream=new DataInputStream();
    }
}
