package internetShop.dao.impl;

import internetShop.lib.Dao;
import internetShop.dao.Storage;
import internetShop.dao.UserDao;
import internetShop.model.User;
import internetShop.web.IdGenerator;

import java.util.NoSuchElementException;
import java.util.Optional;

@Dao
public class UserDaoImpl implements UserDao {

    @Override
    public User create(User user) {
        User newUser = user;
        newUser.setUserId(IdGenerator.getNewUserId());
        Storage.users.add(newUser);
        return newUser;
    }

    @Override
    public Optional<User> get(Long userId) {
        return Optional.ofNullable(Storage.users.stream()
                .filter(u -> u.getUserId().equals(userId))
                .findFirst()
                .orElseThrow(() -> new NoSuchElementException("Can't find user wit id  " + userId)));

    }

    @Override
    public User update(User user) {
        int userPos = 0;
        for (User u : Storage.users) {
            if (u.getUserId().equals(user.getUserId())) {
                break;
            }
            userPos++;
        }
        Storage.users.set(userPos, user);
        return user;
    }

    @Override
    public boolean delete(Long userId) {
        Optional opUser = Optional.ofNullable(Storage.users.stream()
                .filter(i -> i.getUserId().equals(userId)).findFirst());
        return Storage.users.remove(opUser);
    }

    @Override
    public boolean delete(User user) {
        return Storage.users.remove(user);
    }
}
