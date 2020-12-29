package dao;

import entity.Alpinist;
import specification.Specification;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.List;

public class AlpinistDao implements Dao<Alpinist, Integer> {

    private EntityManager manager;

    public AlpinistDao(EntityManager manager) {
        this.manager = manager;
    }

    @Override
    public void add(Alpinist alpinist) {
        manager.persist(alpinist);
    }

    @Override
    public void update(Alpinist alpinist) {
        manager.merge(alpinist);
    }

    @Override
    public Alpinist delete(Alpinist alpinist) {
        manager.remove(alpinist);
        return alpinist;
    }

    @Override
    public Alpinist deleteByPK(Integer id) {
        Alpinist alpinist = getByPK(id);
        if (alpinist != null) {
            delete(alpinist);
            return alpinist;
        }
        return null;
    }

    @Override
    public Alpinist getByPK(Integer id) {
        return manager.find(Alpinist.class, id);
    }

    /**
     * Method for get list of object by spectification
     * @param specification conditional for search objects in Db
     * @return list of object field which corresponds to the given condition
     */
    public List<Alpinist> getBySpecification(Specification<Alpinist> specification) {
        CriteriaBuilder builder = manager.getCriteriaBuilder();
        CriteriaQuery<Alpinist> criteriaQuery = builder.createQuery(Alpinist.class);
        Root<Alpinist> root = criteriaQuery.from(Alpinist.class);
        Predicate predicate = specification.getPredicate(root, builder);
        criteriaQuery.select(root).where(predicate);
        return manager.createQuery(criteriaQuery).getResultList();
    }
}
