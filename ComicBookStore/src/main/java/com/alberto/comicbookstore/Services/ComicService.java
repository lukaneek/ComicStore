package com.alberto.comicbookstore.Services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alberto.comicbookstore.Models.Comic;
import com.alberto.comicbookstore.Models.Comment;
import com.alberto.comicbookstore.Repository.ComicRepository;
import com.alberto.comicbookstore.Repository.CommentRepository;



@Service
public class ComicService {
	@Autowired
	ComicRepository comicRepo;
	
	@Autowired
	CommentRepository commentRepo;
  
  @Autowired
	GenreService genreServ;

	// returns all the comics
	public List<Comic> allComics() {
		return comicRepo.findAll();
	}

	// creates a comic
	public void createComic(Comic comic) {
		comicRepo.save(comic);
	}
	
	// retrieves a comic
	public Comic findComic(Long id) {
		Optional<Comic> optionalComic = comicRepo.findById(id);
		if (optionalComic.isPresent()) {
			return optionalComic.get();
		} else {
			return null;
		}
	}
	
	// updates a comic
	public Comic updateComic(Comic comic) {
		return comicRepo.save(comic);
	}

	// Deletes a comic
	public void deleteComic(Long id) {
		comicRepo.deleteById(id);
	}
	
	public List<Comic> findByTitle(String search) {
		return comicRepo.findComicByTitleContaining(search);
	}

	
    public String addCommentToComic(Long id, Comment comment) {
        Optional<Comic> optionalComic = comicRepo.findById(id);
        
        if (optionalComic.isPresent()) {
            Comic comic = optionalComic.get();
            
            comment.setComic(comic);
            commentRepo.save(comment);
            return "Comment added successfully";
        } else {
            return "Comic not found";
        }
    }
}
