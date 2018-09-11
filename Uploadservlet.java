package com.loginmodule;

import java.io.File;
import java.io.IOException;
import java.util.Collection;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

/**
 * Servlet implementation class Uploadservlet
 */
@WebServlet("/Uploadservlet")
@MultipartConfig(maxFileSize=1024*1024*10)//10MB
public class Uploadservlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		String savepath="C:\\Users\\Scbadmin\\AkshayPractice\\Fileuploading\\src\\com\\input";
		
		Collection<Part> parts=request.getParts();
		for(Part part:parts){
			
			String filename=extractFileName(part);
			System.out.println("File Name : "+new File(filename).getName());
			part.write(savepath+File.separator+new File(filename).getName());
		}
		
		request.setAttribute("message", "Files uploaded Successfully");
		RequestDispatcher rd=request.getRequestDispatcher("Result.jsp");
		rd.forward(request, response);
		
	}

	private String extractFileName(Part part){
		
		 String header=part.getHeader("content-disposition");
		 String [] headers=header.split(";");
		 for(String s:headers){
			 
			 if(s.trim().startsWith("filename")){
				 
				 return(s.trim().substring(s.indexOf("=")+1, s.length()-2));
			 }
		 }
		
		return("");
	}

}
