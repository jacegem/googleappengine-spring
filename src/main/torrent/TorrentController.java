package main.torrent;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.nio.charset.Charset;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.FetchOptions;
import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;
import com.google.appengine.api.datastore.PreparedQuery;
import com.google.appengine.api.datastore.Query;
import com.google.appengine.api.datastore.Query.FilterOperator;
import com.google.appengine.tools.admin.Application;

@Controller
@RequestMapping("/torrent")
public class TorrentController
{
	// 테이블
	private final String TB_TORRENT_ITEM = "TORRENT_ITEM";
	private final String TB_TORRENT_SITE = "TORRENT_SITE";
	private final String TB_TORRENT_LINK = "TORRENT_LINK";
	private final String TB_TORRENT_TEMP = "TORRENT_TEMP";
	
	// 컬럼
	private final String CL_ITEM = "item";	// 아이템
	private final String CL_SITE = "site";		// URL
	private final String CL_DATE = "date";		// URL
	private final String CL_REGEX = "regex";		// REGEX
	private final String CL_LINK = "link";		// LINK
	private final String CL_LINK_IDX = "linkIdx";		// link index
	private final String CL_TITLE = "title";		// TITLE
	private final String CL_TITLE_IDX = "titleIdx";		// title index
	private final String CL_ORDER = "order";		// order
	
	private final String CL_CONTENT = "content";		
	
