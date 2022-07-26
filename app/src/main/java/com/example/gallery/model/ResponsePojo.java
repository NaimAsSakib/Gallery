package com.example.gallery.model;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class ResponsePojo{

	@SerializedName("topic_submissions")
	private TopicSubmissions topicSubmissions;

	@SerializedName("current_user_collections")
	private List<Object> currentUserCollections;

	@SerializedName("color")
	private String color;

	@SerializedName("sponsorship")
	private Sponsorship sponsorship;

	@SerializedName("created_at")
	private String createdAt;

	@SerializedName("description")
	private Object description;

	@SerializedName("liked_by_user")
	private boolean likedByUser;

	@SerializedName("urls")
	private Urls urls;

	@SerializedName("alt_description")
	private Object altDescription;

	@SerializedName("updated_at")
	private String updatedAt;

	@SerializedName("width")
	private int width;

	@SerializedName("blur_hash")
	private String blurHash;

	@SerializedName("links")
	private Links links;

	@SerializedName("id")
	private String id;

	@SerializedName("categories")
	private List<Object> categories;

	@SerializedName("promoted_at")
	private Object promotedAt;

	@SerializedName("user")
	private User user;

	@SerializedName("height")
	private int height;

	@SerializedName("likes")
	private int likes;

	public TopicSubmissions getTopicSubmissions(){
		return topicSubmissions;
	}

	public List<Object> getCurrentUserCollections(){
		return currentUserCollections;
	}

	public String getColor(){
		return color;
	}

	public Sponsorship getSponsorship(){
		return sponsorship;
	}

	public String getCreatedAt(){
		return createdAt;
	}

	public Object getDescription(){
		return description;
	}

	public boolean isLikedByUser(){
		return likedByUser;
	}

	public Urls getUrls(){
		return urls;
	}

	public Object getAltDescription(){
		return altDescription;
	}

	public String getUpdatedAt(){
		return updatedAt;
	}

	public int getWidth(){
		return width;
	}

	public String getBlurHash(){
		return blurHash;
	}

	public Links getLinks(){
		return links;
	}

	public String getId(){
		return id;
	}

	public List<Object> getCategories(){
		return categories;
	}

	public Object getPromotedAt(){
		return promotedAt;
	}

	public User getUser(){
		return user;
	}

	public int getHeight(){
		return height;
	}

	public int getLikes(){
		return likes;
	}
}