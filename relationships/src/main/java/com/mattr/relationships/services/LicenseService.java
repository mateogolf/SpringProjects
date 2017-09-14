package com.mattr.relationships.services;

import java.util.ArrayList;

import org.springframework.stereotype.Service;

import com.mattr.relationships.models.License;
import com.mattr.relationships.repositories.LicenseRepository;
@Service
public class LicenseService {
	private LicenseRepository lRepo;
	
	public LicenseService(LicenseRepository lRepo) {
		this.lRepo = lRepo;
	}
	
//	public ArrayList<License> getAll(){
//		return (ArrayList<License>)lRepo.findAll();
//	}
//	public String generateNum() {
//		ArrayList<License> licenses = this.getAll();
//		String num = String.format("%06d", this.getAll().size()+1);
//		Collections.sort(licenses, Comparator.comparing(License::getNumber));
//		for(int i = 0; i < licenses.size(); i++) {
//			License l = licenses.get(i);
//			if(l.getNumber().equals(num)) {
//				String add = "";
//				int newnum = 1 + i;
//				num = String.format("%06d", this.getAll().size()+newnum);
//			}
//		}
//		return num;		
//	}
	//addLicense
	public void addLicense(License license) {
		License saved = lRepo.save(license);
		String newNum = "";
		String idString = "" + saved.getId();
    	for(int i=0;i<(6-idString.length());i++) {
    		newNum += "0";
    	}
    	newNum += idString;
    	saved.setNumber(newNum);
    	lRepo.save(saved);
	}
	//view License
	public License licenseAt(Long id) {
		return lRepo.findOne(id);
	}
}
