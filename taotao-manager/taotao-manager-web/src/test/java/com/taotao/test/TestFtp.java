package com.taotao.test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.SocketException;

import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.junit.Test;

import com.taotao.common.utils.FtpUtil;

public class TestFtp {

	@Test
	public void testFtp() throws SocketException, IOException {
		// 创建一个FtpClient对象
		FTPClient ftpClient = new FTPClient();
		// 创建一个ftp连接,默认21端口
		ftpClient.connect("192.168.0.202", 21);
		// 登录ftp服务器
		ftpClient.login("chris", "123456");
		// 上传文件
		// 读取本地文件
		FileInputStream inputStream = new FileInputStream(new File("C:\\Users\\Administrator\\Pictures\\Saved Pictures\\0e630c414ca4293b2b2ef491d062e147.jpg"));
		// 设置上传路径
		ftpClient.changeWorkingDirectory("/home/chris");
		// 修改上传文件的格式
		ftpClient.setFileType(FTP.BINARY_FILE_TYPE);
		// 第一个参数：服务器端文档名
		// 第二个参数：上传文档的inputStream
		//
		ftpClient.storeFile("hello4.jpg", inputStream);
		// 关闭连接
		ftpClient.logout();
	}

	@Test
	public void testFTPutils() throws FileNotFoundException {
		FileInputStream inputStream = new FileInputStream(new File("D:\\image\\0.jpg"));
		FtpUtil.uploadFile("192.168.22.129", 21, "chris", "123456", "/images", "/2017/01/03", "hello11113.jpg",
				inputStream);

	}

	@Test
	public void testFtpUtil() throws Exception {
		FileInputStream inputStream = new FileInputStream(new File("D:\\image\\0.jpg"));
		FtpUtil.uploadFile("192.168.22.129", 21, "ftpuser", "123456", "/images", "/2017/01/04",
				"hellochriswwwkkkooqqq.jpg", inputStream);

	}

}
