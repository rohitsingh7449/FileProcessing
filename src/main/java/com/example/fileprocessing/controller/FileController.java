package com.example.fileprocessing.controller;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.fileprocessing.service.FileProcessingService;

@RestController
public class FileController {

	private static final Logger LOGGER=LoggerFactory.getLogger(FileController.class);
	
	@Autowired
	FileProcessingService fileProcessingService;
	@RequestMapping("/")
	public String healthcheck() {
		return "Service is running";
	}
	
	@RequestMapping(value ="/processFiles",method=RequestMethod.GET)
	public List<String> process(
			@RequestParam(value="path",required=true) List<String> filePaths){
		List<String> commonWords= new ArrayList<>();
		LOGGER.debug("List of Path Of Files : \n"+filePaths);
		System.out.println("List of Path Of Files : \n"+filePaths);
		
		commonWords= fileProcessingService.getCommonWordsFromFiles(filePaths);
		System.out.println("Common Words :"+commonWords);
		return commonWords;
	}
}
