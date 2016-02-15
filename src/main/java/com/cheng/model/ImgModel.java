package com.cheng.model;

public class ImgModel {
	private String id;
	private String name;
	private String category;
	private String path;
	private String pid;
	private boolean home;//是否是首页的资源
	private String index;
	
	public ImgModel() {}
	
	public ImgModel(String id, String name, String path, boolean home) {
		super();
		this.id = id;
		this.name = name;
		this.path = path;
		this.home = home;
	}

	public ImgModel(String id, String name,  String category, String srcPath, String pid, boolean home, String index) {
		this.id = id;
		this.name = name;
		this.category = category;
		this.path = srcPath;
		this.pid = pid;
		this.home = home;
		this.index = index;
	}
	
	public String getIndex() {
		return index;
	}
	public void setIndex(String index) {
		this.index = index;
	}
	public boolean isHome() {
		return home;
	}
	public void setHome(boolean home) {
		this.home = home;
	}
	public String getPid() {
		return pid;
	}
	public void setPid(String pid) {
		this.pid = pid;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	
}
