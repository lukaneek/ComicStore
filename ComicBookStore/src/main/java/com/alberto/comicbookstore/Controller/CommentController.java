package com.alberto.comicbookstore.Controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.alberto.comicbookstore.Models.Comic;
import com.alberto.comicbookstore.Models.Comment;
import com.alberto.comicbookstore.Models.User;
import com.alberto.comicbookstore.Services.ComicService;
import com.alberto.comicbookstore.Services.CommentService;
import com.alberto.comicbookstore.Services.UserService;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;

@Controller
public class CommentController {

	@Autowired
	CommentService comments;

	@Autowired
	UserService users;
	
	@Autowired
	ComicService comics;

	// Create a new Comment
	@PostMapping("/newcomments/{comicId}")
	public String createComment(@Valid @ModelAttribute("comment") Comment comment, BindingResult result,
			HttpSession session, @PathVariable("comicId") Long comicId, Model model, RedirectAttributes redirectAttributes) {
		Comic oneComic = comics.findComic(comicId);
		if (result.hasErrors()) {
			model.addAttribute("comic", oneComic);
			return "comicDetails.jsp";
		}
		
		Long userId = (Long) session.getAttribute("userId");
		if (userId == null) {
			return "redirect:/";
		}
		
		
		User loggedInUser = users.getLoggedInUser(userId);
		
		List<Comment> commentList = oneComic.getComment();
		commentList.add(comment);
		
		comment.setComic(oneComic);
		comment.setUser(loggedInUser);
		comments.createComment(comment);
		return "redirect:/Home";
	}

	// Delete a comment by id
	@DeleteMapping("/comments/destroy/{id}")
	public String destroyComment(@PathVariable("id") Long id) {
		comments.deleteComment(id);
		return "redirect:/Home";
	}
}