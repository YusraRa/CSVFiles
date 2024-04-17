package com.tsfn.helper;

import com.tsfn.model.InstagramFile;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.springframework.web.multipart.MultipartFile;

public class CSVHelper {
	public static String TYPE = "text/csv";
	  static String[] HEADERs = { "Post ID", "Account ID", "Account username", "Account name", 
			  "Description", "Duration", "Publish time", "Permalink","Post type", "Data comment", 
			  "Date", "Impressions","Reach", "Shares", "Likes", "Comments","Saves", "Plays"};
	  // I need to read about MulipartFile
	  public static boolean hasCSVFormat(MultipartFile file) {

	    if (!TYPE.equals(file.getContentType())) {
	      return false;
	    }

	    return true;
	  }

	  public static List<InstagramFile> csvToInstagramFiles(InputStream is) {
	    try (BufferedReader fileReader = new BufferedReader(new InputStreamReader(is, "UTF-8"));
	        CSVParser csvParser = new CSVParser(fileReader,
	            CSVFormat.DEFAULT.withFirstRecordAsHeader().withIgnoreHeaderCase().withTrim());) {

	      List<InstagramFile> instagramFiles = new ArrayList<InstagramFile>();

	      Iterable<CSVRecord> csvRecords = csvParser.getRecords();

	      for (CSVRecord csvRecord : csvRecords) {
	    	  InstagramFile instagramFile = new InstagramFile();
	    	  instagramFile.setPostId(Double.parseDouble(csvRecord.get("Post ID")));
              instagramFile.setAccountId(Double.parseDouble(csvRecord.get("Account ID")));
              instagramFile.setAccountUsername(csvRecord.get("Account username"));
              instagramFile.setAccountName(csvRecord.get("Account name"));
              instagramFile.setDescription(csvRecord.get("Description"));
              instagramFile.setDuration(Integer.parseInt(csvRecord.get("Duration (sec)")));
              instagramFile.setPublishTime(csvRecord.get("Publish time"));
              instagramFile.setPermalink(csvRecord.get("Permalink"));
              instagramFile.setPostType(csvRecord.get("Post type"));
              instagramFile.setDataComment(csvRecord.get("Data comment"));
              instagramFile.setDate(csvRecord.get("Date"));
              instagramFile.setImpressions(Integer.parseInt(csvRecord.get("Impressions")));
              instagramFile.setReach(Integer.parseInt(csvRecord.get("Reach")));
              instagramFile.setShares(Integer.parseInt(csvRecord.get("Shares")));
              instagramFile.setLikes(Integer.parseInt(csvRecord.get("Likes")));
              instagramFile.setComments(Integer.parseInt(csvRecord.get("Comments")));
              instagramFile.setSaves(Integer.parseInt(csvRecord.get("Saves")));
              instagramFile.setPlays(Integer.parseInt(csvRecord.get("Plays")));

				/*
				 * Integer.parseInt(csvRecord.get("Post ID")),
				 * Integer.parseInt(csvRecord.get("Account ID")),
				 * csvRecord.get("Account username"), csvRecord.get("Account name"),
				 * csvRecord.get("Description"), Integer.parseInt(csvRecord.get("Duration")),
				 * csvRecord.get("Publish time"), csvRecord.get("Permalink"),
				 * csvRecord.get("Post type"), csvRecord.get("Data comment"),
				 * csvRecord.get("Date"), Integer.parseInt(csvRecord.get("Impressions")),
				 * Integer.parseInt(csvRecord.get("Reach")),
				 * Integer.parseInt(csvRecord.get("Shares")),
				 * Integer.parseInt(csvRecord.get("Likes")),
				 * Integer.parseInt(csvRecord.get("Comments")),
				 * Integer.parseInt(csvRecord.get("Saves")),
				 * Integer.parseInt(csvRecord.get("Plays")));
				 */
	    	  instagramFiles.add(instagramFile);
	      }
	     
	      csvParser.close();
	      fileReader.close();
	      return instagramFiles;
	    } catch (IOException e) {
	      throw new RuntimeException("fail to parse CSV file: " + e.getMessage());
	    }
	  }

}
