package com.citi.tradeservices.domain.tlmonitor;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonFormat.Shape;

@Entity
@Table(name="TL_003_LOG")
public class MessageDetails implements Serializable {
	
	private static final long serialVersionUID = 1948424088446836126L;
	
	private String requestId;
	private Date messageDate;
	private String messageType;
	private String messageContent;
	
	@Id
	@Column(name="TL0003_REQ_ID", nullable=false)
	public String getRequestId() {
		return requestId;
	}
	public void setRequestId(String requestId) {
		this.requestId = requestId;
	}
	
	@JsonFormat(locale="us", shape=Shape.STRING, pattern="MM/dd/yyyy HH:mm:ss")
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="TL0003_DATA_LOG")
	public Date getMessageDate() {
		return messageDate;
	}
	public void setMessageDate(Date messageDate) {
		this.messageDate = messageDate;
	}
	
	@Column(name="TL0003_TYPE")
	public String getMessageType() {
		return messageType;
	}
	public void setMessageType(String messageType) {
		this.messageType = messageType;
	}
	
	@Column(name="TL0003_CONTENT")
	public String getMessageContent() {
		return messageContent;
	}
	public void setMessageContent(String messageContent) {
		this.messageContent = messageContent;
	}
	
	
	

}
