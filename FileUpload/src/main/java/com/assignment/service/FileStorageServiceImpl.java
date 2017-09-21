package com.assignment.service;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.assignment.exception.FileStorageException;
import com.assignment.exception.FileStorageNotFoundException;
import com.assignment.model.FileData;

@Service
public class FileStorageServiceImpl implements FileStorageService {

	private List<FileData> storageList = new ArrayList<FileData>();
	private String storagePath = "uploaded-files";

	@Override
	public void saveFile(MultipartFile file) {
		String name = file.getOriginalFilename();
		try {

			Files.copy(file.getInputStream(), Paths.get(storagePath, name), StandardCopyOption.REPLACE_EXISTING);
			FileData data = new FileData();
			if (name.indexOf("\\.") != -1) {
				data.setName(name.split("\\.")[0]);
				data.setExtension(name.split("\\.")[1]);
			} else {
				data.setName(name);
				data.setExtension("");
			}
			data.setSize(file.getSize());
			data.setContentType(file.getContentType());
			storageList.add(data);
		} catch (Exception excep) {
			throw new FileStorageException("Error while saving file : " + name, excep);
		}
	}

	@Override
	public File getFile(String name) {
		try {
			File file = new File(storagePath + "/" + name);

			if (file.exists()) {
				return file;
			} else {
				throw new FileStorageNotFoundException("File does not exist : " + name);
			}
		} catch (Exception ex) {
			throw new FileStorageNotFoundException("Exception while reading file : " + name, ex);
		}
	}

	@Override
	public List<FileData> getAllFiles() {
		return storageList;
	}

	@Override
	public void createRootDir() {
		File dir = new File(storagePath);
		dir.mkdir();
	}

	@Override
	public void deleteAllFiles() {
		File path = new File(storagePath);

		if (path != null && path.exists()) {
			File[] listFiles = path.listFiles();
			for (File file : listFiles) {
				file.delete();
			}
		}
		storageList = new ArrayList<FileData>();
	}
}