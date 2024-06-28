package com.training.Utilities;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class PropertyUtility {
	
	public static String readDataFromFile(String path, String key) {
		File file=new File(path);
		FileInputStream fileInput=null;
		Properties property=null;
		String data=null;
		
		try {
			fileInput=new FileInputStream(file);
			property=new Properties();
			property.load(fileInput);
			data=property.getProperty(key);
			fileInput.close();
		}
		catch(FileNotFoundException e) {
			System.out.println("-------There is some error in the file path-----");
			e.printStackTrace();
		}
		catch(IOException e) {
			System.out.println("----Error while loading file property-----");
			e.printStackTrace();
		}
		return data;
	}

}
