package tech.grug.io;

import java.io.*;

/**
 * Created by feichen on 2018/4/17.
 * <p>
 * 在Java IO中，流是一个核心的概念。
 * 流从概念上来说是一个连续的数据流。
 * 既可以从流中读取数据，也可以往流中写数据。
 * 流与数据源或者数据流向的媒介相关联。
 * 在Java IO中流既可以是字节流(以字节为单位进行读写)，也可以是字符流(以字符为单位进行读写)。
 */
public class JavaIoTest {

    /**
     * 字节流对应的类应该是InputStream和OutputStream
     *
     * @throws IOException
     */
    public static void writeByteToFile() throws IOException {
        String hello = "hello world";
        byte[] bytes = hello.getBytes();
        File file = new File("/Users/grug/test.txt");
        //字节流写,用outPutStream,写文件用FileOutPutStream
        OutputStream os = new FileOutputStream(file);
        os.write(bytes);
        os.close();
    }

    public static void readByteFromFile() throws IOException {
        File file = new File("/Users/grug/test.txt");
        byte[] fileByte = new byte[(int) file.length()];
        InputStream inputStream = new FileInputStream(file);
        //buffer 可以提高读取速度,比inputStream 一个一个字节读要快
        BufferedInputStream bufferedInputStream = new BufferedInputStream(inputStream, 2 * 1024);

//        inputStream.read(fileByte);
        bufferedInputStream.read(fileByte);
        System.out.println(new String(fileByte));
        inputStream.close();
    }

    /**
     * 字符流对应的类应该是Reader和Writer
     *
     * @throws IOException
     */
    public static void writeCharToFile() throws IOException {
        String hello = "hello world";
        File file = new File("/Users/grug/writeCharToFile_test.txt");
        //字符流写文件
        Writer fileWrite = new FileWriter(file);
        fileWrite.write(hello);
        fileWrite.close();
    }

    public static void readCharFromFile() throws IOException {
        File file = new File("/Users/grug/writeCharToFile_test.txt");
        Reader reader = new FileReader(file);
        //用buffer提高读取速度,可以指定每次读取的大小
        BufferedReader bufferedReader = new BufferedReader(reader, 2 * 1024);
        char[] chars = new char[(int) file.length()];
        bufferedReader.read(chars);
        String result = new String(chars);
        System.out.println(result);
    }

    /**
     * 管道主要用来实现同一个虚拟机中的两个线程进行交流
     *
     * @throws IOException
     */
    public static void pipedStream() throws IOException {
        final PipedOutputStream pipedOutputStream = new PipedOutputStream();
        final PipedInputStream pipedInputStream = new PipedInputStream(pipedOutputStream);
        Thread thread1 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    pipedOutputStream.write("hello pipe".getBytes());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
        Thread thread2 = new Thread(() -> {
            try {
                byte[] dataByte = new byte[10];
                pipedInputStream.read(dataByte);
                System.out.println(new String(dataByte));
            } catch (IOException ex) {
                try {
                    pipedInputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        });
        thread1.start();
        thread2.start();
    }

    /**
     * 字节流可以转换成字符流，
     *
     * @throws IOException
     */
    public static void convertBytesToChars() throws IOException {
        File file = new File("/Users/grug/test.txt");
        InputStream inputStream = new FileInputStream(file);
        Reader fileRead = new InputStreamReader(inputStream);
        char[] chars = new char[(int) file.length()];
        fileRead.read(chars);
        String result = new String(chars);
        System.out.println(result);

    }

    public static void main(String[] args) {
        try {
            pipedStream();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }


}
