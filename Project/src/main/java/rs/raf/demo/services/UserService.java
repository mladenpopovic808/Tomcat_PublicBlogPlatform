package rs.raf.demo.services;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.auth0.jwt.interfaces.JWTVerifier;
import org.apache.commons.codec.digest.DigestUtils;
import rs.raf.demo.entities.User;
import rs.raf.demo.repositories.users.InMemoryUserRepository;
import rs.raf.demo.repositories.users.UserRepositoryInterface;

import javax.inject.Inject;
import java.util.Date;

public class UserService {

    @Inject
    UserRepositoryInterface userRepository;
    public String login(String username, String password)
    {
        //enkriptujemo
        String hashedPassword = DigestUtils.sha256Hex(password);


        User user = this.userRepository.findUser(username);
        if (user == null || !user.getHashedPassword().equals(hashedPassword)) {
            return null;
        }

        InMemoryUserRepository.currentUser = user;//setujemo trenutnog korisnika

        Date issuedAt = new Date();
        Date expiresAt = new Date(issuedAt.getTime() + 24*60*60*1000);

        Algorithm algorithm = Algorithm.HMAC256("secret");
        //za ovo ti treba codec biblioteka u pom.xml.To su algoritmi za hashiranje

        return JWT.create()
                .withIssuedAt(issuedAt)
                .withExpiresAt(expiresAt)
                .withSubject(username) //hashiramo jwt sa username-om
                .sign(algorithm);
        //vracamo token korisniku nakon loginovanja
    }

    public boolean isAuthorized(String token){
        Algorithm algorithm = Algorithm.HMAC256("secret");//secret je kljucna rec,moze biti bilo sta
        JWTVerifier verifier = JWT.require(algorithm).build();
        DecodedJWT jwt = verifier.verify(token);

        String username = jwt.getSubject(); //mapirali smo jwt pod usernameom (withSubject)

        User user = this.userRepository.findUser(username);

        if (user == null){
            return false;
        }

        return true;
    }

    public User getCurrentUser(){
        return InMemoryUserRepository.currentUser;
    }


}
