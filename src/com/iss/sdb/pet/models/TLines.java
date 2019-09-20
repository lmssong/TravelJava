package com.iss.sdb.pet.models;

import java.util.Date;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import com.iss.sdb.commons.persistence.BasicModel;

/**
 * 线路管理表 t_lines
 * 
 * @date 2019-03-22
 */
public class TLines extends BasicModel
{
	private static final long serialVersionUID = 1L;
	
	/** 唯一标识 */
	private Long id;
	/** 线路名称 */
	private String name;
	/** 起点地址 */
	private String beginAddress;
	/** 终点地址 */
	private String endAddress;
	/** 起点经度 */
	private String beginLongitude;
	/** 起点纬度 */
	private String beginLatitude;
	/** 起点经度 */
	private String eginLongitude;
	/** 起点纬度 */
	private String eginLatitude;
	/** 线路介绍 */
	private String introduce;
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
	public void setName(String name) 
	{
		this.name = name;
	}

	public String getName() 
	{
		return name;
	}
	public void setBeginAddress(String beginAddress) 
	{
		this.beginAddress = beginAddress;
	}

	public String getBeginAddress() 
	{
		return beginAddress;
	}
	public void setEndAddress(String endAddress) 
	{
		this.endAddress = endAddress;
	}

	public String getEndAddress() 
	{
		return endAddress;
	}
	public void setBeginLongitude(String beginLongitude) 
	{
		this.beginLongitude = beginLongitude;
	}

	public String getBeginLongitude() 
	{
		return beginLongitude;
	}
	public void setBeginLatitude(String beginLatitude) 
	{
		this.beginLatitude = beginLatitude;
	}

	public String getBeginLatitude() 
	{
		return beginLatitude;
	}
	public void setEginLongitude(String eginLongitude) 
	{
		this.eginLongitude = eginLongitude;
	}

	public String getEginLongitude() 
	{
		return eginLongitude;
	}
	public void setEginLatitude(String eginLatitude) 
	{
		this.eginLatitude = eginLatitude;
	}

	public String getEginLatitude() 
	{
		return eginLatitude;
	}
	public void setIntroduce(String introduce) 
	{
		this.introduce = introduce;
	}

	public String getIntroduce() 
	{
		return introduce;
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
            .append("name", getName())
            .append("beginAddress", getBeginAddress())
            .append("endAddress", getEndAddress())
            .append("beginLongitude", getBeginLongitude())
            .append("beginLatitude", getBeginLatitude())
            .append("eginLongitude", getEginLongitude())
            .append("eginLatitude", getEginLatitude())
            .append("introduce", getIntroduce())
            .append("createTime", getCreateTime())
            .toString();
    }
}
