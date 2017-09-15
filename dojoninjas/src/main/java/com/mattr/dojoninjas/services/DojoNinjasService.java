package com.mattr.dojoninjas.services;

import java.util.ArrayList;

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
//	public ArrayList<Ninja> allNinjasAtDojo(Long id){
//		Dojo dojo = dojoRepo.findOne(id);
//		return (ArrayList<Ninja>)dojo.getNinjas();
//	}
	
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
