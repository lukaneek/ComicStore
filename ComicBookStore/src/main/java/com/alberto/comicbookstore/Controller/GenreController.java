package com.alberto.comicbookstore.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.alberto.comicbookstore.Models.Genre;
import com.alberto.comicbookstore.Services.GenreService;
import com.alberto.comicbookstore.Services.UserService;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;

@Controller
public class GenreController {
	
	@Autowired
	private GenreService genreServ;
	@Autowired
	private UserService userService;
	
	@GetMapping("/genre")
	public String all(Model model, @ModelAttribute("genre") Genre genre, HttpSession session) {
		Long userId = (Long) session.getAttribute("userId");
		if (userId == null) {
			return "redirect:/";
		}
		model.addAttribute("user", userService.getLoggedInUser(userId));
		model.addAttribute("genres", genreServ.allGenres());
		
		return "genre.jsp";
	}
	
	@PostMapping("/genre")
	public String create(@Valid Model model, @ModelAttribute("genre") Genre genre, BindingResult result, HttpSession session) {
		Long userId = (Long) session.getAttribute("userId");
		if (userId == null) {
			return "redirect:/";
		}
		if (result.hasErrors()) {
			model.addAttribute("user", userService.getLoggedInUser(userId));
			model.addAttribute("genres", genreServ.allGenres());
			return "genre.jsp";
		}
		else {
			genreServ.createGenre(genre);
			return "redirect:/genre";
		}
	}
	
	@DeleteMapping("/genre/delete/{id}")
	public String destroy(@PathVariable("id") Long id) {
		genreServ.deleteGenre(id);
		return "redirect:/genre";
	}
}
