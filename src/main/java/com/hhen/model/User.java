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

	private int Id;
	private String Account;
	private String Password;
	private String Name;
	private int Type;
	private String Phone;
	private String Avatar;
	private int Wxinfo_id;
	private Date Updated;
	private Date Created;

	public int getId() {
		return Id;
	}

	public void setId(int id) {
		Id = id;
	}

	public String getAccount() {
		return Account;
	}

	public void setAccount(String account) {
		Account = account;
	}


	public String getPassword() {
		return Password;
	}

	public void setPassword(String password) {
		Password = password;
	}

	public String getName() {
		return Name;
	}

	public void setName(String name) {
		Name = name;
	}

	public int getType() {
		return Type;
	}

	public void setType(int type) {
		Type = type;
	}

	public String getPhone() {
		return Phone;
	}

	public void setPhone(String phone) {
		Phone = phone;
	}

	public String getAvatar() {
		return Avatar;
	}

	public void setAvatar(String avatar) {
		Avatar = avatar;
	}

	public int getWxinfo_id() {
		return Wxinfo_id;
	}

	public void setWxinfo_id(int wxinfo_id) {
		Wxinfo_id = wxinfo_id;
	}

	public Date getUpdated() {
		return Updated;
	}

	public void setUpdated(Date updated) {
		Updated = updated;
	}

	public Date getCreated() {
		return Created;
	}

	public void setCreated(Date created) {
		Created = created;
	}

	@Override
	public String toString() {
		return "User{" +
				"Id=" + Id +
				", Account='" + Account + '\'' +
				", Password='" + Password + '\'' +
				", Name='" + Name + '\'' +
				", Type=" + Type +
				", Phone='" + Phone + '\'' +
				", Avatar='" + Avatar + '\'' +
				", Wxinfo_id=" + Wxinfo_id +
				", Updated=" + Updated +
				", Created=" + Created +
				'}';
	}
}
