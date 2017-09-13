package com.mattr.crud.services;

import java.util.ArrayList;

import org.springframework.stereotype.Service;

import com.mattr.crud.models.Language;
import com.mattr.crud.repositories.LanguageRepository;

@Service
public class CRUDService {
	private LanguageRepository langrepo;
	
	public CRUDService(LanguageRepository langrepo) {
		this.langrepo = langrepo;
	}
	
	public ArrayList<Language> allLang(){
		return (ArrayList<Language>)langrepo.findAll();
	}
	
	//For show method
	public Language langAt(Long id) {
		return langrepo.findOne(id);
	}
	
	//create
	public void addLang(Language lang) {
		langrepo.save(lang);
	}
	
	//edit
	public void updateLang(Long id, Language lang) {
		langrepo.save(lang);
	}
	//destroy
	public void destroyLang(Long id) {
		langrepo.delete(id);
	}
}
