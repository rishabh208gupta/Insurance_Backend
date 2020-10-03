package com.lti.dto;

public class StatusDto {
	private StatusType status;
    
    public static enum StatusType{
        Success,Failure;
    }
    public StatusType getStatus() {
        return status;
    }
    public void setStatus(StatusType status) {
        this.status = status;
    }
	

}