	/**
	 * 아이템 목록을 보여준다.
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public String torrentList(ModelMap model)
	{
		DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
		Query query = new Query(TB_TORRENT_ITEM).addSort(CL_DATE, Query.SortDirection.DESCENDING);
	    List<Entity> torrentItem = datastore.prepare(query).asList(FetchOptions.Builder.withDefaults());

	    model.addAttribute("torrentItem",  torrentItem);

		return "torrent/list";
	}
	
	@RequestMapping(value = "/addItem", method = RequestMethod.GET)
	public ModelAndView addItem(HttpServletRequest request, ModelMap model)
	{
		DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
		String item = request.getParameter("item");
		
		Key key = KeyFactory.createKey(TB_TORRENT_ITEM, item); 
		Date date = new Date();
        Entity torrentItem = new Entity(key);
        torrentItem.setProperty(CL_ITEM, item);
        torrentItem.setProperty(CL_DATE, date);
     
        datastore.put(torrentItem);        
        return new ModelAndView("redirect:list");
	}
	
	@RequestMapping(value = "/site/{item}", method = RequestMethod.GET)
	public String siteWithItem(@PathVariable String item, ModelMap model)
	{
		try {
			item = URLDecoder.decode(item, "utf-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		
		DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
		
		Query query = new Query(TB_TORRENT_SITE);
		query.addSort(CL_DATE, Query.SortDirection.DESCENDING);
		query.addFilter(CL_ITEM, FilterOperator.EQUAL, item);
		List<Entity> siteList = datastore.prepare(query).asList(FetchOptions.Builder.withDefaults());
		
		model.addAttribute("item", item);
		model.addAttribute("siteList", siteList);

		return "torrent/site";
	}
	
	@RequestMapping(value = "/addSite", method = RequestMethod.GET)
	public String addSite(HttpServletRequest request, ModelMap model, RedirectAttributes redirectAttrs)
	{
		DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
		Date date = new Date();
		
		String item = request.getParameter("item");
		String site = request.getParameter("site");
		String regex = request.getParameter("regex");
		String linkIdx = request.getParameter("linkIdx");
		String titleIdx = request.getParameter("titleIdx");
		
		Key key = KeyFactory.createKey(TB_TORRENT_SITE, site);
        Entity entity = new Entity(key);
        entity.setProperty(CL_ITEM, item);
        entity.setProperty(CL_SITE, site);
        entity.setProperty(CL_REGEX, regex);
        entity.setProperty(CL_LINK_IDX, linkIdx);
        entity.setProperty(CL_TITLE_IDX, titleIdx);
        entity.setProperty(CL_DATE, date);

        datastore.put(entity);
        

        try {
			redirectAttrs.addAttribute("item", URLEncoder.encode(item,"utf-8"));
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
        
        //return new ModelAndView("redirect:site/{item}");
        return "redirect:site/{item}";
	}

	
	
	@RequestMapping(value = "/link/{item}", method = RequestMethod.GET)
	public String getLinkByItem(@PathVariable String item, ModelMap model)
	{
		DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
		Query query = new Query(TB_TORRENT_LINK)
			.addFilter(CL_ITEM, FilterOperator.EQUAL, item)
			.addSort(CL_DATE, Query.SortDirection.DESCENDING)
			.addSort(CL_ORDER, Query.SortDirection.ASCENDING);
		List<Entity> linkList = datastore.prepare(query).asList(FetchOptions.Builder.withDefaults());
		
		model.addAttribute("item", item);
		model.addAttribute("linkList", linkList);
		
		return "torrent/link";
	}
	

	@RequestMapping(value = "/cron", method = RequestMethod.GET)
	public ModelAndView getAjaxPage(ModelMap model)
	{
		//String siteUrl = "http://www.tosarang.net/bbs/board.php?bo_table=torrent_video_ani&sca=&sfl=wr_subject&stx=%EA%B2%81%EC%9F%81%EC%9D%B4+%ED%8E%98%EB%8B%AC&sop=and";
		//Pattern pattern = Pattern.compile("<a href='(.+?)'>(.+)</a>   <img src='../skin/board/basic_custom_torrent/img/icon_hot.gif'");
		
		
		// 테이블 정보를 읽는다.
		// 유효성 검증
		// 하나씩 처리한다. 
		// 대상 테이블은 
		// 테이블 읽기 : 전체 읽기		
		DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
		Query query = new Query(TB_TORRENT_SITE).addSort(CL_DATE, Query.SortDirection.DESCENDING);
		
		// Use PreparedQuery interface to retrieve results
		PreparedQuery pq = datastore.prepare(query);
		
	    for (Entity result : pq.asIterable()){
	    	// 가져올 수 있는 정보는
	    	// 아이템명, 사이즈주소, 정규식, 링크인덱스, 타이틀인덱스
	    	String item = (String)result.getProperty(CL_ITEM);
	    	String site = (String)result.getProperty(CL_SITE);
	    	String regex = (String)result.getProperty(CL_REGEX);
	    	String linkIdx = (String) result.getProperty(CL_LINK_IDX);
	    	String titleIdx = (String) result.getProperty(CL_TITLE_IDX);
	    	
	    	boolean bRst = insertLinks(item, site, regex, linkIdx, titleIdx);
	    }

		return new ModelAndView("redirect:list");
	}

	private boolean insertLinks(String item, String site, String regex,
			String linkIdx, String titleIdx) {
		// TODO Auto-generated method stub
		Pattern pattern = Pattern.compile(regex);
		DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
		URL url;
		
		try
		{
			String content = null;
			content = getContent(site, "UTF-8");
			
			// reload
			content = getContent(site, "UTF-8");

			// if EUC-KR
			Pattern pEuckr = Pattern.compile("charset=euc-kr");
			Matcher mEucKr = pEuckr.matcher(content);
			if (mEucKr.find()) {
				content = getContent(site, "EUC-KR");				
			}
			
			if (content.isEmpty()) {
				return false;
			}
			
			Matcher matcher = pattern.matcher(content);
			
			// 줄바꿈을 공백으로 치환
			content = content.replace(System.getProperty("line.separator"), " ");
			
			// 이중 공백은 공백으로 치환
			//content = content.replace("\\s+", " ");
		
			// item, url, title, date 
						
			Date date = new Date();
			
			int order = 1;
			
			while (matcher.find()) {
				String link = matcher.group(Integer.parseInt(linkIdx)).trim();
				String title = matcher.group(Integer.parseInt(titleIdx)).trim();
								
				Key dsKey = KeyFactory.createKey(TB_TORRENT_LINK, title);
				
		        Entity entity = new Entity(dsKey);		        
		        entity.setProperty(CL_ITEM, item);
		        
		        int lastIdx = site.lastIndexOf('/');
		        String webSite = site.substring(0, lastIdx+1);		        
		        		        
		        entity.setProperty(CL_LINK, webSite + link);
		        entity.setProperty(CL_TITLE, title);
		        entity.setProperty(CL_DATE, date);
		        entity.setProperty(CL_ORDER, order++);
		        
		        datastore.put(entity);
			}
		} catch (Exception e) {			
			e.printStackTrace();
		}
		
		return false;
	}

	private void insertTemp(String site, String regex, String content) {		
		// TODO Auto-generated method stub
		if (content.length() > 400) {
			content = content.substring(0, 300);
		}
		
		DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
		
		Date date = new Date();
		
		Key dsKey = KeyFactory.createKey(TB_TORRENT_TEMP, site + date);
		
        Entity entity = new Entity(dsKey);		        
        entity.setProperty(CL_SITE, site);
        entity.setProperty(CL_CONTENT, content);
        entity.setProperty(CL_DATE, date);
        
        datastore.put(entity);
	}

	private String getContent(String site, String encoding) {
		String content = null;
		
		URL url;
		try {
			url = new URL(site);
			URLConnection conn = url.openConnection();
			InputStream is = conn.getInputStream();
			BufferedReader br = new BufferedReader(new InputStreamReader(is, encoding));
			char[] buff = new char[512];
			int len = -1;
			
			
			while ((len = br.read(buff)) != -1)
			{
				content += String.valueOf(buff);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return content;
	}

	private void encodingCheck(String word) throws UnsupportedEncodingException {
		// TODO Auto-generated method stub
		System.out.println("utf-8 -> euc-kr        : " + new String(word.getBytes("utf-8"), "euc-kr"));
		System.out.println("utf-8 -> ksc5601       : " + new String(word.getBytes("utf-8"), "ksc5601"));
		System.out.println("utf-8 -> x-windows-949 : " + new String(word.getBytes("utf-8"), "x-windows-949"));
		System.out.println("utf-8 -> iso-8859-1    : " + new String(word.getBytes("utf-8"), "iso-8859-1"));
		System.out.println("iso-8859-1 -> euc-kr        : " + new String(word.getBytes("iso-8859-1"), "euc-kr"));
		System.out.println("iso-8859-1 -> ksc5601       : " + new String(word.getBytes("iso-8859-1"), "ksc5601"));
		System.out.println("iso-8859-1 -> x-windows-949 : " + new String(word.getBytes("iso-8859-1"), "x-windows-949"));
		System.out.println("iso-8859-1 -> utf-8         : " + new String(word.getBytes("iso-8859-1"), "utf-8"));
		System.out.println("euc-kr -> utf-8         : " + new String(word.getBytes("euc-kr"), "utf-8"));
		System.out.println("euc-kr -> ksc5601       : " + new String(word.getBytes("euc-kr"), "ksc5601"));
		System.out.println("euc-kr -> x-windows-949 : " + new String(word.getBytes("euc-kr"), "x-windows-949"));
		System.out.println("euc-kr -> iso-8859-1    : " + new String(word.getBytes("euc-kr"), "iso-8859-1"));
		System.out.println("ksc5601 -> euc-kr        : " + new String(word.getBytes("ksc5601"), "euc-kr"));
		System.out.println("ksc5601 -> utf-8         : " + new String(word.getBytes("ksc5601"), "utf-8"));
		System.out.println("ksc5601 -> x-windows-949 : " + new String(word.getBytes("ksc5601"), "x-windows-949"));
		System.out.println("ksc5601 -> iso-8859-1    : " + new String(word.getBytes("ksc5601"), "iso-8859-1"));
		System.out.println("x-windows-949 -> euc-kr     : " + new String(word.getBytes("x-windows-949"), "euc-kr"));
		System.out.println("x-windows-949 -> utf-8      : " + new String(word.getBytes("x-windows-949"), "utf-8"));
		System.out.println("x-windows-949 -> ksc5601    : " + new String(word.getBytes("x-windows-949"), "ksc5601"));
		System.out.println("x-windows-949 -> iso-8859-1 : " + new String(word.getBytes("x-windows-949"), "iso-8859-1"));
	}
}
