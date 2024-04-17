package com.tsfn.model;



import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class InstagramFile {
	
	//private int FileId;
	
	
	/*
	 * @Id
	 * 
	 * @GeneratedValue(strategy = GenerationType.IDENTITY) private int id ;
	 */
	 
	
	@Id
	private double postId;
	private double accountId;
	private String accountUsername;
	private String accountName;
	private String description;
	private int duration;
	private String publishTime; //?????
	private String permalink;
	private String postType;
	private String dataComment;
	private String date;
    private int impressions;
    private int reach;
    private int shares;
    private int likes;
    private int comments;
    private int saves;
    private int plays;
    
}
