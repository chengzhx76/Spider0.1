package com.cheng.down;

import com.cheng.model.Data;
import com.cheng.model.SpiderParams;
import com.cheng.queue.ImgQueue;
import com.cheng.utils.RegexUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.concurrent.TimeUnit;

/**
 * 下载图片
 * @author Administrator
 *
 */
public class DownImg {
	
	private static final Logger logger = LoggerFactory.getLogger(DownImg.class);
	
	public static void downImg(Data imgUrl) {
		
		logger.info("开始下载图片地址！{}", imgUrl.getUrl());
		// 下载到默认一个文件夹中
//		StringBuffer rootPath = new StringBuffer(SpiderParams.FILEPATH);

		StringBuffer rootPath = new StringBuffer(SpiderParams.FILEPATH+File.separator);
		if(imgUrl.isFlag()) rootPath.append("thumbs");
		rootPath.append(File.separator).append(RegexUtils.getFirstDir(imgUrl.getUrl())).append(File.separator).append(RegexUtils.getSecondDir(imgUrl.getUrl())).append(File.separator);

		String fileName = imgUrl.getUrl().substring(imgUrl.getUrl().lastIndexOf("/")+1);
		InputStream is = null;
		FileOutputStream out = null;
		try {
			// 创建文件目录
			File files = new File(rootPath.toString());
			// 判断目录是否存在
			if(!files.exists()) {
				files.mkdirs();
			}
			// 获取图片的下载地址
			URL url = new URL(imgUrl.getUrl());
			// 连接网络图片地址
			HttpURLConnection uc = (HttpURLConnection) url.openConnection();
			uc.setConnectTimeout(5000);
			uc.setReadTimeout(5000);
			uc.setRequestProperty("User-Agent","Mozilla/5.0 (Windows NT 6.1; rv:16.0) Gecko/20100101 Firefox/16.0");
			// 获取连接的输出流
			is = uc.getInputStream();
			// 创建文件
			File file = new File(rootPath.append(fileName).toString());
			// 创建输出流，写入文件
			out = new FileOutputStream(file);
			int b=0;
			while ((b = is.read()) != -1) {
				out.write(b);
			}
			logger.info("图片下载完成！{}", fileName);
		} catch (IOException e) {
			logger.error("下载超时:{}", imgUrl);
			ImgQueue.addFirstElement(imgUrl);
			e.printStackTrace();
		} finally {
			try {
				if (is != null)
					is.close();
				if (out != null)
					out.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	public static void DowImgTask() throws InterruptedException {
//		while(true) {//考虑用多线程实现  生产消费模式
			if(ImgQueue.isEmpty()) {
				logger.info("暂时没有下载任务，休息5秒！");
				TimeUnit.SECONDS.sleep(5);
			}
			if(ImgQueue.isEmpty()) {
				logger.info("等了5秒还没有下载任务，程序自动结束！！！");
				System.exit(0);
			}
			while (!ImgQueue.isEmpty()) {
				DownImg.downImg(ImgQueue.outElement());
			}
			// 在此验证还有没有 下载任务
			DowImgTask();
//		}
	}
	
//	public static void main(String[] args) {
//		for(int i=1; i<34; i++) {
//			String p = String.format("%02d", i);
//			String imgUrl = "http://pic.mmfile.net/2015/12/01x"+p+".jpg";
//			System.out.println(imgUrl);
////			downImg(imgUrl);
//		}
//	}
	
}
