package com.amwater.microservices.demo;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlRootElement;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
@XmlRootElement(name = "response")
public class IpAddress implements Serializable {
	private static final long serialVersionUID = 1L;

	private String ip;
	@JsonIgnore
	private String extraUnusedField;

    public IpAddress() {
    }
    
    public IpAddress(String ip) {
    	this.ip = ip;
    	this.extraUnusedField = null;
    }
    
	public String getExtraUnusedField() {
		return extraUnusedField;
	}

	public void setExtraUnusedField(String extraUnusedField) {
		this.extraUnusedField = extraUnusedField;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}
}
