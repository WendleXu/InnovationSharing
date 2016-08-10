package com.yonyou.tag.form;

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
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * Tag entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "tag", catalog = "innoShare")
public class Tag implements java.io.Serializable {

	// Fields

	private Integer tagId;
	private String tagName;
	private Integer deleteFlag;
	private Date createTime;
	private Date lastUpdateTime;
	private Integer creatorId;
	private Integer updatorId;
	/*private Set<Ideatagmap> ideatagmaps = new HashSet<Ideatagmap>(0);
	private Set<Projecttagmap> projecttagmaps = new HashSet<Projecttagmap>(0);*/

	// Constructors

	

	/** default constructor */
	public Tag() {
	}

	/** full constructor */
	/*public Tag(String tagName, Integer deleteFlag, Date createTime,
			Date lastUpdateTime, Integer creatorId,
			Set<Ideatagmap> ideatagmaps, Set<Projecttagmap> projecttagmaps) {
		this.tagName = tagName;
		this.deleteFlag = deleteFlag;
		this.createTime = createTime;
		this.lastUpdateTime = lastUpdateTime;
		this.creatorId = creatorId;
		this.ideatagmaps = ideatagmaps;
		this.projecttagmaps = projecttagmaps;
	}*/

	// Property accessors
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "tagId", unique = true, nullable = false)
	public Integer getTagId() {
		return this.tagId;
	}

	public void setTagId(Integer tagId) {
		this.tagId = tagId;
	}

	@Column(name = "tagName", length = 32)
	public String getTagName() {
		return this.tagName;
	}

	public void setTagName(String tagName) {
		this.tagName = tagName;
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

	@Column(name = "creatorId")
	public Integer getCreatorId() {
		return this.creatorId;
	}

	public void setCreatorId(Integer creatorId) {
		this.creatorId = creatorId;
	}
	@Column(name = "updatorId")
	public Integer getUpdatorId() {
		return updatorId;
	}

	public void setUpdatorId(Integer updatorId) {
		this.updatorId = updatorId;
	}

	/*@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "tag")
	public Set<Ideatagmap> getIdeatagmaps() {
		return this.ideatagmaps;
	}

	public void setIdeatagmaps(Set<Ideatagmap> ideatagmaps) {
		this.ideatagmaps = ideatagmaps;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "tag")
	public Set<Projecttagmap> getProjecttagmaps() {
		return this.projecttagmaps;
	}

	public void setProjecttagmaps(Set<Projecttagmap> projecttagmaps) {
		this.projecttagmaps = projecttagmaps;
	}*/

}