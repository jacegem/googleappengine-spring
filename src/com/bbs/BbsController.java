package com.bbs;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/bbs")
public class BbsController
{
	@RequestMapping(value = "/best", method = RequestMethod.GET)
	public String getBestBbsPage(ModelMap model)
	{
		// 데이터를 쿼리
		// 데이터를 모델에 담는다.
		// 뷰에 전달.
		return "bbs/best";
	}

	@RequestMapping(value = "/set", method = RequestMethod.GET)
	public String setBestBbs(ModelMap model)
	{
		// 웹페이지 조회
		String siteUrl = "http://clien.net/cs2/bbs/board.php?bo_table=useful";

		URL url;
		String line;
		String message = new String();
		try
		{
			url = new URL(siteUrl);
			URLConnection conn = url.openConnection();
			InputStream is = conn.getInputStream();
			BufferedReader br = new BufferedReader(new InputStreamReader(is));

			while ((line = br.readLine()) != null)
			{
				// buffer.append(line);
				message += line + "\n";
			}

			System.out.print(message);
		} catch (Exception e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// 데이터 추출
		// 데이터 저장
		return "bbs/set";
	}
}
