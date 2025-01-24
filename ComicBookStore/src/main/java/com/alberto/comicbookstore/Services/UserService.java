package com.alberto.comicbookstore.Services;

import java.util.Optional;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

import com.alberto.comicbookstore.Models.LoggedInUser;
import com.alberto.comicbookstore.Models.User;
import com.alberto.comicbookstore.Repository.UserRepository;


@Service
public class UserService {
	@Autowired
	private UserRepository userRepo;

	public User register(User newUser, BindingResult result) {
		// Checks to see if an account exists with that email.
		Optional<User> potentialUser = userRepo.findByEmail(newUser.getEmail());
		if (potentialUser.isPresent()) {
			result.rejectValue("email", "Matches", "An Account already exits, please login");
		}
		// Checking to see if password and confirm password match
		if (!newUser.getPassword().equals(newUser.getConfirm())) {
			result.rejectValue("confirm", "Matches", "Passwords dont match");
		}
		// Checking Any additional errors
		if (result.hasErrors()) {
			return null;
		}
		// If we got this far, password must be hashed, salted and saved in DB
		String hashedPassword = BCrypt.hashpw(newUser.getPassword(), BCrypt.gensalt());
		newUser.setPassword(hashedPassword);
		return userRepo.save(newUser);
	}

	public User login(LoggedInUser newLoginObject, BindingResult result) {
		// Checks if the email exists in the database
		Optional<User> potentialUser = userRepo.findByEmail(newLoginObject.getEmail());
		// If User doesn't exist
		if (!potentialUser.isPresent()) {
			result.rejectValue("email", "Matches", "Email not found, try registering");
			return null;
		}
		// Get and Store the email from database to verify password
		User user = potentialUser.get();
		// Checks the hashed password to match what's in the database
		if (!BCrypt.checkpw(newLoginObject.getPassword(), user.getPassword())) {
			result.rejectValue("password", "Matches", "Invalid login attempt, try again");
		}
		// If password did not match password in Database, display errors
		if (result.hasErrors()) {
			return null;
		} else {
			// Email and password match what is in DataBase
			return user;
		}
	}

	public User getLoggedInUser(Long id) {
		Optional<User> potentialUser = userRepo.findById(id);
		if (potentialUser.isPresent()) {
			return potentialUser.get();
		}
		return null;
	}
}
