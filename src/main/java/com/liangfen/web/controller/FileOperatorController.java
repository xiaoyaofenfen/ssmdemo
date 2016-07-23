package com.liangfen.web.controller;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;
import java.nio.charset.CharsetEncoder;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FileUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class FileOperatorController {

	@RequestMapping(value="showFilePage")
	public String showFilePath()
	{
		return "fileops";
	}
	@RequestMapping(value="fileDownload2")
    public ModelAndView download(HttpServletRequest request,HttpServletResponse response) throws Exception {  
        String filePath =request.getSession().getServletContext().getRealPath("WEB-INF/download/workbook.xls");
        File file = new File(filePath);  
        
        // 清空response   
       response.reset();   
       response.setHeader("Content-Disposition", "attachment; filename=\"" + new String("售卡列表模板.xls".getBytes(),"iso-8859-1")  + "\"");    
       response.addHeader("Content-Length", "" + file.length());    
       response.setContentType("application/octet-stream;charset=UTF-8");    
       InputStream in = null;  
       OutputStream toClient = null;  
       try{   
            //以流的形式下载文件   
           in = new BufferedInputStream(new FileInputStream(filePath));   
            byte[] buffer = new byte[in.available()];   
            in.read(buffer);   
            in.close();  
              
            toClient = new BufferedOutputStream(response.getOutputStream());   
            toClient.write(buffer);   
            toClient.flush();
              
        }catch(Exception e){   
            e.printStackTrace();   
        }finally{  
            toClient.close();   
        }  
          
        return null;  
   }  
   
	@RequestMapping(value="fileDownload")
	public ResponseEntity<byte[]> download(HttpServletRequest request) throws IOException {  
		
		String path=request.getSession().getServletContext().getRealPath("WEB-INF/download/售卡列表模板.xls");
		File file = new File(path);
		
		Charset cs = Charset.forName("UTF-8");
		List<Charset> charsets = new ArrayList<Charset>();
		charsets.add(cs);
		
	    HttpHeaders headers = new HttpHeaders();  
	    headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);  
	    headers.setAcceptCharset(charsets);
	    
	    String name = file.getName();
	    System.out.println(name);
	    headers.setContentDispositionFormData("attachment", new String(name.getBytes(),"iso-8859-1")); // 显示下载文件名必须使用iso-8859-1编码
	    return new ResponseEntity<byte[]>(FileUtils.readFileToByteArray(file),  
	                                      headers, HttpStatus.CREATED);
	}

}
