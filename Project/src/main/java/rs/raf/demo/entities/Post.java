package rs.raf.demo.entities;

public class Post {
    private String author;
    private String title;
    private String description;

    private Integer id;



    public Post(String author, String title, String description) {
        this.author = author;
        this.title = title;
        this.description = description;
    }

    public Post(String author, String title, String description, Integer id) {
        this.author = author;
        this.title = title;
        this.description = description;
        this.id = id;
    }

    public Post(){

    }
    public void setId(Integer id) {
        this.id = id;
    }

    public String getAuthor() {
        return author;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public Integer getId() {
        return id;
    }
}
