package com.echelon.response;

public class Response<T> implements StatusCodes{
	private static final String SUCCESS_MSG = "success";

	private Integer statusCode;
	private String message;
	private T data;
	
	public Response() {
		this.statusCode = OK;
	}
	public Response(String message, T data) {
		this.statusCode = OK;
		this.message = message;
		this.data = data;
	}
	public Response(Integer statusCode, String message) {
		this.statusCode = statusCode;
		this.message = message;		
	}
	public Response(Integer statusCode, String message, T data) {
		this.statusCode = statusCode;
		this.message = message;
		this.data = data;
	}
	public Response(Integer statusCode){
		this.statusCode = statusCode;
		if (this.statusCode.equals(NOT_FOUND)) {
			this.message = "Resource not found";
		}
	}
	public Response(T data) {
		this.data = data;
		if (data == null) {
			this.message = "Resource not found";
			this.statusCode = NOT_FOUND;
		} else {
			this.message = SUCCESS_MSG;
			this.statusCode = OK;
		}
	}

	
	public Integer getStatusCode() {
		return statusCode;
	}
	public void setStatusCode(Integer statusCode) {
		this.statusCode = statusCode;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public T getData() {
		return data;
	}
	public void setData(T data) {
		this.data = data;
	}
	@Override
	public String toString() {
		return "Response [statusCode=" + statusCode + ", message=" + message + ", data=" + data + "]";
	}
	
}
