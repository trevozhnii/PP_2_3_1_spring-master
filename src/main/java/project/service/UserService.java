package project.service;

import project.dao.UserDao;
import project.model.User;

import java.util.List;

public interface UserService {
    void saveUser(User user);

    void deleteUserById(Long id);

    User getUserById(Long id);

    List<User> getAllUser();

    void userEditor(User user, Long id);
}
