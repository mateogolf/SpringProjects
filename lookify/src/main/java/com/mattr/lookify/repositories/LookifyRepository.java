package com.mattr.lookify.repositories;

import java.util.ArrayList;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.mattr.lookify.models.Song;

@Repository
public interface LookifyRepository extends CrudRepository<Song,Long>{
	ArrayList<Song> findByArtistContaining(String search);
	ArrayList<Song> findTop10ByOrderByRatingDesc();
}
