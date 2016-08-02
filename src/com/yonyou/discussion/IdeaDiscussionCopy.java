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
public class IdeaDiscussionCopy implements java.io.Serializable {

	public Integer getDiscussionId() {
		return discussionId;
	}
	public void setDiscussionId(Integer discussionId) {
		this.discussionId = discussionId;
	}
	public Integer getIdeaID() {
		return ideaID;
	}
	public void setIdeaID(Integer ideaID) {
		this.ideaID = ideaID;
	}
	public Integer getUpdatorID() {
		return updatorID;
	}
	public void setUpdatorID(Integer updatorID) {
		this.updatorID = updatorID;
	}
	public Integer getCreatorID() {
		return creatorID;
	}
	public void setCreatorID(Integer creatorID) {
		this.creatorID = creatorID;
	}
	public Integer getFatherID() {
		return fatherID;
	}
	public void setFatherID(Integer fatherID) {
		this.fatherID = fatherID;
	}
	public Integer getFaviourCount() {
		return faviourCount;
	}
	public void setFaviourCount(Integer faviourCount) {
		this.faviourCount = faviourCount;
	}
	public String getDiscussionContent() {
		return discussionContent;
	}
	public void setDiscussionContent(String discussionContent) {
		this.discussionContent = discussionContent;
	}
	public Integer getDeleteFag() {
		return deleteFag;
	}
	public void setDeleteFag(Integer deleteFag) {
		this.deleteFag = deleteFag;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public Date getLastUpdateTime() {
		return lastUpdateTime;
	}
	public void setLastUpdateTime(Date lastUpdateTime) {
		this.lastUpdateTime = lastUpdateTime;
	}
	private Integer discussionId;
	private Integer ideaID;
	private Integer updatorID;
	private Integer creatorID;
	private Integer fatherID;
	private Integer faviourCount;
	private String discussionContent;
	private Integer deleteFag;
	private Date createTime;
	private Date lastUpdateTime;
	private String creatorName;
	private String creatorPortraitUrl;
	public String getCreatorName() {
		return creatorName;
	}
	public void setCreatorName(String creatorName) {
		this.creatorName = creatorName;
	}
	public String getCreatorPortraitUrl() {
		return creatorPortraitUrl;
	}
	public void setCreatorPortraitUrl(String creatorPortraitUrl) {
		this.creatorPortraitUrl = creatorPortraitUrl;
	}

}