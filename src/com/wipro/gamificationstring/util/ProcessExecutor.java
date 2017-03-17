package com.wipro.gamificationstring.util;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;

public class ProcessExecutor {
	
	private static String printLines(String name, InputStream ins) throws Exception {
	    String line = "";
	    StringBuilder str = new StringBuilder("");
	    BufferedReader in = new BufferedReader(
	        new InputStreamReader(ins));
	    while ((line = in.readLine()) != null) {
	    	str = str.append(line);
	    	str = new StringBuilder(str.toString() + "\n");
	    }
	    return str.toString();
	  }

	  public static HashMap<String, String> runProcess(String command) throws Exception {
		  System.out.println(command);
	    Process pro = Runtime.getRuntime().exec(command);
	    String output = printLines(" stdout:", pro.getInputStream());
	    String error = printLines(" stderr:", pro.getErrorStream());
	    pro.waitFor();
	    String exitValue =new Integer(pro.exitValue()).toString();
	    HashMap<String, String> map = new HashMap<String, String>();
	    map.put("output", output);
	    map.put("error", error);
	    map.put("exitValue", exitValue);
	    return map;
	  }
	
	  public static void main(String[] args) {
		  
		  HashMap<String, String> map = new HashMap<String, String>();
		
		  try {
			 map = runProcess("java -cp d:\\tomcat\\uploads\\user\\ MainClass hi");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		  
		  System.out.println("Output = "+ map.get("output"));
		  System.out.println("Error = "+map.get("error"));
		  System.out.println("Exit code = "+map.get("exitValue"));
		  
		  
	}

}
