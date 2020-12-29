package specification;

import entity.Alpinist;
import entity.Alpinist_;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

public class AlpinistAgeBetween implements Specification<Alpinist> {

    private int from;
    private int before;

    public AlpinistAgeBetween(int from, int before) {
        this.from = from;
        this.before = before;
    }

    public int getFrom() {
        return from;
    }

    public void setFrom(int from) {
        this.from = from;
    }

    public int getBefore() {
        return before;
    }

    public void setBefore(int before) {
        this.before = before;
    }

    // "SELECT * FROM alpinists WHERE age >" + from " AND age <" + before";
    @Override
    public Predicate getPredicate(Root<Alpinist> root, CriteriaBuilder criteriaBuilder) {
        Predicate from = criteriaBuilder.gt(root.get(Alpinist_.AGE), this.from);
        Predicate before = criteriaBuilder.lessThan(root.get(Alpinist_.AGE),this.before);
        return criteriaBuilder.and(from, before);
    }
}
