package com.yonyou.idea.form;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;

import static javax.persistence.GenerationType.IDENTITY;

import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.yonyou.discussion.form.IdeaDiscussion;
import com.yonyou.image.form.IdeaImage;
import com.yonyou.user.form.User;

/**
 * Idea entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "idea", catalog = "innoshare")
public class Idea implements java.io.Serializable {

	// Fields

	private Integer ideaID;
	private User user;
	private String ideaTitle;
	private Integer faviourCount;
	private Integer commentCount;
	private Integer titleImgID;
	private String description;
	private Integer readCount;
	private Integer deleteFlag;
	private String detail;
	private Date createTime;
	private Date lastUpdateTime;
	private String country;
	private Set<IdeaTagMap> ideaTagMaps = new HashSet<IdeaTagMap>(0);
	private Set<IdeaImage> ideaImages = new HashSet<IdeaImage>(0);
	private Set<IdeaDiscussion> ideaDiscussions = new HashSet<IdeaDiscussion>(0);
	/*private Set<Ideaupdaterecord> ideaupdaterecords = new HashSet<Ideaupdaterecord>(
			0);*/

	// Constructors

	/** default constructor */
	public Idea() {
	}

	/** full constructor */
	/*public Idea(User user, String ideaTitle, Integer faviourCount,
			Integer commentCount, Integer backgroundPictureId,
			String description, Integer tagsId, Integer readCount,
			Integer deleteFlag, String detail, Date createTime,
			Date lastUpdateTime, String country, Set<IdeaTagMap> ideatagmaps,
			Set<Ideaimagemap> ideaimagemaps,
			Set<Ideadiscussion> ideadiscussions,
			Set<Ideaupdaterecord> ideaupdaterecords) {
		this.user = user;
		this.ideaTitle = ideaTitle;
		this.faviourCount = faviourCount;
		this.commentCount = commentCount;
		this.backgroundPictureId = backgroundPictureId;
		this.description = description;
		this.tagsId = tagsId;
		this.readCount = readCount;
		this.deleteFlag = deleteFlag;
		this.detail = detail;
		this.createTime = createTime;
		this.lastUpdateTime = lastUpdateTime;
		this.country = country;
		this.ideatagmaps = ideatagmaps;
		this.ideaimagemaps = ideaimagemaps;
		this.ideadiscussions = ideadiscussions;
		this.ideaupdaterecords = ideaupdaterecords;
	}*/

	// Property accessors
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "ideaID", unique = true, nullable = false)
	public Integer getIdeaID() {
		return this.ideaID;
	}

	public void setIdeaID(Integer ideaID) {
		this.ideaID = ideaID;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "userID")
	public User getUser() {
		return this.user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@Column(name = "ideaTitle")
	public String getIdeaTitle() {
		return this.ideaTitle;
	}

	public void setIdeaTitle(String ideaTitle) {
		this.ideaTitle = ideaTitle;
	}

	@Column(name = "faviourCount")
	public Integer getFaviourCount() {
		return this.faviourCount;
	}

	public void setFaviourCount(Integer faviourCount) {
		this.faviourCount = faviourCount;
	}

	@Column(name = "commentCount")
	public Integer getCommentCount() {
		return this.commentCount;
	}

	public void setCommentCount(Integer commentCount) {
		this.commentCount = commentCount;
	}

	@Column(name = "titleImgID")
	public Integer getTitleImgID() {
		return titleImgID;
	}

	public void setTitleImgID(Integer titleImgID) {
		this.titleImgID = titleImgID;
	}

	@Column(name = "description", length = 65535)
	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Column(name = "readCount")
	public Integer getReadCount() {
		return this.readCount;
	}

	public void setReadCount(Integer readCount) {
		this.readCount = readCount;
	}

	@Column(name = "deleteFlag")
	public Integer getDeleteFlag() {
		return this.deleteFlag;
	}

	public void setDeleteFlag(Integer deleteFlag) {
		this.deleteFlag = deleteFlag;
	}

	@Column(name = "detail", length = 65535)
	public String getDetail() {
		return this.detail;
	}

	public void setDetail(String detail) {
		this.detail = detail;
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

	@Column(name = "country", length = 32)
	public String getCountry() {
		return this.country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "idea")
	public Set<IdeaTagMap> getIdeaTagMaps() {
		return this.ideaTagMaps;
	}

	public void setIdeaTagMaps(Set<IdeaTagMap> ideaTagMaps) {
		this.ideaTagMaps = ideaTagMaps;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "idea")
	public Set<IdeaImage> getIdeaImages() {
		return this.ideaImages;
	}

	public void setIdeaImages(Set<IdeaImage> ideaImages) {
		this.ideaImages = ideaImages;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "idea")
	public Set<IdeaDiscussion> getIdeaDiscussions() {
		return this.ideaDiscussions;
	}

	public void setIdeaDiscussions(Set<IdeaDiscussion> ideaDiscussions) {
		this.ideaDiscussions = ideaDiscussions;
	}

	/*@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "idea")
	public Set<Ideaupdaterecord> getIdeaupdaterecords() {
		return this.ideaupdaterecords;
	}

	public void setIdeaupdaterecords(Set<Ideaupdaterecord> ideaupdaterecords) {
		this.ideaupdaterecords = ideaupdaterecords;
	}
*/
}