package com.alberto.comicbookstore.Services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alberto.comicbookstore.Models.Comment;
import com.alberto.comicbookstore.Repository.ComicRepository;
import com.alberto.comicbookstore.Repository.CommentRepository;






@Service
public class CommentService {
	// adding the comment repository as a dependency
	@Autowired
	CommentRepository commentRepository;
	@Autowired
	ComicRepository comicRepository;

	public CommentService(CommentRepository commentRepository) {
		this.commentRepository = commentRepository;
	}

	// returns all the comments
	public List<Comment> allComments() {
		return commentRepository.findAll();
	}

	// creates a comment
	public Comment createComment(Comment b) {
		return commentRepository.save(b);
	}

	// updates a comment
	public Comment updateComment(Comment b) {
		return commentRepository.save(b);
	}

	// Deletes a comment
	public void deleteComment(Long id) {
		commentRepository.deleteById(id);
	}

	// retrieves a comment
	public Comment findComment(Long id) {
		Optional<Comment> optionalComment = commentRepository.findById(id);
		if (optionalComment.isPresent()) {
			return optionalComment.get();
		} else {
			return null;
		}
	}
	
}
