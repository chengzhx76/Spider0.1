package com.cheng.queue;

import com.cheng.model.Data;

import java.util.concurrent.BlockingDeque;
import java.util.concurrent.LinkedBlockingDeque;

/**
 * 等待下载的图片URL
 * @author Administrator
 */
public class ImgQueue {
	private static BlockingDeque<Data> imgDeques = new LinkedBlockingDeque<>();
	
	public static void addElement(Data url) {
		try {
			imgDeques.putLast(url);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	public static Data outElement() {
		try {
			return imgDeques.takeFirst();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		return null;
	}
	public static void addFirstElement(Data url){
		try {
			imgDeques.putFirst(url);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	public static boolean isEmpty() {
		return imgDeques.isEmpty();
	}
	public static int size() {
		return imgDeques.size();
	}
	public static boolean isContains(Data url) {
		return imgDeques.contains(url);
	}
}
