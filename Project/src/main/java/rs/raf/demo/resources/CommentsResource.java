package rs.raf.demo.resources;


import rs.raf.demo.entities.Comment;
import rs.raf.demo.services.CommentService;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/comments")
public class CommentsResource {

    @Inject
    private CommentService commentService;

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getCommentsForPost(@PathParam("id") Integer id){
        return Response.ok(this.commentService.getAllComments(id)).build();
    }

    @POST
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Comment addComment(@PathParam("id") Integer id, Comment comment){
        return this.commentService.addComment(id,comment);

    }


}
