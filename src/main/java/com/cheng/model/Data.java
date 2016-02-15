package com.cheng.model;

public class Data {
	private String id;
	private String url;
	private boolean flag;
	
	public Data(String id, String url) {
		super();
		this.id = id;
		this.url = url;
	}
	
	public Data(String url, boolean flag) {
		super();
		this.url = url;
		this.flag = flag;
	}
	public boolean isFlag() {
		return flag;
	}

	public void setFlag(boolean flag) {
		this.flag = flag;
	}

	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	
}
