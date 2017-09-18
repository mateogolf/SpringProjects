package com.mattr.dojooverflow.services;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Service;

import com.mattr.dojooverflow.models.Tag;
import com.mattr.dojooverflow.repositories.TagRepo;

@Service
public class TagService {
	private TagRepo repo;

	public TagService(TagRepo repo) {
		this.repo = repo;
	}
	
	public List<Tag> generateTags(String newTags) {
		if(newTags.length() == 0) {
			return null;
		}
		//split tags by ","
        ArrayList<String> tagList = new ArrayList<String>(Arrays.asList(newTags.split("\\,")));
		ArrayList<Tag> oldTags = (ArrayList<Tag>)repo.findAll();//all tags
		ArrayList<Tag> qTags = new ArrayList<Tag>();
		for(int i=0;i<tagList.size();i++){
            String testStr = tagList.get(i).trim();//removes white space before and after
            boolean found = false;
            for(Tag oldTag:oldTags){//Loop through oldTags
                //if newTag present in oldTag
                if(testStr.equals(oldTag.getSubject())){
                    qTags.add(oldTag);//add found tag to ArrayList<Tag> to be returned
                    found = true;
                }
            }
            //if !found(outside loop for oldTags)
            if(!found){
                Tag newTag = new Tag(testStr);//create new tag with string
                Tag saved = repo.save(newTag);//add to database
                qTags.add(saved);//add to ArrayList to be returned
            }
        }
		return (List<Tag>)qTags;
	}
	public String tagsToString(ArrayList<Tag> tags) {
		String result = "";
		for(int i=0;i<tags.size()-1;i++) {
			result += tags.get(i).getSubject() + ", ";
		}
		result += tags.get(tags.size()-1).getSubject();
		return result;
	}
	
}
