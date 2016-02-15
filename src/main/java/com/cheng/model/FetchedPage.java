package com.cheng.model;

public class FetchedPage {

//	private String url;
	private Data data;
	private String content;
	private int statusCode;
	
	public FetchedPage() {
		super();
	}
	
	public FetchedPage(Data data, String content, int statusCode) {
		super();
		this.data = data;
		this.content = content;
		this.statusCode = statusCode;
	}

//	public FetchedPage(String url, String content, int statusCode) {
//		super();
//		this.url = url;
//		this.content = content;
//		this.statusCode = statusCode;
//	}
//	public String getUrl() {
//		return url;
//	}
//	public void setUrl(String url) {
//		this.url = url;
//	}
	
	public Data getData() {
		return data;
	}
	public void setData(Data data) {
		this.data = data;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public int getStatusCode() {
		return statusCode;
	}
	public void setStatusCode(int statusCode) {
		this.statusCode = statusCode;
	}
	
}
