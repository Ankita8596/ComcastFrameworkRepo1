package com.comcast.crm.generic.fileutility;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class FileUtility {
	public String getDataFromProertyFile(String Key) throws IOException {
		FileInputStream fis = new FileInputStream("./configAppData/commanData.properties");
		Properties prop = new Properties();
		prop.load(fis);
		String data = prop.getProperty(Key);
		return data;
		
	}

}
