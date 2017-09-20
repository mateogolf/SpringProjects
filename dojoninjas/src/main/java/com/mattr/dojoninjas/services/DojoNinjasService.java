package com.mattr.dojoninjas.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.mattr.dojoninjas.models.Dojo;
import com.mattr.dojoninjas.models.Ninja;
import com.mattr.dojoninjas.repositories.DojoRepo;
import com.mattr.dojoninjas.repositories.NinjaRepo;

@Service
public class DojoNinjasService {
	private DojoRepo dojoRepo;
	private NinjaRepo ninjaRepo;
	
	public DojoNinjasService(DojoRepo dojoRepo, NinjaRepo ninjaRepo) {
		this.dojoRepo = dojoRepo;
		this.ninjaRepo = ninjaRepo;
	}
	//all Dojos
	public ArrayList<Dojo> allDojos(){
		return (ArrayList<Dojo>)dojoRepo.findAll();
	}
	
//	//all ninjas for a Dojo
//	public ArrayList<Dojo> findAllDojosByNinjaCountDesc(){
//		return (ArrayList<Dojo>) dojoRepo.findAllOrderByNinjasDesc();
//	}
	public ArrayList<Object[]> joinDojosAndNinjas2(){
		ArrayList<Object[]> table = dojoRepo.joinDojosAndNinjas2();
		for(Object[] row : table) {
		    Dojo dojo = (Dojo) row[0];
		    Ninja ninja = (Ninja) row[1];
		}
		return table;
	}
	
	//new Dojo
	public void addDojo(Dojo dojo) {
		dojoRepo.save(dojo);
	}
	//new ninja
	public void addNinja(Ninja ninja) {
		ninjaRepo.save(ninja);
	}
	//Find Dojo @ given id
	public Dojo dojoAt(Long id) {
		return dojoRepo.findOne(id);
	}
}
