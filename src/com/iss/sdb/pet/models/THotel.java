package com.iss.sdb.pet.models;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.iss.sdb.commons.persistence.BasicModel;
import java.util.Date;

/**
 * 酒店管理表 t_hotel
 * 
 * @date 2019-03-22
 */
public class THotel extends BasicModel
{
	private static final long serialVersionUID = 1L;
	
	/** 唯一标识 */
	private Long id;
	/** 酒店名称 */
	private String name;
	/** 地址 */
	private String address;
	/** 预定电话 */
	private String tel;
	/** 展示图片 */
	private String image;
	/** 酒店介绍 */
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
	public void setAddress(String address) 
	{
		this.address = address;
	}

	public String getAddress() 
	{
		return address;
	}
	public void setTel(String tel) 
	{
		this.tel = tel;
	}

	public String getTel() 
	{
		return tel;
	}
	public void setImage(String image) 
	{
		this.image = image;
	}

	public String getImage() 
	{
		return image;
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
            .append("address", getAddress())
            .append("tel", getTel())
            .append("image", getImage())
            .append("introduce", getIntroduce())
            .append("createTime", getCreateTime())
            .toString();
    }
}
