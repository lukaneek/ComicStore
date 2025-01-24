package com.alberto.comicbookstore.Repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.alberto.comicbookstore.Models.Genre;

@Repository
public interface GenreRepository extends CrudRepository<Genre, Long>{
	List<Genre> findAll();
}
