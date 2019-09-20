package com.iss.sdb.commons.utils.tag;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;

import org.apache.log4j.Logger;

import com.iss.sdb.commons.utils.CommonUtil;

/**
 * 查询按钮. <br>
 * 与普通按钮的区别在于当做表单post提交之前，会调用分页的一个方法，将当前页码改为1再查询.
 * 如果自己做成普通按钮，不忘记调用分页方法将页码置为1，效果是一样的，当前页面没有分页的不需要用此标签。
 * <p>
 * Copyright: Copyright (c) 2013-12-3 下午2:35:05
 * <p>
 * Company: 
 * <p>
 * 
 * @author hqsunc
 * @version 1.0.0
 */
public class SearchTag extends TagSupport {
	/**
	 * button的onclick
	 */
	private String onclick;

	public String getOnclick() {
		return onclick;
	}

	public void setOnclick(String onclick) {
		this.onclick = onclick;
	}

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 1L;
	private static final Logger log = Logger.getLogger(SearchTag.class);

	/**
	 * 方法描述:重写父类方法，开始标签处理
	 * 
	 * @return 忽略标签体内内容 date:2013-12-6 add by: hqsunc
	 */
	public int doStartTag() throws JspException {
		try {
			// 标签体内容
			StringBuffer tagContent = new StringBuffer();
			tagContent.append("<input type='button' onclick=");
			if (!CommonUtil.isEmpty(this.onclick)) {
				tagContent.append(this.onclick);
				tagContent.append(";");
			}
			tagContent.append("changePage(1); class='tabSub' value='查询'/>");
			super.pageContext.getOut().write(tagContent.toString());
		} catch (Exception e) {
			log.error(CommonUtil.getErrorMessage(Thread.currentThread().getStackTrace()[1], e));
		}
		return SKIP_BODY;
	}

	/**
	 * 方法描述:重写父类方法，结束标签处理
	 * 
	 * @return 继续处理页面其他内容 date:2013-12-3 add by: hqsunc
	 */
	public int doEndTag() throws JspException {
		return EVAL_PAGE;
	}
}