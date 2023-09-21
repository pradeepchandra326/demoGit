package com.files;

public class jsonFile_one {
	
	
	public static  String addBookPayload() {
		
		String payload="{\n"
				+ "\n"
				+ "\n"
				+ "\"name\":\"Learn Appium Automation with Java\",\n"
				+ "\"isbn\":\"bcd\",\n"
				+ "\"aisle\":\"2657\",\n"
				+ "\"author\":\"John foe\"\n"
				+ "}\n"
				+ "";
		
		return payload;
	}

	public static String addBookPayload(String aisel_name, String isbn_number) {
		// TODO Auto-generated method stub
		String payload="{\n"
				+ "\n"
				+ "\n"
				+ "\"name\":\"Learn Appium Automation with Java\",\n"
				+ "\"isbn\":\"" +isbn_number+ ",\n"
				+ "\"aisle\":\""+aisel_name+ ",\n"
				+ "\"author\":\"John foe\"\n"
				+ "}\n"
				+ "";
		
		return payload;
	}

}
