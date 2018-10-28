package com.example.fileprocessing.service;

import java.util.List;

public interface FileProcessingService {

	public List<String> getCommonWordsFromFiles(List<String> filePaths);
}
