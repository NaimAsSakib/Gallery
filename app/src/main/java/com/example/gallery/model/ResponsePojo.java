package com.example.gallery.model;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class ResponsePojo{

	@SerializedName("ResponsePojo")
	private List<ResponsePojoItem> responsePojo;

	public List<ResponsePojoItem> getResponsePojo(){
		return responsePojo;
	}
}