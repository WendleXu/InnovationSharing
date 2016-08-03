package com.yonyou.record.form;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Discussionfaviourrecord entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "discussionfaviourrecord", catalog = "innoshare")
public class DiscussionFaviourRecord implements java.io.Serializable {

	// Fields

	private Integer id;
	private Integer userID;
	private Integer discussionID;
	private Date createTime;
	private Date lastUpdateTime;
	private Integer deleteFlag;
	private Integer isFavour;
	private String discussionType;

	// Constructors

	/** default constructor */
	public DiscussionFaviourRecord() {
	}

	/** full constructor */
	public DiscussionFaviourRecord(Integer userID, Integer discussionID,
			Date createTime, Date lastUpdateTime, Integer deleteFlag,
			Integer isFavour, String discussionType) {
		this.userID = userID;
		this.discussionID = discussionID;
		this.createTime = createTime;
		this.lastUpdateTime = lastUpdateTime;
		this.deleteFlag = deleteFlag;
		this.isFavour = isFavour;
		this.discussionType = discussionType;
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

	@Column(name = "userID")
	public Integer getUserID() {
		return this.userID;
	}

	public void setUserID(Integer userID) {
		this.userID = userID;
	}

	@Column(name = "discussionID")
	public Integer getDiscussionID() {
		return this.discussionID;
	}

	public void setDiscussionID(Integer discussionID) {
		this.discussionID = discussionID;
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

	@Column(name = "discussionType", length = 16)
	public String getDiscussionType() {
		return this.discussionType;
	}

	public void setDiscussionType(String discussionType) {
		this.discussionType = discussionType;
	}

}