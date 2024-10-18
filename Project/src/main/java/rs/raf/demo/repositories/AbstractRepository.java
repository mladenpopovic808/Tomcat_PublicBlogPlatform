package rs.raf.demo.repositories;

import java.sql.*;
import java.util.Optional;

public class AbstractRepository {

    //Ovu klasu nasledjuju svi repozotiriji,ovde je izvdojena konekcija na bazu

    public AbstractRepository() {
        try {
            //u runtime-u se ucitava klasa koja implementira interfejs Driver,vezujemo implementaciju
            //za specifikaciju
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    protected Connection newConnection() throws SQLException {
        return DriverManager.getConnection(
                "jdbc:mysql://" + this.getHost() + ":" + this.getPort() + "/" + this.getDatabaseName(), this.getUsername(), this.getPassword()
        );
    }

    protected String getHost() {
        return "localhost";
    }

    protected int getPort() {
        return 3306;
    }

    protected String getDatabaseName() {
        return "domaci6";
    }

    //ovo su username i pass od stefana,treba da promenis itd.
    protected String getUsername() {
        return "root";
    }

    protected String getPassword() {
        return "sifra";
    }

    protected void closeStatement(Statement statement) {
        try {
            Optional.of(statement).get().close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    protected void closeResultSet(ResultSet resultSet) {
        try {
            Optional.of(resultSet).get().close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    protected void closeConnection(Connection connection) {
        try {
            Optional.of(connection).get().close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

}
