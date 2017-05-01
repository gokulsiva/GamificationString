package com.wipro.gamificationstring.util;


/**
 * @author Sharmila
 *
 */


public class ProgramStrings {
	
	private static String mainProgram = "public class MainClass {\r\n" + 
			"\r\n" + 
			"	public static void main(String[] args) {\r\n" + 
			"\r\n" + 
			"		String input = \"\";\r\n" + 
			"		for(String s : args){\r\n" + 
			"			input = input+\" \"+s;\r\n" + 
			"		}\r\n" + 
			"\r\n" + 
			"		input = input.trim();\r\n" + 
			"		\r\n" + 
			"		String string = GamificationString.resultString(input);\r\n" + 
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
