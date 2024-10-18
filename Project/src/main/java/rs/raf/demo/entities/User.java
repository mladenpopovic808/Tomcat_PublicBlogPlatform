package rs.raf.demo.entities;

public class User {

    private String username;

    private String hashedPassword;

    private Integer id;

    public User() {
    }


    public User(String username,String hashedPassword) {
        this.username = username;
        this.hashedPassword = hashedPassword;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getHashedPassword() {
        return hashedPassword;
    }

    public void setHashedPassword(String hashedPassword) {
        this.hashedPassword = hashedPassword;
    }
}
