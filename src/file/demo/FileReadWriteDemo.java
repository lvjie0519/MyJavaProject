package file.demo;

import java.io.*;
import java.util.zip.Inflater;

public class FileReadWriteDemo {

    public static void main(String []args){
//        readFileByFileInputStream();

//        readAndWrite();

        String path = "D:\\xiaomi\\JavaProject\\MyJavaProject\\src\\file\\demo\\res\\1213";
        byte[] data = uncompress(new File(path));
        System.out.println(new String(data));
    }


    public static void readFileByFileInputStream(){
        String path = "D:\\xiaomi\\JavaProject\\MyJavaProject\\src\\file\\demo\\res\\text.txt";

        File file = new File(path);
        try {
            FileInputStream fileInputStream = new FileInputStream(file);

            byte[] b = new byte[5];
            for(int i=0; i<6; i++){
                /**
                 * 每次读取一个字节,返回对应字节assic码, 读取指针自动后移
                 */
//                int readed = fileInputStream.read();
//                System.out.println("readed="+readed+"  char: "+(char)readed);


                /**
                 * 一次读取b长度的的数据，并把数据存放到b种，返回读取数据的长度, 读取指针自动后移
                 **/
//                int readed = fileInputStream.read(b);
//                System.out.println("readed="+readed+"  char: "+(char)readed+"  b: "+new String(b));

                /**
                 * 一次向b中写入 len长度的字节数据,从b的off出开始写，文件中依然是从文件头开始读数据；读取指针自动后移
                 * 返回读取的数据字节数, -1到文件末尾
                 */
//                int readed = fileInputStream.read(b, 0, 3);
//                System.out.println("readed="+readed+"  char: "+(char)readed+"  b: "+new String(b));
            }

            /**
             * 跳过文件n字节开始读取内容
             */
            fileInputStream.skip(1);
            int readed = fileInputStream.read(b, 0, 3);
            System.out.println("readed="+readed+"  char: "+(char)readed+"  b: "+new String(b));


        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public static void readAndWrite(){
        String path = "D:\\xiaomi\\JavaProject\\MyJavaProject\\src\\file\\demo\\res\\text.txt";
        String desPath = "D:\\xiaomi\\JavaProject\\MyJavaProject\\src\\file\\demo\\res\\text1.txt";
        File srcFile = new File(path);
        File desFile = new File(desPath);
        if(desFile.exists()){
            desFile.delete();
        }
        try {
            desFile.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }


        try {

            byte[] b = new byte[3];

            FileInputStream fileInputStream = new FileInputStream(srcFile);
            FileOutputStream fileOutputStream = new FileOutputStream(desFile);
            boolean hashData = true;
            while (hashData){
                int result = fileInputStream.read(b, 0, b.length);
                System.out.println(result);
                if(result == -1){
                    hashData = false;
                }else{
                    fileOutputStream.write(b, 0, result);
                }


            }

            fileInputStream.close();
            fileOutputStream.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }



    private static byte[] uncompress(File file) {

        byte[] data = new byte[0];
        try {
            data = getBytes(new FileInputStream(file));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        byte[] output = new byte[0];
        Inflater inflater = new Inflater();
        inflater.reset();
        inflater.setInput(data, 0, data.length);
        ByteArrayOutputStream outputStream = null;
        try {
            outputStream = new ByteArrayOutputStream(data.length);
            byte[] buf = new byte[1024];
            while (!inflater.finished()) {
                int i = inflater.inflate(buf);
                outputStream.write(buf, 0, i);
            }
            output = outputStream.toByteArray();
        } catch (Exception e) {
            output = data;
        } finally {
            try {
                if (outputStream != null) outputStream.close();
            } catch (IOException e) {
            }
        }
        inflater.end();
        return output;
    }

    /**
     * 获得指定文件的byte数组
     * @param in
     * @return
     */
    private static byte[] getBytes(FileInputStream in) {
        byte[] buffer = null;
        try {
            ByteArrayOutputStream bos = new ByteArrayOutputStream(1000);
            byte[] b = new byte[1000];
            int n;
            while ((n = in.read(b)) != -1) {
                bos.write(b, 0, n);
            }
            in.close();
            bos.close();
            buffer = bos.toByteArray();
        } catch (FileNotFoundException e) {
        } catch (IOException e) {
        }
        return buffer;
    }
}
