package com.pstock.server;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.pstock.explorer.Walker;

/**
 * Servlet implementation class Fetch
 */
@WebServlet("/Fetch")
public class Fetch extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private Walker walker = new Walker();
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Fetch() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String path = (String) request.getParameter("path");
		
		if(path != null) {
			File file = walker.isFile(path);
			if(file != null) {

		        response.setHeader("Content-disposition", "attachment; filename=" + file.getName());
				try (
						InputStream in = new FileInputStream(file); 
						OutputStream out = response.getOutputStream()) {
	
					byte[] buffer = new byte[1048];
	
					int numBytesRead;
					while ((numBytesRead = in.read(buffer)) > 0) {
						out.write(buffer, 0, numBytesRead);
					}
				}
				return;
			}
		}
		
		ArrayList<String> files = walker.getData(path);

		request.setAttribute("files", files);
		RequestDispatcher dispatcher =  request.getRequestDispatcher("files.jsp"); 
		
		dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
