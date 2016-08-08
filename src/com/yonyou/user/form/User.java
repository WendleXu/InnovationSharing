package com.yonyou.user.form;

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

import com.yonyou.idea.form.Idea;

/**
 * User entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "user", catalog = "innoShare")
public class User implements java.io.Serializable {

	// Fields

	private Integer userId;
	private String password;
	private String userName;
	private Integer deleteFlag;
	private String mobilePhone;
	private String userEmail;
	private String userAccount;
	private String lastLoginType;
	private String chanelId;
	private String pushId;
	private String country;
	private Date createTime;
	private Date lastUpdateTime;
	private String portraitUrl;
	private Integer integral;
	

	private Set<Idea> ideas = new HashSet<Idea>(0);
	/*private Set<Project> projects = new HashSet<Project>(0);
	private Set<Ideadiscussion> ideadiscussions = new HashSet<Ideadiscussion>(0);
	private Set<Ideaupdaterecord> ideaupdaterecords = new HashSet<Ideaupdaterecord>(0);*/

	// Constructors

	/** default constructor */
	public User() {
	}

	/** full constructor */
	/*public User(String password, String userName, Integer deleteFlag,
			String mobilePhone, String userEmail, String userAccount,
			String lastLoginType, String chanelId, String pushId,
			String country, Date createTime, Date lastUpdateTime,
			String portraitUrl, Set<Idea> ideas, Set<Project> projects,
			Set<Ideadiscussion> ideadiscussions,
			Set<Ideaupdaterecord> ideaupdaterecords) {
		this.password = password;
		this.userName = userName;
		this.deleteFlag = deleteFlag;
		this.mobilePhone = mobilePhone;
		this.userEmail = userEmail;
		this.userAccount = userAccount;
		this.lastLoginType = lastLoginType;
		this.chanelId = chanelId;
		this.pushId = pushId;
		this.country = country;
		this.createTime = createTime;
		this.lastUpdateTime = lastUpdateTime;
		this.portraitUrl = portraitUrl;
		this.ideas = ideas;
		this.projects = projects;
		this.ideadiscussions = ideadiscussions;
		this.ideaupdaterecords = ideaupdaterecords;
	}*/

	// Property accessors
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "userId", unique = true, nullable = false)
	public Integer getUserId() {
		return this.userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	@Column(name = "password", length = 32)
	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Column(name = "userName", length = 32)
	public String getUserName() {
		return this.userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	@Column(name = "deleteFlag")
	public Integer getDeleteFlag() {
		return this.deleteFlag;
	}

	public void setDeleteFlag(Integer deleteFlag) {
		this.deleteFlag = deleteFlag;
	}

	@Column(name = "mobilePhone", length = 16)
	public String getMobilePhone() {
		return this.mobilePhone;
	}

	public void setMobilePhone(String mobilePhone) {
		this.mobilePhone = mobilePhone;
	}

	@Column(name = "userEmail")
	public String getUserEmail() {
		return this.userEmail;
	}

	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}

	@Column(name = "userAccount", length = 32)
	public String getUserAccount() {
		return this.userAccount;
	}

	public void setUserAccount(String userAccount) {
		this.userAccount = userAccount;
	}

	@Column(name = "lastLoginType", length = 16)
	public String getLastLoginType() {
		return this.lastLoginType;
	}

	public void setLastLoginType(String lastLoginType) {
		this.lastLoginType = lastLoginType;
	}

	@Column(name = "chanelId", length = 32)
	public String getChanelId() {
		return this.chanelId;
	}

	public void setChanelId(String chanelId) {
		this.chanelId = chanelId;
	}

	@Column(name = "pushId", length = 32)
	public String getPushId() {
		return this.pushId;
	}

	public void setPushId(String pushId) {
		this.pushId = pushId;
	}

	@Column(name = "country", length = 16)
	public String getCountry() {
		return this.country;
	}

	public void setCountry(String country) {
		this.country = country;
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

	@Column(name = "portraitUrl")
	public String getPortraitUrl() {
		return this.portraitUrl;
	}

	public void setPortraitUrl(String portraitUrl) {
		this.portraitUrl = portraitUrl;
	}
	
	@Column(name = "integral")
	public Integer getIntegral() {
		return integral;
	}

	public void setIntegral(Integer integral) {
		this.integral = integral;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "user")
	public Set<Idea> getIdeas() {
		return this.ideas;
	}

	public void setIdeas(Set<Idea> ideas) {
		this.ideas = ideas;
	}

	/*@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "user")
	public Set<Project> getProjects() {
		return this.projects;
	}

	public void setProjects(Set<Project> projects) {
		this.projects = projects;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "user")
	public Set<Ideadiscussion> getIdeadiscussions() {
		return this.ideadiscussions;
	}

	public void setIdeadiscussions(Set<Ideadiscussion> ideadiscussions) {
		this.ideadiscussions = ideadiscussions;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "user")
	public Set<Ideaupdaterecord> getIdeaupdaterecords() {
		return this.ideaupdaterecords;
	}

	public void setIdeaupdaterecords(Set<Ideaupdaterecord> ideaupdaterecords) {
		this.ideaupdaterecords = ideaupdaterecords;
	}*/

}