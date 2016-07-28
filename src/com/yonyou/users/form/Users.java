package com.yonyou.users.form;
// default package

import com.yonyou.users.form.IdeasDiscussion;
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
import javax.persistence.OneToMany;
import javax.persistence.Table;


/**
 * UsersForm entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name="users"
    ,catalog="innoshare"
)

public class Users  implements java.io.Serializable {


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
     private Set<Ideas> ideasForms = new HashSet<Ideas>(0);
     private Set<Projects> projectsForms = new HashSet<Projects>(0);
     private Set<IdeasDiscussion> ideasdiscussions = new HashSet<IdeasDiscussion>(0);
     private Set<IdeasUpdates> ideasupdateses = new HashSet<IdeasUpdates>(0);


    // Constructors

    /** default constructor */
    public Users() {
    }

    
    /** full constructor */
    public Users(String password, String userName, Integer deleteFlag, String mobilePhone, String userEmail, String userAccount, String lastLoginType, String chanelId, String pushId, String country, Date createTime, Date lastUpdateTime, String portraitUrl, Set<Ideas> ideasForms, Set<Projects> projectsForms, Set<IdeasDiscussion> ideasdiscussions, Set<IdeasUpdates> ideasupdateses) {
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
        this.ideasForms = ideasForms;
        this.projectsForms = projectsForms;
        this.ideasdiscussions = ideasdiscussions;
        this.ideasupdateses = ideasupdateses;
    }

   
    // Property accessors
    @Id @GeneratedValue(strategy=IDENTITY)
    
    @Column(name="userID", unique=true, nullable=false)

    public Integer getUserId() {
        return this.userId;
    }
    
    public void setUserId(Integer userId) {
        this.userId = userId;
    }
    
    @Column(name="password", length=32)

    public String getPassword() {
        return this.password;
    }
    
    public void setPassword(String password) {
        this.password = password;
    }
    
    @Column(name="userName", length=32)

    public String getUserName() {
        return this.userName;
    }
    
    public void setUserName(String userName) {
        this.userName = userName;
    }
    
    @Column(name="deleteFlag")

    public Integer getDeleteFlag() {
        return this.deleteFlag;
    }
    
    public void setDeleteFlag(Integer deleteFlag) {
        this.deleteFlag = deleteFlag;
    }
    
    @Column(name="mobilePhone", length=16)

    public String getMobilePhone() {
        return this.mobilePhone;
    }
    
    public void setMobilePhone(String mobilePhone) {
        this.mobilePhone = mobilePhone;
    }
    
    @Column(name="userEmail")

    public String getUserEmail() {
        return this.userEmail;
    }
    
    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }
    
    @Column(name="userAccount", length=32)

    public String getUserAccount() {
        return this.userAccount;
    }
    
    public void setUserAccount(String userAccount) {
        this.userAccount = userAccount;
    }
    
    @Column(name="lastLoginType", length=16)

    public String getLastLoginType() {
        return this.lastLoginType;
    }
    
    public void setLastLoginType(String lastLoginType) {
        this.lastLoginType = lastLoginType;
    }
    
    @Column(name="chanelID", length=32)

    public String getChanelId() {
        return this.chanelId;
    }
    
    public void setChanelId(String chanelId) {
        this.chanelId = chanelId;
    }
    
    @Column(name="pushID", length=32)

    public String getPushId() {
        return this.pushId;
    }
    
    public void setPushId(String pushId) {
        this.pushId = pushId;
    }
    
    @Column(name="country", length=16)

    public String getCountry() {
        return this.country;
    }
    
    public void setCountry(String country) {
        this.country = country;
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
    
    @Column(name="portraitURL")

    public String getPortraitUrl() {
        return this.portraitUrl;
    }
    
    public void setPortraitUrl(String portraitUrl) {
        this.portraitUrl = portraitUrl;
    }
@OneToMany(cascade=CascadeType.ALL, fetch=FetchType.LAZY, mappedBy="usersForm")

    public Set<Ideas> getIdeasForms() {
        return this.ideasForms;
    }
    
    public void setIdeasForms(Set<Ideas> ideasForms) {
        this.ideasForms = ideasForms;
    }
@OneToMany(cascade=CascadeType.ALL, fetch=FetchType.LAZY, mappedBy="usersForm")

    public Set<Projects> getProjectsForms() {
        return this.projectsForms;
    }
    
    public void setProjectsForms(Set<Projects> projectsForms) {
        this.projectsForms = projectsForms;
    }
@OneToMany(cascade=CascadeType.ALL, fetch=FetchType.LAZY, mappedBy="usersForm")

    public Set<IdeasDiscussion> getIdeasdiscussions() {
        return this.ideasdiscussions;
    }
    
    public void setIdeasdiscussions(Set<IdeasDiscussion> ideasdiscussions) {
        this.ideasdiscussions = ideasdiscussions;
    }
@OneToMany(cascade=CascadeType.ALL, fetch=FetchType.LAZY, mappedBy="usersForm")

    public Set<IdeasUpdates> getIdeasupdateses() {
        return this.ideasupdateses;
    }
    
    public void setIdeasupdateses(Set<IdeasUpdates> ideasupdateses) {
        this.ideasupdateses = ideasupdateses;
    }
   








}