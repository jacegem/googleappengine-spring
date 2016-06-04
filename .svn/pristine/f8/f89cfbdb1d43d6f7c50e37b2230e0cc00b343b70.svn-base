package main.bamboo;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.Date;
import java.util.List;

import main.util.GlobalVariable;
import main.util.Util;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.FetchOptions;
import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;
import com.google.appengine.api.datastore.PreparedQuery;
import com.google.appengine.api.datastore.Query;
import com.google.appengine.api.datastore.Query.Filter;
import com.google.appengine.api.datastore.Query.FilterOperator;
import com.google.appengine.api.datastore.Query.FilterPredicate;
import com.google.appengine.api.datastore.Query.SortDirection;
import com.google.gson.Gson;



@Controller
@RequestMapping("/bamboo")
public class BambooController
{	
	DatastoreService mDatastore = DatastoreServiceFactory.getDatastoreService();
	GlobalVariable m = GlobalVariable.getInstance();
	
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public String torrentList(ModelMap model)
	{
		DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
		Query query = new Query(m.TB_BAMBOO_SECRET).addSort(m.CL_DATE, Query.SortDirection.ASCENDING);
		List<Entity> listEntity = datastore.prepare(query).asList(FetchOptions.Builder.withLimit(10));

		model.addAttribute("bambooSecret", listEntity);

		return "bamboo/list";
	}
	
	@RequestMapping(value = "/category/list", method = RequestMethod.GET)
	public String bambooCategoryList(ModelMap model)
	{
		Query q = new Query(m.TB_BAMBOO_CATEGORY);
		q.addSort(m.CL_CATEGORY,  SortDirection.ASCENDING);
		PreparedQuery pq = mDatastore.prepare(q);
		List<Entity> le = pq.asList(FetchOptions.Builder.withDefaults());
		
		model.addAttribute(m.ATTR_CATEGORY_LIST,  le);
		
		return "bamboo/category/list";
	}
	
	/*
	@RequestMapping(value = "/category/json", method = RequestMethod.GET)
	public @ResponseBody BambooCategoryDto bambooCategoryJson(ModelMap model)
	{
		Query q = new Query(m.TB_BAMBOO_CATEGORY);
		q.addSort(m.CL_CATEGORY,  SortDirection.ASCENDING);
		PreparedQuery pq = mDatastore.prepare(q);
		List<Entity> le = pq.asList(FetchOptions.Builder.withDefaults());
		
		
		BambooCategoryDto bc = new BambooCategoryDto();
		bc.setCategory("TEST_JSON");
		
		
		Gson gson = new Gson();
		//String json = gson.toJson(bc);
		
		
    	//return gson.toJson(bc);
		model.addAttribute(m.ATTR_OUTPUT_JSON,  (String)gson.toJson(bc)); 
        
		return "bamboo/category/json";
		
	}
	*/
	
	@RequestMapping(value = "/category/add/{category}", method = RequestMethod.GET)
	public ModelAndView bambooAddCategory(@PathVariable(value="category") String pCategory, ModelMap model)
	{	
		// 키를 찾아본다.
		Key key = getCategoryKey(pCategory);
				
		// 키가 없으면 삽입
		if (key == null) {
			String category = Util.getUrlDecode(pCategory);
			
			key = KeyFactory.createKey(m.TB_BAMBOO_CATEGORY, category);
	        Entity entity = new Entity(key);		        
	        		        
	        Date date = new Date();
	        entity.setProperty(m.CL_DATE, date);
	        entity.setProperty(m.CL_CATEGORY, category);	        
	        mDatastore.put(entity);
		}

		return new ModelAndView("redirect:/bamboo/category/list");
	}
	

	/**
	 * 키를 찾아서 반환한다. 없으면 null을 반환한다. 
	 * @param pCategory
	 * @return
	 */
	private Key getCategoryKey(String pCategory) {
		String category = Util.getUrlDecode(pCategory);
		
		Query query = new Query(m.TB_BAMBOO_CATEGORY);
		Filter filter = new FilterPredicate(m.CL_CATEGORY, FilterOperator.EQUAL, category);
		query.setFilter(filter);
		PreparedQuery pq = mDatastore.prepare(query);
		
		Entity entity = pq.asSingleEntity();
		
		Key keyResut = null;
		if (entity != null) {
			keyResut = entity.getKey();
		}

		return keyResut;
	}

	@RequestMapping(value = "/add/{msg}", method = RequestMethod.GET)
	public ModelAndView bambooAdd(@PathVariable(value="msg") String pMsg, ModelMap model)
	{		
		String msg = "";
		try {
			msg = URLDecoder.decode(pMsg, "utf-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		Date date = new Date();		
		Key key = KeyFactory.createKey(m.TB_BAMBOO_SECRET, date + msg);
		Entity bambooSecret = new Entity(key);
		bambooSecret.setProperty(m.CL_DATE, date);
		bambooSecret.setProperty(m.CL_SECRET, msg);
		
		mDatastore.put(bambooSecret);        
	    return new ModelAndView("redirect:/bamboo/list");
	}
	
	@RequestMapping(value = "/add/{category}/{msg}", method = RequestMethod.GET)
	public ModelAndView bambooAddInCategory(
			@PathVariable(value="category") String pCategory
			, @PathVariable(value="msg") String pMsg
			, ModelMap model)
	{		
		String msg = "";
		try {
			msg = URLDecoder.decode(pMsg, "utf-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		Date date = new Date();		
		Key key = KeyFactory.createKey(m.TB_BAMBOO_SECRET, date + msg);
		Entity bambooSecret = new Entity(key);
		bambooSecret.setProperty(m.CL_DATE, date);
		bambooSecret.setProperty(m.CL_SECRET, msg);
		
		mDatastore.put(bambooSecret);        
	    return new ModelAndView("redirect:/bamboo/list");
	}
}
