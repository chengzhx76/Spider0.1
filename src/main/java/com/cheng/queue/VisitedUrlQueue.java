package com.cheng.queue;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * 已经抓取的列队
 * @author Administrator
 */

public class VisitedUrlQueue {
	private static BlockingQueue<String> visitedUrlQueue = new LinkedBlockingQueue<>();
	
	public static void addElement(String url) {
		try {
			visitedUrlQueue.put(url);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	public static String outElement(String url) {
		try {
			return visitedUrlQueue.take();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		return null;
	}
	public static boolean isEmpty() {
		return visitedUrlQueue.isEmpty();
	}
	public static int size() {
		return visitedUrlQueue.size();
	}
	public static boolean isContains(String url) {
		return visitedUrlQueue.contains(url);
	}
	
}
