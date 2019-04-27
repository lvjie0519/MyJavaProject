package file.demo;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.zip.GZIPInputStream;

/**
 * Created by 41002 on 2019/4/27.
 */
public class FileGzDemo {

    public static void main(String []args){
        System.out.println("hello world");


        String fileUrl = "https://raw.githubusercontent.com/lvjie0519/MRNStudyProject/master/app/src/main/res/mipmap-mdpi/ic_launcher_round.png";
        String fileAbsolutePath = "C:\\lvjie\\MyProject\\MyJavaProject\\src\\file\\demo\\res\\abc.jpg";
//        String fileAbsolutePath = "C:\\lvjie\\MyProject\\MyJavaProject\\src\\file\\demo\\res\\13";
        downloadFile(fileUrl, null, fileAbsolutePath, 6000, 6000);

//        byte[] bytes = readFile(fileAbsolutePath);
//
//        System.out.println("file length is "+bytes.length);
//        int length = bytes.length;
//        for(int i=0; i<length; i++){
//            if(i%10==0){
//                System.out.println(bytes[i]);
//            }else{
//                System.out.print(bytes[i]+"  ");
//            }
//        }
    }

    public static byte[] readFile(String fileAbsoluteName){
        FileInputStream inputStream = null;
        ByteArrayOutputStream baos = null;
        try {
            inputStream = new FileInputStream(fileAbsoluteName);

            baos = new ByteArrayOutputStream();

            int defaultSize = 1024;
            byte[] buf = new byte[defaultSize];
            int len = 0;
            while((len=inputStream.read(buf, 0, defaultSize))!=-1){
                baos.write(buf, 0, len);
            }

            byte[] bytes = baos.toByteArray();
            return bytes;

        } catch (Exception e) {
        }finally{
            if(inputStream!=null){
                try {
                    inputStream.close();
                } catch (IOException e) {
                }
            }
            if(baos!=null){
                try {
                    baos.close();
                } catch (IOException e) {
                }
            }
        }

        return null;
    }

    public static byte[] readGzFile(String fileAbsoluteName){
        GZIPInputStream gzipInputStream = null;
        ByteArrayOutputStream baos = null;
        try {
            gzipInputStream = new GZIPInputStream(new FileInputStream(fileAbsoluteName));

            baos = new ByteArrayOutputStream();

            int defaultSize = 1024;
            byte[] buf = new byte[defaultSize];
            int len = 0;
            while((len=gzipInputStream.read(buf, 0, defaultSize))!=-1){
                baos.write(buf, 0, len);
            }

            byte[] bytes = baos.toByteArray();
            return bytes;

        } catch (Exception e) {
        }finally{
            if(gzipInputStream!=null){
                try {
                    gzipInputStream.close();
                } catch (IOException e) {
                }
            }
            if(baos!=null){
                try {
                    baos.close();
                } catch (IOException e) {
                }
            }
        }

        return null;
    }

    public static void downloadFile(String url, HashMap<String, Object> header, String file, int connectionTimeout, int readTimeout) {
        InputStream input = null;
        OutputStream output = null;
        HttpURLConnection connection = null;

        int total = 0;
        int lengthOfFile = 0;
        try {
            connection = (HttpURLConnection) new URL(url).openConnection();
            if (header != null) {
                for (Map.Entry<String, Object> entry : header.entrySet()) {
                    Object value = entry.getValue();
                    if (value != null) {
                        connection.setRequestProperty(URLEncoder.encode(entry.getKey()), URLEncoder.encode(value.toString()));
                    }
                }
            }

            connection.setConnectTimeout(connectionTimeout);
            connection.setReadTimeout(readTimeout);
            connection.connect();

            int statusCode = connection.getResponseCode();
            lengthOfFile = connection.getContentLength();

            boolean isRedirect = (
                    statusCode != HttpURLConnection.HTTP_OK &&
                            (
                                    statusCode == HttpURLConnection.HTTP_MOVED_PERM ||
                                            statusCode == HttpURLConnection.HTTP_MOVED_TEMP ||
                                            statusCode == 307 ||
                                            statusCode == 308
                            )
            );

            if (isRedirect) {
                String redirectURL = connection.getHeaderField("Location");
                connection.disconnect();

                connection = (HttpURLConnection) new URL(redirectURL).openConnection();
                connection.setConnectTimeout(5000);
                connection.connect();

                statusCode = connection.getResponseCode();
                lengthOfFile = connection.getContentLength();
            }
            if (statusCode >= 200 && statusCode < 300) {
                Map<String, List<String>> headers = connection.getHeaderFields();
                for (Map.Entry<String, List<String>> entry : headers.entrySet()) {
                    String headerKey = entry.getKey();
                    String valueKey = entry.getValue().get(0);
                    if (headerKey != null && valueKey != null) {
//                        System.out.println("headerKey="+headerKey+"   valueKey="+valueKey);
                    }
                }

                input = new BufferedInputStream(connection.getInputStream(), 8 * 1024);
                File file1 = new File(file);
                if(file1.exists()){
                    file1.delete();
                }
                file1.createNewFile();
                output = new FileOutputStream(file);
                byte data[] = new byte[1024];
                int count;

                while ((count = input.read(data)) != -1) {
                    System.out.println("count="+count);
                    total += count;
                    // data：将data的数据输出到output， offset：指从data的其实位置,  length:offset到length的长度数据
                    output.write(data, 0, count);
                }

                output.flush();
            }
            System.out.println("total="+total+"   lengthOfFile="+lengthOfFile);
        } catch (Throwable e) {
        } finally {
            try {
                if (output != null) output.close();
                if (input != null) input.close();
                if (connection != null) connection.disconnect();
            } catch (Exception e) {
            }
        }
    }
}
