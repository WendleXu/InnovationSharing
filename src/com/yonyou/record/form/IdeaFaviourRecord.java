package com.yonyou.record.form;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;

import static javax.persistence.GenerationType.IDENTITY;

import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Ideafaviourrecord entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "ideaFaviourRecord", catalog = "innoShare")
public class IdeaFaviourRecord implements Serializable {

	// Fields

	private Integer id;
	private Integer userId;
	private Integer ideaId;
	private Date createTime;
	private Date lastUpdateTime;
	private Integer deleteFlag;
	private Integer isFavour;

	// Constructors

	/** default constructor */
	public IdeaFaviourRecord() {
	}

	/** full constructor */
	public IdeaFaviourRecord(Integer userId, Integer ideaId, Date createTime,
			Date lastUpdateTime, Integer deleteFlag, Integer isFavour) {
		this.userId = userId;
		this.ideaId = ideaId;
		this.createTime = createTime;
		this.lastUpdateTime = lastUpdateTime;
		this.deleteFlag = deleteFlag;
		this.isFavour = isFavour;
	}

	// Property accessors
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@Column(name = "userId")
	public Integer getUserId() {
		return this.userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	@Column(name = "ideaId")
	public Integer getIdeaId() {
		return this.ideaId;
	}

	public void setIdeaId(Integer ideaId) {
		this.ideaId = ideaId;
	}

	@Column(name = "createTime", length = 19)
	public Date getCreateTime() {
		return this.createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	@Column(name = "lastUpdateTime", length = 19)
	public Date getLastUpdateTime() {
		return this.lastUpdateTime;
	}

	public void setLastUpdateTime(Date lastUpdateTime) {
		this.lastUpdateTime = lastUpdateTime;
	}

	@Column(name = "deleteFlag")
	public Integer getDeleteFlag() {
		return this.deleteFlag;
	}

	public void setDeleteFlag(Integer deleteFlag) {
		this.deleteFlag = deleteFlag;
	}

	@Column(name = "isFavour")
	public Integer getIsFavour() {
		return this.isFavour;
	}

	public void setIsFavour(Integer isFavour) {
		this.isFavour = isFavour;
	}

}