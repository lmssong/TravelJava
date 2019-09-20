/**
 * OerateLogModel.java 2016年9月24日
 * 
 * Copyright 2001-2016  All rights reserved.
 *  PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.iss.sdb.commons.models;

import java.util.Date;

import org.apache.commons.lang3.StringUtils;

import com.iss.sdb.commons.persistence.BasicModel;
import com.iss.sdb.commons.utils.DateFormatter;

/**
 * 日志模型
 * 
 * @author hqsunc
 * @since 2016年9月24日
 * 
 */
public class LogModel extends BasicModel {

	private long id;

	/**
	 * 用户id
	 */
	private long userId = 0;

	/**
	 * 用户帐号
	 */
	private String userAccount = "";

	/**
	 * 用户姓名
	 */
	private String userName = "";

	/**
	 * 客户端ip
	 */
	private String clientIp = "";

	/**
	 * 1,操作日志；2其他日志
	 */
	private int type = 1;

	/**
	 * 1,新增；2,修改；3,删除；4,查看；5,发布；6,导入；7,导出
	 */
	private int idx = 0;

	/**
	 * 日志动词
	 */
	private String verb = "";

	/**
	 * 描述信息
	 */
	private String noun = "";

	/**
	 * 日志时间
	 */
	private Date logTime = new Date();

	/**
	 * 入库时间
	 */
	private Date dbTime;

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.iss.sdb.commons.persistence.BasicModel#getId()
	 */
	public Long getId() {
		return this.id;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.iss.sdb.commons.persistence.BasicModel#setId(java.lang.Long)
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * @return the userId
	 * @see LogModel#userId
	 */
	public long getUserId() {
		return userId;
	}

	/**
	 * @param userId
	 *            the userId to set
	 * @see LogModel#userId
	 */
	public void setUserId(long userId) {
		if (userId > 0) {
			this.userId = userId;
		}
	}

	/**
	 * @return the userAccount
	 * @see LogModel#userAccount
	 */
	public String getUserAccount() {
		return userAccount;
	}

	/**
	 * @param userAccount
	 *            the userAccount to set
	 * @see LogModel#userAccount
	 */
	public void setUserAccount(String userAccount) {
		if (StringUtils.isNotBlank(userAccount)) {
			this.userAccount = userAccount;
		}
	}

	/**
	 * @return the userName
	 * @see LogModel#userName
	 */
	public String getUserName() {
		return userName;
	}

	/**
	 * @param userName
	 *            the userName to set
	 * @see LogModel#userName
	 */
	public void setUserName(String userName) {
		if (StringUtils.isNotBlank(userName)) {
			this.userName = userName;
		}
	}

	/**
	 * @return the clientIp
	 * @see LogModel#clientIp
	 */
	public String getClientIp() {
		return clientIp;
	}

	/**
	 * @param clientIp
	 *            the clientIp to set
	 * @see LogModel#clientIp
	 */
	public void setClientIp(String clientIp) {
		if (StringUtils.isNotBlank(clientIp)) {
			this.clientIp = clientIp;
		}
	}

	/**
	 * @return the type
	 * @see LogModel#type
	 */
	public int getType() {
		return type;
	}

	/**
	 * @param type
	 *            the type to set
	 * @see LogModel#type
	 */
	public void setType(int type) {
		if (type > 0) {
			this.type = type;
		}
	}

	/**
	 * @return the idx
	 * @see LogModel#idx
	 */
	public int getIdx() {
		return idx;
	}

	/**
	 * @param idx
	 *            the idx to set
	 * @see LogModel#idx
	 */
	public void setIdx(int idx) {
		if (idx > 0) {
			this.idx = idx;
		}
	}

	/**
	 * @return the verb
	 * @see LogModel#verb
	 */
	public String getVerb() {
		return verb;
	}

	/**
	 * @param verb
	 *            the verb to set
	 * @see LogModel#verb
	 */
	public void setVerb(String verb) {
		if (StringUtils.isNotBlank(verb)) {
			this.verb = verb;
		}
	}

	/**
	 * @return the noun
	 * @see LogModel#noun
	 */
	public String getNoun() {
		return noun;
	}

	/**
	 * @param noun
	 *            the noun to set
	 * @see LogModel#noun
	 */
	public void setNoun(String noun) {
		if (StringUtils.isNotBlank(noun)) {
			this.noun = noun;
		}
	}

	/**
	 * @return the logTime
	 * @see LogModel#logTime
	 */
	public Date getLogTime() {
		return logTime;
	}

	/**
	 * @param logTime
	 *            the logTime to set
	 * @see LogModel#logTime
	 */
	public void setLogTime(Date logTime) {
		if (null != logTime) {
			this.logTime = logTime;
		}
	}

	/**
	 * @return the dbTime
	 * @see LogModel#dbTime
	 */
	public Date getDbTime() {
		return dbTime;
	}

	/**
	 * @param dbTime
	 *            the dbTime to set
	 * @see LogModel#dbTime
	 */
	public void setDbTime(Date dbTime) {
		this.dbTime = dbTime;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		// 日志分隔符
		final String separator = " | ";

		StringBuffer buff = new StringBuffer();
		buff.append(userId).append(separator);
		buff.append(userAccount).append(separator);
		buff.append(userName).append(separator);
		buff.append(clientIp).append(separator);
		buff.append(idx).append(separator);
		buff.append(verb).append(separator);
		buff.append(noun).append(separator);
		buff.append(DateFormatter.formatDate("yyyy年MM月dd日 HH时mm分ss秒", logTime));

		return buff.toString();
	}

}
