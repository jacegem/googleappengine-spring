package com.sample;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/sample")
public class SampleController
{
	@RequestMapping(value = "/show", method = RequestMethod.GET)
	public String showSamplePage(ModelMap model)
	{
		// 데이터를 쿼리
		// 데이터를 모델에 담는다.
		// 뷰에 전달.
		return "sample/show";
	}
	
	@RequestMapping(value = "/popup", method = RequestMethod.GET)
	public String getPopupPage(ModelMap model)
	{
		// 데이터를 쿼리
		// 데이터를 모델에 담는다.
		// 뷰에 전달.
		return "sample/popup";
	}
	
	@RequestMapping(value = "/ajax", method = RequestMethod.GET)
	public String getAjaxPage(ModelMap model)
	{
		// 데이터를 모델에 담는다.
		// 뷰에 전달.
		model.addAttribute("result",  "TEXT FROM SERVER");
		return "sample/ajax";
	}
}
