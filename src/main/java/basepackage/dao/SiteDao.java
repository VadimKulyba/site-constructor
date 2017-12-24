package basepackage.dao;

import basepackage.models.Site;

import java.util.Collection;

public interface SiteDao {
    Collection<Site> getAll();
    void add(Site site);
    void update(Site site);
    void remove(Integer id);
    Collection<Site> getByUserLogin(String login);
    Site getById(int id);
}
