package com;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
 
@Controller
@RequestMapping("/movie")
public class MovieController {
 
	//DI via Spring
	String message;
 
	@RequestMapping(value="/{name}", method = RequestMethod.GET)
	public String getMovie(@PathVariable String name, ModelMap model) {
 
		model.addAttribute("movie", name);
		model.addAttribute("message", this.message);
 
		//return to jsp page, configured in mvc-dispatcher-servlet.xml, view resolver
		return "list";
 
	}
	
	@RequestMapping(value="/add", method = RequestMethod.GET)
	public String getAddCustomerPage(ModelMap model) {
		return "add";

	}	
	
	@RequestMapping(value="/get", method = RequestMethod.GET)
	public String getSiteAddCustomerPage(ModelMap model) {
		
		URL url;
		try
		{
			url = new URL("http://www.naver.com");
			URLConnection conn = url.openConnection();
			InputStream is = conn.getInputStream();
			BufferedReader br = new BufferedReader(new InputStreamReader(is));
			char[] buff = new char[512];
			int len = -1;

			while ((len = br.read(buff)) != -1)
			{
				System.out.print(new String(buff, 0, len));
			}
			
			String buf = String.valueOf(buff);
			
			model.addAttribute("buf",  buf);
		} catch (Exception e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return "print";
	}
 
	public void setMessage(String message) {
		this.message = message;
	}
 
}