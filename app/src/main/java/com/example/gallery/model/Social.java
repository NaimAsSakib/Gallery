package com.example.gallery.model;

import com.google.gson.annotations.SerializedName;

public class Social{

	@SerializedName("twitter_username")
	private Object twitterUsername;

	@SerializedName("paypal_email")
	private Object paypalEmail;

	@SerializedName("instagram_username")
	private String instagramUsername;

	@SerializedName("portfolio_url")
	private String portfolioUrl;

	public Object getTwitterUsername(){
		return twitterUsername;
	}

	public Object getPaypalEmail(){
		return paypalEmail;
	}

	public String getInstagramUsername(){
		return instagramUsername;
	}

	public String getPortfolioUrl(){
		return portfolioUrl;
	}
}