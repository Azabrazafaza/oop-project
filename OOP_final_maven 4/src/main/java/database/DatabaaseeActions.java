package database;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public interface DatabaaseeActions {

    // Connection Pool
    class ConnectionPool {
        private static final HikariDataSource dataSource;

        static {
            HikariConfig config = new HikariConfig();
            config.setJdbcUrl("jdbc:postgresql://localhost:5432/university");
            config.setUsername("postgres");
            config.setPassword("!234");
            config.setMaximumPoolSize(10);
            config.setMinimumIdle(2);
            config.setIdleTimeout(30000);
            config.setConnectionTimeout(20000);
            config.setMaxLifetime(1800000);
            dataSource = new HikariDataSource(config);
        }

        public static DataSource getDataSource() {
            return dataSource;
        }
    }

    // Establish a connection using the pool
    static Connection connect() {
        try {
            return ConnectionPool.getDataSource().getConnection();
        } catch (SQLException e) {
            System.err.println("Failed to obtain connection: " + e.getMessage());
            throw new RuntimeException(e);
        }
    }

    // Save an entity to the table
    static void save(String table, String columnNames, Object... values) {
        String placeholders = String.join(", ", "?".repeat(values.length).split(""));
        String query = "INSERT INTO " + table + " (" + columnNames + ") VALUES (" + placeholders + ")";

        try (Connection connection = connect();
             PreparedStatement statement = connection.prepareStatement(query)) {

            for (int i = 0; i < values.length; i++) {
                statement.setObject(i + 1, values[i]);
            }
            statement.executeUpdate();
            System.out.println("Entity saved to table: " + table);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    static void save(String table, Object values) {
//        String placeholders = String.join(", ", "?".repeat(values.length).split(""));
//        String query = "INSERT INTO " + table + " (" + columnNames + ") VALUES (" + placeholders + ")";
//
//        try (Connection connection = connect();
//             PreparedStatement statement = connection.prepareStatement(query)) {
//
//            for (int i = 0; i < values.length; i++) {
//                statement.setObject(i + 1, values[i]);
//            }
//            statement.executeUpdate();
//            System.out.println("Entity saved to table: " + table);
//
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
    }

    // Find an entity by its ID
    static ResultSet findById(String table, int id) {
        String query = "SELECT * FROM ? WHERE id = ?";

        try (Connection connection = connect();
             PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setString(1,table);
            statement.setInt(2, id);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
//                System.out.println("Entity found in table: " + table + ", ID: " + id);
                return resultSet;
            } else {
//                System.out.println("No entity found in table: " + table + ", ID: " + id);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }


    // Update an existing entity
    static void update(String table, String columnName, Object value, int id) {
        String query = "UPDATE " + table + " SET " + columnName + " = ? WHERE id = ?";
        try (Connection connection = connect();
             PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setObject(1, value);
            statement.setInt(2, id);
            statement.executeUpdate();
            System.out.println("Entity updated in table: " + table + ", ID: " + id);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    static void update(String table,Object value) {
//        String query = "UPDATE " + table + " SET " + columnName + " = ? WHERE id = ?";
//        try (Connection connection = connect();
//             PreparedStatement statement = connection.prepareStatement(query)) {
//
//            statement.setObject(1, value);
//            statement.setInt(2, id);
//            statement.executeUpdate();
//            System.out.println("Entity updated in table: " + table + ", ID: " + id);
//
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
    }

    // Delete an entity by its ID
    static void delete(String table, int id) {
        String query = "DELETE FROM " + table + " WHERE id = ?";
        try (Connection connection = connect();
             PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setInt(1, id);
            statement.executeUpdate();
            System.out.println("Entity deleted from table: " + table + ", ID: " + id);

        } catch (SQLException e) {
            e.printStackTrace();

        }
    }

    // Retrieve all entities from the table
    static List<ResultSet> findAll(String table) {
        String query = "SELECT * FROM ?" ;
        List<ResultSet> results = new ArrayList<>();

        try (Connection connection = connect();
             PreparedStatement statement = connection.prepareStatement(query)) {


            statement.setString(1,table);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                results.add(resultSet);
            }
//            System.out.println("All entities retrieved from table: " + table);

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return results;
    }
}
