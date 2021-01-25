package cn.qinwh.qbookcommon.utils;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;
import org.apache.commons.net.ftp.FTPReply;

import java.io.*;

/**
 * @program: qbook
 * @description: ftp工具类
 * @author: qinwh
 * @create: 2021-01-25 14:35
 **/
@Slf4j
public class FtpUtils {

    private static final String url = "127.0.0.1";
    private static final int port = 9022;
    private static final String userName = "qbook";
    private static final String password = "123456";
    private static final String baseDir = "/qbook/file";
    private static final int timeout = 1000*30;

    /**
     * 测试
     */
     public static void main(String s[]) throws FileNotFoundException {
         File file = new File("D://qinwh/code/caiji/pro.txt");
         upload(new FileInputStream(file), "/Uploads/book/"+file.getName());
     }


    /**
    * @Description: 获取连接
    * @Param: []
    * @return: org.apache.commons.net.ftp.FTPClient
    * @Author: qinwh
    * @Date: 2021/1/25
    */
    private static FTPClient connectFtpServer(){
        FTPClient ftpClient = new FTPClient();
        //设置连接超时时间
        ftpClient.setConnectTimeout(timeout);
        //设置ftp字符集
        ftpClient.setControlEncoding("utf-8");
        //设置被动模式，文件传输端口设置
        ftpClient.enterLocalPassiveMode();
        try {
            //设置文件传输模式为二进制，可以保证传输的内容不会被改变
//            ftpClient.setFileType(FTP.BINARY_FILE_TYPE);
            ftpClient.connect(url, port);
            ftpClient.login(userName,password);
            int replyCode = ftpClient.getReplyCode();
            if (!FTPReply.isPositiveCompletion(replyCode)){
                log.error("connect ftp {} failed",url);
                ftpClient.disconnect();
                return null;
            }
        } catch (IOException e) {
            log.error("connect fail ------->>>",e);
            return null;
        }
        return ftpClient;
    }

    /**
    * @Description: 上传文件
    * @Param: [inputStream, originName]
    * @return: void
    * @Author: qinwh
    * @Date: 2021/1/25
    */
    public static void upload(InputStream inputStream, String originName){
        FTPClient ftpClient = connectFtpServer();
        if (ftpClient == null){
            return;
        }

        try {
            //分离目标文件的目录和文件名
            PathAndName pathAndName = getPathAndName(originName);
            //创建目录
            createDirecroty(ftpClient, pathAndName.getPath());
            //进入到文件保存的目录
            ftpClient.changeWorkingDirectory(pathAndName.getPath());
            //保存文件
            Boolean isSuccess = ftpClient.storeFile(pathAndName.getName(),inputStream);
            if (!isSuccess){
                log.error("{}---》上传失败！",originName);
            }else{
                log.info("{}---》上传成功！",originName);
            }
            ftpClient.logout();
        } catch (IOException e) {
            log.error("{}---》上传失败！",e);
        }finally {
            if (ftpClient.isConnected()){
                try {
                    ftpClient.disconnect();
                } catch (IOException e) {
                    log.error("disconnect fail ------->>>",e);
                }
            }
        }
    }

    /**
    * @Description: 下载文件
    * @Param: [remoteFileName, localFileName]
    * @return: void
    * @Author: qinwh
    * @Date: 2021/1/25
    */
    public static OutputStream download(String remoteFileName, OutputStream outputStream){
        FTPClient ftpClient = connectFtpServer();
        if (ftpClient == null){
            return outputStream;
        }
        try {
            //分离目标文件的目录和文件名
            PathAndName pathAndName = getPathAndName(remoteFileName);
            ftpClient.changeWorkingDirectory(pathAndName.getPath());
            FTPFile[] ftpFiles = ftpClient.listFiles(pathAndName.getPath());
            Boolean flag = false;
            //遍历当前目录下的文件，判断是否存在待下载的文件
            for (FTPFile ftpFile:ftpFiles){
                if (ftpFile.getName().equals(pathAndName.getName())){
                    flag = true;
                    break;
                }
            }

            if (!flag){
                log.error("directory：{}下没有 {}",pathAndName.getPath(),pathAndName.getName());
                return outputStream;
            }
            //下载文件
            Boolean isSuccess = ftpClient.retrieveFile(pathAndName.getName(),outputStream);
            if (!isSuccess){
                log.error("download file 【{}】 fail",pathAndName.getPath() + pathAndName.getName());
            }
            ftpClient.logout();
        } catch (IOException e) {
            log.error("download file 【{}】 fail ------->>>",e);
        }finally {
            if (ftpClient.isConnected()){
                try {
                    ftpClient.disconnect();
                } catch (IOException e) {
                    log.error("disconnect fail ------->>>",e);
                }
            }
        }

        return outputStream;
    }


