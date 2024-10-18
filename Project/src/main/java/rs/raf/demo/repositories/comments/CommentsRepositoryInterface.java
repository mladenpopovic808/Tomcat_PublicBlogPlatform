package rs.raf.demo.repositories.comments;

import rs.raf.demo.entities.Comment;

import java.util.List;


public interface CommentsRepositoryInterface {

    public Comment addComment(Comment comment, Integer postId);
    public List<Comment> getAllComments(Integer postId);




}
