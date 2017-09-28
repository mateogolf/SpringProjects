package com.mattr.pollLitHub.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.mattr.pollLitHub.models.Chara;
import com.mattr.pollLitHub.models.Clearance;
import com.mattr.pollLitHub.models.Follower;
import com.mattr.pollLitHub.models.Law;
import com.mattr.pollLitHub.models.Lore;
import com.mattr.pollLitHub.models.User;
import com.mattr.pollLitHub.models.World;
import com.mattr.pollLitHub.repositories.CharRepo;
import com.mattr.pollLitHub.repositories.ClearanceRepo;
import com.mattr.pollLitHub.repositories.FollowerRepo;
import com.mattr.pollLitHub.repositories.LawRepo;
import com.mattr.pollLitHub.repositories.LoreRepo;
import com.mattr.pollLitHub.repositories.UserRepository;
import com.mattr.pollLitHub.repositories.WorldRepo;
@Service
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
	
	//World:All
	public ArrayList<World> allWorlds(){
		return (ArrayList<World>) repo.findAll();
	}
	//World:Search
	public ArrayList<World> worldsBy(String search) {
		return (ArrayList<World>) repo.findByNameContaining(search);
	}
	//World:ViewOne
	public World worldAt(Long id){
		return repo.findOne(id);
	}
	//World:ADD
	public World addWorld(World world) {
		return repo.save(world);
	}
	//	Delete
	public void destroyWorld(Long id) {
		repo.delete(id);
	}
	//Is User a writer?
	public boolean isWriter(Long userId,World world) {
		List<User> writers = world.getWriters();
		for(User user:writers) {
			if(user.getId()==userId) {
				return true;
			}
		}
		return false;
	}
	
	//Laws
	//1)View: All
	public ArrayList<Law> allLaws(){
		return (ArrayList<Law>)lawRepo.findAll();
	}

	//2)Show One and 5)GET: Edit
	public Law lawAt(Long id) {
		return lawRepo.findOne(id);
	}

	//4)POST: Create
	public void addLaw(Law law) {
		lawRepo.save(law);
	}

	//7)Destroy
	public void destroyLaw(Long id) {
		lawRepo.delete(id);
	}
	
	
	//Lore
	//1)View: All
	public ArrayList<Lore> allLores(){
		return (ArrayList<Lore>)loreRepo.findAll();
	}

	//2)Show One and 5)GET: Edit
	public Lore loreAt(Long id) {
		return loreRepo.findOne(id);
	}

	//4)POST: Create
	public void addLore(Lore lore) {
		loreRepo.save(lore);
	}
	//6)POST: Update
	public Lore updateLore(Lore lore) {
		return loreRepo.save(lore);
	}
	//7)Destroy
	public void destroyLore(Long id) {
		loreRepo.delete(id);
	}
	
	
	//Characters
	//1)View: All
	public ArrayList<Chara> allCharas(){
		return (ArrayList<Chara>)charRepo.findAll();
	}

	//2)Show One and 5)GET: Edit
	public Chara charaAt(Long id) {
		return charRepo.findOne(id);
	}

	//4)POST: Create
	public void addChara(Chara chara) {
		charRepo.save(chara);
	}

	//7)Destroy
	public void destroyChara(Long id) {
		charRepo.delete(id);
	}
	
	//	Follower: Add users to specific clearances(WRITER ONLY)
	//1)View: All
	public ArrayList<Follower> allFollowers(){
		return (ArrayList<Follower>)fRepo.findAll();
	}

	//2)Show One and 5)GET: Edit
	public Follower followerAt(Long id) {
		return fRepo.findOne(id);
	}

	//4)POST: Create
	public void addFollower(Follower follower) {
		fRepo.save(follower);
	}

	//7)Destroy
	public void destroyFollower(Long id) {
		fRepo.delete(id);
	}
	
	public ArrayList<Follower> followerAt(User user,World world) {
		return fRepo.findByUserAndWorld(user, world);
	}
	public Follower follow(User user,World world) {
//		User user = userRepository.findOne(userId);
//		World world = repo.findOne(worldId);
		//default is as a fan
		Clearance clearance = clearRepo.findOne((long) 3);
		Follower follower = new Follower(user,world,clearance);
		return fRepo.save(follower);
	}
	//If follower_id not known
	public void unfollow(Follower follower) {
		fRepo.delete(follower);
	}
	//Add users to specific clearances: Follower add
	
	//Add Law/Lore/Chara(***StoryNode in StoryService***)
}
