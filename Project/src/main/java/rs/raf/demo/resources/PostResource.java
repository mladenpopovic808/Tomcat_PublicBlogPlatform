package rs.raf.demo.resources;


import rs.raf.demo.entities.Post;
import rs.raf.demo.services.PostService;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/posts")
public class PostResource {

    @Inject
    private PostService postService;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllPosts(){

        return Response.ok(this.postService.getAllPosts()).build();
    }
    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Post getPostWithId(@PathParam("id") Integer id){
        return postService.getPost(id);
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public Post addPost(Post post){
        return postService.addPost(post);
    }



}
