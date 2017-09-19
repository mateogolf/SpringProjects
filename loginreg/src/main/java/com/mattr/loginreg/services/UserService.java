package com.mattr.loginreg.services;

import java.util.Date;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

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
	
}
