package com.iss.sdb.pet.models;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.iss.sdb.commons.persistence.BasicModel;
import java.util.Date;

/**
 * 留言管理表 t_message
 * 
 * @date 2019-03-22
 */
public class TMessage extends BasicModel
{
	private static final long serialVersionUID = 1L;
	
	/** 唯一标识 */
	private Long id;
	/** 留言人 */
	private String person;
	/** 留言内容 */
	private String content;
	/** 创建时间 */
	private Date createTime;

	public void setId(Long id) 
	{
		this.id = id;
	}

	public Long getId() 
	{
		return id;
	}
	public void setPerson(String person) 
	{
		this.person = person;
	}

	public String getPerson() 
	{
		return person;
	}
	public void setContent(String content) 
	{
		this.content = content;
	}

	public String getContent() 
	{
		return content;
	}
	public void setCreateTime(Date createTime) 
	{
		this.createTime = createTime;
	}

	public Date getCreateTime() 
	{
		return createTime;
	}

    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("person", getPerson())
            .append("content", getContent())
            .append("createTime", getCreateTime())
            .toString();
    }
}
