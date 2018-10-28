package com.example.fileprocessing.service.impl;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.example.fileprocessing.service.FileProcessingService;
import com.example.fileprocessing.util.FileUtility;

@Service
public class FileProcessingServiceImpl implements FileProcessingService{

	private static final Logger LOGGER=LoggerFactory.getLogger(FileProcessingServiceImpl.class);
	@Override
	public List<String> getCommonWordsFromFiles(List<String> filePaths) {
		
		List<Map> listOfFileMaps = new ArrayList<>();
		for(String file : filePaths) {
			HashMap map=findMapOfWords(FileUtility.readFile(file));
			LOGGER.debug("Word Map of File : "+file+" is : "+map);
			listOfFileMaps.add(map);
		}
		
		//iterate all the map store the string into list which are common in all maps
		List<String> commonWords=findAllCommon(listOfFileMaps);
	
		return commonWords;
	}

	private List<String> findAllCommon(List<Map> listOfFileMaps) {
		List<String> list=new ArrayList<>();
		Set<String> set=listOfFileMaps.get(0).keySet();
		
		for(String key:set) {
			boolean flag= false;
			for(int i=1; i<listOfFileMaps.size();i++) {
				if(listOfFileMaps.get(i).containsKey(key)) {
					flag=true;
				}else {
					flag=false;
					break;
				}
			}
			if(flag) {
				list.add(key);
			}
			
		}
		// TODO Auto-generated method stub
		return list;
	}
	
	
	private HashMap findMapOfWords(String file) {
		HashMap<String ,Integer> map=new HashMap<>();
		Scanner sc;
		sc = new Scanner(file);
		while(sc.hasNextLine()) {
			String x=sc.nextLine();
			String arr[] =x.split(" ");
			//StringBuffer sf=new StringBuffer();
			//yourString.replaceAll("[-+.^:,]","")
			for(int i=0;i<arr.length;i++) {
				String str=arr[i];
				StringBuffer sf = new StringBuffer();
				// remove special character from String 
				for(int j=0;j<str.length();j++) {
					if((str.charAt(j)>=65 && str.charAt(j)<=90) ||  (str.charAt(j)>=97 && str.charAt(j)<=122)) {
						sf.append(str.charAt(j));
					}
					else {
						continue;
					}
				}
				if(!map.containsKey(sf.toString())) {
					map.put(sf.toString().toLowerCase(), 1);
				}
				else {
					map.put(sf.toString().toLowerCase(), map.get(sf.toString())+1);					
				}
			}
		}
		return map;
	}
}
