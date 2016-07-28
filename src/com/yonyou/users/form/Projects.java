package com.yonyou.users.form;
// default package

import com.yonyou.users.form.ProjectsImagesMap;
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
 * ProjectsForm entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name="projects"
    ,catalog="innoshare"
)

public class Projects  implements java.io.Serializable {


    // Fields    

     private Integer projectId;
     private Users usersForm;
     private String projectTitle;
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
     private Set<ProjectsImagesMap> projectsimagesmaps = new HashSet<ProjectsImagesMap>(0);


    // Constructors

    /** default constructor */
    public Projects() {
    }

    
    /** full constructor */
    public Projects(Users usersForm, String projectTitle, Integer faviourCount, Integer commentCount, Integer backgroundPictureId, String description, Integer tagsId, Integer readCount, Integer deleteFlag, String detail, Date createTime, Date lastUpdateTime, Set<ProjectsImagesMap> projectsimagesmaps) {
        this.usersForm = usersForm;
        this.projectTitle = projectTitle;
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
        this.projectsimagesmaps = projectsimagesmaps;
    }

   
    // Property accessors
    @Id @GeneratedValue(strategy=IDENTITY)
    
    @Column(name="projectID", unique=true, nullable=false)

    public Integer getProjectId() {
        return this.projectId;
    }
    
    public void setProjectId(Integer projectId) {
        this.projectId = projectId;
    }
	@ManyToOne(fetch=FetchType.LAZY)
        @JoinColumn(name="userID")

    public Users getUsersForm() {
        return this.usersForm;
    }
    
    public void setUsersForm(Users usersForm) {
        this.usersForm = usersForm;
    }
    
    @Column(name="projectTitle")

    public String getProjectTitle() {
        return this.projectTitle;
    }
    
    public void setProjectTitle(String projectTitle) {
        this.projectTitle = projectTitle;
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
@OneToMany(cascade=CascadeType.ALL, fetch=FetchType.LAZY, mappedBy="projectsForm")

    public Set<ProjectsImagesMap> getProjectsimagesmaps() {
        return this.projectsimagesmaps;
    }
    
    public void setProjectsimagesmaps(Set<ProjectsImagesMap> projectsimagesmaps) {
        this.projectsimagesmaps = projectsimagesmaps;
    }
   








}