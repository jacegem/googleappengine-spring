package main.util;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

public class Util {

	public static String getUrlDecode(String pUrlParam) {
		String decode = "";
		try {
			decode = URLDecoder.decode(pUrlParam, "utf-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return decode;
	}

}
