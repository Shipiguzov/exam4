package specification;

import entity.Group;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

public class GroupListOpenAdditional implements Specification<Group> {

    @Override
    public Predicate getPredicate(Root<Group> root, CriteriaBuilder criteriaBuilder) {
        Predicate conditional = criteriaBuilder.isTrue(root.get("open"));
        return conditional;
    }
}
