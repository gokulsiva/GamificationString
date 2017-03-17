package com.wipro.gamificationstring.util;

public class ProgramStrings {
	
	private static String mainProgram = "public class MainClass {\r\n" + 
			"\r\n" + 
			"	public static void main(String[] args) {\r\n" + 
			"		\r\n" + 
			"		String string = GamificationString.resultString(args[0]);\r\n" + 
			"		System.out.println(string);\r\n" + 
			"	}\r\n" + 
			"	\r\n" + 
			"}";

	private static String template = "import java.lang.*;\r\n" + 
			"import java.util.*;\r\n" + 
			"\r\n" + 
			"public class GamificationString {\r\n" + 
			"\r\n" + 
			"	public static String resultString(String str)\r\n" + 
			"	{\r\n" + 
			"		return str;\r\n" + 
			"	} \r\n" + 
			"	\r\n" + 
			"}";

	public static String getMainProgram() {
		return mainProgram;
	}

	public static String getTemplate() {
		return template;
	}
		
}
