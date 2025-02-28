package com.sist.model;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.PrintWriter;
import java.nio.file.FileSystems;
import java.nio.file.Paths;

import org.json.simple.JSONObject;

import com.sist.controller.Controller;
import com.sist.controller.RequestMapping;

import jakarta.servlet.ServletContext;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Controller
public class TestModel extends HttpServlet{
	
	@RequestMapping("board/list.do")
	public String test_list(HttpServletRequest request, HttpServletResponse response) {
		return "list.jsp";
	}
	
	@RequestMapping("board/insert.do")
	public String test_insert(HttpServletRequest request, HttpServletResponse response) {
		return "insert.jsp";
	}
	
	@RequestMapping("board/image_convert.do")
	public void image_convert(HttpServletRequest request, HttpServletResponse response)
	{
		String image=request.getParameter("image");
		JSONObject obj=new JSONObject();
		obj.put("test", image+" hello");
		
		try
		{
			File file=new File("C:\\upload\\image2.png");
			
			System.out.println(file.getPath());
			response.addHeader("Accept-Ranges", "bytes");
			response.setContentLength((int) file.length());
			response.setContentType("image/png");
			BufferedInputStream bis = new BufferedInputStream(new FileInputStream(file));
			BufferedOutputStream bos = new BufferedOutputStream(response.getOutputStream());

			byte b[] = new byte[(int) file.length()];

			int read = 0;
			while ((read = bis.read(b)) != -1) {
				bos.write(b, 0, read);
			}
			   
			bos.flush();
			   
			bos.close();
			bis.close();
		}catch(Exception ex) {ex.printStackTrace();}
		
		
		
		
	}
}
