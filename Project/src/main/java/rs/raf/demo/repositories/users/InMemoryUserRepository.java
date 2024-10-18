package rs.raf.demo.repositories.users;

import rs.raf.demo.entities.User;

import java.util.Iterator;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class InMemoryUserRepository implements UserRepositoryInterface{

    private static List<User> USERS = new CopyOnWriteArrayList<>();
    public static User currentUser = null;

    static { //SHA enkriptovane lozinke,posle u loginu enkriptujemo unetu lozinku sa tim istim algoritmom i proveravamo poklapanje
        USERS.add(new User("admin", "a665a45920422f9d417e4867efdc4fb8a04a1f3fff1fa07e998e86f7f7a27ae3")); // 123
        USERS.add(new User("makelele", "2ac9a6746aca543af8dff39894cfe8173afba21eb01c6fae33d52947222855ef")); // 000
    }

    @Override
    public User findUser(String username) {
        User user = null;
        Iterator<User> userIterator = USERS.iterator();
        while (userIterator.hasNext() && user == null) {
            User u = userIterator.next();
            if (u.getUsername().equals(username)) {
                user = u;
            }
        }
        return user;
    }
    public User getCurrentUser(){
        return currentUser;
    }
}
