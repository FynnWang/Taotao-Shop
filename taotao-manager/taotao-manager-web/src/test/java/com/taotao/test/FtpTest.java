package com.taotao.test;

import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.junit.Test;

import java.io.File;
import java.io.FileInputStream;

public class FtpTest {

    @Test
    public void testFtpClient() throws Exception {

        FTPClient ftpClient = new FTPClient();
        ftpClient.connect("192.168.0.202", 21);
        ftpClient.login("chris", "123456");
        FileInputStream fileInputStream = new FileInputStream(new File("C:\\Users\\Administrator\\Pictures\\Saved Pictures\\0e630c414ca4293b2b2ef491d062e147.jpg"));
        ftpClient.changeWorkingDirectory("/home/chris/www");
        ftpClient.setFileType(FTP.BINARY_FILE_TYPE);
        ftpClient.storeFile("hello3.jpg", fileInputStream);
        ftpClient.logout();
    }
}