    public static InputStream download(String remoteFileName){
        InputStream inputStream = null;
        FTPClient ftpClient = connectFtpServer();
        if (ftpClient == null){
            return inputStream;
        }
        try {
            //分离目标文件的目录和文件名
            PathAndName pathAndName = getPathAndName(remoteFileName);
            ftpClient.changeWorkingDirectory(pathAndName.getPath());
            FTPFile[] ftpFiles = ftpClient.listFiles(pathAndName.getPath());
            Boolean flag = false;
            //遍历当前目录下的文件，判断是否存在待下载的文件
            for (FTPFile ftpFile:ftpFiles){
                if (ftpFile.getName().equals(pathAndName.getName())){
                    flag = true;
                    break;
                }
            }

            if (!flag){
                log.error("directory：{}下没有 {}",pathAndName.getPath(),pathAndName.getName());
                return inputStream;
            }
            //下载文件
            inputStream = ftpClient.retrieveFileStream(pathAndName.getName());
            ftpClient.logout();
        } catch (IOException e) {
            log.error("download file 【{}】 fail ------->>>",e);
        }finally {
            if (ftpClient.isConnected()){
                try {
                    ftpClient.disconnect();
                } catch (IOException e) {
                    log.error("disconnect fail ------->>>",e);
                }
            }
        }

        return inputStream;
    }

    public static String readFile (String remoteFileName){
        StringBuffer sb = new StringBuffer("");
        try{
            InputStream inputStream = download(remoteFileName);
            if(inputStream == null){
                log.info("该文件不存在:"+remoteFileName);
                return null;
            }
            BufferedReader br = new BufferedReader(new InputStreamReader(inputStream));
            String line = "";
            while((line=br.readLine())!=null){
                sb.append(line);
            }
        }catch (Exception e){
            log.error("文件读取错误", e);
        }
        return sb.toString();

    }
    /**
    * @Description: 创建多层目录
    * @Param: [remote]
    * @return: boolean
    * @Author: qinwh
    * @Date: 2021/1/25
    */
    private static void createDirecroty(FTPClient ftpClient, String remote) throws IOException {
        String temp = "";
        String[] dirs = remote.split("/");
        for(String dir : dirs){
            if(dir != null && !"".equals(dir)){
                temp += "/"+dir;
                ftpClient.makeDirectory(temp);
            }
        }
    }

    //获取一个全文件名的路径和文件名
    private static PathAndName getPathAndName(String remote){
        String fileName = "";
        String path = baseDir;
        int index = 0;
        if((index=remote.lastIndexOf("/")) != -1){
            String tmp = remote.substring(0,index);
            if(!"/".equals(tmp.substring(0,1))){
                //如果这个目录前面没有“/”，则手动添加
                path += ("/" + tmp);
            }else{
                path += tmp;
            }
            fileName = remote.substring(index+1);
        }else{
            fileName = remote;
        }
        return new PathAndName(path, fileName);
    }

    private static class PathAndName {
        private String path;
        private String name;

        public PathAndName(String path, String name){
            this.path = path;
            this.name = name;
        }

        public String getPath() {
            return path;
        }

        public void setPath(String path) {
            this.path = path;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }
}
