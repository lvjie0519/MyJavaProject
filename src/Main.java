import java.io.*;
import java.lang.reflect.Field;
import java.nio.charset.Charset;
import java.util.*;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

/**
 * Created by Administrator on 2017/9/3 0003.
 */
public class Main {

    public static void main(String []args){
        System.out.println("hello world");


//        String filePath = ".."+File.separator+".."+File.separator+"tiqiaacommonsdk";
//        String filePath = "."+File.separator+"."+File.separator+"tiqiaacommonsdk";
//        String filePath = "tiqiaacommonsdk.txt";
//        String filePath = "...."+File.separator+".....aa"+"tiqiaacommonsdk";
//        String filePath = "...."+File.separator+".....aa"+"tiqiaacommonsdk"+File.separator+"a.txt";
//        String filePath = ".."+File.separator+"..";
////        String filePath = "..";
//        String [] files = getFileList(filePath);
//        if(files != null){
//            for (String item:files) {
//                System.out.println(item);
//            }
//        }else{
//            System.out.println("fiels is null");
//        }

        String srcZipPath = "C:\\lvjie\\test\\aaa.zip";
        String desFilePath = "C:\\lvjie\\test\\aaa";
//        File file = new File(srcZipPath);
//        unZip(file, desFilePath);

//        doUnzip(srcZipPath, desFilePath, null);

//        String demoPath = "C:\\lvjie\\test\\aaa2\\..";
//        File file = new File(demoPath);
//        System.out.println(file.getAbsoluteFile());
//        if(!file.exists()){
//            System.out.println("1 **********");
//            try {
//                file.createNewFile();
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        }else{
//            System.out.println("2 **********");
//        }

//        String srcZipPath = "C:\\\\lvjie\\test\\aaa.zip";
//        String[] splits = srcZipPath.split(File.separator+File.separator);
//        System.out.println(splits.length+"  "+splits[0]);
//
        verifyFileNameValid("C:\\lvjie\\test", "a\\aa");
        System.out.println();
        verifyFileNameValid("C:\\lvjie\\test", "..\\aa");
        System.out.println();
        verifyFileNameValid("C:\\lvjie\\test", "\\..\\aa");
        System.out.println();
        verifyFileNameValid("C:\\lvjie\\test", "a\\..\\..\\aa");

    }

