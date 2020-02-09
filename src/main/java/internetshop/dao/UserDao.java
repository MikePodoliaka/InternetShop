package internetshop.dao;

import java.util.Optional;

import internetshop.exceptions.DataProcessingException;
import internetshop.model.User;

public interface UserDao extends GenericDao<User, Long> {
    Optional<User> findByLogin(String login) throws DataProcessingException;
}