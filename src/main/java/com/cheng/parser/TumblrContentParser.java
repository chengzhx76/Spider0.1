package com.cheng.parser;

/**
 * 对Fetcher下载的页面内容进行解析，获取目标数据
 * @author Administrator
 */
public class TumblrContentParser {
	
//	private static final Logger logger = LoggerFactory.getLogger(TumblrContentParser.class);
//	
//	public Object parse(FetchedPage fetchedPage){
//		Object targetObject = null;
//		Document doc = Jsoup.parse(fetchedPage.getContent());
//		
//		// 如果当前页面包含目标数据
//		if(containsTargetData(fetchedPage.getUrl(), doc)){
//			// 解析并获取目标数据
//			Element pins = doc.getElementById("posts");
//			if(pins!=null) {
//				
//				Elements pages = pins.getElementsByClass("photo");
//				
//				for(Element page : pages) {
//					String mainImg = page.getElementsByTag("img").attr("src");
//					logger.info(mainImg);
////					DownImg.downImg(mainImg);
//				}
//			}
//		}
//		// 将URL放入已爬取队列
//		VisitedUrlQueue.addElement(fetchedPage.getUrl());
//		
//		// 根据当前页面和URL获取下一步爬取的URLs
//		// TODO
//		
//		return targetObject; 
//	}
//	
//	private boolean containsTargetData(String url, Document contentDoc){
//		
//		Element pins = contentDoc.getElementById("posts");
//		if(pins!=null) {
//			return true;
//		}
//		return false;
//	}
}
