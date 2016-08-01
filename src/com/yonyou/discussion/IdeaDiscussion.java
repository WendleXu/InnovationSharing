package com.yonyou.discussion;

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

import com.yonyou.idea.form.Idea;
import com.yonyou.user.form.User;

/**
 * Ideadiscussion entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "ideadiscussion", catalog = "innoshare")
public class IdeaDiscussion implements java.io.Serializable {

	// Fields

	private Integer discussionId;
	private Idea idea;
	private User updator;
	private User creator;
	private Integer fatherID;
	private Integer faviourCount;
	private String discussionContent;
	private Integer deleteFag;
	private Date createTime;
	private Date lastUpdateTime;

	// Constructors

	/** default constructor */
	public IdeaDiscussion() {
	}

	/** full constructor */
	public IdeaDiscussion(Idea idea, User updator,
			User creator, Integer fatherID, Integer faviourCount,
			String discussionContent, Integer deleteFag, Date createTime,
			Date lastUpdateTime) {
		this.idea = idea;
		this.updator = updator;
		this.creator = creator;
		this.fatherID = fatherID;
		this.faviourCount = faviourCount;
		this.discussionContent = discussionContent;
		this.deleteFag = deleteFag;
		this.createTime = createTime;
		this.lastUpdateTime = lastUpdateTime;
	}

	// Property accessors
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "discussionID", unique = true, nullable = false)
	public Integer getDiscussionId() {
		return this.discussionId;
	}

	public void setDiscussionId(Integer discussionId) {
		this.discussionId = discussionId;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ideaID")
	public Idea getIdea() {
		return this.idea;
	}

	public void setIdea(Idea idea) {
		this.idea = idea;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "updatorID")
	public User getUpdator() {
		return this.updator;
	}

	public void setUpdator(User updator) {
		this.updator = updator;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "creatorID")
	public User getCreator() {
		return this.creator;
	}

	public void setCreator(User creator) {
		this.creator = creator;
	}

	@Column(name = "fatherID")
	public Integer getFatherID() {
		return this.fatherID;
	}

	public void setFatherID(Integer fatherID) {
		this.fatherID = fatherID;
	}

	@Column(name = "faviourCount")
	public Integer getFaviourCount() {
		return this.faviourCount;
	}

	public void setFaviourCount(Integer faviourCount) {
		this.faviourCount = faviourCount;
	}

	@Column(name = "discussionContent", length = 65535)
	public String getDiscussionContent() {
		return this.discussionContent;
	}

	public void setDiscussionContent(String discussionContent) {
		this.discussionContent = discussionContent;
	}

	@Column(name = "deleteFag")
	public Integer getDeleteFag() {
		return this.deleteFag;
	}

	public void setDeleteFag(Integer deleteFag) {
		this.deleteFag = deleteFag;
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

}