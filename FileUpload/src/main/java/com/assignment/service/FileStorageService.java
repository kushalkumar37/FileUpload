package com.assignment.service;

import java.io.File;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.assignment.model.FileData;

public interface FileStorageService {

	void saveFile(MultipartFile file);

	List<FileData> getAllFiles();

	File getFile(String name);

	void createRootDir();

	void deleteAllFiles();

}