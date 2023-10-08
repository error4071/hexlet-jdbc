package hexlet.code;

import java.sql.DriverManager;
import java.sql.SQLException;

public class Main {

    public static void main(String[] args) throws SQLException {

        var conn = DriverManager.getConnection("jdbc:h2:mem:hexlet");

        var sql = "CREATE TABLE films"
                + "(id BIGINT PRIMARY KEY AUTO_INCREMENT, title VARCHAR(255), release_year INT, duration INT)";

        var statement = conn.createStatement();
        statement.execute(sql);
        statement.close();

        var sql2 = "INSERT INTO films"
                + "(title, release_year, duration) VALUES ('Godfather', '1972', '175'), ('The Green Mile', '1999', '189')";
        var statement2 = conn.createStatement();
        statement2.executeUpdate(sql2);
        statement2.close();

        var sql3 = "SELECT * FROM films";
        var statement3 = conn.createStatement();
        var resultSet = statement3.executeQuery(sql3);

        while (resultSet.next()) {
            System.out.printf(
                    "%s %s %s\n",
                    resultSet.getString("title"),
                    resultSet.getInt("release_year"),
                    resultSet.getInt("duration")
            );
        }
        statement3.close();

        conn.close();
    }
}
