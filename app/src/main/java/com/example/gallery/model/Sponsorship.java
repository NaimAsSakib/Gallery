package com.example.gallery.model;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class Sponsorship{

	@SerializedName("sponsor")
	private Sponsor sponsor;

	@SerializedName("tagline_url")
	private String taglineUrl;

	@SerializedName("tagline")
	private String tagline;

	@SerializedName("impression_urls")
	private List<String> impressionUrls;

	public Sponsor getSponsor(){
		return sponsor;
	}

	public String getTaglineUrl(){
		return taglineUrl;
	}

	public String getTagline(){
		return tagline;
	}

	public List<String> getImpressionUrls(){
		return impressionUrls;
	}
}