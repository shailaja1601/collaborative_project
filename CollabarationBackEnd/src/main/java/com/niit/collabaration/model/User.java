package com.niit.collabaration.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

@Entity
@Table(name="COL_USER_DETAIL")
@Component
public class User extends BaseDomain{
  
	  @Id
	  @Column(name="USER_ID")
	  private String userId;
	  
	  @Column(name="NAME")
	  private String name;
	  
	  @Column(name="MAIL_ID")
	  private String mailID;
	  
	  @Column(name="PASSWORD")
	  private String password;
	  
	  @Column(name="ADDRESS")
	  private String address;
	  
	  @Column(name="MOBILE")
	  private String mobile;
	  
	  @Column(name="STATUS")
	  private char status;
	  
	  @Column(name="REASON")
	  private String reason;
	  
	  @Column(name="ROLE")
	  private String role;
	  
	  @Column(name="IS_ONLINE")
	  private char isOnline;
	
	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getMailID() {
		return mailID;
	}

	public void setMailID(String mailID) {
		this.mailID = mailID;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public char getStatus() {
		return status;
	}

	public void setStatus(char status) {
		this.status = status;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public char getIsOnline() {
		return isOnline;
	}

	public void setIsOnline(char isOnline) {
		this.isOnline = isOnline;
	}
    
	  
}
