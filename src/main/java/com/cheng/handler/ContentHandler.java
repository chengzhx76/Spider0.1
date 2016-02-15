package com.cheng.handler;

import com.cheng.model.FetchedPage;
import com.cheng.queue.UrlQueue;
import org.apache.http.HttpStatus;

/**
 * 对Fetcher下载的页面进行初步处理，如判断该页面的返回状态码是否正确、页面内容是否为反爬信息等，从而保证传到Parser进行解析的页面是正确的
 * @author Administrator
 *
 */
public class ContentHandler {
	// 检查页面  有反爬验证返回False
	public boolean check(FetchedPage fetchedPage) {
		// 如果抓取的页面包含反爬取内容，则将当前URL放入待爬取队列，以便重新爬取
		if(isAntiScratch(fetchedPage)){
			UrlQueue.addFirstElement(fetchedPage.getData());
			return false;
		}
		return true;
	}
	// 是否有反爬验证
	private boolean isAntiScratch(FetchedPage fetchedPage) {
		// 403 禁止访问
		if(statusCodeValid(fetchedPage.getStatusCode()) && fetchedPage.getStatusCode() == HttpStatus.SC_FORBIDDEN) {
			return true;
		}
		// 页面内容是否有反爬内容
		if(fetchedPage.getContent().contains("<div>禁止访问</div>")) {
			return true;
		}
		return false;
	}
	// 状态码验证
	private boolean statusCodeValid(int statusCode) {
		if(statusCode >= HttpStatus.SC_OK && statusCode < HttpStatus.SC_BAD_REQUEST) {
			return true;
		}
		return false;
	}
}
