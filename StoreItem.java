package StoreItem;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class StoreItem {

    private int id;
    private String name;
    private float price;

    public static StoreItem find(int id) throws SQLException {
        StoreItem item = null;

        try (
                Connection connection = dataSource.getConnection();
                PreparedStatement statement = connection.prepareStatement("SELECT id, name, price FROM items WHERE id=?");
            ) {
            statement.setInt(1, id);

            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    item = new StoreItem();
                    item.setId(resultSet.getInt("id"));
                    item.setName(resultSet.getString("name"));
                    item.setPrice(resultSet.getFloat("price"));
                }
            }
        }

        return item;
    }

    public void save() throws SQLException {
        try (
                Connection connection = dataSource.getConnection();
                PreparedStatement statement = connection.prepareStatement("REPLACE INTO items VALUES (? ? ?)");
            ) {
            statement.setInt(1, this.id);
            statement.setString(2, this.name);
            statement.setFloat(3, this.price);

            statement.executeQuery()
        }
    }
}

