package internetShop.service.impl;

import internetShop.exeptions.AuthorizationException;
import internetShop.lib.Inject;
import internetShop.lib.Service;
import internetShop.dao.UserDao;
import internetShop.model.User;
import internetShop.service.UserService;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.UUID;

@Service
public class UserServiceImpl implements UserService {
    @Inject
    private static UserDao userDao;

    @Override
    public User create(User user) {
user.setToken(getToken());
        return userDao.create(user);
    }
private String getToken () {
        return UUID.randomUUID().toString();
}
    @Override
    public User get(Long id) {
return userDao.get(id).orElseThrow(()-> new NoSuchElementException("Can't find User with id"+id));
    }

    @Override
    public User update(User user) {
        return userDao.update(user);
    }

    @Override
    public void delete(Long userId) {
        userDao.delete(userId);
    }

    @Override
    public boolean delete(User user) {
        return userDao.delete(user);
    }

    @Override
    public List<User> getAll() {
        return userDao.getAll();
    }

    @Override
    public Optional<User> getByToken(String token) {
        return userDao.getByToken(token);
    }

    @Override
    public User login(String login, String password) throws AuthorizationException {
        return userDao.login(login, password);
    }
}
