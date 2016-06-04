package main.util;

public class GlobalVariable {
	
	


	
	
	// 테이블
	/**
	 * ^  key  ^  
	 */ 
	public final String TB_BAMBOO_CATEGORY = "BAMBOO_CATEGORY";
	
	//   
	
	public final String TB_BAMBOO_SECRET = "BABMBOO_SECRET";
	
	// 컬럼
	public final String CL_DATE = "date"; 
	public final String CL_CATEGORY = "category"; 
	public final String CL_SECRET = "secret"; 
	public final String CL_REGEX = "regex"; // REGEX
	public final String CL_LINK = "link"; // LINK

	// Attribute
	public final String ATTR_CATEGORY_LIST = "category_list";
	public final String ATTR_OUTPUT_JSON = "output_json";
	
	
	
	private static GlobalVariable instance = null;
	
	public static GlobalVariable getInstance() {		
		if (instance == null) {
			instance = new GlobalVariable();
		}
		return instance;
	}
	

}
