package singleton;
import database.DatabaaseeActions;
import users.User;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class UserRegistry {
    // Приватное статическое поле для хранения единственного экземпляра
    private static UserRegistry instance;
    private List< Map < String, String > > users;
// keeys id,username,password,name


    //  конструктор для предотвращения создания экземпляров вне класса
    private UserRegistry() {
        users = new ArrayList<>();
        try {
            loadUsersFromDatabase();
        } catch (SQLException e) {
            System.err.println("Failed to load users from database: " + e.getMessage());
        }
    }
    // Публичный статический метод для получения единственного экземпляра
    public static synchronized UserRegistry getInstance() throws SQLException {
        if (instance == null) {
            instance = new UserRegistry();
        }
        return instance;
    }
//    public int id ;
//    public String username;
//    public String password;
//    public String name;
    // Метод для загрузки пользователей из bd
    private void loadUsersFromDatabase() throws SQLException {
        List<ResultSet> res = DatabaaseeActions.findAll("users");
        for (ResultSet i :res) {

            Map < String, String > UserData  = new HashMap<>();

            UserData.put("id"       ,i.getString("id"));
            UserData.put("username" ,i.getString("username"));
            UserData.put("password" ,i.getString("password"));
            UserData.put("name"     ,i.getString("name"));

            users.add(UserData);
        }

    }

    public List<Map < String, String >> getUsers() {
        return users;
    }

    // Метод для добавления нового пользователя
    public void addUser(User user) {
    }
}

