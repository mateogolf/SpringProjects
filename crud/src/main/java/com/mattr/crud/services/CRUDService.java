package com.mattr.crud.services;

import java.util.ArrayList;

import org.springframework.stereotype.Service;

import com.mattr.crud.models.Language;

@Service
public class CRUDService {
	private ArrayList<Language> languages = new ArrayList<Language>();
	
	public ArrayList<Language> allLang(){
		return languages;
	}
	
	//For show method
	public Language langAt(int index) {
		if (index < languages.size()){
            return languages.get(index);
        }else{
            return null;
        }
	}
	
	//create
	public void addLang(Language lang) {
		languages.add(lang);
	}
	
	//edit
	public void updateLang(int id, Language lang) {
		if (id < languages.size()){
	        languages.set(id, lang);
	    }
	}
	//destroy
	public void destroyLang(int id) {
		if (id < languages.size()){
			languages.remove(id);
        }
	}
}
