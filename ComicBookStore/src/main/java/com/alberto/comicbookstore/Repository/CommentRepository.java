package com.alberto.comicbookstore.Repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.alberto.comicbookstore.Models.Comment;





@Repository
public interface CommentRepository extends CrudRepository<Comment, Long>{
    // this method retrieves all the comments from the database
	List<Comment> findAll();
}
