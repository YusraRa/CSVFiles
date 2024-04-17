package com.tsfn.model;

import jakarta.persistence.Column;
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
public class LinkedInFile {
//	@Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private int id;
	
	//@Column(columnDefinition = "nvarchar(max)")
	
    private String postTitle;
    @Id
    private String postLink;
    private String postType;
    private String campaignName;
    private String postedBy;
    private String createdDate;
    private String campaignStartDate;
    private String campaignEndDate;
    private String audience;
    private int impressions;
    private int viewsExcludingOffsite;
    private int offsiteViews;
    private int clicks;
    private double clickThroughRate;
    private int likes;
    private int comments;
    private int reposts;
    private int follows;
    private double engagementRate;
    private String contentType;
}
