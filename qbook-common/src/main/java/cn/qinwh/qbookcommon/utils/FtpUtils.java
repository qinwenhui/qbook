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
         File file = new File("E://test.txt");
         upload(new FileInputStream(file), file.getName());
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
            //创建根目录
            createDirecroty(ftpClient, baseDir);
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
            //进入到文件保存的目录
            ftpClient.changeWorkingDirectory(baseDir);
            //保存文件
            Boolean isSuccess = ftpClient.storeFile(originName,inputStream);
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
            ftpClient.changeWorkingDirectory(baseDir);
            FTPFile[] ftpFiles = ftpClient.listFiles(baseDir);
            Boolean flag = false;
            //遍历当前目录下的文件，判断是否存在待下载的文件
            for (FTPFile ftpFile:ftpFiles){
                if (ftpFile.getName().equals(remoteFileName)){
                    flag = true;
                    break;
                }
            }

            if (!flag){
                log.error("directory：{}下没有 {}",baseDir,remoteFileName);
                return outputStream;
            }
            //下载文件
            Boolean isSuccess = ftpClient.retrieveFile(remoteFileName,outputStream);
            if (!isSuccess){
                log.error("download file 【{}】 fail",remoteFileName);
            }

            log.info("download file success");
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

            if (outputStream != null){
                try {
                    outputStream.close();
                } catch (IOException e) {
                    log.error("outputStream close fail ------->>>",e);
                }
            }
        }

        return outputStream;
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
}
