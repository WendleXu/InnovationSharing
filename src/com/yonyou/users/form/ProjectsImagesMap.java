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
 * Projectsimagesmap entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name="projectsimagesmap"
    ,catalog="innoshare"
)

public class ProjectsImagesMap  implements java.io.Serializable {


    // Fields    

     private Integer projectImageId;
     private Projects projectsForm;
     private String imgUrl;
     private Integer deleteFlag;
     private Date createTime;
     private Date lastUpdateTime;


    // Constructors

    /** default constructor */
    public ProjectsImagesMap() {
    }

    
    /** full constructor */
    public ProjectsImagesMap(Projects projectsForm, String imgUrl, Integer deleteFlag, Date createTime, Date lastUpdateTime) {
        this.projectsForm = projectsForm;
        this.imgUrl = imgUrl;
        this.deleteFlag = deleteFlag;
        this.createTime = createTime;
        this.lastUpdateTime = lastUpdateTime;
    }

   
    // Property accessors
    @Id @GeneratedValue(strategy=IDENTITY)
    
    @Column(name="projectImageID", unique=true, nullable=false)

    public Integer getProjectImageId() {
        return this.projectImageId;
    }
    
    public void setProjectImageId(Integer projectImageId) {
        this.projectImageId = projectImageId;
    }
	@ManyToOne(fetch=FetchType.LAZY)
        @JoinColumn(name="projectID")

    public Projects getProjectsForm() {
        return this.projectsForm;
    }
    
    public void setProjectsForm(Projects projectsForm) {
        this.projectsForm = projectsForm;
    }
    
    @Column(name="imgURL")

    public String getImgUrl() {
        return this.imgUrl;
    }
    
    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }
    
    @Column(name="deleteFlag")

    public Integer getDeleteFlag() {
        return this.deleteFlag;
    }
    
    public void setDeleteFlag(Integer deleteFlag) {
        this.deleteFlag = deleteFlag;
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