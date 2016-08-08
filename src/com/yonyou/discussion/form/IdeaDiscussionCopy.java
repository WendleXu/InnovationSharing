package com.yonyou.discussion.form;

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
	public Integer getIdeaId() {
		return ideaId;
	}
	public void setIdeaId(Integer ideaId) {
		this.ideaId = ideaId;
	}
	public Integer getUpdatorId() {
		return updatorId;
	}
	public void setUpdatorId(Integer updatorId) {
		this.updatorId = updatorId;
	}
	public Integer getCreatorId() {
		return creatorId;
	}
	public void setCreatorId(Integer creatorId) {
		this.creatorId = creatorId;
	}
	public Integer getFatherId() {
		return fatherId;
	}
	public void setFatherId(Integer fatherId) {
		this.fatherId = fatherId;
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
	private Integer ideaId;
	private Integer updatorId;
	private Integer creatorId;
	private Integer fatherId;
	private Integer faviourCount;
	private String discussionContent;
	private Integer deleteFag;
	private Date createTime;
	private Date lastUpdateTime;
	private String creatorName;
	private String creatorPortraitUrl;
	private Integer isFavour;
	public Integer getIsFavour() {
		return isFavour;
	}
	public void setIsFavour(Integer isFavour) {
		this.isFavour = isFavour;
	}
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