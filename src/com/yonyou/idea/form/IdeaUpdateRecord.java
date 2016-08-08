package com.yonyou.idea.form;
// default package

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;

import static javax.persistence.GenerationType.IDENTITY;

import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.yonyou.user.form.User;

/**
 * Ideaupdaterecord entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "ideaUpdateRecord", catalog = "innoShare")
public class IdeaUpdateRecord implements java.io.Serializable {

	// Fields

	private Integer id;
	private Idea idea;
	private User user;
	private String updateDescription;
	private Integer deleteFlag;
	private Date createTime;
	private Date lastUpdateTime;
	private String updateContent;
	private String updateType;
	private String updateTitle;

	// Constructors

	/** default constructor */
	public IdeaUpdateRecord() {
	}

	/** full constructor */
	public IdeaUpdateRecord(Idea idea, User user, String updateDescription,
			Integer deleteFlag, Date createTime, Date lastUpdateTime,
			String updateContent, String updateType) {
		this.idea = idea;
		this.user = user;
		this.updateDescription = updateDescription;
		this.deleteFlag = deleteFlag;
		this.createTime = createTime;
		this.lastUpdateTime = lastUpdateTime;
		this.updateContent = updateContent;
		this.updateType = updateType;
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

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ideaId")
	public Idea getIdea() {
		return this.idea;
	}

	public void setIdea(Idea idea) {
		this.idea = idea;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "creatorId")
	public User getUser() {
		return this.user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@Column(name = "updateDescription", length = 65535)
	public String getUpdateDescription() {
		return this.updateDescription;
	}

	public void setUpdateDescription(String updateDescription) {
		this.updateDescription = updateDescription;
	}

	@Column(name = "deleteFlag")
	public Integer getDeleteFlag() {
		return this.deleteFlag;
	}

	public void setDeleteFlag(Integer deleteFlag) {
		this.deleteFlag = deleteFlag;
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

	@Column(name = "updateContent", length = 65535)
	public String getUpdateContent() {
		return this.updateContent;
	}

	public void setUpdateContent(String updateContent) {
		this.updateContent = updateContent;
	}

	@Column(name = "updateType", length = 32)
	public String getUpdateType() {
		return this.updateType;
	}

	public void setUpdateType(String updateType) {
		this.updateType = updateType;
	}
	
	@Column(name = "updateTitle", length = 255)
	public String getUpdateTitle() {
		return updateTitle;
	}

	public void setUpdateTitle(String updateTitle) {
		this.updateTitle = updateTitle;
	}

}