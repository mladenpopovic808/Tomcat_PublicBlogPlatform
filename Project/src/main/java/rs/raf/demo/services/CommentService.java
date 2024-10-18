package rs.raf.demo.services;

import rs.raf.demo.entities.Comment;
import rs.raf.demo.repositories.comments.CommentsRepositoryInterface;

import javax.inject.Inject;
import java.util.List;

public class CommentService {

    @Inject
    public CommentsRepositoryInterface repository;

    public CommentService(){
        System.err.println("Kreiran CommentService");
    }
    public Comment addComment(Integer postId, Comment comment){
        return repository.addComment(comment,postId);
    }
    public List<Comment> getAllComments(Integer postId){
        return repository.getAllComments(postId);
    }

}
