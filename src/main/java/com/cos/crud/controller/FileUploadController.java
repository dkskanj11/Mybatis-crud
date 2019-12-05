package com.cos.crud.controller;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.cos.crud.model.User;
import com.cos.crud.repository.UserRepository;

@Controller
public class FileUploadController {
	
	@Autowired
	UserRepository uRepo;
	@Value("${file.path}")
	private String resourcePath;
	
	@PostMapping("/file/upload/{id}")
	public String imageUpload(HttpServletRequest request, @PathVariable int id, @RequestParam("userProfile") MultipartFile file) {
	
	System.out.println("=====진입");
	
	UUID uuid = UUID.randomUUID();
	String uuidFileName = uuid+"_"+file.getOriginalFilename();
	
	Path filePath = Paths.get(resourcePath+uuidFileName);
	System.out.println("filePath : "+filePath);
	
	try {
		Files.write(filePath, file.getBytes());
		
		String profile = "/userProfile/" + uuidFileName; //filePath.toString();
		
		uRepo.update(profile, id);
		
		HttpSession session = request.getSession();
		User newUser = (User)session.getAttribute("user");
		
		newUser.setProfile(profile);
		
	} catch (Exception e) {
		e.printStackTrace();
	}
			return "redirect:/";
	}
}
