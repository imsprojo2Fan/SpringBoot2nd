package com.hhen.model;

import java.io.Serializable;
import java.sql.Date;

/**
 * @Author: imsprojo2Fan
 * @Description:
 * @Date: Created in 9:29 2018/7/2
 * @Modified By:
 */
public class User implements Serializable {

	private static final long serialVersionUID = 1L;

	private int id;
	private String account;
	private String password;
	private String name;
	private int type;
	private String phone;
	private String avatar;
	private int wxinfo_id;
	private Date updated;
	private Date created;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getAvatar() {
		return avatar;
	}

	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}

	public int getWxinfo_id() {
		return wxinfo_id;
	}

	public void setWxinfo_id(int wxinfo_id) {
		this.wxinfo_id = wxinfo_id;
	}

	public Date getUpdated() {
		return updated;
	}

	public void setUpdated(Date updated) {
		this.updated = updated;
	}

	public Date getCreated() {
		return created;
	}

	public void setCreated(Date created) {
		this.created = created;
	}

	@Override
	public String toString() {
		return "User{" +
				"id=" + id +
				", account='" + account + '\'' +
				", password='" + password + '\'' +
				", name='" + name + '\'' +
				", type=" + type +
				", phone='" + phone + '\'' +
				", avatar='" + avatar + '\'' +
				", wxinfo_id=" + wxinfo_id +
				", updated=" + updated +
				", created=" + created +
				'}';
	}
}
