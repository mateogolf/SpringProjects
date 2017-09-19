package com.mattr.loginreg.services;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.mattr.loginreg.models.Role;
import com.mattr.loginreg.models.User;
import com.mattr.loginreg.repositories.RoleRepo;
import com.mattr.loginreg.repositories.UserRepo;

@Service
public class UserService {
	private UserRepo uRepo;
	private RoleRepo rRepo;
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	public UserService(UserRepo uRepo, RoleRepo rRepo, BCryptPasswordEncoder bCryptPasswordEncoder) {
		this.uRepo = uRepo;
		this.rRepo = rRepo;
		this.bCryptPasswordEncoder = bCryptPasswordEncoder;
	}
	public void saveWithUserRole(User user) {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        user.setRoles(rRepo.findByName("ROLE_USER"));
        uRepo.save(user);
    }
     
     // 2 
    public void saveUserWithAdminRole(User user) {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        user.setRoles(rRepo.findByName("ROLE_ADMIN"));
        uRepo.save(user);
    }
    public User findByEmail(String email) {
    	return uRepo.findByEmail(email);
    }
    public void updateUser(User user) {
    	user.setUpdatedAt(new Date());
    	uRepo.save(user);
    }
	public List<User> allAdmins(){
		List<Role> role = rRepo.findByName("ROLE_ADMIN");
		return role.get(0).getUsers();
	}
	public ArrayList<User> allUsers(){
		return (ArrayList<User>)uRepo.findAll();
	}
	public void makeAdmin(Long id) {
		User user = uRepo.findOne(id);
		List<Role> roles = rRepo.findByName("ROLE_ADMIN");
		user.setRoles(roles);
		uRepo.save(user);
	}
	public User userAt(Long id) {
		return uRepo.findOne(id);
	}
	public void destroyUser(Long id) {
		uRepo.delete(id);
	}
}
