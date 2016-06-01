package com.swarodaya.vo;

import java.util.Date;

public class PaymentVO {

	private long userId;
	private long paymentId;
	private String paymentstatus;
	private long amount;
	private String paymentMode;
	private String modeDetails;
	private Date paymentDate;
	private Object receipt;
	private String receiptsent;
	
	public long getUserId() {
		return userId;
	}
	public void setUserId(long userId) {
		this.userId = userId;
	}
	public long getPaymentId() {
		return paymentId;
	}
	public void setPaymentId(long paymentId) {
		this.paymentId = paymentId;
	}
	public String getPaymentstatus() {
		return paymentstatus;
	}
	public void setPaymentstatus(String paymentstatus) {
		this.paymentstatus = paymentstatus;
	}
	public long getAmount() {
		return amount;
	}
	public void setAmount(long amount) {
		this.amount = amount;
	}
	public String getPaymentMode() {
		return paymentMode;
	}
	public void setPaymentMode(String paymentMode) {
		this.paymentMode = paymentMode;
	}
	public String getModeDetails() {
		return modeDetails;
	}
	public void setModeDetails(String modeDetails) {
		this.modeDetails = modeDetails;
	}
	public Date getPaymentDate() {
		return paymentDate;
	}
	public void setPaymentDate(Date paymentDate) {
		this.paymentDate = paymentDate;
	}
	public Object getReceipt() {
		return receipt;
	}
	public void setReceipt(Object receipt) {
		this.receipt = receipt;
	}
	public String getReceiptsent() {
		return receiptsent;
	}
	public void setReceiptsent(String receiptsent) {
		this.receiptsent = receiptsent;
	}
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("PaymentVO [userId=");
		builder.append(userId);
		builder.append(", paymentId=");
		builder.append(paymentId);
		builder.append(", paymentstatus=");
		builder.append(paymentstatus);
		builder.append(", amount=");
		builder.append(amount);
		builder.append(", paymentMode=");
		builder.append(paymentMode);
		builder.append(", modeDetails=");
		builder.append(modeDetails);
		builder.append(", paymentDate=");
		builder.append(paymentDate);
		builder.append(", receipt=");
		builder.append(receipt);
		builder.append(", receiptsent=");
		builder.append(receiptsent);
		builder.append("]");
		return builder.toString();
	}
	
	
}
