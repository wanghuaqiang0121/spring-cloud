package com.server.users.security.entity;

import java.util.Date;

import javax.validation.constraints.NotBlank;


/**
 * Copyright © 2018 Fist Team. All rights reserved.
 *
 * @author: LiuGangQiang
 * @date: 2018年12月19日
 * @description: 用户
 */
public class User {

	private Integer id;
	@NotBlank(message = "{user.name.not.blank}", groups = { Login.class })
	private String name;
	@NotBlank(message = "{user.password.not.blank}", groups = { Login.class })
	private String password;
	private Integer sex;
	private String idCard;
	private Date birthday;
	private String phone;
	private String province;
	private String coty;
	private String area;
	private String street;
	private Double lng;
	private Double lat;
	private String address;
	private Date createDate;
	private Date updateDate;
	private String remark;

	public interface Login {
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Integer getSex() {
		return sex;
	}

	public void setSex(Integer sex) {
		this.sex = sex;
	}

	public String getIdCard() {
		return idCard;
	}

	public void setIdCard(String idCard) {
		this.idCard = idCard;
	}

	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public String getCoty() {
		return coty;
	}

	public void setCoty(String coty) {
		this.coty = coty;
	}

	public String getArea() {
		return area;
	}

	public void setArea(String area) {
		this.area = area;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public Double getLng() {
		return lng;
	}

	public void setLng(Double lng) {
		this.lng = lng;
	}

	public Double getLat() {
		return lat;
	}

	public void setLat(Double lat) {
		this.lat = lat;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public Date getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

}
