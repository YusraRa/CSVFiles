package com.tsfn.service;

/*public class ServiceCSV {

}*/


import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import com.opencsv.exceptions.CsvValidationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.tsfn.model.InstagramFile;
import com.tsfn.repository.InstagramFileRepository;


import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.List;

@Service
public class CSVInstagramFileService {

    @Autowired
    private InstagramFileRepository InstaFileRepo;

    public void processCsvInstagramFile(MultipartFile file) {
        try (Reader reader = new InputStreamReader(file.getInputStream());
             CSVReader csvReader = new CSVReaderBuilder(reader).withSkipLines(1).build()) {
            
            String[] nextRecord;
            while ((nextRecord = csvReader.readNext()) != null) {
                try {
                	InstagramFile entity = new InstagramFile();
                	entity.setPostId(Double.parseDouble(nextRecord[0]));
                	entity.setAccountId(Double.parseDouble(nextRecord[1]));
                	entity.setAccountUsername(nextRecord[2]);
                	entity.setAccountName(nextRecord[3]);
                	entity.setDescription(truncateDescription(nextRecord[4]));
                	entity.setDuration(Integer.parseInt(nextRecord[5]));
                	entity.setPublishTime(nextRecord[6]);
                	entity.setPermalink(nextRecord[7]);
                	entity.setPostType(nextRecord[8]);
                	entity.setDataComment(nextRecord[9]);
                	entity.setDate(nextRecord[10]);
                	entity.setImpressions(Integer.parseInt(nextRecord[11]));
                	entity.setReach(Integer.parseInt(nextRecord[12]));
                	entity.setShares(Integer.parseInt(nextRecord[13]));
                	entity.setLikes(Integer.parseInt(nextRecord[14]));
                	entity.setComments(Integer.parseInt(nextRecord[15]));
                	entity.setSaves(Integer.parseInt(nextRecord[16]));
                	entity.setPlays(Integer.parseInt(nextRecord[17]));


                    InstaFileRepo.save(entity);
                } catch (ArrayIndexOutOfBoundsException | IllegalArgumentException e) {
                    // Log the error or handle it appropriately
                    e.printStackTrace();
                }
            }
        } catch (IOException | CsvValidationException e) {
            // Log the error or handle it appropriately
            e.printStackTrace();
        }
    }
    
    private String truncateDescription(String description) {
        int maxLength = 255; // Maximum length allowed for the description column
        if (description.length() > maxLength) {
            return description.substring(0, maxLength);
        }
        return description;
    }
    
    public List<InstagramFile> getAllInstagramFiles() {
	    return InstaFileRepo.findAll();
	  }

	public int deleteAllInstagramFiles() {
		// TODO Auto-generated method stub
		try {
            List<InstagramFile> allFiles = InstaFileRepo.findAll();
            int deletedCount = allFiles.size();
            InstaFileRepo.deleteAll();
            return deletedCount;
        } catch (Exception e) {
            // Log the error or handle it appropriately
            e.printStackTrace();
            return 0; // Return 0 if an error occurs during deletion
        }
	}


}

