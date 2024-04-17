package com.tsfn.service;

import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import com.opencsv.exceptions.CsvValidationException;
import com.tsfn.model.InstagramFile;
import com.tsfn.model.LinkedInFile;
import com.tsfn.repository.LinkedInFileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.List;

@Service
public class CSVLinkedInFileService {

    @Autowired
    private LinkedInFileRepository linkedInFileRepository;

    public void processCsv(MultipartFile file) {
        try (Reader reader = new InputStreamReader(file.getInputStream());
             CSVReader csvReader = new CSVReaderBuilder(reader).withSkipLines(1).build()) {

            String[] nextRecord;
            while ((nextRecord = csvReader.readNext()) != null) {
                try {
                    LinkedInFile entity = new LinkedInFile();
                    entity.setPostTitle(nextRecord[0]);
                    entity.setPostLink(truncatePostTitle(nextRecord[1]));
                    entity.setPostType(nextRecord[2]);
                    entity.setCampaignName(nextRecord[3]);
                    entity.setPostedBy(nextRecord[4]);
                    entity.setCreatedDate(nextRecord[5]);
                    entity.setCampaignStartDate(nextRecord[6]);
                    entity.setCampaignEndDate(nextRecord[7]);
                    entity.setAudience(nextRecord[8]);
                    entity.setImpressions(Integer.parseInt(nextRecord[9]));
                    entity.setViewsExcludingOffsite(Integer.parseInt(nextRecord[10]));
                    entity.setOffsiteViews(Integer.parseInt(nextRecord[11]));
                    entity.setClicks(Integer.parseInt(nextRecord[12]));
                    entity.setClickThroughRate(Double.parseDouble(nextRecord[13]));
                    entity.setLikes(Integer.parseInt(nextRecord[14]));
                    entity.setComments(Integer.parseInt(nextRecord[15]));
                    entity.setReposts(Integer.parseInt(nextRecord[16]));
                    entity.setFollows(Integer.parseInt(nextRecord[17]));
                    entity.setEngagementRate(Double.parseDouble(nextRecord[18]));
                    entity.setContentType(nextRecord[19]);

                    linkedInFileRepository.save(entity);
                } catch (ArrayIndexOutOfBoundsException | NumberFormatException e) {
                    // Log the error or handle it appropriately
                    e.printStackTrace();
                }
            }
        } catch (IOException | CsvValidationException e) {
            // Log the error or handle it appropriately
            e.printStackTrace();
        }
    }

    public List<LinkedInFile> getAllLinkedInFiles() {
        return linkedInFileRepository.findAll();
    }
    
    private String truncatePostTitle(String PostTitle) {
        int maxLength = 255; // Maximum length allowed for the description column
        if (PostTitle.length() > maxLength) {
            return PostTitle.substring(0, maxLength);
        }
        return PostTitle;
    }
    public int deleteAllLinkedInFiles() {
    	// TODO Auto-generated method stub
		try {
            List<LinkedInFile> allFiles = linkedInFileRepository.findAll();
            int deletedCount = allFiles.size();
            linkedInFileRepository.deleteAll();
            return deletedCount;
        } catch (Exception e) {
            // Log the error or handle it appropriately
            e.printStackTrace();
            return 0; // Return 0 if an error occurs during deletion
        }
    }
}