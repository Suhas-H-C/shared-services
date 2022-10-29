package com.shared.algo.model;

import com.shared.algo.annotations.FiledHeaderConfig;

public class IpData {

	@FiledHeaderConfig(header = "Id")
	private int id;
	@FiledHeaderConfig(header = "First Name")
	private String first_name;
	@FiledHeaderConfig(header = "Last Name")
	private String last_name;
	@FiledHeaderConfig(header = "Email Id")
	private String email;
	@FiledHeaderConfig(header = "Gender")
	private String gender;
	@FiledHeaderConfig(header = "Ip Address")
	private String ip_address;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getFirst_name() {
		return first_name;
	}

	public void setFirst_name(String first_name) {
		this.first_name = first_name;
	}

	public String getLast_name() {
		return last_name;
	}

	public void setLast_name(String last_name) {
		this.last_name = last_name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getIp_address() {
		return ip_address;
	}

	public void setIp_address(String ip_address) {
		this.ip_address = ip_address;
	}

	public IpData(int id, String first_name, String last_name, String email, String gender, String ip_address) {
		super();
		this.id = id;
		this.first_name = first_name;
		this.last_name = last_name;
		this.email = email;
		this.gender = gender;
		this.ip_address = ip_address;
	}

	public IpData() {
	}

	@Override
	public String toString() {
		return "IpData [id=" + id + ", first_name=" + first_name + ", last_name=" + last_name + ", email=" + email
				+ ", gender=" + gender + ", ip_address=" + ip_address + "]";
	}
}
