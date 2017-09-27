package com.mattr.pollLitHub.services;

import com.mattr.pollLitHub.repositories.CharRepo;
import com.mattr.pollLitHub.repositories.ClearanceRepo;
import com.mattr.pollLitHub.repositories.FollowerRepo;
import com.mattr.pollLitHub.repositories.LawRepo;
import com.mattr.pollLitHub.repositories.LoreRepo;
import com.mattr.pollLitHub.repositories.UserRepository;
import com.mattr.pollLitHub.repositories.WorldRepo;

public class WorldService {
	private CharRepo charRepo;
	private ClearanceRepo clearRepo;
	private FollowerRepo fRepo;
	private LawRepo lawRepo;
	private LoreRepo loreRepo;
	private WorldRepo repo;
	private UserRepository userRepository;
	
	public WorldService(CharRepo charRepo, ClearanceRepo clearRepo, FollowerRepo fRepo, LawRepo lawRepo,
			LoreRepo loreRepo, WorldRepo repo, UserRepository userRepository) {
		this.charRepo = charRepo;
		this.clearRepo = clearRepo;
		this.fRepo = fRepo;
		this.lawRepo = lawRepo;
		this.loreRepo = loreRepo;
		this.repo = repo;
		this.userRepository = userRepository;
	}
	
	
	//Manages the basic functions for world:
	//All Worlds
	//View World
	//All Clearance
	//ADD:
	//	World
	//	Follower: Add users to specific clearances(WRITER ONLY)
	
	//Add users to specific clearances: Follower add
	
	//Add Law/Lore/Character(***StoryNode in StoryService***)
}
