package internetShop.dao.impl;

import internetShop.lib.Dao;
import internetShop.dao.Storage;
import internetShop.dao.UserDao;
import internetShop.model.User;

import java.util.NoSuchElementException;
import java.util.Optional;

@Dao
public class UserDaoImpl implements UserDao {

    @Override
    public User create(User user) {
       Storage.users.add(user);
        return user;
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
       Optional<User> updateUser=get(user.getUserId());
       updateUser.get().setName(user.getName());
       updateUser.get().setLogin(user.getLogin());
       updateUser.get().setPassword(user.getPassword());
        return user;
    }

    @Override
    public void delete (Long userId) {
        Storage.users.removeIf(u->u.getUserId().equals(userId));

        //Optional opUser = Optional.ofNullable(Storage.users.stream()
         //       .filter(i -> i.getUserId().equals(userId)).findFirst());
       // return Storage.users.remove(opUser);
    }

    @Override
    public boolean delete(User user) {
        return Storage.users.remove(user);
    }
}
