package rs.raf.demo.repositories.comments;

import rs.raf.demo.entities.Comment;

import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;

public class InMemoryCommentsRepository implements CommentsRepositoryInterface{

    private static ConcurrentHashMap<Integer, List<Comment>> comments = new ConcurrentHashMap<>();


    public InMemoryCommentsRepository(){
        System.err.println("Kreiran Comment repo");
        comments.put(0,new CopyOnWriteArrayList<>());
        comments.put(1,new CopyOnWriteArrayList<>());

        //add comments
        comments.get(0).add(new Comment("Mladen","Neki komentar"));
        comments.get(0).add(new Comment("Uros","Neki komentar"));
    }
    //synchronized zbog liste
    @Override
    public synchronized Comment addComment(Comment comment, Integer postId) {

        //ako nema komentare,pravimo mu listu komentara
        if(!comments.containsKey(postId)) {
            comments.put(postId, new CopyOnWriteArrayList<>());
        }


       comments.get(postId).add(comment);
       return comments.get(postId).get(comments.get(postId).size()-1);
    }

    @Override
    public List<Comment> getAllComments(Integer postId) {
        return comments.get(postId);
    }
}
