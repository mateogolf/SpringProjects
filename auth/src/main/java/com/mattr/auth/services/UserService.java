package com.mattr.auth.services;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.mattr.auth.models.User;
import com.mattr.auth.repositories.RoleRepository;
import com.mattr.auth.repositories.UserRepository;
import com.mattr.auth.models.Role;

@Service
public class UserService {
    private UserRepository userRepository;
    private RoleRepository roleRepository;
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    
    public UserService(UserRepository userRepository, RoleRepository roleRepository, BCryptPasswordEncoder bCryptPasswordEncoder)     {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }
    
    
    // 1
    public void saveWithUserRole(User user) {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        user.setRoles(roleRepository.findByName("ROLE_USER"));
        userRepository.save(user);
    }
     
     // 2 
    public void saveUserWithAdminRole(User user) {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        user.setRoles(roleRepository.findByName("ROLE_ADMIN"));
        userRepository.save(user);
    }    
    
    // 3
    public User findByUsername(String username) {
        return userRepository.findByUsername(username);
    }
    public void updateUser(User user) {
    	user.setUpdatedAt(new Date());
    	userRepository.save(user);
    }
	public List<User> allAdmins(){
		List<Role> role = roleRepository.findByName("ROLE_ADMIN");
		return role.get(0).getUsers();
	}
	public ArrayList<User> allUsers(){
		return (ArrayList<User>)userRepository.findAll();
	}
	public void makeAdmin(Long id) {
		User user = userRepository.findOne(id);
		List<Role> roles = roleRepository.findByName("ROLE_ADMIN");
		user.setRoles(roles);
		userRepository.save(user);
	}
	public void destroyUser(Long id) {
		userRepository.delete(id);
	}
}
