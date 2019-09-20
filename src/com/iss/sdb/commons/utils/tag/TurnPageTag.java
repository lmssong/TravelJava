package com.iss.sdb.commons.utils.tag;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;

import org.apache.log4j.Logger;

import com.iss.sdb.commons.utils.CommonUtil;
import com.iss.sdb.pet.pojo.Page;

/**
 * 自定义标签-分页组件. <br>
 * 封装了分页的展现、翻页的功能实现，以当前的form的POST方式，改变页码参数后请求。
 * <p>
 * Copyright: Copyright (c) 2013-11-8 下午1:56:39
 * <p>
 * Company: 
 * <p>
 * @author hqsunc
 * @version 1.0.0
 */
public class TurnPageTag extends TagSupport {

	/**
	 * serialVersionUID 
	 */
	private static final long serialVersionUID = 1L;
	
	private static final Logger log = Logger.getLogger(TurnPageTag.class);
	
	/**
	 * 分页对象
	 */
	private Page page;
	
	/**
	 * 翻页时请求路径
	 */
	private String requestPath;
	
	/**
	 * 分页对象名称前缀
	 */
	private String prefix;
	
	public String getRequestPath() {
		return requestPath;
	}

	public void setRequestPath(String requestPath) {
		this.requestPath = requestPath;
	}
	
	public Page getPage() {
		return page;
	}
	
	public String getPrefix() {
		return prefix;
	}

	public void setPrefix(String prefix) {
		this.prefix = prefix;
	}

	public void setPage(Page page) {
		// 获取分页对象的同时对当前页数与总页数进行异常处理：
		// 当前页数小于1置为1，总页数小于置为1
		if(page.getCurrentPage() < 1) {
			page.setCurrentPage(1);
		}
		if(page.getTotal() < 1) {
			page.setTotal(1);
		}
		this.page = page;
	}
	
	/**
	 * 方法描述:重写父类方法，开始标签处理
	 * @return 忽略标签体内内容
	 * date:2013-11-21
	 * add by: hqsunc
	 */
	public int doStartTag() throws JspException {
		try {
			// 标签体内容
			StringBuffer tagContent = new StringBuffer();
			
			// 脚本--start
			tagContent.append("<script>");
			tagContent.append("function changePage(pageNo) {");
			tagContent.append("if(!pageNo) {");
			tagContent.append("pageNo = $('#currentPageText').val();");
			tagContent.append("}");
			tagContent.append("var rule = /^[0-9]*[1-9][0-9]*$/;");
			tagContent.append("if(!rule.test(pageNo) || pageNo < 1) {");
			tagContent.append("pageNo = 1;");
			tagContent.append("}");
			tagContent.append("if(pageNo > " + this.page.getTotal() + ") {");
			tagContent.append("pageNo = " + this.page.getTotal() + ";");
			tagContent.append("}");
			tagContent.append("$('#currentPage').val(pageNo);");
			if(!CommonUtil.isEmpty(this.requestPath)) {
				tagContent.append("$('form').eq(0).attr('action','" + this.requestPath + "');");
			}
			tagContent.append("$('form').eq(0).submit();");
			tagContent.append("}");
			tagContent.append("</script>");
			// 脚本--end
			
			// 分页内容--start
			tagContent.append("<div id='pager' class='pages' style='padding: 0px 10px 10px;'>");
			String currentPage;
			if(CommonUtil.isEmpty(this.prefix)) {
				currentPage = "currentPage";
			} else {
				currentPage = this.prefix + ".currentPage";
			}

			if(this.page.getCurrentPage() != 1) {
				tagContent.append("<a href='javascript:void(0);' onclick=\"changePage('" + (this.page.getCurrentPage() - 1) + "');\" class='p_pre'>上一页</a>");
			}else{
			    tagContent.append("<a href='javascript:void(0);' class='p_pre disabled'>上一页</a>");
			}
			
			if(this.page.getTotalPage()<=8){
			    for(int i=1; i<=this.page.getTotalPage();i++){
			        if(this.page.getCurrentPage() == i){
			            tagContent.append("<a href='javascript:void(0)' class='cur disabled'>"+i+"</a>");
			        }else{
			            tagContent.append("<a href='javascript:void(0)' onclick='changePage(" + i + ");'>"+i+"</a>");
			        }
			    }
			}else{
			    int i, _s_p_c = 6;
			    if(this.page.getCurrentPage() == 1){
	                i = 1;
	            }else if(this.page.getCurrentPage() == this.page.getTotalPage()){
	                i = this.page.getTotalPage() - 2;
	                _s_p_c = this.page.getTotalPage();
	            }else{
	                _s_p_c = this.page.getCurrentPage() + 4;
	                i = this.page.getCurrentPage() - 1;
	            }
			    
	            for (; i <= _s_p_c; i++) {
	                /*判断是否是当前页*/
	                if (i == this.page.getCurrentPage()) {
	                    tagContent.append("<a href='javascript:void(0)' class='cur disabled'>"+i+"</a>");
	                }else{
                        tagContent.append("<a href='javascript:void(0)' onclick='changePage(" + i + ");'>"+i+"</a>");
	                }
	            }
	            if((this.page.getTotalPage() - this.page.getCurrentPage())>=2){
	                tagContent.append("<a class='sl' href='javascript:void(0)'> ...</a> ");
                    tagContent.append("<a href='javascript:void(0)' onclick='changePage(" + this.page.getTotalPage() + ");'>"+this.page.getTotalPage()+"</a>");
	            }
	            
			}
			
			
			if(this.page.getCurrentPage() != this.page.getTotalPage()) {
				tagContent.append("<a href='javascript:void(0);' onclick=\"changePage('" + (this.page.getCurrentPage() + 1) + "')\" class='p_pre next'>下一页</a>");
			}else{
			    tagContent.append("<a href='javascript:void(0);' class='p_pre disabled'>下一页</a>");
			}
			
			tagContent.append("<a href='javascript:void(0)' class='jump'><span>跳转至第</span><input type='text' id='currentPageText' value='"+this.page.getCurrentPage()+"'><span style='margin-left: 10px'>页</span></a>");
			tagContent.append("<a href='javascript:void(0);' onclick='changePage();' class='ok'>确定</a>");
			tagContent.append("<input type='hidden' name='" + currentPage + "' id='currentPage' value='" + this.page.getCurrentPage() + "'/>");
			tagContent.append("</div>");
			// 分页内容--end
			super.pageContext.getOut().write(tagContent.toString());
		} catch (Exception e) {
			log.error(CommonUtil.getErrorMessage(Thread.currentThread().getStackTrace()[1], e));
		}
		return SKIP_BODY;
	}
	
	/**
	 * 
	 * 方法描述:重写父类方法，结束标签处理
	 * @return 继续处理页面其他内容
	 * date:2013-11-21
	 * add by: hqsunc
	 */
	public int doEndTag() throws JspException {
		return EVAL_PAGE;
	}
}