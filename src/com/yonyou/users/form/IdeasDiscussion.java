package com.yonyou.users.form;

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


/**
 * Ideasdiscussion entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name="ideasdiscussion"
    ,catalog="innoshare"
)

public class IdeasDiscussion  implements java.io.Serializable {


    // Fields    

     private Integer discussionId;
     private Ideas ideasForm;
     private Users usersForm;
     private Integer remarkUserId;
     private Date updateDate;
     private Integer isMainTopic;
     private Integer fatherId;
     private Integer faviourCount;
     private String content;
     private Integer deleteFag;
     private Date createTime;
     private Date lastUpdateTime;


    // Constructors

    /** default constructor */
    public IdeasDiscussion() {
    }

    
    /** full constructor */
    public IdeasDiscussion(Ideas ideasForm, Users usersForm, Integer remarkUserId, Date updateDate, Integer isMainTopic, Integer fatherId, Integer faviourCount, String content, Integer deleteFag, Date createTime, Date lastUpdateTime) {
        this.ideasForm = ideasForm;
        this.usersForm = usersForm;
        this.remarkUserId = remarkUserId;
        this.updateDate = updateDate;
        this.isMainTopic = isMainTopic;
        this.fatherId = fatherId;
        this.faviourCount = faviourCount;
        this.content = content;
        this.deleteFag = deleteFag;
        this.createTime = createTime;
        this.lastUpdateTime = lastUpdateTime;
    }

   
    // Property accessors
    @Id @GeneratedValue(strategy=IDENTITY)
    
    @Column(name="discussionID", unique=true, nullable=false)

    public Integer getDiscussionId() {
        return this.discussionId;
    }
    
    public void setDiscussionId(Integer discussionId) {
        this.discussionId = discussionId;
    }
	@ManyToOne(fetch=FetchType.LAZY)
        @JoinColumn(name="ideaID")

    public Ideas getIdeasForm() {
        return this.ideasForm;
    }
    
    public void setIdeasForm(Ideas ideasForm) {
        this.ideasForm = ideasForm;
    }
	@ManyToOne(fetch=FetchType.LAZY)
        @JoinColumn(name="userID")

    public Users getUsersForm() {
        return this.usersForm;
    }
    
    public void setUsersForm(Users usersForm) {
        this.usersForm = usersForm;
    }
    
    @Column(name="remarkUserID")

    public Integer getRemarkUserId() {
        return this.remarkUserId;
    }
    
    public void setRemarkUserId(Integer remarkUserId) {
        this.remarkUserId = remarkUserId;
    }
    
    @Column(name="updateDate", length=19)

    public Date getUpdateDate() {
        return this.updateDate;
    }
    
    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }
    
    @Column(name="isMainTopic")

    public Integer getIsMainTopic() {
        return this.isMainTopic;
    }
    
    public void setIsMainTopic(Integer isMainTopic) {
        this.isMainTopic = isMainTopic;
    }
    
    @Column(name="fatherID")

    public Integer getFatherId() {
        return this.fatherId;
    }
    
    public void setFatherId(Integer fatherId) {
        this.fatherId = fatherId;
    }
    
    @Column(name="faviourCount")

    public Integer getFaviourCount() {
        return this.faviourCount;
    }
    
    public void setFaviourCount(Integer faviourCount) {
        this.faviourCount = faviourCount;
    }
    
    @Column(name="content", length=65535)

    public String getContent() {
        return this.content;
    }
    
    public void setContent(String content) {
        this.content = content;
    }
    
    @Column(name="deleteFag")

    public Integer getDeleteFag() {
        return this.deleteFag;
    }
    
    public void setDeleteFag(Integer deleteFag) {
        this.deleteFag = deleteFag;
    }
    
    @Column(name="createTime", length=19)

    public Date getCreateTime() {
        return this.createTime;
    }
    
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
    
    @Column(name="lastUpdateTime", length=19)

    public Date getLastUpdateTime() {
        return this.lastUpdateTime;
    }
    
    public void setLastUpdateTime(Date lastUpdateTime) {
        this.lastUpdateTime = lastUpdateTime;
    }
   








}