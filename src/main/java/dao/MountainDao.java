package dao;

import entity.Group;
import entity.Mountain;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;
import java.util.Locale;

public class MountainDao implements Dao<Mountain, Integer> {

    private EntityManager manager;

    public MountainDao(EntityManager manager) {
        this.manager = manager;
    }

    @Override
    public void add(Mountain mountain) {
        manager.persist(mountain);
    }

    @Override
    public void update(Mountain mountain) {
        manager.merge(mountain);
    }

    @Override
    public Mountain delete(Mountain mountain) {
        manager.remove(mountain);
        return mountain;
    }

    @Override
    public Mountain deleteByPK(Integer id) {
        Mountain mountain = getByPK(id);
        if (mountain != null) delete(mountain);
        return mountain;
    }

    @Override
    public Mountain getByPK(Integer id) {
        return manager.find(Mountain.class, id);
    }

    public Mountain getMountainByName(String mountainName) {
        TypedQuery<Mountain> query = manager.createNamedQuery("Mountain.getMountainByName", Mountain.class);
        query.setParameter("mountainName", mountainName);
        return query.getSingleResult();
    }

    public List<Mountain> getMountainByCountry(String country) {
        TypedQuery<Mountain> query = manager.createNamedQuery("Mountain.getMountainByCountry", Mountain.class);
        query.setParameter("country", country.toLowerCase());
        List<Mountain> mountains = query.getResultList();
        return mountains;
    }
}
