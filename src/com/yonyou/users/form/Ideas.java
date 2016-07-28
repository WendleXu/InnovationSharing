package com.yonyou.users.form;
// default package

import com.yonyou.users.form.IdeasDiscussion;
import com.yonyou.users.form.IdeasImagesMap;
import com.yonyou.users.form.IdeasUpdates;
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


/**
 * IdeasForm entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name="ideas"
    ,catalog="innoshare"
)

public class Ideas  implements java.io.Serializable {


    // Fields    

     private Integer ideaId;
     private Users usersForm;
     private String ideaTitle;
     private Integer faviourCount;
     private Integer commentCount;
     private Integer backgroundPictureId;
     private String description;
     private Integer tagsId;
     private Integer readCount;
     private Integer deleteFlag;
     private String detail;
     private Date createTime;
     private Date lastUpdateTime;
     private String country;
     private Set<IdeasImagesMap> ideasimagesmaps = new HashSet<IdeasImagesMap>(0);
     private Set<IdeasDiscussion> ideasdiscussions = new HashSet<IdeasDiscussion>(0);
     private Set<IdeasUpdates> ideasupdateses = new HashSet<IdeasUpdates>(0);


    // Constructors

    /** default constructor */
    public Ideas() {
    }

    
    /** full constructor */
    public Ideas(Users usersForm, String ideaTitle, Integer faviourCount, Integer commentCount, Integer backgroundPictureId, String description, Integer tagsId, Integer readCount, Integer deleteFlag, String detail, Date createTime, Date lastUpdateTime, String country, Set<IdeasImagesMap> ideasimagesmaps, Set<IdeasDiscussion> ideasdiscussions, Set<IdeasUpdates> ideasupdateses) {
        this.usersForm = usersForm;
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
        this.ideasimagesmaps = ideasimagesmaps;
        this.ideasdiscussions = ideasdiscussions;
        this.ideasupdateses = ideasupdateses;
    }

   
    // Property accessors
    @Id @GeneratedValue(strategy=IDENTITY)
    
    @Column(name="ideaID", unique=true, nullable=false)

    public Integer getIdeaId() {
        return this.ideaId;
    }
    
    public void setIdeaId(Integer ideaId) {
        this.ideaId = ideaId;
    }
	@ManyToOne(fetch=FetchType.LAZY)
        @JoinColumn(name="userID")

    public Users getUsersForm() {
        return this.usersForm;
    }
    
    public void setUsersForm(Users usersForm) {
        this.usersForm = usersForm;
    }
    
    @Column(name="ideaTitle")

    public String getIdeaTitle() {
        return this.ideaTitle;
    }
    
    public void setIdeaTitle(String ideaTitle) {
        this.ideaTitle = ideaTitle;
    }
    
    @Column(name="faviourCount")

    public Integer getFaviourCount() {
        return this.faviourCount;
    }
    
    public void setFaviourCount(Integer faviourCount) {
        this.faviourCount = faviourCount;
    }
    
    @Column(name="commentCount")

    public Integer getCommentCount() {
        return this.commentCount;
    }
    
    public void setCommentCount(Integer commentCount) {
        this.commentCount = commentCount;
    }
    
    @Column(name="backgroundPictureID")

    public Integer getBackgroundPictureId() {
        return this.backgroundPictureId;
    }
    
    public void setBackgroundPictureId(Integer backgroundPictureId) {
        this.backgroundPictureId = backgroundPictureId;
    }
    
    @Column(name="description", length=65535)

    public String getDescription() {
        return this.description;
    }
    
    public void setDescription(String description) {
        this.description = description;
    }
    
    @Column(name="tagsID")

    public Integer getTagsId() {
        return this.tagsId;
    }
    
    public void setTagsId(Integer tagsId) {
        this.tagsId = tagsId;
    }
    
    @Column(name="readCount")

    public Integer getReadCount() {
        return this.readCount;
    }
    
    public void setReadCount(Integer readCount) {
        this.readCount = readCount;
    }
    
    @Column(name="deleteFlag")

    public Integer getDeleteFlag() {
        return this.deleteFlag;
    }
    
    public void setDeleteFlag(Integer deleteFlag) {
        this.deleteFlag = deleteFlag;
    }
    
    @Column(name="detail", length=65535)

    public String getDetail() {
        return this.detail;
    }
    
    public void setDetail(String detail) {
        this.detail = detail;
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
    
    @Column(name="country", length=32)

    public String getCountry() {
        return this.country;
    }
    
    public void setCountry(String country) {
        this.country = country;
    }
@OneToMany(cascade=CascadeType.ALL, fetch=FetchType.LAZY, mappedBy="ideasForm")

    public Set<IdeasImagesMap> getIdeasimagesmaps() {
        return this.ideasimagesmaps;
    }
    
    public void setIdeasimagesmaps(Set<IdeasImagesMap> ideasimagesmaps) {
        this.ideasimagesmaps = ideasimagesmaps;
    }
@OneToMany(cascade=CascadeType.ALL, fetch=FetchType.LAZY, mappedBy="ideasForm")

    public Set<IdeasDiscussion> getIdeasdiscussions() {
        return this.ideasdiscussions;
    }
    
    public void setIdeasdiscussions(Set<IdeasDiscussion> ideasdiscussions) {
        this.ideasdiscussions = ideasdiscussions;
    }
@OneToMany(cascade=CascadeType.ALL, fetch=FetchType.LAZY, mappedBy="ideasForm")

    public Set<IdeasUpdates> getIdeasupdateses() {
        return this.ideasupdateses;
    }
    
    public void setIdeasupdateses(Set<IdeasUpdates> ideasupdateses) {
        this.ideasupdateses = ideasupdateses;
    }
   








}