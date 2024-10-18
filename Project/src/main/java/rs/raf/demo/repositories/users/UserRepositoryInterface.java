package rs.raf.demo.repositories.users;

import rs.raf.demo.entities.User;

public interface UserRepositoryInterface {
    public User findUser(String username);
}
