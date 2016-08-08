package com.yonyou.softversion;
// default package

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * SoftVersion entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "softVersion", catalog = "innoShare")
public class SoftVersion implements java.io.Serializable {

	// Fields

	private Integer id;
	private String packageName;
	private String phoneType;
	private String currentVersion;
	private Integer deleteFlag;
	private Date createTime;
	private Date lastUpdateTime;
	private String softContent;
	private String remark;
	private Integer creatorId;
	private Integer updatorId;
	private String previousVersion;
	private String updateUrl;
	private String updateMsg;

	// Constructors

	/** default constructor */
	public SoftVersion() {
	}

	/** full constructor */
	public SoftVersion(String packageName, String phoneType,
			String currentVersion, Integer deleteFlag, Date createTime,
			Date lastUpdateTime, String softContent, String remark,
			Integer creatorId, Integer updatorId, String previousVersion,
			String updateUrl, String updateMsg) {
		this.packageName = packageName;
		this.phoneType = phoneType;
		this.currentVersion = currentVersion;
		this.deleteFlag = deleteFlag;
		this.createTime = createTime;
		this.lastUpdateTime = lastUpdateTime;
		this.softContent = softContent;
		this.remark = remark;
		this.creatorId = creatorId;
		this.updatorId = updatorId;
		this.previousVersion = previousVersion;
		this.updateUrl = updateUrl;
		this.updateMsg = updateMsg;
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

	@Column(name = "packageName", length = 32)
	public String getPackageName() {
		return this.packageName;
	}

	public void setPackageName(String packageName) {
		this.packageName = packageName;
	}

	@Column(name = "phoneType", length = 16)
	public String getPhoneType() {
		return this.phoneType;
	}

	public void setPhoneType(String phoneType) {
		this.phoneType = phoneType;
	}

	@Column(name = "currentVersion", length = 16)
	public String getCurrentVersion() {
		return this.currentVersion;
	}

	public void setCurrentVersion(String currentVersion) {
		this.currentVersion = currentVersion;
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

	@Column(name = "softContent", length = 65535)
	public String getSoftContent() {
		return this.softContent;
	}

	public void setSoftContent(String softContent) {
		this.softContent = softContent;
	}

	@Column(name = "remark", length = 65535)
	public String getRemark() {
		return this.remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
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
		return this.updatorId;
	}

	public void setUpdatorId(Integer updatorId) {
		this.updatorId = updatorId;
	}

	@Column(name = "previousVersion", length = 16)
	public String getPreviousVersion() {
		return this.previousVersion;
	}

	public void setPreviousVersion(String previousVersion) {
		this.previousVersion = previousVersion;
	}

	@Column(name = "updateUrl")
	public String getUpdateUrl() {
		return this.updateUrl;
	}

	public void setUpdateUrl(String updateUrl) {
		this.updateUrl = updateUrl;
	}

	@Column(name = "updateMsg")
	public String getUpdateMsg() {
		return this.updateMsg;
	}

	public void setUpdateMsg(String updateMsg) {
		this.updateMsg = updateMsg;
	}

}