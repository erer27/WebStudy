package com.sist.model;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.PrintWriter;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Enumeration;
import java.util.List;
import java.util.Map;

import org.apache.commons.fileupload2.core.DiskFileItemFactory;
import org.apache.commons.fileupload2.core.FileItem;
import org.apache.commons.fileupload2.jakarta.servlet6.JakartaServletFileUpload;
import org.json.simple.JSONObject;

import com.sist.controller.Controller;
import com.sist.controller.RequestMapping;

import jakarta.servlet.ServletContext;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Controller
public class TestModel extends HttpServlet{
	private final String uploadPath = "C:\\upload\\";
	
	@RequestMapping("board/list.do")
	public String test_list(HttpServletRequest request, HttpServletResponse response) {
		return "list.jsp";
	}
	
	@RequestMapping("board/insert.do")
	public String test_insert(HttpServletRequest request, HttpServletResponse response) {
		return "insert.jsp";
	}
	
	@RequestMapping("board/image_convert.do")// 이미지 저장후 이미지 이름 반환
	public void image_convert(HttpServletRequest request, HttpServletResponse response)
	{
		
		if (JakartaServletFileUpload.isMultipartContent(request)) {
            DiskFileItemFactory factory = DiskFileItemFactory.builder().get();
            JakartaServletFileUpload upload = new JakartaServletFileUpload(factory);
            upload.setFileSizeMax(1024*1024);
            upload.setSizeMax(1024*1024);

            

            try {
            	String uploadPath = "C:\\upload\\";
                File uploadDir = new File(uploadPath);
                if (!uploadDir.exists()) uploadDir.mkdir();
            	String fileName=null;
            	
            	
            	//request객체를 이 코드 앞에서 사용하고 있으면 여기 리스트에 데이터 안들어감
                List<FileItem> formItems = upload.parseRequest(request);
                if (formItems != null && formItems.size() > 0) {
                    for (FileItem item : formItems) {
                        if (!item.isFormField()) {
                            fileName = new File(item.getName()).getName();
                            
                            //파일 저장하기 전에 파일이름 uuid로 생성해서 바꾸고 저장하기
                            //추후에 db insert
                            item.write(Path.of(uploadPath, fileName));
                            JSONObject obj=new JSONObject();
                    		obj.put("imageName", item.getName());
                    		response.setContentType("application/x-json; charset=utf-8");
                            response.getWriter().print(obj);
                        }
                    }
                }
            } catch (Exception ex) {
                System.out.println("There was an error: " + ex.getMessage());
            }
        }
		
	}
	@RequestMapping("board/get_converted_image.do")//이미지파일 링크 리턴
	public void get_converted_image(HttpServletRequest request, HttpServletResponse response)
	{
		String fileName = request.getParameter("image");
		try {
			File file=new File(uploadPath+fileName);
			
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
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
