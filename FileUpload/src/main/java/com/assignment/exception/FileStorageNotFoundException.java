package com.assignment.exception;

public class FileStorageNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 8513050868292419226L;

	public FileStorageNotFoundException(String msg) {
		super(msg);
	}

	public FileStorageNotFoundException(String msg, Exception ex) {
		super(msg, ex);
	}
}
