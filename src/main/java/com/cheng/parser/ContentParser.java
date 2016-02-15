package com.cheng.parser;

import com.cheng.model.Data;
import com.cheng.model.FetchedPage;
import com.cheng.model.ImgModel;
import com.cheng.queue.ImgQueue;
import com.cheng.queue.UrlQueue;
import com.cheng.queue.VisitedUrlQueue;
import com.cheng.storage.DataStorage;
import com.cheng.utils.StringUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 对Fetcher下载的页面内容进行解析，获取目标数据
 * @author Administrator
 */
public class ContentParser {
	
	private static final Logger logger = LoggerFactory.getLogger(ContentParser.class);
	
	private volatile int count=1;
	
	public Object parse(FetchedPage fetchedPage){
		
		ImgModel img = null;
//		Object targetObject = null;
		
		Document doc = Jsoup.parse(fetchedPage.getContent());
		
		// 如果当前页面包含目标数据
		if(containsTargetData(fetchedPage.getData().getUrl(), doc)){
			// 解析并获取目标数据
			Element pins = doc.getElementById("pins");
			if(pins!=null) {
				Elements indexLiTags = pins.getElementsByTag("li");
				for(Element indexLiTag : indexLiTags) {
					Element indexATag = indexLiTag.getElementsByTag("a").get(0);
					String urlPath = indexATag.attr("href");
					String imgPath = indexATag.getElementsByTag("img").attr("data-original");

					String indexTitle = indexLiTag.getElementsByTag("span").get(0).getElementsByTag("a").get(0).text();
					
					String id = StringUtils.uuid();
					UrlQueue.addElement(new Data(id, urlPath));
					
					DataStorage.indexStore(new ImgModel(id, indexTitle, imgPath, true));
					ImgQueue.addElement(new Data(imgPath, true));
					logger.info("Index添加图片地址到列队==妹子id:{}，名字：{}，图片地址：{}", id, indexTitle, imgPath);
				}
			}else {
				// 获取当前页的主要信息
				
				Element pageDiv = doc.getElementsByClass("pagenavi").get(0);
				// 当前页码
				String pageNum = pageDiv.select("div.pagenavi > span").get(0).text();
				if("…".equals(pageNum)) pageNum = pageDiv.select("div.pagenavi > span").get(1).text();
				// 标题
				Elements mainTitleDiv = doc.getElementsByClass("main-title");
				String title = mainTitleDiv.get(0).text();
				// 类别
				Elements mainMetaDiv = doc.getElementsByClass("main-meta");
				String category = mainMetaDiv.get(0).getElementsByTag("span").get(0).getElementsByTag("a").get(0).text();
				// 图片路径
				Elements mainImgDiv = doc.getElementsByClass("main-image");
				String imgPath = mainImgDiv.get(0).getElementsByTag("img").attr("src");
				
				String id = StringUtils.uuid();
				logger.info("Detail添加图片地址到列队==妹子id:{}，名字：{}，类别：{}，图片地址：{}， pid：{}，index：{}",id, title, category, imgPath, fetchedPage.getData().getId(), pageNum);
				
				img = new ImgModel(id, title, category, imgPath, fetchedPage.getData().getId(), false, pageNum);
				
				ImgQueue.addElement(new Data(imgPath, false));
//				DownImg.downImg(mainImg);
				
				Element nextPage = pageDiv.getElementsByTag("a").last();
				String nextNum = nextPage.text();
				String nextPageUrl = nextPage.attr("href");
				
				if(!nextNum.equals("下一组»") && !nextNum.equals("下一组&raquo;")) {
					UrlQueue.addElement(new Data(fetchedPage.getData().getId(), nextPageUrl));
					logger.info("下一页地址=="+nextPageUrl);
				}else if(nextNum.equals("下一组»")) {
					System.err.println("已下载完 "+ count++ +" 个");
				}
			}
		}
		// 将URL放入已爬取队列
		VisitedUrlQueue.addElement(fetchedPage.getData().getUrl());
		
		// 根据当前页面和URL获取下一步爬取的URLs
		// TODO
		
		return img; 
	}
	
	private boolean containsTargetData(String url, Document contentDoc){
		// 通过URL判断
		// mzitu index
		Element pins = contentDoc.getElementById("pins");
		// 获取当前页的主要图片
		Elements mainDiv = contentDoc.getElementsByClass("main-image");
		if(pins!=null) {
			return true;
		}else if(mainDiv!=null){
			return true;
		}
		return false;
	}
}
