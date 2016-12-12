package com.rest.restFulExample.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.rest.restFulExample.dataBase.DataBaseClass;
import com.rest.restFulExample.model.Profile;

public class ProfileService {

	private Map<String,Profile> profiles = DataBaseClass.getProfiles();
	
	public ProfileService(){
		profiles.put("kart", new Profile(1,"kart","karthik","Musunur"));
		profiles.put("wells", new Profile(2,"wells" ,"Tim","Sloan"));
		profiles.put("jeev", new Profile(3,"jeev" ,"Jeevitha","Vakati"));
	}
	
	public List<Profile> getAllProfiles(){
		return new ArrayList<Profile>(profiles.values());
	}
	
	public Profile getProfile(String profileName){
		return profiles.get(profileName);
	}
	
	public Profile addProfile(Profile profile){
		profile.setId(profiles.size()+1);
		profiles.put(profile.getProfileName(), profile);
		return profile;
	}
	
	public Profile updateProfile(Profile profile){
		if(profile.getProfileName().isEmpty()){
			return null;
		}
		profiles.put(profile.getProfileName(), profile);
		return profile;
	}
	
	public Profile removeProfile(String profileName){
		return profiles.remove(profileName);
	}
	
}
