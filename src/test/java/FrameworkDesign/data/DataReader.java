package FrameworkDesign.data;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.io.FileUtils;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

public class DataReader {
	
	public void getJsonData(String filePath) throws IOException {
		
		//read json to String 
		String jsonContent= FileUtils.readFileToString(new File(System.getProperty("user.dir")+"\\src\\test\\java\\FrameworkDesign\\data\\PurchaseOrderData.json"), StandardCharsets.UTF_8);
		
		//convert string to hashmap
		ObjectMapper objectMapper = new ObjectMapper();
		List<HashMap<String, String>> data= objectMapper.readValue(jsonContent, new TypeReference<List<HashMap<String, String>>>(){
			
		});
	}

}
