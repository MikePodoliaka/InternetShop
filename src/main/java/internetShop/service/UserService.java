package internetShop.service;

import internetShop.model.User;

import java.util.List;
import java.util.Optional;

public interface UserService {
    User create(User user);

    Optional<User> get(Long userId);

    User update(User user);

    boolean delete(Long userId);

    boolean delete(User user);

    public List<User> getAll();
}
