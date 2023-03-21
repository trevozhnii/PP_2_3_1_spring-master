package project.dao;

import project.exception.UserNotFoundException;
import project.model.User;

import java.util.List;

public interface UserDao {

    void saveUser(User user);

    void deleteUserById(Long id) throws UserNotFoundException;

    User getUserById(Long id) throws UserNotFoundException;

    List<User> getAllUser();

    void userEditor(User user, Long id);
}
