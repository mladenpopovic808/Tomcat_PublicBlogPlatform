package rs.raf.demo.repositories.post;

import rs.raf.demo.entities.Post;

import java.util.List;

public interface PostRepositoryInterface {

    public Post addPost(Post post);

    public List<Post> getAllPosts();

    public Post getPost(Integer id);





}
