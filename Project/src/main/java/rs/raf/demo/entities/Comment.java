package rs.raf.demo.entities;

public class Comment {
    private String author;
    private String comment;

    private Integer id;

    public Comment(String author, String comment) {
        this.author = author;
        this.comment = comment;

    }
    public Comment(){

    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }



}
