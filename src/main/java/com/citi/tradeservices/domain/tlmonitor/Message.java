package com.citi.tradeservices.domain.tlmonitor;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.xml.bind.annotation.XmlRootElement;

import org.springframework.hateoas.ResourceSupport;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonFormat.Shape;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name="TL_002_ERROR_LOG")
@XmlRootElement
@JsonIgnoreProperties(value = { "handler", "hibernateLazyInitializer" })
public class Message extends ResourceSupport implements Serializable {
	
	private static final long serialVersionUID = -5259542838404270586L;
	
	private String requestId;
	private Integer messageCode;
	private Long dealId;
	private Long bacenContractId;
	private Integer bacenContractYear;
	private Long fxContractId;
	private String messageDescription;
	private Date messageDate;
	
	@Id
	@Column(name="TL0002_REQ_ID", nullable=false)
	public String getRequestId() {
		return requestId;
	}
	public void setRequestId(String requestId) {
		this.requestId = requestId;
	}
	
	@Column(name="TL0002_COD_ERRO", nullable=false)
	public Integer getMessageCode() {
		return messageCode;
	}
	public void setMessageCode(Integer errorCode) {
		this.messageCode = errorCode;
	}
	
	@Column(name="TL0002_DEAL_PK")
	public Long getDealId() {
		return dealId;
	}
	public void setDealId(Long dealId) {
		this.dealId = dealId;
	}
	
	@Column(name="TL0002_BACEN_CONTRACT_PK")
	public Long getBacenContractId() {
		return bacenContractId;
	}
	public void setBacenContractId(Long bacenContractId) {
		this.bacenContractId = bacenContractId;
	}
	
	@Column(name="TL0002_BACEN_CONTRACT_YEAR")
	public Integer getBacenContractYear() {
		return bacenContractYear;
	}
	public void setBacenContractYear(Integer bacenContractYear) {
		this.bacenContractYear = bacenContractYear;
	}
	
	@Column(name="TL0002_CONTRATO_FX")
	public Long getFxContractId() {
		return fxContractId;
	}
	public void setFxContractId(Long fxContractId) {
		this.fxContractId = fxContractId;
	}
	
	@Column(name="TL0002_DESC_ERRO")
	public String getMessageDescription() {
		return messageDescription;
	}
	public void setMessageDescription(String errorDescription) {
		this.messageDescription = errorDescription;
	}
	
	@JsonFormat(locale="us", shape=Shape.STRING, pattern="MM/dd/yyyy HH:mm:ss")
	@Column(name="TL0002_DATA_ERRO")
	@Temporal(TemporalType.TIMESTAMP)
	public Date getMessageDate() {
		return messageDate;
	}
	public void setMessageDate(Date errorDate) {
		this.messageDate = errorDate;
	}
	
	@Transient
	public boolean getIsError() { 
		return this.messageCode != null && this.messageCode != 100;
	}
	


}
