package basepackage.dao.Impl;

import basepackage.dao.UserDao;
import basepackage.models.User;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Collection;

@Repository
public class UserDaoImpl implements UserDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public User getUser(String login) {
        User user = entityManager.find(User.class, login);
        if (user == null) return null;
        return user;
    }

    @Override
    public void add(User user) {
        entityManager.persist(user);
    }

    @Override
    public void remove(String login) {
        User user = entityManager.find(User.class, login);
        if (user != null){
            /*Collection<Site> sites = user.getSites();
            for (Site site: sites){

            }*/
            entityManager.remove(user);
        }
    }

    @Override
    public void update(User user) {
        User u = entityManager.find(User.class, user.getLogin());
        entityManager.remove(u);
        entityManager.persist(user);
    }

    @Override
    public Collection<User> getAll() {
        return entityManager.createQuery("from User", User.class).getResultList();
    }
}
