package com.wipro.gamificationstring.servlets;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import org.apache.tomcat.util.http.fileupload.disk.DiskFileItemFactory;
import org.apache.tomcat.util.http.fileupload.servlet.ServletFileUpload;

import com.wipro.gamificationstring.bean.QuestionBean;
import com.wipro.gamificationstring.service.QuestionAdmin;
import com.wipro.gamificationstring.util.ProcessExecutor;
import com.wipro.gamificationstring.util.ProgramStrings;


/**
 * @author Gokul
 *
 */


@WebServlet("/UploadServlet")
@MultipartConfig
public class UploadServlet extends HttpServlet {
   
   
	private static final long serialVersionUID = 1L;
	private boolean isMultipart;
    private String filePath;
    private String mainProgramUrl;
    private int maxFileSize = 50 * 1024;
    private int maxMemSize = 4 * 1024;

    public void init( ){
        // Get the file location where it would be stored.
        filePath = getServletContext().getInitParameter("file-upload"); 
        mainProgramUrl = getServletContext().getInitParameter("mainFile");
     }
    
   public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, java.io.IOException {
	   
	   if(request.getSession().getAttribute("GamificationStringUserEmail") == null){
			response.sendRedirect("index.jsp?message=Please LogIn.");
			return;
		}
	   
      // Check that we have a file upload request
      isMultipart = ServletFileUpload.isMultipartContent(request);
      response.setContentType("text/html");
      java.io.PrintWriter out = response.getWriter( );
      if( !isMultipart ){
         out.println("No file uploaded"); 
         return;
      }
      DiskFileItemFactory factory = new DiskFileItemFactory();
      // maximum size that will be stored in memory
      factory.setSizeThreshold(maxMemSize);
      // Location to save data that is larger than maxMemSize.
      factory.setRepository(new File("c:\\temp"));

      // Create a new file upload handler
      ServletFileUpload upload = new ServletFileUpload(factory);
      // maximum file size to be uploaded.
      upload.setSizeMax( maxFileSize );
      
      QuestionBean question = QuestionAdmin.getQuestion(new Integer(request.getParameter("questionId")));      
      
      String user = (String) request.getSession().getAttribute("GamificationStringUserEmail");
      String dir = filePath+user+System.getProperty("file.separator");
      String userCodeFilename = dir+"GamificationString.java";
      String mainCodeFilename = dir+"MainClass.java";
      String testCase = question.getTestCase_1();
      String mainProgram = "";
      String result = "";
      
      
      HashMap<String, String> userCompileOutput = new HashMap<String, String>();
      HashMap<String, String> mainCompileOutput = new HashMap<String, String>();
      HashMap<String, String> executedOutput = new HashMap<String, String>();
      
      

      Part filePart = request.getPart("file"); // Retrieves <input type="file" name="file">
      InputStream filecontent = filePart.getInputStream();
      byte[] buffer = new byte[filecontent.available()];
      filecontent.read(buffer);
      
      File targetFile = new File(userCodeFilename);
      targetFile.getParentFile().mkdirs();//!correct
      if (!targetFile.exists()){
          targetFile.createNewFile();
      } 
	  OutputStream outStream = new FileOutputStream(targetFile);
      outStream.write(buffer);
      outStream.close();
      
      try {
    	  File mainFile = new File(mainCodeFilename);
    	  try{
    		  mainProgram = readFile(mainProgramUrl);  
    	  } catch (IOException e) {
			System.out.println(e);
			mainProgram = ProgramStrings.getMainProgram();		
		}
          FileWriter writer = new FileWriter(mainFile);
          writer.write(mainProgram);
          writer.flush();
          writer.close();
	} catch (Exception e) {
		e.printStackTrace();
	} 
      
      
      try {
		userCompileOutput = ProcessExecutor.runProcess("javac -cp "+dir+" -d "+dir+" "+userCodeFilename);
		mainCompileOutput = ProcessExecutor.runProcess("javac -cp "+dir+" -d "+dir+" "+mainCodeFilename);
		if(userCompileOutput.get("exitValue").equals("0"))
		{
			if(mainCompileOutput.get("exitValue").equals("0")){
				executedOutput = ProcessExecutor.runProcess("java -cp "+dir+" MainClass "+testCase);
				if (executedOutput.get("exitValue").equals("0")) {
					result = executedOutput.get("output").trim();
				} else {
					result = executedOutput.get("error");
				}
			} else {
				result = mainCompileOutput.get("error");
			}
		} else {
			result = userCompileOutput.get("error");
		}
			
	} catch (Exception e) {
		e.printStackTrace();
	}
      result = result.substring(result.lastIndexOf('\\')+1);
      out.write(result);
      out.flush();
      out.close();
      
   }
   
   private String readFile(String fileName) throws IOException {
	    BufferedReader br = new BufferedReader(new FileReader(fileName));
	    try {
	        StringBuilder sb = new StringBuilder();
	        String line = br.readLine();

	        while (line != null) {
	            sb.append(line);
	            sb.append("\n");
	            line = br.readLine();
	        }
	        return sb.toString();
	    } finally {
	        br.close();
	    }
	}

}
