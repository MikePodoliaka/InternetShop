package internetShop.dao;

import internetShop.model.User;

import java.util.Optional;

public interface UserDao {
    User create(User user);

    Optional<User> get(Long id);

    User update(User user);

    void delete(Long userId);

    boolean delete(User user);
}
