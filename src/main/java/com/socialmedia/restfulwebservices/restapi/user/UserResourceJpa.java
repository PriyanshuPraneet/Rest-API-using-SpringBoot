package com.socialmedia.restfulwebservices.restapi.user;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.socialmedia.restfulwebservices.restapi.jpa.PostRepository;
import com.socialmedia.restfulwebservices.restapi.jpa.UserRepository;

@RestController
public class UserResourceJpa {
	
	
	private UserRepository repository;
	private PostRepository postRepo;

	public UserResourceJpa(UserRepository repository, PostRepository postRepo) {
		this.repository = repository;
		this.postRepo = postRepo;
	}
	
	@GetMapping(path = "jpa/users")
	public List<User> retriveAllUsers(){
		return repository.findAll();
	}
	
	@GetMapping(path = "jpa/users/{id}")
	public EntityModel<User> retrieveUser(@PathVariable Integer id) {
		Optional<User> user =  repository.findById(id);
		if(user.isEmpty()) {
			throw new UserNotFoundException("id:"+id+" not found");
		}
		
		EntityModel<User> entityModel = EntityModel.of(user.get());
		
		WebMvcLinkBuilder link = linkTo(methodOn(this.getClass()).retriveAllUsers());
		entityModel.add(link.withRel("all-users"));
		
		return entityModel;
	}
	
	@PostMapping("jpa/users")
	public ResponseEntity<User> addUser(@RequestBody User user) {
		User addedUser = repository.save(user);
		URI location = ServletUriComponentsBuilder
						.fromCurrentRequest()
						.path("/{id}")
						.buildAndExpand(addedUser.getId())
						.toUri();
		return ResponseEntity.created(location).build();
	}
	
	@DeleteMapping("jpa/users/{id}")
	public void deleteUser(@PathVariable Integer id) {
		repository.deleteById(id);
	}
	
	@GetMapping("jpa/users/{id}/posts")
	public List<Post> getPostOfUser(@PathVariable int id){
		Optional<User> user =  repository.findById(id);
		if(user.isEmpty()) {
			throw new UserNotFoundException("id:"+id+" not found");
		}
		return user.get().getPosts();
	}
	
	@PostMapping("jpa/users/{id}/posts")
	public ResponseEntity<Post> addPost(@PathVariable int id, @RequestBody Post post){
		Optional<User> user =  repository.findById(id);
		if(user.isEmpty()) {
			throw new UserNotFoundException("id:"+id+" not found");
		}
		post.setUser(user.get());
		Post posted = postRepo.save(post);
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(posted.getId()).toUri();
		return ResponseEntity.created(location).build();
	}
	
	@GetMapping("jpa/users/{id}/posts/{postId}")
	public EntityModel<Post> getPost(@PathVariable int id, @PathVariable int postId) {
		Optional<User> user =  repository.findById(id);
		if(user.isEmpty()) {
			throw new UserNotFoundException("id:"+id+" not found");
		}
		Optional<Post> post = postRepo.findById(postId);
		if(post.isEmpty()) {
			throw new PostNotFoundException("postId:"+postId+" not found");
		}
		
		EntityModel<Post> entityModel = EntityModel.of(post.get());
		WebMvcLinkBuilder link = linkTo(methodOn(this.getClass()).getPostOfUser(id));
		entityModel.add(link.withRel("all-posts"));
		
		return entityModel;
	}
	
}
