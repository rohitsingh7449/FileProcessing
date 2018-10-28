package com.example.fileprocessing.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.ResourceUtils;

public class FileUtility {

	private static final Logger LOGGER=LoggerFactory.getLogger(FileUtility.class);
	
	public static String readFile(String path) {
		String output="";
		LOGGER.debug("Path of File : "+path);
		try {
			File file= ResourceUtils.getFile(path);
			InputStream in= new FileInputStream(file);
			output=readFromInputStream(in);
		}catch(IOException e) {
			LOGGER.error("Error while Reading File");
		}
		return output;
	}
	
	private static String readFromInputStream(InputStream inputStream)
			  throws IOException {
			    StringBuilder resultStringBuilder = new StringBuilder();
			    try (BufferedReader br
			      = new BufferedReader(new InputStreamReader(inputStream))) {
			        String line;
			        while ((line = br.readLine()) != null) {
			            resultStringBuilder.append(line).append("\n");
			        }
			    }
			  return resultStringBuilder.toString();
	}
	
	public static void main(String args[]) {
		String path="classpath:hello.txt";
		String output=FileUtility.readFile(path);
		System.out.println("Content of File : \n"+output);
	}
}
