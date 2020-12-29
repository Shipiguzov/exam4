package dao;

import entity.Group;
import entity.Group_;
import entity.Mountain;
import specification.Specification;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.List;

public class GroupDao implements Dao<Group,Integer> {

    EntityManager manager;

    public GroupDao(EntityManager manager) {
        this.manager = manager;
    }

    @Override
    public void add(Group group) {
        this.manager.persist(group);
    }

    @Override
    public void update(Group group) {
        this.manager.merge(group);
    }

    @Override
    public Group delete(Group group) {
        manager.remove(group);
        return group;
    }

    @Override
    public Group deleteByPK(Integer id) {
        Group group = getByPK(id);
        if (group != null) delete(group);
        return group;
    }

    @Override
    public Group getByPK(Integer id) {
        return manager.find(Group.class, id);
    }

    public List<Group> groupListOpenAdditional(Specification<Group> specification) {
        CriteriaBuilder criteriaBuilder = manager.getCriteriaBuilder();
        CriteriaQuery<Group> criteriaQuery = criteriaBuilder.createQuery(Group.class);
        Root<Group> root = criteriaQuery.from(Group.class);
        Predicate predicate = specification.getPredicate(root, criteriaBuilder);
        criteriaQuery.select(root).where(predicate);
        return manager.createQuery(criteriaQuery).getResultList();
    }

    public List<Group> getGroupsByMountain(String mountainName) {
        MountainDao mountainDao = new MountainDao(manager);
        Mountain mountain = mountainDao.getMountainByName(mountainName);
        CriteriaBuilder criteriaBuilder = manager.getCriteriaBuilder();
        CriteriaQuery<Group> criteriaQuery = criteriaBuilder.createQuery(Group.class);
        Root<Group> root = criteriaQuery.from(Group.class);
        Predicate predicate = criteriaBuilder.equal(root.get(Group_.MOUNTAIN), mountain);
        criteriaQuery.select(root).where(predicate);
        return manager.createQuery(criteriaQuery).getResultList();
    }
}
