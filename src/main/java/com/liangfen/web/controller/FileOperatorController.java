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
import java.util.Iterator;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.io.FileUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.druid.sql.ast.expr.SQLCaseExpr.Item;
import com.liangfen.model.excel.CardNumUserNamePair;
import com.liangfen.model.excel.ExcelHelper;

@Controller
public class FileOperatorController {

	@RequestMapping(value="showFilePage")
	public String showFilePath(Model model,@RequestParam(value="statusString", required=false) String statusString)
	{
		model.addAttribute("statusString", statusString);
		return "fileops";
	}
   
	@RequestMapping(value="fileDownload", method=RequestMethod.GET)
	public ResponseEntity<byte[]> download(HttpSession session) throws IOException {  
		
		String path = session.getServletContext().getRealPath("download/售卡列表模板.xls");
		File file = new File(path);

	    HttpHeaders headers = new HttpHeaders();
	    headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);  
	    headers.setContentDispositionFormData("attachment", new String(file.getName().getBytes(),"iso-8859-1")); // 显示下载文件名必须使用iso-8859-1编码
	    return new ResponseEntity<byte[]>(FileUtils.readFileToByteArray(file),  
	                                      headers, HttpStatus.CREATED);
	}
	
	@RequestMapping(value="/fileUpload", method=RequestMethod.POST)  
    public String upload2(HttpServletRequest request, Model model) throws IllegalStateException, IOException {  
        //创建一个通用的多部分解析器  
        CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver(request.getSession().getServletContext());  
        //判断 request 是否有文件上传,即多部分请求  
        if(multipartResolver.isMultipart(request)){  
            //转换成多部分request    
            MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest)request;  
            //取得request中的所有文件名  
            Iterator<String> iter = multiRequest.getFileNames();  
            if(iter.hasNext()){  
                //记录上传过程起始时的时间，用来计算上传时间  
                int pre = (int) System.currentTimeMillis();  
                //取得上传文件  
                MultipartFile file = multiRequest.getFile(iter.next());  
                if(file != null){  
                    //取得当前上传文件的文件名称  
                    String myFileName = file.getOriginalFilename();  
                    //如果名称不为“”,说明该文件存在，否则说明该文件不存在  
                    if(myFileName.trim() !=""){  
                        System.out.println(myFileName);  
                        //重命名上传后的文件名  
                        String fileName = UUID.randomUUID() + ".xls";
                        //定义上传路径  
                        String path = request.getSession().getServletContext().getRealPath("upload") + "/" + fileName;
                        File localFile = new File(path);
                        file.transferTo(localFile);
                        
                        //记录上传该文件后的时间
                        int finaltime = (int) System.currentTimeMillis(); 
                        model.addAttribute("statusString", (finaltime - pre) + " ms");
                        
                        // validate
                        if(localFile.isFile() && localFile.exists())
                        {
                        	// successful
                        	ExcelHelper excelHelper = new ExcelHelper();
                        	if(excelHelper.readXls(localFile.getAbsolutePath()))
                        	{
                        		for(CardNumUserNamePair item : excelHelper.getCardNumUserNamePairsList())
                        		{
                        			System.out.println(item);
                        		}
                        	}
                        }
                    }  
                }
            }  
              
        }  
        return "redirect:/showFilePage";  
    }  

}
