package com.cheng.storage;

import com.cheng.model.ImgModel;
import com.cheng.utils.DBConn;
import com.cheng.utils.RegexUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;


/**
 * 将Parser解析出的目标数据存入本地存储，可以是MySQL传统数据库，也可以Redis等KV存储
 * @author Administrator
 */
public class DataStorage {
	private static final Logger logger = LoggerFactory.getLogger(DataStorage.class);
	
	public static Connection conn = DBConn.getConnection();
	
	public void store(Object data){
		if(null!=data) {
			ImgModel img = (ImgModel) data;

			String firstDir = RegexUtils.getFirstDir(img.getPath());
			String secondDir = RegexUtils.getSecondDir(img.getPath());
			String fileName = img.getPath().substring(img.getPath().lastIndexOf("/")+1);
			String directory = "mzitu/"+firstDir+"/"+secondDir+"/"+fileName;
			
			saveImg(img,directory);
			logger.info("detail存储目录为：=== "+directory);
		}
	}
	
	
	public static void indexStore(Object data) {
		if(null!=data) {
			ImgModel img = (ImgModel) data;
			
			String firstDir = RegexUtils.getFirstDir(img.getPath());
			String secondDir = RegexUtils.getSecondDir(img.getPath());
			String fileName = img.getPath().substring(img.getPath().lastIndexOf("/")+1);
			String directory = "mzitu/thumbs/"+firstDir+"/"+secondDir+"/"+fileName;
			
			saveImg(img,directory);
			logger.info("index存储目录为：=== "+directory);
		
		}
	}
	
	private static void saveImg(ImgModel img, String newPath) {
		PreparedStatement ps;
		try{
			if(img.isHome()) {
				ps = conn.prepareStatement("INSERT INTO t_img(id, title, srcPath, newPath, home) VALUES (?, ?, ?, ?, ?)");
				ps.setString(1, img.getId());
				ps.setString(2, img.getName());
				ps.setString(3, img.getPath());
				ps.setString(4, newPath);
				ps.setBoolean(5, img.isHome());
			}else {
				ps = conn.prepareStatement("INSERT INTO t_img(id, title, category, srcPath, newPath, pid, home, seq) VALUES (?, ?, ?, ?, ?, ?, ?, ?)");
				ps.setString(1, img.getId());
				ps.setString(2, img.getName());
				ps.setString(3, img.getCategory());
				ps.setString(4, img.getPath());
				ps.setString(5, newPath);
				ps.setString(6, img.getPid());
				ps.setBoolean(7, img.isHome());
				ps.setString(8, img.getIndex());
			}
			ps.execute();
			logger.info("Succesfully");
			ps.close();
		}catch (SQLException e) {
			logger.error(e.getMessage());
			e.getStackTrace();
		}
	}
		
	
	
}
