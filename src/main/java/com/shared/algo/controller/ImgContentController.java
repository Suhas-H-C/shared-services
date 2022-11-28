package com.shared.algo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.shared.algo.service.ImgContentService;

@RestController
@RequestMapping(value = "/img")
public class ImgContentController {

	@Autowired
	private ImgContentService imgContentService;

	@GetMapping(value = "/process")
	public String processImage(@RequestParam(name = "file", required = true) MultipartFile image,
			@RequestParam(name = "language", required = true) String lang) throws Exception {
		return imgContentService.processImage(image, lang);
	}

}
