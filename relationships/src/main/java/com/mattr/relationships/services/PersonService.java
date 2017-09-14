package com.mattr.relationships.services;

import java.util.ArrayList;

import org.springframework.stereotype.Service;

import com.mattr.relationships.models.License;
import com.mattr.relationships.models.Person;
import com.mattr.relationships.repositories.LicenseRepository;
import com.mattr.relationships.repositories.PersonRepository;

@Service
public class PersonService {
	private PersonRepository pRepo;
//	private LicenseRepository lRepo;
	public PersonService(PersonRepository pRepo) {
		this.pRepo = pRepo;
	}
	//viewPeople
	public ArrayList<Person> allDrivers(){
		return (ArrayList<Person>)pRepo.findAll();
	}
	
	

	//viewPeoplewithoutLicense
	public ArrayList<Person> allDriversNoLicense(){
		return (ArrayList<Person>)pRepo.findByLicenseIsNull();
	}
	//addPerson
	public void addPerson(Person person) {
		pRepo.save(person);
		System.out.println("Added to Repo...");
	}
	//view Person's Profile
	public Person personAt(Long id) {
		return pRepo.findOne(id);
	}
}
