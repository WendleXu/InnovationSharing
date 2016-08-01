package com.yonyou.image.form;

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

/**
 * Ideaimage entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "ideaimage", catalog = "innoshare")
public class IdeaImage implements java.io.Serializable {

	// Fields

	private Integer ideaImageId;
	private Idea idea;
	private String imgUrl;
	private Integer deleteFag;
	private Date createTime;
	private Date lastUpdateTime;

	// Constructors

	/** default constructor */
	public IdeaImage() {
	}

	/** full constructor */
	public IdeaImage(Idea idea, String imgUrl, Integer deleteFag,
			Date createTime, Date lastUpdateTime) {
		this.idea = idea;
		this.imgUrl = imgUrl;
		this.deleteFag = deleteFag;
		this.createTime = createTime;
		this.lastUpdateTime = lastUpdateTime;
	}

	// Property accessors
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "ideaImageID", unique = true, nullable = false)
	public Integer getIdeaImageId() {
		return this.ideaImageId;
	}

	public void setIdeaImageId(Integer ideaImageId) {
		this.ideaImageId = ideaImageId;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ideaID")
	public Idea getIdea() {
		return this.idea;
	}

	public void setIdea(Idea idea) {
		this.idea = idea;
	}

	@Column(name = "imgURL")
	public String getImgUrl() {
		return this.imgUrl;
	}

	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
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