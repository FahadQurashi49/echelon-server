package com.echelon.response;

public class Response<T> implements StatusCodes{
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
	public Response(Integer statusCode, String message, T data) {
		this.statusCode = statusCode;
		this.message = message;
		this.data = data;
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
