package entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Class describe an alpinist
 */
@Entity
@Table(name = "alpinist")
public class Alpinist extends BaseIdentify {

    @Column(nullable = false, length = 50)
    private String name;

    @Column(length = 100)
    private String address;

    @Column(nullable = false)
    private int age;

    @ManyToMany(mappedBy = "alpinistList", fetch = FetchType.LAZY)
    //@JoinColumn(nullable = false)
    private List<Group> groups = new ArrayList<>();

    /**
     * Constructor for Alpinist class
     *
     * @param name name of alpinist. Must be more 3 characters
     * @param age  age of alpinist. Must be 18 or more
     */
    public Alpinist(String name, int age) {
        this.setName(name);
        this.setAge(age);
    }

    /**
     * Constructor for Alpinist class
     *
     * @param name    name of alpinist. Must be more 3 characters
     * @param age     age of alpinist. Must be 18 or more
     * @param address address of alpinist. Must be more 5 characters
     */
    public Alpinist(String name, int age, String address) {
        this.setName(name);
        this.setAge(age);
        this.setAddress(address);
    }

    /**
     * Method for receive of alpinist name
     *
     * @return name of alpinist
     */
    public String getName() {
        return name;
    }

    /**
     * Method for set a name of alpinist
     *
     * @param name name of alpinist. Must be more 3 characters
     */
    public void setName(String name) {
        if (Objects.isNull(name) || name.length() < 4)
            throw new IllegalArgumentException("Name of alpinist must be 3 or more characters");
        this.name = name;
    }

    /**
     * Method for receive of alpinist address
     *
     * @return address of alpinist
     */
    public String getAddress() {
        return address;
    }

    /**
     * Method for set alpinist address
     *
     * @param address adress of alpinist. Must be more 5 characters
     */
    public void setAddress(String address) {
        if (Objects.isNull(address) || address.length() < 6)
            throw new IllegalArgumentException("Address of alpinist must be 5 or more characters");
        this.address = address;
    }

    /**
     * Methoed for receive alpinist age
     *
     * @return age of alpinist
     */
    public int getAge() {
        return age;
    }

    /**
     * Method for set alpinist age
     *
     * @param age age of apinist. Must be 18 or more
     */
    public void setAge(int age) {
        if (age < 18) throw new IllegalArgumentException("Age of alpinist must be 18 or more");
        this.age = age;
    }

    /**
     * Method of alpinist check for interfering group
     *
     * @param group alpinist group
     */
    public boolean checkAlpinistGroups(Group group) {
        if (groups == null) return true;
        for (Group group1 : groups) {
            if (group.getDate().isAfter(group1.getDate()) &&
                    group.getDate().plusDays(group.getDuration()).
                            isBefore(group1.getDate().plusDays(group1.getDuration())))
                return false;
        }
        return true;
    }

    /**
     * Method for receive alpinist's groups
     *
     * @return list of alpinist's groups
     */
    public List<Group> getGroups() {
        return groups;
    }

    public void addAlpinistToGroup(Group group) {
        if (Objects.nonNull(group)) this.groups.add(group);
        else throw new IllegalArgumentException("You try to add alpinist in null-group");
    }
}