    private static boolean doUnzip(String zipPath, String outputDirectory, String onlyUnzipFile) {
        ZipFile zipFile = null;
        int BUFFER_SIZE = 1024;
        try {
            zipFile = new ZipFile(zipPath);
            Enumeration<? extends ZipEntry> zipList = zipFile.entries();
            ZipEntry ze = null;
            byte[] buffer = new byte[BUFFER_SIZE];

            while (zipList.hasMoreElements()) {
                ze = zipList.nextElement();

                if (ze.isDirectory()) {
                    continue;
                }

                String name = ze.getName();
                name = verifyFileName(name);
                if (name != null && name.indexOf("\\") != -1) {
                    name = name.replaceAll("\\\\", File.separator);
                }
                String filePath = outputDirectory + File.separator + name;
                System.out.println("filePath="+filePath);

                if (onlyUnzipFile != null && (filePath.indexOf(onlyUnzipFile) == -1)) {
                    continue;
                }
                File file = createFileIfNotExists(filePath);

                OutputStream os = new BufferedOutputStream(new FileOutputStream(file));
                InputStream is = new BufferedInputStream(zipFile.getInputStream(ze));

                int count = 0;
                while ((count = is.read(buffer, 0, BUFFER_SIZE)) != -1) {
                    os.write(buffer, 0, count);
                }
                os.flush();

                is.close();
                os.close();

            }

        } catch (FileNotFoundException e) {

            return false;
        } catch (Throwable e) {
            e.printStackTrace();
            return false;
        } finally {
            if(zipFile != null) {
                try {
                    zipFile.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }

        return true;

    }

    public static File createFileIfNotExists(String filePath) {
        File file = new File(filePath);

        File parent = file.getParentFile();
        if (!parent.exists()) {
            parent.mkdirs();
        }

        try {
            if (!file.exists()) {
                file.createNewFile();
            }
        } catch (IOException e) {
        }

        return file;
    }

    /**
     * zip解压
     * @param srcFile        zip源文件
     * @param destDirPath     解压后的目标文件夹
     * @throws RuntimeException 解压失败会抛出运行时异常
     */
    public static void unZip(File srcFile, String destDirPath) throws RuntimeException {
        long start = System.currentTimeMillis();
        // 判断源文件是否存在
        if (!srcFile.exists()) {
            throw new RuntimeException(srcFile.getPath() + "所指文件不存在");
        }
        // 开始解压
        ZipFile zipFile = null;
        try {
            zipFile = new ZipFile(srcFile);
            Enumeration<?> entries = zipFile.entries();
            while (entries.hasMoreElements()) {
                ZipEntry entry = (ZipEntry) entries.nextElement();
                System.out.println("解压" + entry.getName());
                // 如果是文件夹，就创建个文件夹
                if (entry.isDirectory()) {
                    String dirPath = destDirPath + "/" + entry.getName();
                    File dir = new File(dirPath);
                    dir.mkdirs();
                } else {
                    // 如果是文件，就先创建一个文件，然后用io流把内容copy过去
                    File targetFile = new File(destDirPath + "/" + entry.getName());
                    // 保证这个文件的父文件夹必须要存在
                    if(!targetFile.getParentFile().exists()){
                        targetFile.getParentFile().mkdirs();
                    }
                    targetFile.createNewFile();
                    // 将压缩文件内容写入到这个文件中
                    InputStream is = zipFile.getInputStream(entry);
                    FileOutputStream fos = new FileOutputStream(targetFile);
                    int len;
                    byte[] buf = new byte[1024];
                    while ((len = is.read(buf)) != -1) {
                        fos.write(buf, 0, len);
                    }
                    // 关流顺序，先打开的后关闭
                    fos.close();
                    is.close();
                }
            }
            long end = System.currentTimeMillis();
            System.out.println("解压完成，耗时：" + (end - start) +" ms");
        } catch (Exception e) {
            throw new RuntimeException("unzip error from ZipUtils", e);
        } finally {
            if(zipFile != null){
                try {
                    zipFile.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }


        private static String[] getFileList(String fileName){

        String validFileName = verifyFileName(fileName);

        String baseFilePath = "C:\\xiaomi\\GitTest";
        String filePath = baseFilePath+File.separator+validFileName;
        System.out.println("filePath="+filePath+"  fileName="+fileName+"  validFileName="+validFileName);
        File file = new File(filePath);
        if(file.exists()){
            return file.list();
        }else{
            return null;
        }
    }


    /**
     *
     * @param basePath  基础路径
     * @param fileName   文件名
     * @return
     */
    private static boolean verifyFileNameValid(String basePath, String fileName) {
        boolean result = false;
        if (basePath == null || fileName == null) {
            return false;
        }

        String fileAbsolutePathName = basePath+File.separator+fileName;
        return  verifyFileAbosolutePath(basePath, fileAbsolutePathName);
    }

    /**
     * 验证fileNamePath的合法性，对fileNamePath 中的 .. 进行解析，最终是否已 basePath 开头
     * @param basePath
     * @param fileAbsolutePathName
     * @return
     */
    private static boolean verifyFileAbosolutePath(String basePath, String fileAbsolutePathName){
        boolean result = false;
        if (basePath == null || fileAbsolutePathName == null) {
            return false;
        }

        String[] separatorFileNamePaths = fileAbsolutePathName.split(File.separator + File.separator);

        List<String> folders = new ArrayList<>();
        int len = separatorFileNamePaths.length;
        for(int i=0; i<len; i++){
            folders.add(separatorFileNamePaths[i]);
        }

        // 转换  对合并的路径进行解析，..需要前移
        Stack<String> resultFolders = new Stack<>();
        len = folders.size();
        for(int i=0; i<len; i++){
            if("..".equals(folders.get(i))){
                if(resultFolders.size() <= 0){
                    // 路径非法
                    result = false;
                    break;
                }else{
                    resultFolders.pop();
                }
            }else if("".equals(folders.get(i))){
                // 空字符串，什么也不做
            }else{
                resultFolders.push(folders.get(i));
            }
        }

        // 提取
        StringBuilder resultFilePathName = new StringBuilder();
        len = resultFolders.size();
        for(int i=0; i<len; i++){
            if(i == len-1){
                resultFilePathName.append(resultFolders.get(i));
            }else{
                resultFilePathName.append(resultFolders.get(i)).append(File.separator);
            }
        }
        // 校验
        if(resultFilePathName.toString().startsWith(basePath)){
            result = true;
        }else{
            result = false;
        }

        System.out.println("fileAbsolutePathName="+fileAbsolutePathName);
        System.out.println("resultFilePathName="+resultFilePathName.toString());
        System.out.println("result="+result);

        return result;
    }

    private static String verifyFileName(String fileName){

        if(!fileName.contains("..")){
            return fileName;
        }

        String [] separatorPaths = fileName.split(File.separator+File.separator);
        String result = "";

        int separatorPathsLen = separatorPaths.length;
        for (int i=0; i<separatorPathsLen; i++) {
            while (separatorPaths[i].startsWith("..")){
                int len = separatorPaths[i].length();
                separatorPaths[i] = separatorPaths[i].substring(2, len);
            }
            if(!"".equals(separatorPaths[i])){
                if(i == separatorPathsLen-1){
                    result += separatorPaths[i];
                }else{
                    result += separatorPaths[i]+File.separator;
                }

            }
        }
        return result;
    }

}
