package com.alberto.comicbookstore.Services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alberto.comicbookstore.Models.Rental;
import com.alberto.comicbookstore.Repository.RentalRepository;

@Service
public class RentalService {

	@Autowired
	private RentalRepository rentalRepo;
	
	// Create comic rental
	public Rental createRental(Rental rental) {
		return rentalRepo.save(rental);
	}
	// Find a User's rentals
	public List<Rental> findRentalsByUser(Long userId) {
		return rentalRepo.findByUserId(userId);
	}
	// Find a rental by ID
	public Rental findRentalById(Long rentalId) {
		return rentalRepo.findById(rentalId).orElse(null);
	}
	// Find comics that are currently out on rent
	public List<Long> findRentedComicIds() {
		return rentalRepo.findCurrentlyRentedComicIds();
	}
	
	// Delete a rental (return the comic)
	public void deleteRental(Long rentalId) {
		rentalRepo.deleteById(rentalId);
	}
}