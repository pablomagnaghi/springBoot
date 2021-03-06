package com.pmagnaghi;

import java.util.Date;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pmagnaghi.domain.Author;
import com.pmagnaghi.domain.Post;
import com.pmagnaghi.repository.AuthorRepository;
import com.pmagnaghi.repository.PostRepository;

@Service
public class DatabaseLoader {

	private static final Logger logger = LoggerFactory.getLogger(MongodemoApplication.class);
	private final PostRepository postRepository;
	private final AuthorRepository authorRepository;
	
	@Autowired
	public DatabaseLoader(PostRepository postRepository,AuthorRepository authorRepository){
		this.postRepository = postRepository;
		this.authorRepository = authorRepository;
	}
	
    @PostConstruct
    private void initDatabase(){
    	
    	authorRepository.deleteAll();
    	
    	Author dv = new Author();
    	dv.setFirstName("Dan");
    	dv.setLastName("Vega");
    	dv.setEmail("danvega@gmail.com");
    	authorRepository.save( dv );
    	
    	postRepository.deleteAll();
    	
    	Post post = new Post();
    	post.setTitle("Spring Data Rocks!");
    	post.setSlug("spring-data-rocks");
    	post.setTeaser("Post Teaser");
    	post.setBody("Post Body");
    	post.setPostedOn(new Date());
    	post.setAuthor(dv);
    	postRepository.save(post);
    	
    	dv.getPosts().add(post);
    	authorRepository.save( dv );
    	
    }
}
