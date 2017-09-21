package com.assignment.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.assignment.model.FileData;
import com.assignment.service.FileStorageService;

@Controller
public class FileUploadController {

	@Autowired
	private FileStorageService fileService;

	@GetMapping("/")
	public String getAllFiles(Model model) throws IOException {

		List<FileData> allFiles = fileService.getAllFiles();
		model.addAttribute("files", allFiles);
		return "uploadForm";
	}

	@GetMapping("/file/{name:.+}")
	@ResponseBody
	public ResponseEntity<?> getFile(@PathVariable String name) {
		try {
			File file = fileService.getFile(name);

			InputStreamResource resource = new InputStreamResource(new FileInputStream(file));
			return ResponseEntity.ok()
					.header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + file.getName() + "\"")
					.contentLength(file.length()).contentType(MediaType.parseMediaType("application/octet-stream"))
					.body(resource);
		} catch (Exception e) {
			return ResponseEntity.ok().body("Invalid file");
		}
	}

	@PostMapping("/")
	public String saveFile(@RequestParam("file") MultipartFile file, RedirectAttributes redirectAttributes) {

		fileService.saveFile(file);
		redirectAttributes.addFlashAttribute("message", "File uploaded successfully" + file.getOriginalFilename());

		return "redirect:/";
	}
}