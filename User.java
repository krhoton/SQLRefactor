package User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class User {

    private String username;
    private String password;
    private String email;

    public static User find(String username, String password) throws SQLException {
        User user = null;

        try (
                Connection connection = dataSource.getConnection();
                PreparedStatement statement = connection.prepareStatement("SELECT id, username, email FROM users WHERE username=? AND password=?");
            ) {
            statement.setString(1, username);
            statement.setString(2, password);

            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    user = new User();
                    user.setId(resultSet.getLong("id"));
                    user.setUsername(resultSet.getString("username"));
                    user.setEmail(resultSet.getString("email"));
                }
            }
        }       

        return user;
    }

    public void save() throws SQLException {
        try (
                Connection connection = dataSource.getConnection();
                PreparedStatement statement = connection.prepareStatement("REPLACE INTO users VALUES (? ? ?)");
            ) {
            statement.setString(1, this.username);
            statement.setString(2, this.password);
            statement.setString(3, this.email);

            statement.executeQuery()
        }
    }
}
