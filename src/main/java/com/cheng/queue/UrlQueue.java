package com.cheng.queue;

import com.cheng.model.Data;

import java.util.concurrent.BlockingDeque;
import java.util.concurrent.LinkedBlockingDeque;

/**
 * 等待抓取的Url列队
 * @author Administrator
 */
public class UrlQueue {
	private static BlockingDeque<Data> urlDeques = new LinkedBlockingDeque<>();
	
	public static void addElement(Data url) {
		try {
			urlDeques.putLast(url);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	public static Data outElement() {
		try {
			return urlDeques.takeFirst();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		return null;
	}
	public static void addFirstElement(Data url){
		try {
			urlDeques.putFirst(url);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	public static boolean isEmpty() {
		
/*		if(urlDeques.isEmpty()) {
			try {
				TimeUnit.SECONDS.sleep(1);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}*/
		
		return urlDeques.isEmpty();
	}
	public static int size() {
		return urlDeques.size();
	}
	public static boolean isContains(Data url) {
		return urlDeques.contains(url);
	}
}
