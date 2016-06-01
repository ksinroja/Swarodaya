package com.swarodaya.vo;

import java.io.Serializable;


public class ResultObject implements Serializable {
	
	private long m_nResponseCode;
	
	private Object m_objResponse;
	
	private Throwable m_exception;
	
	private static final long serialVersionUID = 1L;
		
	private String responseMessage;
		
	public ResultObject(){
	}
	
	public ResultObject(long responseCode,Object obj){
		this.m_nResponseCode = responseCode;
		this.m_objResponse = obj;
	}
	
	public void setException(Throwable exception)
	{
		this.m_exception = exception;
	}
	
	public Throwable getException()
	{
		return( this.m_exception );
	}
	
	public void setResponseCode(long nResponseCode) {
		this.m_nResponseCode = nResponseCode;
	}
	
	public void setResponseObject(Object objResponse) {
		this.m_objResponse = objResponse;
	}
	
	public long getResponseCode() {
		return this.m_nResponseCode;
	}
	
	public Object getResponseObject() {
		return m_objResponse;
	}
	
	public boolean isError() {
		if( this.m_nResponseCode >= 0 )
			return false;
		else
			return true;
	}
	
	public void setResponseMessage(String responseMessage){
		this.responseMessage = responseMessage;
	}
	
	public String getResponseMessage(){
		return this.responseMessage;
	}
	
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	public String toString(){
		
		StringBuilder strResultObject = new StringBuilder();
		strResultObject = strResultObject.append("ResultObject [responceCode=").append(m_nResponseCode).append(",responseObject=").append(m_objResponse);
		strResultObject = strResultObject.append(",exception=").append(m_exception).append(",responseMessage=").append(responseMessage);
		strResultObject = strResultObject.append("]");
		
		return strResultObject.toString();
	}
}

