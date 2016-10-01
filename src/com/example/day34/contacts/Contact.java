package com.example.day34.contacts;

import android.app.Activity;

public class Contact extends Activity {
	private String name;
	private String phone;
	private String email;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public Contact(String name, String phone, String email) {
		super();
		this.name = name;
		this.phone = phone;
		this.email = email;
	}
	public Contact() {
		super();
	}
	@Override
	public String toString() {
		return "Contact [name=" + name + ", phone=" + phone + ", email="
				+ email + "]";
	}
	
}
