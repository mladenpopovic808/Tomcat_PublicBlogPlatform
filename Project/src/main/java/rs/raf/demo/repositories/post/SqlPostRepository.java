package rs.raf.demo.repositories.post;

import rs.raf.demo.entities.Post;
import rs.raf.demo.repositories.AbstractRepository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SqlPostRepository extends AbstractRepository implements PostRepositoryInterface{
    @Override
    public Post addPost(Post post) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            connection = this.newConnection();


            String[] generatedColumns = {"id"};//Navodimo koje auto-generisane kolone zelimo da nam query vrati

            preparedStatement = connection.prepareStatement("INSERT INTO posts (author,title,description) VALUES(?, ?, ?)", generatedColumns);
            preparedStatement.setString(1, post.getAuthor());
            preparedStatement.setString(2, post.getTitle());
            preparedStatement.setString(3, post.getDescription());
            preparedStatement.executeUpdate();//ako imamo update, insert, delete

            resultSet = preparedStatement.getGeneratedKeys();

            //naveli smo samo id da nam vrati,tako da to uzimamo ovde
            //kada pravimo subjecta iz jave,ne zelimo mi da mu setujemo id,vec zelimo da setujemo prvi sledeci
            //id iz baze,tako da prvo ga dodamo,getujemo taj id i setujemo ga u subjectu ovde,da mozemo da radimo sa njim
            if (resultSet.next()) {
                post.setId(resultSet.getInt(1));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            this.closeStatement(preparedStatement);
            this.closeResultSet(resultSet);
            this.closeConnection(connection);
        }

        return post;
    }

    @Override
    public List<Post> getAllPosts() {
        List<Post> posts=new ArrayList<>();

        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;

        try {
            connection = this.newConnection();

            statement = connection.createStatement();
            resultSet = statement.executeQuery("select * from posts");
            while (resultSet.next()) {
                posts.add(new Post(resultSet.getString("author"),
                                   resultSet.getString("title"),
                                   resultSet.getString("description"),
                                   resultSet.getInt("id")));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            this.closeStatement(statement);
            this.closeResultSet(resultSet);
            this.closeConnection(connection);
        }
        return posts;

    }

    @Override
    public Post getPost(Integer id) {
        Post post = null;

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            connection = this.newConnection();

            preparedStatement = connection.prepareStatement("SELECT * FROM posts where id = ?");
            preparedStatement.setInt(1, id);
            resultSet = preparedStatement.executeQuery();

            if(resultSet.next()) {
                String author = resultSet.getString("author");
                String title = resultSet.getString("title");
                String description = resultSet.getString("description");
                post=new Post(author,title,description,id);
            }
            resultSet.close();
            preparedStatement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            this.closeStatement(preparedStatement);
            this.closeResultSet(resultSet);
            this.closeConnection(connection);
        }
        return post;

    }
}
