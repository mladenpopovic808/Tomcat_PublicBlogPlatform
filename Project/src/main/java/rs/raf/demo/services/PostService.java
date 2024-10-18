package rs.raf.demo.services;


import rs.raf.demo.entities.Post;
import rs.raf.demo.repositories.post.PostRepositoryInterface;

import javax.inject.Inject;
import java.util.List;

public class PostService {

    @Inject
    public PostRepositoryInterface repository;

    public PostService(){System.err.println("Kreiran PostService");}
    public Post addPost(Post post){
        return repository.addPost(post);
    }
    public List<Post> getAllPosts(){return repository.getAllPosts();}
    public Post getPost(Integer id){return repository.getPost(id);}

}
