package internetShop.dao.impl;

import internetShop.dao.UserDao;
import internetShop.exeptions.AuthorizationException;
import internetShop.lib.Dao;
import internetShop.lib.Inject;
import internetShop.model.User;
import internetShop.service.ItemService;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Dao
public class UserDaoImpl extends AbstractDao<User> implements UserDao {
    @Inject

    public UserDaoImpl(Connection connection) {
        super(connection);
    }

    @Override
    public User create(User user) {
        String query = String.format("insert into users (name,login,password) values('%s','%s','%s')",
                user.getName(), user.getLogin(), user.getPassword());
        try (Statement statement = connection.createStatement()) {
            statement.executeUpdate(query);
        } catch (SQLException e) {
            System.out.println("Can't create user");
        }
        return user;
    }

    @Override
    public Optional<User> get(Long id) {
        String query = "SELECT * FROM users WHERE user_id=?;";

        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setLong(1, id);
            ResultSet resultSet=statement.executeQuery();
            while (resultSet.next()) {
                User user = new User();
                resultSet.getString("name");
                resultSet.getString("login");
                resultSet.getString("password");
                user.setUserId(resultSet.getLong("user_id"));

                return Optional.of(user);
            }
        } catch (SQLException e) {
            System.out.println("problem in getUserDao");
        }
        return Optional.empty(); //ОБРАТИТЬ ВНИМАНИЕ ЧТО ВОЗВРАЩАЕТ НЕ ЮЗЕР!!!
    }

    @Override
    public User update(User user) {

        String query = String.format("update users SATE name='%s', login='%s', password='%s' WHERE user_id=%d",
                user.getName(), user.getLogin(), user.getPassword());
        try (Statement statement = connection.createStatement()) {
            statement.executeUpdate(query);
        } catch (SQLException e) {
            System.out.println("Can't update user");
        }
        return user;
    }

    @Override
    public void delete(Long userId) {

        String query = String.format("DELETE FROM users WHERE user_id=%d", userId);
        try (Statement statement = connection.createStatement()) {
            statement.executeUpdate(query);

        } catch (SQLException e) {
            System.out.println("Can't delete user");
        }
    }

    @Override
    public boolean delete(User user) {
        return false;
    }

    @Override
    public List<User> getAll() {
        List<User> listUsers = new ArrayList<>();
        String query = String.format("SELECT*FROM users");
        try (Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                User user = new User(
                        resultSet.getString("name"),
                        resultSet.getString("login"),
                        resultSet.getString("password"));
                user.setUserId(resultSet.getLong("user_id"));
                listUsers.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return listUsers;
    }

    @Override
    public User login(String login, String password) throws AuthorizationException {
        String query = "SELECT * FROM users WHERE login=? and password=?;";

        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, login);
            statement.setString(2, password);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                long userId = resultSet.getLong("user_id");
                String name = resultSet.getString("name");

                User user = new User(userId);
                user.setName(name);
                user.setPassword(password);
                user.setLogin(login);
                return user;

            }
        } catch (SQLException e) {
            System.out.println("Can't login user");
        }
        throw new AuthorizationException("Can't get user by login");
    }

    @Override
    public Optional<User> getByToken(String token) {
        return Optional.empty();
    }

}
