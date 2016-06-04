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
public class TorrentControllerTotal
{
	// 占쎈슣�뵠�뇡占�	
	private final String TB_TORRENT_ITEM = "TORRENT_ITEM";
	private final String TB_TORRENT_SITE = "TORRENT_SITE";
	private final String TB_TORRENT_LINK = "TORRENT_LINK";
	private final String TB_TORRENT_TEMP = "TORRENT_TEMP";
	
	// �뚎됱쓥
	private final String CL_ITEM = "item";	// 占쎄쑴�뵠占쏙옙	private final String CL_SITE = "site";		// URL
	private final String CL_DATE = "date";		// URL
	private final String CL_REGEX = "regex";		// REGEX
	private final String CL_LINK = "link";		// LINK
	private final String CL_LINK_IDX = "linkIdx";		// link index
	private final String CL_TITLE = "title";		// TITLE
	private final String CL_TITLE_IDX = "titleIdx";		// title index
	private final String CL_ORDER = "order";		// order
	
	private final String CL_CONTENT = "content";		
	
	/**
	 * ## TB_TORRENT_DATA
	 * 
	 * INSERT_DATE : 占쎄퀣�뵠占쏙옙占쎈굝�젾占쏙옙
	 * MAGNETIC : 占쎈Ŋ苑� 占쎈베�궖
	 * TORRENT_FILE : 占쎌쥓�젉占쏙옙占쎈슣�뵬 
	 * INFOMATION_URL : 占쎈베�궖 URL
	 */
	
	/**
	 * 占쎄쑴猿� 占쎄쑴�뵠占쎌뮇�뱽 癰귣똻肉т빳占쎈뼄. 
	 * @param model
	 * @return
	 */
	/**
	 * 占쎄쑴猿� 占쎄쑴�뵠占쎌뮇�뱽 癰귣똻肉т빳占쎈뼄. 
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/total", method = RequestMethod.GET)
	public String torrentList(ModelMap model)
	{
		return torrentList("0", model);		
	}
	
	@RequestMapping(value = "/total/{page}", method = RequestMethod.GET)
	public String torrentList(@PathVariable(value="page") String pageStr, ModelMap model)
	{
		DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
		int pageOffset = 1;
		
		// 占쎌꼷�뵠筌욑옙而わ옙占쏙옙怨뺤뵬 占쎌꼷�뵠筌욑옙野껉퀗�궢�몴占썼퉪�똻肉т빳占쎈뼄.
		int page = 0;
		try {
			pageStr = URLDecoder.decode(pageStr, "utf-8");
			page = Integer.parseInt(pageStr);
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		Query query = new Query(TB_TORRENT_ITEM);
		query.addSort(CL_DATE, Query.SortDirection.DESCENDING);		
		PreparedQuery pq = datastore.prepare(query);
						
	    //List<Entity> torrentItem = datastore.prepare(query).asList(FetchOptions.Builder.withDefaults());
		List<Entity> torrentItem = pq.asList(FetchOptions.Builder.withOffset(page * pageOffset).limit(pageOffset)); 

	    model.addAttribute("torrentItem",  torrentItem);
	    model.addAttribute("page", page);

		return "torrent/list";
	}
}
