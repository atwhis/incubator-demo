package com.ymchen.incubatordemo.examples.jdk8feature.interview;

public class Extension {
	private String firstName;
	private String lastName;
	private String ext;
	private String extType;

	public Extension(String firstName, String lastName, String ext) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.ext = ext;
	}

	public Extension(String extType) {
		this.extType = extType;
	}

	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getExt() {
		return ext;
	}
	public void setExt(String ext) {
		this.ext = ext;
	}
	public String getExtType() {
		return extType;
	}
	public void setExtType(String extType) {
		this.extType = extType;
	}

	@Override
	public String toString() {
		return "firstName:" + this.firstName + ",lastName:" + this.lastName + ",ext:" + this.ext + ",extType:" + this.extType;
	}

}
