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
 * Ideasupdates entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name="ideasupdates"
    ,catalog="innoshare"
)

public class IdeasUpdates  implements java.io.Serializable {


    // Fields    

     private Integer id;
     private Ideas ideasForm;
     private Users usersForm;
     private Date updateDate;
     private String updateDescription;
     private Integer deleteFlag;
     private Date createTime;
     private Date lastUpdateTime;


    // Constructors

    /** default constructor */
    public IdeasUpdates() {
    }

    
    /** full constructor */
    public IdeasUpdates(Ideas ideasForm, Users usersForm, Date updateDate, String updateDescription, Integer deleteFlag, Date createTime, Date lastUpdateTime) {
        this.ideasForm = ideasForm;
        this.usersForm = usersForm;
        this.updateDate = updateDate;
        this.updateDescription = updateDescription;
        this.deleteFlag = deleteFlag;
        this.createTime = createTime;
        this.lastUpdateTime = lastUpdateTime;
    }

   
    // Property accessors
    @Id @GeneratedValue(strategy=IDENTITY)
    
    @Column(name="id", unique=true, nullable=false)

    public Integer getId() {
        return this.id;
    }
    
    public void setId(Integer id) {
        this.id = id;
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
        @JoinColumn(name="updateUserID")

    public Users getUsersForm() {
        return this.usersForm;
    }
    
    public void setUsersForm(Users usersForm) {
        this.usersForm = usersForm;
    }
    
    @Column(name="updateDate", length=19)

    public Date getUpdateDate() {
        return this.updateDate;
    }
    
    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }
    
    @Column(name="updateDescription", length=65535)

    public String getUpdateDescription() {
        return this.updateDescription;
    }
    
    public void setUpdateDescription(String updateDescription) {
        this.updateDescription = updateDescription;
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