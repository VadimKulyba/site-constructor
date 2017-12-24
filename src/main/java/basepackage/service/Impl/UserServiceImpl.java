package basepackage.service.Impl;

import basepackage.dao.UserDao;
import basepackage.models.User;
import basepackage.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Collection;

@Transactional
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Override
    public User getUser(String login) {
        return userDao.getUser(login);
    }

    @Override
    public void add(User user) {
        userDao.add(user);
    }

    @Override
    public void remove(String login) {
        userDao.remove(login);
    }

    @Override
    public void update(User user) {
        userDao.update(user);
    }

    @Override
    public Collection<User> getAll() {
        return userDao.getAll();
    }
}
