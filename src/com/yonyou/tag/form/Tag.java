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
@Table(name = "tag", catalog = "innoshare")
public class Tag implements java.io.Serializable {

	// Fields

	private Integer tagID;
	private String tagName;
	private Integer deleteflag;
	private Date creattime;
	private Date lastupdatetime;
	private Integer createrId;
	/*private Set<Ideatagmap> ideatagmaps = new HashSet<Ideatagmap>(0);
	private Set<Projecttagmap> projecttagmaps = new HashSet<Projecttagmap>(0);*/

	// Constructors

	/** default constructor */
	public Tag() {
	}

	/** full constructor */
	/*public Tag(String tagName, Integer deleteflag, Date creattime,
			Date lastupdatetime, Integer createrId,
			Set<Ideatagmap> ideatagmaps, Set<Projecttagmap> projecttagmaps) {
		this.tagName = tagName;
		this.deleteflag = deleteflag;
		this.creattime = creattime;
		this.lastupdatetime = lastupdatetime;
		this.createrId = createrId;
		this.ideatagmaps = ideatagmaps;
		this.projecttagmaps = projecttagmaps;
	}*/

	// Property accessors
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "tagID", unique = true, nullable = false)
	public Integer getTagID() {
		return this.tagID;
	}

	public void setTagID(Integer tagID) {
		this.tagID = tagID;
	}

	@Column(name = "tagName", length = 32)
	public String getTagName() {
		return this.tagName;
	}

	public void setTagName(String tagName) {
		this.tagName = tagName;
	}

	@Column(name = "deleteflag")
	public Integer getDeleteflag() {
		return this.deleteflag;
	}

	public void setDeleteflag(Integer deleteflag) {
		this.deleteflag = deleteflag;
	}

	@Column(name = "creattime", length = 19)
	public Date getCreattime() {
		return this.creattime;
	}

	public void setCreattime(Date creattime) {
		this.creattime = creattime;
	}

	@Column(name = "lastupdatetime", length = 19)
	public Date getLastupdatetime() {
		return this.lastupdatetime;
	}

	public void setLastupdatetime(Date lastupdatetime) {
		this.lastupdatetime = lastupdatetime;
	}

	@Column(name = "createrID")
	public Integer getCreaterId() {
		return this.createrId;
	}

	public void setCreaterId(Integer createrId) {
		this.createrId = createrId;
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