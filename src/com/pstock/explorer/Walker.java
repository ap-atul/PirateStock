package com.pstock.explorer;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;

import com.pstock.config.Constants;

public class Walker {

	private ArrayList<String> folders = null;
	private ArrayList<String> files = null;
	private String rootDir = Constants.rootDir;
	private String rootPath = null;

	public ArrayList<String> getData(String path) {
		if(path != null)
			this.rootPath = this.rootDir + path;
		else 
			this.rootPath = this.rootDir;
		
		System.out.println(this.rootPath);
		ArrayList<String> output = new ArrayList<String>();

		getFoldersFiles();
		output = sortData();
		return output;
	}

	private void getFoldersFiles() {
		folders = new ArrayList<String>();
		files = new ArrayList<String>();

		File start = new File(this.rootPath);
		File[] walks = start.listFiles();

		if(walks != null) {
			for (File file : walks) {

				// skipping the dot files
				if(file.getName().charAt(0) == '.')
					continue;
	
				if (file.isDirectory()) {
					folders.add(file.getName());
				} else {
					files.add(file.getName());
				}
	
			}
		}
	}

	private ArrayList<String> sortData() {
		ArrayList<String> output = null;

		if (folders != null && files != null) {

			output = new ArrayList<String>(folders.size() + files.size());

			Collections.sort(folders);
			Collections.sort(files);

			output.addAll(folders);
			output.addAll(files);

		}

		return output;
	}
	
	public File isFile(String path) {
		this.rootPath = this.rootDir + path;
		File file = new File(this.rootPath);
		if(file.isFile())
			return file;
		
		return null;
	}

}
