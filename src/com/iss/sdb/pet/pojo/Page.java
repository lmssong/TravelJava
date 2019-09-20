/**
 * Page.java 2016年9月13日
 * 
 * Copyright 2001-2016  All rights reserved.
 *  PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.iss.sdb.pet.pojo;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import com.iss.sdb.commons.utils.BeanToMapUtil;

/**
 * 分页查询型响应数据模型
 * 
 * @author hqsunc
 * @since 2016年9月13日
 *
 */
public class Page {
    /**
     * 当前分页号
     */
    private Integer currentPage = 1;

    /**
     * 分页数量
     */
    private Integer pageSize = 10;

    /**
     * 记录总条数
     */
    private Integer total = 0;
    

    /**
     * 总页面数
     */
    private int totalPage = 1;

    /**
     * 分页数据
     */
    private Collection<?> rows;

    private Map<String, Object> searchParam;

    /**
     * @return the currentPage
     * @see Page#currentPage
     */
    public Integer getCurrentPage() {
        return currentPage;
    }

    /**
     * @param currentPage
     *            the currentPage to set
     * @see Page#currentPage
     */
    public void setCurrentPage(Integer currentPage) {
        if (currentPage != null && currentPage.intValue() > 0) {
            this.currentPage = currentPage;
        }
    }

    /**
     * @return the pageSize
     * @see Page#pageSize
     */
    public Integer getPageSize() {
        return pageSize;
    }

    /**
     * @param pageSize
     *            the pageSize to set
     * @see Page#pageSize
     */
    public void setPageSize(Integer pageSize) {
        if (pageSize != null && pageSize.intValue() > 0) {
            this.pageSize = pageSize;
        }
    }

    /**
     * @return the total
     * @see Page#total
     */
    public Integer getTotal() {
        return total;
    }

    /**
     * @param total
     *            the total to set
     * @see Page#total
     */
    public void setTotal(Integer total) {
        if (total != null && total.intValue() > 0) {
            this.total = total;
            this.count();
        }
    }

    private void count() {
        if ((total != 0) && (this.total % this.pageSize == 0)) {
            this.totalPage = this.total / this.pageSize;
        } else {
            this.totalPage = this.total / this.pageSize + 1;
        }
        if(this.currentPage > this.totalPage) {
            this.currentPage = this.totalPage;
        }
    }
    
    /**
     * @return the totalPage
     * @see Page#totalPage
     */
    public int getTotalPage() {
        return totalPage;
    }

    /**
     * @param totalPage the totalPage to set
     * @see Page#totalPage
     */
    public void setTotalPage(int totalPage) {
        this.totalPage = totalPage;
    }

    /**
     * @return the rows
     * @see Page#rows
     */
    public Collection<?> getRows() {
        return rows;
    }

    /**
     * @param rows
     *            the rows to set
     * @see Page#rows
     */
    public Page setRows(Collection<?> rows) {
        this.rows = rows;

        return this;
    }

    public Map<String, Object> getSearchParam() {
        if (null == searchParam) {
            searchParam = new HashMap<String, Object>();
        }
        return searchParam;
    }

    public void setSearchParam(Map<String, Object> searchParam) {
        this.searchParam = searchParam;
    }

    @SuppressWarnings("unchecked")
    public void setSearchParam(Object bean) {
        this.setSearchParam(BeanToMapUtil.convertBean(bean));
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
    }
}
