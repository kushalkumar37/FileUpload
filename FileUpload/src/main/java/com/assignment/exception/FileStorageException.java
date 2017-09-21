package com.assignment.exception;

public class FileStorageException extends RuntimeException {

	private static final long serialVersionUID = 8513050868292419226L;

	public FileStorageException(String msg) {
		super(msg);
	}

	public FileStorageException(String msg, Exception ex) {
		super(msg, ex);
	}
}
