package com.iss.sdb.commons.utils.tag;

import java.util.Map.Entry;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;

import org.apache.log4j.Logger;

import com.iss.sdb.commons.utils.CommonUtil;
import com.iss.sdb.pet.pojo.Page;

/**
 * 自定义标签-值保留. <br>
 * 针对列表页面向其他页面跳转，再跳转回列表页面，需要将列表页面跳转前输入的检索条件与分页信息一直保留，
 * 本标签将在引用处，自动将需要值保留的字段与值以hidden的形式输出
 * ，表单提交时这些值将会提交，服务端需要将这些值暂存再传给下个页面，如此一直到再回到列表页面。
 * <p>
 * Copyright: Copyright (c) 2013-11-25 上午10:25:53
 * <p>
 * Company: 
 * <p>
 * 
 * @author hqsunc
 * @version 1.0.0
 */
public class RetainValueTag extends TagSupport {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 1L;

	private static final Logger log = Logger.getLogger(RetainValueTag.class);

	/**
	 * 分页对象
	 */
	private Page page;
	
	/**
	 * 分页对象名称前缀
	 */
	private String prefix;

	public Page getPage() {
		return page;
	}

	public void setPage(Page page) {
		this.page = page;
	}

	public String getPrefix() {
		return prefix;
	}

	public void setPrefix(String prefix) {
		this.prefix = prefix;
	}

	/**
	 * 方法描述:重写父类方法，开始标签处理
	 * 
	 * @return 忽略标签体内内容 date:2013-11-25 add by: hqsunc
	 */
	public int doStartTag() throws JspException {
		try {
			// 标签体内容
			StringBuffer tagContent = new StringBuffer();
			String currentPage;
			String searchParam;
			if(CommonUtil.isEmpty(this.prefix)) {
				currentPage = "currentPage";
				searchParam = "searchParam";
			} else {
				currentPage = this.prefix + ".currentPage";
				searchParam = this.prefix + ".searchParam";
			}
			
			if (this.page != null) {
				tagContent.append("<input type='hidden' name='" + currentPage + "' value='" + this.page.getCurrentPage() + "'/>");
				if (this.page.getSearchParam() != null) {
					for (Entry<String, Object> entry : this.page.getSearchParam().entrySet()) {
						tagContent.append("<input type='hidden' name='" + searchParam + "[" + entry.getKey() + "]' value='" + entry.getValue() + "'/>");
					}
				}
			}
			super.pageContext.getOut().write(tagContent.toString());
		} catch (Exception e) {
			log.error(CommonUtil.getErrorMessage(Thread.currentThread().getStackTrace()[1], e));
		}
		return SKIP_BODY;
	}

	/**
	 * 方法描述:重写父类方法，结束标签处理
	 * 
	 * @return 继续处理页面其他内容 date:2013-11-25 add by: hqsunc
	 */
	public int doEndTag() throws JspException {
		return EVAL_PAGE;
	}
}