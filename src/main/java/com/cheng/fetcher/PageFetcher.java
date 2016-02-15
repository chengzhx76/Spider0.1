package com.cheng.fetcher;

import com.cheng.model.Data;
import com.cheng.model.FetchedPage;
import com.cheng.model.SpiderParams;
import com.cheng.queue.UrlQueue;
import org.apache.http.HttpEntity;
import org.apache.http.HttpHeaders;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.client.methods.RequestBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

/**
 * 页面的抓取
 * @author Administrator
 */
public class PageFetcher {
	private static final Logger logger = LoggerFactory.getLogger(PageFetcher.class);
	
	private CloseableHttpClient client;
	private HttpUriRequest request;
	
	public void close() {
		if(request!=null) {
			if(request.isAborted()) {
				request.abort();
		    }
		}
	    try {
	    	if(client!=null) {
	    		client.close();
	    	}
	    } catch (IOException e) {
	      e.printStackTrace();
	    }
	}
	
	public FetchedPage getHtmlContentByUrl(Data data) {
		HttpResponse response;
		int statusCode = 500;
		String content = null;
		
		client = HttpClients.custom().build();
		request = RequestBuilder.get().setUri(data.getUrl())
		  .setHeader(HttpHeaders.TIMEOUT, "10000")
		  .setHeader("User-Agent","Mozilla/5.0 (Windows NT 6.1; rv:16.0) Gecko/20100101 Firefox/16.0")
		  .build();
		try {
			// 获取Html源码
			response = client.execute(request);
			statusCode = response.getStatusLine().getStatusCode();
			HttpEntity entity = response.getEntity();
			if(entity!=null) {
				content = EntityUtils.toString(entity, SpiderParams.SITECHARSET);
			}
		} catch (IOException e) {
			e.printStackTrace();
			
			// 因请求超时等问题产生的异常，将URL放回待抓取队列，重新爬取
			logger.info(">> Put back url: " + data.getUrl());
			UrlQueue.addFirstElement(data);
		}
		return new FetchedPage(data, content, statusCode);
	}
	
}
