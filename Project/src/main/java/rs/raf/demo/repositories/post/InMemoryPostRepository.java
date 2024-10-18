package rs.raf.demo.repositories.post;


import rs.raf.demo.entities.Post;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class InMemoryPostRepository implements PostRepositoryInterface {

    private static List<Post> posts=new CopyOnWriteArrayList<>();
    private static int id=0;

    public InMemoryPostRepository(){
        System.err.println("Kreiran Post Repo");
        addPost(new Post("Mladen","Neki Random naslov","bla-bla-bla"));
        addPost(new Post("Uros","(Photo)(Video)()","lorem ipsum lorem ipsum opis"));
    }
    @Override
    public synchronized Post addPost(Post post) {
        post.setId(id++);
        posts.add(post);
        return post;
    }
    @Override
    public List<Post> getAllPosts() {
        return posts;
    }
    @Override
    public Post getPost(Integer id) {
        return posts.get(id);
    }

}
