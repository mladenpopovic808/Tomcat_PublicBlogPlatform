package rs.raf.demo;

import org.glassfish.hk2.utilities.binding.AbstractBinder;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.server.ServerProperties;
import rs.raf.demo.repositories.comments.InMemoryCommentsRepository;
import rs.raf.demo.repositories.comments.CommentsRepositoryInterface;
import rs.raf.demo.repositories.comments.SqlCommentsRepository;
import rs.raf.demo.repositories.post.InMemoryPostRepository;
import rs.raf.demo.repositories.post.PostRepositoryInterface;
import rs.raf.demo.repositories.post.SqlPostRepository;
import rs.raf.demo.repositories.users.InMemoryUserRepository;
import rs.raf.demo.repositories.users.UserRepositoryInterface;
import rs.raf.demo.services.CommentService;
import rs.raf.demo.services.PostService;
import rs.raf.demo.services.UserService;

import javax.inject.Singleton;
import javax.ws.rs.ApplicationPath;


@ApplicationPath("/api")
public class HelloApplication extends ResourceConfig {


    public HelloApplication() {

        property(ServerProperties.BV_SEND_ERROR_IN_RESPONSE, true);


        AbstractBinder binder = new AbstractBinder() {
            @Override
            protected void configure() {

                this.bind(SqlPostRepository.class).to(PostRepositoryInterface.class).in(Singleton.class);
                System.out.println("kreirana applikacija");
                this.bind(SqlCommentsRepository.class).to(CommentsRepositoryInterface.class).in(Singleton.class);
                this.bind(InMemoryUserRepository.class).to(UserRepositoryInterface.class).in(Singleton.class);

                this.bind(PostService.class).to(PostService.class).in(Singleton.class);
                this.bind(CommentService.class).to(CommentService.class).in(Singleton.class);
                this.bind(UserService.class).to(UserService.class).in(Singleton.class);

//                this.bindAsContract(PostService.class);
//                this.bindAsContract(CommentService.class);
            }
        };
        register(binder);

        packages("rs.raf.demo.resources,rs.raf.demo.services");

    }
}
