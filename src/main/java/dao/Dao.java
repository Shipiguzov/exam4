package dao;

import specification.Specification;

import java.util.List;

/**
 * Interface for Data Access Object
 * @param <T> type of obj
 * @param <PK> type of Primary Key
 */
public interface Dao<T, PK> {

    /**
     * Method for add object T-type into DB
     * @param t object that must be added in DB
     */
    void add(T t);

    /**
     * Method for update information about object into DB by object
     * @param t object for update in DB
     */
    void update(T t);

    /**
     * Method for delete object from DB
     * @param t deleted from DB object
     * @return deleted from DB object
     */
    T delete(T t);

    /**
     * Method for delete object from DB by Primary Key
     * @param id Primary Key of deleting form DB object
     * @return deleted from DB object
     */
    T deleteByPK(PK id);

    /**
     * Method for getting object from DB by Primary key
     * @param id primary key of needed object
     * @return object with primary key == id
     */
    T getByPK(PK id);
}
