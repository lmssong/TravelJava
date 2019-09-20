package com.iss.sdb.pet.models;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.iss.sdb.commons.persistence.BasicModel;
import java.util.Date;

/**
 * 新闻管理表 t_news
 * 
 * @date 2019-03-22
 */
public class TNews extends BasicModel
{
	private static final long serialVersionUID = 1L;
	
	/** 唯一标识 */
	private Long id;
	/** 新闻标题 */
	private String title;
	/** 新闻摘要 */
	private String abstracts;
	/** 新闻图片 */
	private String image;
	/** 新闻内容 */
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
	public void setTitle(String title) 
	{
		this.title = title;
	}

	public String getTitle() 
	{
		return title;
	}
	public void setAbstracts(String abstracts) 
	{
		this.abstracts = abstracts;
	}

	public String getAbstracts() 
	{
		return abstracts;
	}
	public void setImage(String image) 
	{
		this.image = image;
	}

	public String getImage() 
	{
		return image;
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
            .append("title", getTitle())
            .append("abstracts", getAbstracts())
            .append("image", getImage())
            .append("content", getContent())
            .append("createTime", getCreateTime())
            .toString();
    }
}
