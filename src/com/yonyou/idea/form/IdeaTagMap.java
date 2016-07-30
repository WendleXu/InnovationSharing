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

import com.yonyou.tag.form.Tag;

/**
 * IdeaTagMap entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "ideatagmap", catalog = "innoshare")
public class IdeaTagMap implements java.io.Serializable {

	// Fields

	private Integer id;
	private Idea idea;
	private Tag tag;
	private Integer deleteFlag;
	private Date createTime;
	private Date lastUpdateTime;
	private Integer createrId;

	// Constructors

	/** default constructor */
	public IdeaTagMap() {
	}

	/** full constructor */
	public IdeaTagMap(Idea idea, Tag tag, Integer deleteFlag, Date createTime,
			Date lastUpdateTime, Integer createrId) {
		this.idea = idea;
		this.tag = tag;
		this.deleteFlag = deleteFlag;
		this.createTime = createTime;
		this.lastUpdateTime = lastUpdateTime;
		this.createrId = createrId;
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
	@JoinColumn(name = "ideaID")
	public Idea getIdea() {
		return this.idea;
	}

	public void setIdea(Idea idea) {
		this.idea = idea;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "tagID")
	public Tag getTag() {
		return this.tag;
	}

	public void setTag(Tag tag) {
		this.tag = tag;
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

	@Column(name = "createrID")
	public Integer getCreaterId() {
		return this.createrId;
	}

	public void setCreaterId(Integer createrId) {
		this.createrId = createrId;
	}

}