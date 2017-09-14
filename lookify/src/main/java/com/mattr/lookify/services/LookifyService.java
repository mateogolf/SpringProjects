package com.mattr.lookify.services;

import java.util.ArrayList;

import org.springframework.stereotype.Service;

import com.mattr.lookify.models.Song;
import com.mattr.lookify.repositories.LookifyRepository;

@Service
public class LookifyService {
	private LookifyRepository lookrepo;

	public LookifyService(LookifyRepository lookrepo) {
		this.lookrepo = lookrepo;
	}
	
	//view all
	public ArrayList<Song> allSongs(){
		return (ArrayList<Song>)lookrepo.findAll();
	}
	
	//view one
	public Song songAt(Long id){
		return lookrepo.findOne(id);
	}
	
	//search the list by the artist
	public ArrayList<Song> songsBy(String search){
		return (ArrayList<Song>)lookrepo.findByArtistContaining(search);
	}
		
	
	//add new
	public void addSong(Song song) {
		lookrepo.save(song);
	}
	
	//delete
	public void destorySong(Long id) {
		lookrepo.delete(id);
	}
	//edit
	public void updateSong(Song song) {
		lookrepo.save(song);
	}
	//top10 by rating--query rating ordered by top rating
	public ArrayList<Song> top10(){
		ArrayList<Song> results = lookrepo.findTop10ByOrderByRatingDesc();
		return results;
	}
	
	
}
