package com.liangfen.tags;

import java.io.IOException;
import java.io.StringWriter;
import java.util.Date;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.JspFragment;
import javax.servlet.jsp.tagext.SimpleTagSupport;

public class SimpleTagDemo extends SimpleTagSupport {

	private int count = 1;
	private Date date = null;
	
	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	@Override
	public void doTag() throws JspException, IOException {
		
        // 得到代表jsp标签体的JspFragment
        JspFragment jspFragment = this.getJspBody();
        StringWriter sw = new StringWriter();
        //将标签体的内容写入到sw流中
        jspFragment.invoke(sw);
        //获取sw流缓冲区的内容
        String content = sw.getBuffer().toString();
        
        // 修改内容
        if(date != null)
        {
        	content += " : " + date.toString() + ";";
        }
                
        PageContext pageContext = (PageContext) this.getJspContext();
        
        //将修改后的content输出到浏览器中
        for(int index = 0; index < count; index++)
        {
        	pageContext.getOut().write(content);
        }        
	}

	
}
